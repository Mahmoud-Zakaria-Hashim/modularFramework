import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OpenBrowserSoft {


    WebDriver driver = null;
    @BeforeTest
    public void openBrowser() throws InterruptedException {
        // Set up ChromeDriver path.
        String chromePath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",chromePath);

        // Create WebDriver object for Chrome browser.
         driver = new ChromeDriver();

        // Navigate to the website, maximize the screen and wait for 1 second.
        driver.manage().window().maximize();
        Thread.sleep(1000);

    }
    @Test
    public void validData() throws InterruptedException {
        driver.navigate().to("https://the-internet.herokuapp.com/login");

        // Enter username and password. (Positive)
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        Thread.sleep(1000);
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        String expectedResult = "You logged into a secure area!";
        String actualResault = driver.findElement(By.id("flash")).getText();

        SoftAssert soft = new SoftAssert();

        soft.assertTrue(actualResault.contains(expectedResult));
        Thread.sleep(1000);

        soft.assertTrue(driver.findElement(By.cssSelector("a[href=\"/logout\"]")).isDisplayed());
        soft.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/secure");

        soft.assertAll();
    }
    @Test
    public void inValidData() throws InterruptedException {
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        // Enter invalid username and invalid password.  (Negative)
        driver.findElement(By.id("username")).sendKeys("tomsith");
        Thread.sleep(1000);
        driver.findElement(By.name("password")).sendKeys("Password!");
        driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        String expectedResult = "Your username is invalid!";
        String actualResault = driver.findElement(By.id("flash")).getText();

        Assert.assertTrue(actualResault.contains(expectedResult));

    }

    @AfterTest
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(2000);
        // Close the browser.
        driver.close();
    }

}




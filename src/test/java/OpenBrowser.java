import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OpenBrowser {

    WebDriver driver;
    LoginPage login;

//    @BeforeTest
//    public void setup() {
//        String chromePath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
//        System.setProperty("webdriver.chrome.driver", chromePath);
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        login = new LoginPage(driver);
//    }


    @BeforeTest
    public void setup() {
        String chromePath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        login = new LoginPage(driver);
    }

    @Test
    public void validData() {
        driver.get("https://the-internet.herokuapp.com/login");
        login.login("tomsmith", "SuperSecretPassword!");

        String expectedMessage = "You logged into a secure area!";
        String actualMessage = driver.findElement(login.getFlashLocator()).getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage));
        Assert.assertTrue(driver.findElement(login.getLogoutLocator()).isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/secure");
    }

    @Test
    public void invalidData() {
        driver.get("https://the-internet.herokuapp.com/login");
        login.login("tomsmitha", "SuperSecretPassword");

        String expectedMessage = "Your username is invalid!";
        String actualMessage = driver.findElement(login.getFlashLocator()).getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}
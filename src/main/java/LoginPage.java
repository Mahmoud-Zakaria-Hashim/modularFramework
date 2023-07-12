import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    @FindBy(id="username")
    private WebElement usernameField;

    @FindBy(id="password")
    private WebElement passwordField;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public By getFlashLocator() {
        return By.id("flash");
    }

    public By getLogoutLocator() {
        return By.cssSelector("a[href=\"/logout\"]");
    }

    public void login(String username, String password) {
        usernameField.clear();
        usernameField.sendKeys(username);

        passwordField.clear();
        passwordField.sendKeys(password);
        passwordField.sendKeys(Keys.ENTER);
    }
}
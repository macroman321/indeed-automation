package indeed.pages;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sun.rmi.runtime.Log;

@Data
@Log4j2
public class LoginPage {

    private WebDriver driver;

    @FindBy(id = "login-email-input")
    private WebElement emailField;
    @FindBy(id = "login-password-input")
    private WebElement passField;
    @FindBy(id = "login-submit-button")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver){
        this.driver = driver;

        this.driver.get("https://secure.indeed.com/account/login");

        PageFactory.initElements(driver, this);

    }

    public void login(String email, String password){
        this.emailField.sendKeys(email);
        this.passField.sendKeys(password);
        this.loginBtn.click();
    }

}

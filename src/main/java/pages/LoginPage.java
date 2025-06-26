package pages;

import common.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObject {

    @FindBy(css = "[data-test=\"login-credentials\"]")
    public WebElement loginCredentialsText;

    @FindBy(id = "user-name")
    public WebElement userNameInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(css = "[data-test=\"login-button\"]")
    public WebElement loginButton;

    @FindBy(css = ".error-message-container.error")
    public WebElement loginErrorMessage;

    @FindBy(css = "[data-test=\"login-credentials\"] br:first-of-type")
    public WebElement userName;

    @FindBy(xpath = "//*[@data-test='login-password']/text()[1]")
    public WebElement password;


}

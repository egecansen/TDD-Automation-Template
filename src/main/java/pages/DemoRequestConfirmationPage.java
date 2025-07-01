package pages;

import common.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoRequestConfirmationPage extends PageObject {

    @FindBy(css = "[data-id='601c213'] h1")
    public WebElement thankYouForSubmittingText;
}

package pages;

import common.BaseObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoRequestConfirmationPage extends BaseObject {

    @FindBy(css = "[data-id='601c213'] h1")
    public WebElement thankYouForSubmittingText;
}

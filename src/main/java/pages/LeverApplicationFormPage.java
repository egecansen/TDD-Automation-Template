package pages;

import common.BaseObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LeverApplicationFormPage extends BaseObject {

    @FindBy(css = "[class='posting-headline'] h2")
    public WebElement jobTitle;

    @FindBy(css = "[class='postings-btn-wrapper'] a")
    public WebElement applyForThisJobButton;

}

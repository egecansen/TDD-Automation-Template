package pages;

import common.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LeverApplicationFormPage extends PageObject {

    @FindBy(css = ".posting-headline h2")
    public WebElement jobTitle;

    @FindBy(css = "[class='postings-btn-wrapper'] a")
    public WebElement applyForThisJobButton;

}

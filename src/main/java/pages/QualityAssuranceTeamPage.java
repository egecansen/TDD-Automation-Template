package pages;

import common.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class QualityAssuranceTeamPage extends PageObject {

    @FindBy(css = "[class='button-group d-flex flex-row'] a")
    public WebElement seeAllQAJobsButton;

}

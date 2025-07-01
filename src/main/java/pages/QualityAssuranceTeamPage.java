package pages;

import common.BaseObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class QualityAssuranceTeamPage extends BaseObject {
    @FindBy(css = "[class='button-group d-flex flex-row'] a")
    public WebElement seeAllQAJobsButton;

}

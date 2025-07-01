package pages;

import common.BaseObject;
import junit.framework.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Printer;

import java.util.List;

public class QualityAssuranceJobsPage extends BaseObject {

    static  Printer log = new Printer(QualityAssuranceJobsPage.class);

    @FindBy(css = "[data-select2-id='1']")
    public WebElement filterByLocationContainer;

    @FindBy(css = "[data-select2-id='4']")
    public WebElement filterByDepartmentContainer;

    @FindBy(css = "[id='jobs-list']")
    public WebElement jobsListBlock;

    @FindBy(css = "[id='deneme']")
    public WebElement totalNumberOfJobs;

    @FindBy(css = "[data-team='qualityassurance']")
    public List<WebElement> listedJobs;

    @FindBy(css = "[data-team='qualityassurance'] [class*='position-title']")
    public List<WebElement> listedJobTitles;

    @FindBy(css = "[class='select2-results'] li")
    public List<WebElement> filterItems;

    @FindBy(css = "[class*='position-list-item-wrapper'] [class*='position-department ']")
    public List<WebElement> positionDepartmentTexts;

    @FindBy(css = "[class*='position-list-item-wrapper'] [class*='position-location ']")
    public List<WebElement> positionLocationTexts;

    @FindBy(css = "[class*='position-list-item-wrapper'] a")
    public WebElement viewRoleButton;

    public static void verifyTheListedJobConfigurations(String expectedTeam, String expectedLocation) {
        List<WebElement> jobs = reflections.getElementsFromPage("listedJobs", "QualityAssuranceJobsPage");
        int count = 1;
        for (WebElement job : jobs) {
            String location = job.getDomAttribute("data-location");
            String team = job.getDomAttribute("data-team");

            Assert.assertEquals(location, expectedLocation);
            log.success("Location info verified as '" + expectedLocation + "' for Job " + count);

            Assert.assertEquals(team, expectedTeam);
            log.success("Team info verified as '" + expectedTeam + "' for Job " + count);
            count++;
        }
    }
}



import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.*;
import pickleib.enums.ElementState;
import steps.BaseTest;


public class ApplicationTests extends BaseTest {

    LandingPage landingPage = new LandingPage();
    CareersPage careersPage = new CareersPage();
    QualityAssuranceJobsPage qualityAssuranceJobsPage = new QualityAssuranceJobsPage();
    QualityAssuranceTeamPage qualityAssuranceTeamPage = new QualityAssuranceTeamPage();
    LeverApplicationFormPage leverApplicationFormPage = new LeverApplicationFormPage();

    @Test @Tag("Case1") @DisplayName("Verify job data for Quaility Assurance open position in Istanbul")
    public void verifyQualityAssuranceTextInJobs() {
        landingPage.navigate("https://useinsider.com/");
        landingPage.log.important("Verifying the URL is https://useinsider.com/");
        landingPage.verifyCurrentUrl("https://useinsider.com/");

        landingPage.log.important("Accepting cookies");
        WebElement acceptCookieButton = acquisition.acquireElementFromPage("acceptCookieButton", "LandingPage");
        landingPage.clickElement(acceptCookieButton);

        landingPage.log.important("Verifying the slider banner element called homeLogoReel is displayed");
        WebElement homeLogoReel = acquisition.acquireElementFromPage("homeLogoReel", "LandingPage");
        landingPage.verifyElementState(homeLogoReel, ElementState.displayed);

        landingPage.log.important("Clicking on Career item under the Company menu from the main navigation");
        landingPage.hoverOverNavigationItemByText("Company");
        WebElement career = acquisition.acquireListedElementFromPage("Career", "companyMenuSubItems", "LandingPage");
        landingPage.clickElement(career);

        landingPage.log.important("Verifying the Locations, Teams and Life at Insider blocks on the Careers page");
        WebElement locationsBlock = acquisition.acquireElementFromPage("locationsBlock", "CareersPage");
        careersPage.verifyElementState(locationsBlock, ElementState.displayed);
        WebElement teamsBlock = acquisition.acquireElementFromPage("teamsBlock", "CareersPage");
        careersPage.verifyElementState(teamsBlock, ElementState.displayed);
        WebElement lifeAtInsiderBlock = acquisition.acquireElementFromPage("lifeAtInsiderBlock", "CareersPage");
        careersPage.verifyElementState(lifeAtInsiderBlock, ElementState.displayed);

        landingPage.log.important("Clicking on See All Teams button");
        WebElement seeAllTeamsButton = acquisition.acquireElementFromPage("seeAllTeamsButton", "CareersPage");
        careersPage.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'instant'});", seeAllTeamsButton);
        careersPage.clickElement(seeAllTeamsButton, true);

        landingPage.log.important("Scrolling through teams and clicking on Quality Assurance");
        WebElement QATeam = acquisition.acquireListedElementFromPage("Quality Assurance", "teams", "CareersPage");
        careersPage.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'instant'});", QATeam);
        careersPage.clickElement(QATeam, true);

        landingPage.log.important("Verifying See All QA Jobs button on the Quality Assurance Team page is displayed");
        WebElement seeAllQAJobsButton = acquisition.acquireElementFromPage("seeAllQAJobsButton", "QualityAssuranceTeamPage");
        qualityAssuranceTeamPage.verifyElementState(seeAllQAJobsButton, ElementState.displayed);

        landingPage.log.important("Verifying the URL is https://useinsider.com/careers/quality-assurance/");
        qualityAssuranceTeamPage.verifyCurrentUrl("https://useinsider.com/careers/quality-assurance/");
        qualityAssuranceTeamPage.log.success("Curren URL verified as: https://useinsider.com/careers/quality-assurance/");

        landingPage.log.important("Clicking on See All QA Jobs button");
        qualityAssuranceTeamPage.clickElement(seeAllQAJobsButton);

        landingPage.log.important("Waiting until the total number of jobs displayed on the Quality Assurance jobs page");
        WebElement totalNumberOfJobs = acquisition.acquireElementFromPage("totalNumberOfJobs", "QualityAssuranceJobsPage");
        qualityAssuranceJobsPage.verifyElementState(totalNumberOfJobs, ElementState.displayed);

        landingPage.log.important("Selecting 'Istanbul, Turkiye' under the Filter by Location container");
        WebElement filterByLocationContainer = acquisition.acquireElementFromPage("filterByLocationContainer", "QualityAssuranceJobsPage");
        qualityAssuranceJobsPage.clickTowards(filterByLocationContainer);
        WebElement istanbul = acquisition.acquireListedElementFromPage("Istanbul, Turkiye", "filterItems", "QualityAssuranceJobsPage");
        qualityAssuranceJobsPage.clickElement(istanbul);

        landingPage.log.important("Selecting 'Quality Assurance' under the Filter by Department container");
        WebElement filterByDepartmentContainer = acquisition.acquireElementFromPage("filterByDepartmentContainer", "QualityAssuranceJobsPage");
        qualityAssuranceJobsPage.clickTowards(filterByDepartmentContainer);
        WebElement qualityAssurance = acquisition.acquireListedElementFromPage("Quality Assurance", "filterItems", "QualityAssuranceJobsPage");
        qualityAssuranceJobsPage.clickElement(qualityAssurance);

        landingPage.log.important("Verifying the listed jobs contains 'Quality Assurance' and 'Istanbul, Turkiye'");
        qualityAssuranceJobsPage.verifyTheListedJobConfigurations("qualityassurance", "istanbul-turkiye");

        landingPage.log.important("Hovering over on job card with title -> Senior Software Quality Assurance Engineer");
        WebElement softwareQAEngineerRoleCard = acquisition.acquireListedElementByAttribute("innerText", "Senior Software Quality Assurance Engineer", "listedJobTitles", "QualityAssuranceJobsPage");
        qualityAssuranceJobsPage.hoverOver(softwareQAEngineerRoleCard);
        landingPage.log.important("Clicking View Role button");
        WebElement viewRoleButton = acquisition.acquireElementFromPage("viewRoleButton", "QualityAssuranceJobsPage");
        qualityAssuranceJobsPage.clickTowards(viewRoleButton);

        landingPage.log.important("Switching to the new tab");
        qualityAssuranceJobsPage.switchWindowByIndex(1);

        landingPage.log.important("Verifying the job title and Apply for This Job button is displayed");
        WebElement jobTitle = acquisition.acquireElementFromPage("jobTitle", "LeverApplicationFormPage");
        leverApplicationFormPage.verifyElementState(jobTitle, ElementState.displayed);
        WebElement applyForThisJobButton = acquisition.acquireElementFromPage("applyForThisJobButton", "LeverApplicationFormPage");
        leverApplicationFormPage.verifyElementState(applyForThisJobButton, ElementState.displayed);
        landingPage.log.important("Verifying the URL contains: https://jobs.lever.co/useinsider/");
        leverApplicationFormPage.verifyUrlContains("https://jobs.lever.co/useinsider/");

    }

}

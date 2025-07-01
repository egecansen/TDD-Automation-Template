import common.models.PersonalDataModel;
import ollama.models.inference.InferenceModel;
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
    ProductDemoPage productDemoPage = new ProductDemoPage();
    DemoRequestPage demoRequestPage = new DemoRequestPage();
    QualityAssuranceJobsPage qualityAssuranceJobsPage = new QualityAssuranceJobsPage();
    QualityAssuranceTeamPage qualityAssuranceTeamPage = new QualityAssuranceTeamPage();
    LeverApplicationFormPage leverApplicationFormPage = new LeverApplicationFormPage();

    @Test @Tag("Case1") @DisplayName("Navigate through QA job listings, verify form fields, and ensure the application process works as expected.")
    public void verifyQualityAssuranceJobsInIstanbul() {
        landingPage.navigate("https://useinsider.com/");
        log.important("Verifying the URL is https://useinsider.com/");
        landingPage.verifyCurrentUrl("https://useinsider.com/");

        log.important("Accepting cookies");
        WebElement acceptCookieButton = acquisition.acquireElementFromPage("acceptCookieButton", "LandingPage");
        landingPage.clickElement(acceptCookieButton);

        log.important("Verifying the slider banner element called homeLogoReel is displayed");
        WebElement homeLogoReel = acquisition.acquireElementFromPage("homeLogoReel", "LandingPage");
        landingPage.verifyElementState(homeLogoReel, ElementState.displayed);

        log.important("Clicking on Career item under the Company menu from the main navigation");
        landingPage.hoverOverNavigationItemByText("Company");
        WebElement career = acquisition.acquireListedElementFromPage("Career", "companyMenuSubItems", "LandingPage");
        landingPage.clickElement(career);

        log.important("Verifying the Locations, Teams and Life at Insider blocks on the Careers page");
        WebElement locationsBlock = acquisition.acquireElementFromPage("locationsBlock", "CareersPage");
        careersPage.verifyElementState(locationsBlock, ElementState.displayed);
        WebElement teamsBlock = acquisition.acquireElementFromPage("teamsBlock", "CareersPage");
        careersPage.verifyElementState(teamsBlock, ElementState.displayed);
        WebElement lifeAtInsiderBlock = acquisition.acquireElementFromPage("lifeAtInsiderBlock", "CareersPage");
        careersPage.verifyElementState(lifeAtInsiderBlock, ElementState.displayed);

        log.important("Clicking on See All Teams button");
        WebElement seeAllTeamsButton = acquisition.acquireElementFromPage("seeAllTeamsButton", "CareersPage");
        careersPage.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'instant'});", seeAllTeamsButton);
        careersPage.clickElement(seeAllTeamsButton, true);

        log.important("Scrolling through teams and clicking on Quality Assurance");
        WebElement QATeam = acquisition.acquireListedElementFromPage("Quality Assurance", "teams", "CareersPage");
        careersPage.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'instant'});", QATeam);
        careersPage.clickElement(QATeam, true);

        log.important("Verifying See All QA Jobs button on the Quality Assurance Team page is displayed");
        WebElement seeAllQAJobsButton = acquisition.acquireElementFromPage("seeAllQAJobsButton", "QualityAssuranceTeamPage");
        qualityAssuranceTeamPage.verifyElementState(seeAllQAJobsButton, ElementState.displayed);

        log.important("Verifying the URL is https://useinsider.com/careers/quality-assurance/");
        qualityAssuranceTeamPage.verifyCurrentUrl("https://useinsider.com/careers/quality-assurance/");
        qualityAssuranceTeamPage.log.success("Curren URL verified as: https://useinsider.com/careers/quality-assurance/");

        log.important("Clicking on See All QA Jobs button");
        qualityAssuranceTeamPage.clickElement(seeAllQAJobsButton);

        log.important("Waiting until the total number of jobs displayed on the Quality Assurance jobs page");
        WebElement totalNumberOfJobs = acquisition.acquireElementFromPage("totalNumberOfJobs", "QualityAssuranceJobsPage");
        qualityAssuranceJobsPage.verifyElementState(totalNumberOfJobs, ElementState.displayed);

        log.important("Selecting 'Istanbul, Turkiye' under the Filter by Location container");
        WebElement filterByLocationContainer = acquisition.acquireElementFromPage("filterByLocationContainer", "QualityAssuranceJobsPage");
        qualityAssuranceJobsPage.clickTowards(filterByLocationContainer);
        WebElement istanbul = acquisition.acquireListedElementFromPage("Istanbul, Turkiye", "filterItems", "QualityAssuranceJobsPage");
        qualityAssuranceJobsPage.clickElement(istanbul);

        log.important("Selecting 'Quality Assurance' under the Filter by Department container");
        WebElement filterByDepartmentContainer = acquisition.acquireElementFromPage("filterByDepartmentContainer", "QualityAssuranceJobsPage");
        qualityAssuranceJobsPage.clickTowards(filterByDepartmentContainer);
        WebElement qualityAssurance = acquisition.acquireListedElementFromPage("Quality Assurance", "filterItems", "QualityAssuranceJobsPage");
        qualityAssuranceJobsPage.clickElement(qualityAssurance);

        log.important("Verifying the listed jobs contains 'Quality Assurance' and 'Istanbul, Turkiye'");
        qualityAssuranceJobsPage.verifyTheListedJobConfigurations("qualityassurance", "istanbul-turkiye");

        log.important("Hovering over on job card with title -> Senior Software Quality Assurance Engineer");
        WebElement softwareQAEngineerRoleCard = acquisition.acquireListedElementByAttribute("innerText", "Senior Software Quality Assurance Engineer", "listedJobTitles", "QualityAssuranceJobsPage");
        qualityAssuranceJobsPage.hoverOver(softwareQAEngineerRoleCard);
        log.important("Clicking View Role button");
        WebElement viewRoleButton = acquisition.acquireElementFromPage("viewRoleButton", "QualityAssuranceJobsPage");
        qualityAssuranceJobsPage.clickTowards(viewRoleButton);

        log.important("Switching to the new tab");
        qualityAssuranceJobsPage.switchWindowByIndex(1);

        log.important("Verifying the job title and Apply for This Job button is displayed");
        WebElement jobTitle = acquisition.acquireElementFromPage("jobTitle", "LeverApplicationFormPage");
        leverApplicationFormPage.verifyElementState(jobTitle, ElementState.displayed);
        WebElement applyForThisJobButton = acquisition.acquireElementFromPage("applyForThisJobButton", "LeverApplicationFormPage");
        leverApplicationFormPage.verifyElementState(applyForThisJobButton, ElementState.displayed);
        log.important("Verifying the URL contains: https://jobs.lever.co/useinsider/");
        leverApplicationFormPage.verifyUrlContains("https://jobs.lever.co/useinsider/");
    }

    @Test @Tag("Case2") @DisplayName("Submit a demo request with LLM generated mock data")
    public void submitDemoForm() {
        landingPage.navigate("https://useinsider.com/");
        log.important("Verifying the URL is https://useinsider.com/");
        landingPage.verifyCurrentUrl("https://useinsider.com/");

        log.important("Accepting cookies");
        WebElement acceptCookieButton = acquisition.acquireElementFromPage("acceptCookieButton", "LandingPage");
        landingPage.clickElement(acceptCookieButton);

        log.important("Verifying the slider banner element called homeLogoReel is displayed");
        WebElement homeLogoReel = acquisition.acquireElementFromPage("homeLogoReel", "LandingPage");
        landingPage.verifyElementState(homeLogoReel, ElementState.displayed);

        log.important("Clicking on getADemoButton from the main navigation");
        WebElement getADemoButton = acquisition.acquireElementFromPage("getADemoButton", "LandingPage");
        landingPage.clickElement(getADemoButton);

        log.info("Generating user data for the demo request form");
        InferenceModel model = new InferenceModel.Builder()
                .model("gemma3:27b")
                .prompt("Generate personal data for submitting a demo request to Insider. Do not include the country code to the phone number.")
                .build();
        PersonalDataModel personalDataModel = ollama.inference(model, PersonalDataModel.class);

        log.important("Filling the firsName form input on DemoRequestPage");
        WebElement firsName = acquisition.acquireElementFromPage("firsName", "DemoRequestPage");
        demoRequestPage.fillInput(firsName, personalDataModel.getFirstName());

        log.important("Filling the lastName form input on DemoRequestPage");
        WebElement lastName = acquisition.acquireElementFromPage("lastName", "DemoRequestPage");
        demoRequestPage.fillInput(lastName, personalDataModel.getLastName());

        log.important("Filling the email form input on DemoRequestPage");
        WebElement email = acquisition.acquireElementFromPage("email", "DemoRequestPage");
        demoRequestPage.fillInput(email, personalDataModel.getBusinessEmail());

        log.important("Filling the industry form input on DemoRequestPage");
        WebElement industry = acquisition.acquireElementFromPage("industry", "DemoRequestPage");
        demoRequestPage.fillInput(industry, personalDataModel.getIndustry());

        log.important("Filling the jobTitle form input on DemoRequestPage");
        WebElement jobTitle = acquisition.acquireElementFromPage("jobTitle", "DemoRequestPage");
        demoRequestPage.fillInput(jobTitle, personalDataModel.getJobTitle());

        log.important("Filling the companyName form input on DemoRequestPage");
        WebElement companyName = acquisition.acquireElementFromPage("companyName", "DemoRequestPage");
        demoRequestPage.fillInput(companyName, personalDataModel.getCompanyName());

        log.important("Filling the phoneNumber form input on DemoRequestPage");
        WebElement phoneNumber = acquisition.acquireElementFromPage("phoneNumber", "DemoRequestPage");
        demoRequestPage.fillInput(phoneNumber, personalDataModel.getPhone());

        log.important("Filling the howDidYouHearAboutUs form input on DemoRequestPage");
        WebElement howDidYouHearAboutUs = acquisition.acquireElementFromPage("howDidYouHearAboutUs", "DemoRequestPage");
        demoRequestPage.fillInput(howDidYouHearAboutUs, personalDataModel.getHowDidYouHearAboutUs());

        log.important("Clicking on submitButton");
        WebElement submitButton = acquisition.acquireElementFromPage("submitButton", "DemoRequestPage");
        demoRequestPage.clickElement(submitButton);

        log.important("Verifying the thankYouForSubmittingText content on the DemoRequestConfirmationPage");
        WebElement thankYouForSubmittingText = acquisition.acquireElementFromPage("thankYouForSubmittingText", "DemoRequestConfirmationPage");
        demoRequestPage.verifyElementText(thankYouForSubmittingText, "Thank you for submitting the form");
        log.important("thankYouForSubmittingText content is verified as: Thank you for submitting the form");
    }

    @Test @Tag("Case3") @DisplayName("Explore insider - Tutorial")
    public void exploreInsider() {
        landingPage.navigate("https://useinsider.com/");
        log.important("Verifying the URL is https://useinsider.com/");
        landingPage.verifyCurrentUrl("https://useinsider.com/");

        log.important("Accepting cookies");
        WebElement acceptCookieButton = acquisition.acquireElementFromPage("acceptCookieButton", "LandingPage");
        landingPage.clickElement(acceptCookieButton);

        log.important("Verifying the slider banner element called homeLogoReel is displayed");
        WebElement homeLogoReel = acquisition.acquireElementFromPage("homeLogoReel", "LandingPage");
        landingPage.verifyElementState(homeLogoReel, ElementState.displayed);

        log.important("Clicking on 'Explore Insider' from the main navigation");
        WebElement exploreInsider = acquisition.acquireElementFromPage("exploreInsider", "LandingPage");
        landingPage.clickElement(exploreInsider);

        log.important("Switching to the new tab");
        landingPage.switchWindowByIndex(1);

        log.important("Clicking on exploreInsiderDemoButton on the ProductDemoPage");
        WebElement exploreInsiderDemoButton = acquisition.acquireElementFromPage("exploreInsiderDemoButton", "ProductDemoPage");
        productDemoPage.verifyElementState(exploreInsiderDemoButton, ElementState.displayed);
        landingPage.verifyUrlContains("https://useinsider.com/product-demo-hub/");

        productDemoPage.clickElement(exploreInsiderDemoButton);

        log.important("Clicking on exploreInsiderLaunchButton on the ProductDemoPage");
        WebElement exploreInsiderLaunchButton = acquisition.acquireElementFromPage("exploreInsiderLaunchButton", "ProductDemoPage");
        productDemoPage.verifyElementState(exploreInsiderDemoButton, ElementState.displayed);
        productDemoPage.clickElement(exploreInsiderLaunchButton);

        log.important("Switching to the new tab");
        productDemoPage.switchWindowByIndex(2);

        log.important("Clicking on letsGetStartedButton on the DemoHubAppPage");
        WebElement iframe;
        WebElement letsGetStartedButton;
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        letsGetStartedButton = acquisition.acquireElementFromPage("letsGetStartedButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, letsGetStartedButton);

        log.important("Clicking on gotItButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement gotItButton = acquisition.acquireElementFromPage("gotItButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, gotItButton);

        log.important("Clicking on greatButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement greatButton = acquisition.acquireElementFromPage("greatButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, greatButton);

        log.important("Clicking on letsProceedButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement letsProceedButton = acquisition.acquireElementFromPage("letsProceedButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, letsProceedButton);

        log.important("Clicking on doneButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement doneButton = acquisition.acquireElementFromPage("doneButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, doneButton);

        log.important("Clicking on interestingButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement interestingButton = acquisition.acquireElementFromPage("interestingButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, interestingButton);

        log.important("Clicking on userProfilesMenuItem on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement userProfilesMenuItem = acquisition.acquireElementFromPage("userProfilesMenuItem", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, userProfilesMenuItem);

        log.important("Clicking on superCoolButton on the DemoHubAppPage");
        WebElement superCoolButton;
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        superCoolButton = acquisition.acquireElementFromPage("superCoolButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, superCoolButton);

        log.important("Clicking on wowInterestingButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement wowInterestingButton = acquisition.acquireElementFromPage("wowInterestingButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, wowInterestingButton);

        log.important("Clicking on letsGoButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement letsGoButton = acquisition.acquireElementFromPage("letsGoButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, letsGoButton);

        log.important("Clicking on architectMenuItem on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement architectMenuItem = acquisition.acquireElementFromPage("architectMenuItem", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, architectMenuItem);

        log.important("Clicking on superCoolButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        superCoolButton = acquisition.acquireElementFromPage("superCoolButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, superCoolButton);

        log.important("Clicking on startJourneyCreatorCard on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement startJourneyCreatorCard = acquisition.acquireElementFromPage("startJourneyCreatorCard", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, startJourneyCreatorCard);

        log.important("Clicking on wowSuperCoolButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement wowSuperCoolButton = acquisition.acquireElementFromPage("wowSuperCoolButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, wowSuperCoolButton);

        log.important("Clicking on sendPromptButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement sendPromptButton = acquisition.acquireElementFromPage("sendPromptButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, sendPromptButton);

        log.important("Clicking on looksGreatButton on the DemoHubAppPage");
        WebElement looksGreatButton;
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        looksGreatButton = acquisition.acquireElementFromPage("looksGreatButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, looksGreatButton);

        log.important("Clicking on showReasonsButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement showReasonsButton = acquisition.acquireElementFromPage("showReasonsButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, showReasonsButton);

        log.important("Clicking on letsGetStartedButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        letsGetStartedButton = acquisition.acquireElementFromPage("letsGetStartedButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, letsGetStartedButton);

        log.important("Clicking on letsStartButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement letsStartButton = acquisition.acquireElementFromPage("letsStartButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, letsStartButton);

        log.important("Clicking on onEventBox on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement onEventSignUpBox = acquisition.acquireElementFromPage("onEventSignUpBox", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, onEventSignUpBox);

        log.important("Clicking on smartSegmentCreatorButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement smartSegmentCreatorButton = acquisition.acquireElementFromPage("smartSegmentCreatorButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, smartSegmentCreatorButton);

        log.important("Clicking on createTheSegmentButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement createTheSegmentButton = acquisition.acquireElementFromPage("createTheSegmentButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, createTheSegmentButton);

        log.important("Clicking on applySegmentationButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement applySegmentationButton = acquisition.acquireElementFromPage("applySegmentationButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, applySegmentationButton);

        log.important("Clicking on wonderfulButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement wonderfulButton = acquisition.acquireElementFromPage("wonderfulButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, wonderfulButton);

        log.important("Clicking on thatsInterestingButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement thatsInterestingButton = acquisition.acquireElementFromPage("thatsInterestingButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, thatsInterestingButton);

        log.important("Clicking on emailBox on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement emailBox = acquisition.acquireElementFromPage("emailBox", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, emailBox);

        log.important("Clicking on fabulousButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement fabulousButton = acquisition.acquireElementFromPage("fabulousButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, fabulousButton);

        log.important("Clicking on letsContinueButton on the DemoHubAppPage");
        WebElement letsContinueButton;
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        letsContinueButton = acquisition.acquireElementFromPage("letsContinueButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, letsContinueButton);

        log.important("Clicking on letsDoIt on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement letsDoIt = acquisition.acquireElementFromPage("letsDoIt", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, letsDoIt);

        log.important("Clicking on looksGreatButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        looksGreatButton = acquisition.acquireElementFromPage("looksGreatButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, looksGreatButton);

        log.important("Clicking on interesting100Button on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement interesting100Button = acquisition.acquireElementFromPage("interesting100Button", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, interesting100Button);

        log.important("Clicking on smsBox on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement smsBox = acquisition.acquireElementFromPage("smsBox", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, smsBox);

        log.important("Clicking on generateSmsContentButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement generateSmsContentButton = acquisition.acquireElementFromPage("generateSmsContentButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, generateSmsContentButton);

        log.important("Clicking on thatsWonderfulButton on the DemoHubAppPage");
        WebElement thatsWonderfulButton;
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        thatsWonderfulButton = acquisition.acquireElementFromPage("thatsWonderfulButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, thatsWonderfulButton);

        log.important("Clicking on generateCopyButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement generateCopyButton = acquisition.acquireElementFromPage("generateCopyButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, generateCopyButton);

        log.important("Clicking on letsPickOneButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement letsPickOneButton = acquisition.acquireElementFromPage("letsPickOneButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, letsPickOneButton);

        log.important("Clicking on letsContinueButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        letsContinueButton = acquisition.acquireElementFromPage("letsContinueButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, letsContinueButton);

        log.important("Clicking on enableSendTimeOptimizationCheckMark on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement enableSendTimeOptimizationCheckMark = acquisition.acquireElementFromPage("enableSendTimeOptimizationCheckMark", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, enableSendTimeOptimizationCheckMark);

        log.important("Clicking on thatsWonderfulButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        thatsWonderfulButton = acquisition.acquireElementFromPage("thatsWonderfulButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, thatsWonderfulButton);

        log.important("Clicking on perfectButton on the DemoHubAppPage");
        WebElement perfectButton;
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        perfectButton = acquisition.acquireElementFromPage("perfectButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, perfectButton);

        log.important("Clicking on excellentButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement excellentButton = acquisition.acquireElementFromPage("excellentButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, excellentButton);

        log.important("Clicking on perfectButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        perfectButton = acquisition.acquireElementFromPage("perfectButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, perfectButton);

        log.important("Clicking on switchTheChannelButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement switchTheChannelButton = acquisition.acquireElementFromPage("switchTheChannelButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, switchTheChannelButton);

        log.important("Clicking on appPushMenuItem on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement appPushMenuItem = acquisition.acquireElementFromPage("appPushMenuItem", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, appPushMenuItem);

        log.important("Clicking on awesomeButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement awesomeButton = acquisition.acquireElementFromPage("awesomeButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, awesomeButton);

        log.important("Clicking on generateImageWithAIButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement generateImageWithAIButton = acquisition.acquireElementFromPage("generateImageWithAIButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, generateImageWithAIButton);

        log.important("Clicking on perfectButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        perfectButton = acquisition.acquireElementFromPage("perfectButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, perfectButton);

        log.important("Clicking on suggestImage on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement suggestImage = acquisition.acquireElementFromPage("suggestImage", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, suggestImage);

        log.important("Clicking on addDynamicContentOption on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement addDynamicContentOption = acquisition.acquireElementFromPage("addDynamicContentOption", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, addDynamicContentOption);

        log.important("Clicking on couponMenuItem on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement couponMenuItem = acquisition.acquireElementFromPage("couponMenuItem", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, couponMenuItem);

        log.important("Clicking on saveButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement saveButton = acquisition.acquireElementFromPage("saveButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, saveButton);

        log.important("Clicking on goToLaunchButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement goToLaunchButton = acquisition.acquireElementFromPage("goToLaunchButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, goToLaunchButton);

        log.important("Clicking on fantasticButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement fantasticButton = acquisition.acquireElementFromPage("fantasticButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, fantasticButton);

        log.important("Clicking on superCoolButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        superCoolButton = acquisition.acquireElementFromPage("superCoolButton", "DemoHubAppPage");
        productDemoPage.clickIframeButton(iframe, superCoolButton);
    }

}

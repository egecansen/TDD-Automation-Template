
import common.models.PersonalDataModel;
import ollama.models.inference.InferenceModel;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;
import pages.LandingPage;
import pages.QualityAssuranceJobsPage;
import pickleib.enums.ElementState;
import common.BaseTest;

public class ApplicationTests extends BaseTest {

    @Test @Tag("Case1") @DisplayName("Navigate through QA job listings, verify the job details.")
    public void verifyQualityAssuranceJobsInIstanbul() {
        base.navigate("https://useinsider.com/");
        log.important("Verifying the URL is https://useinsider.com/");
        base.verifyCurrentUrl("https://useinsider.com/");

        log.important("Accepting cookies");
        WebElement acceptCookieButton = acquisition.acquireElementFromPage("acceptCookieButton", "LandingPage");
        base.clickIfPresent(acceptCookieButton);

        log.important("Verifying the slider banner element called homeLogoReel is displayed");
        WebElement homeLogoReel = acquisition.acquireElementFromPage("homeLogoReel", "LandingPage");
        base.verifyElementState(homeLogoReel, ElementState.displayed);

        log.important("Clicking on Career item under the Company menu from the main navigation");
        LandingPage.hoverOverNavigationItemByText("Company");
        WebElement career = acquisition.acquireListedElementFromPage("Career", "companyMenuSubItems", "LandingPage");
        base.clickElement(career);

        log.important("Verifying the Locations, Teams and Life at Insider blocks on the Careers page");
        WebElement locationsBlock = acquisition.acquireElementFromPage("locationsBlock", "CareersPage");
        base.verifyElementState(locationsBlock, ElementState.displayed);
        WebElement teamsBlock = acquisition.acquireElementFromPage("teamsBlock", "CareersPage");
        base.verifyElementState(teamsBlock, ElementState.displayed);
        WebElement lifeAtInsiderBlock = acquisition.acquireElementFromPage("lifeAtInsiderBlock", "CareersPage");
        base.verifyElementState(lifeAtInsiderBlock, ElementState.displayed);

        log.important("Clicking on See All Teams button");
        WebElement seeAllTeamsButton = acquisition.acquireElementFromPage("seeAllTeamsButton", "CareersPage");
        base.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'instant'});", seeAllTeamsButton);
        base.clickElement(seeAllTeamsButton, true);

        log.important("Scrolling through teams and clicking on Quality Assurance");
        WebElement QATeam = acquisition.acquireListedElementFromPage("Quality Assurance", "teams", "CareersPage");
        base.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'instant'});", QATeam);
        base.clickElement(QATeam, true);

        log.important("Verifying See All QA Jobs button on the Quality Assurance Team page is displayed");
        WebElement seeAllQAJobsButton = acquisition.acquireElementFromPage("seeAllQAJobsButton", "QualityAssuranceTeamPage");
        base.verifyElementState(seeAllQAJobsButton, ElementState.displayed);

        log.important("Verifying the URL is https://useinsider.com/careers/quality-assurance/");
        base.verifyCurrentUrl("https://useinsider.com/careers/quality-assurance/");
        base.log.success("Curren URL verified as: https://useinsider.com/careers/quality-assurance/");

        log.important("Clicking on See All QA Jobs button");
        base.clickElement(seeAllQAJobsButton);

        log.important("Waiting until the total number of jobs displayed on the Quality Assurance jobs page");
        WebElement totalNumberOfJobs = acquisition.acquireElementFromPage("totalNumberOfJobs", "QualityAssuranceJobsPage");
        base.verifyElementState(totalNumberOfJobs, ElementState.displayed);

        log.important("Selecting 'Istanbul, Turkiye' under the Filter by Location container");
        WebElement filterByLocationContainer = acquisition.acquireElementFromPage("filterByLocationContainer", "QualityAssuranceJobsPage");
        base.clickTowards(filterByLocationContainer);
        WebElement istanbul = acquisition.acquireListedElementFromPage("Istanbul, Turkiye", "filterItems", "QualityAssuranceJobsPage");
        base.clickElement(istanbul);

        log.important("Selecting 'Quality Assurance' under the Filter by Department container");
        WebElement filterByDepartmentContainer = acquisition.acquireElementFromPage("filterByDepartmentContainer", "QualityAssuranceJobsPage");
        base.clickTowards(filterByDepartmentContainer);
        WebElement qualityAssurance = acquisition.acquireListedElementFromPage("Quality Assurance", "filterItems", "QualityAssuranceJobsPage");
        base.clickElement(qualityAssurance);

        log.important("Verifying the listed jobs contains 'Quality Assurance' and 'Istanbul, Turkiye'");
        QualityAssuranceJobsPage.verifyTheListedJobConfigurations("qualityassurance", "istanbul-turkiye");

        log.important("Hovering over on job card with title Senior Software Quality Assurance Engineer");
        WebElement softwareQAEngineerRoleCard = acquisition.acquireListedElementByAttribute("innerText", "Senior Software Quality Assurance Engineer", "listedJobTitles", "QualityAssuranceJobsPage");
        base.hoverOver(softwareQAEngineerRoleCard);
        log.important("Clicking View Role button");
        WebElement viewRoleButton = acquisition.acquireElementFromPage("viewRoleButton", "QualityAssuranceJobsPage");
        base.clickTowards(viewRoleButton);

        log.important("Switching to the new tab");
        base.switchWindowByIndex(1);

        log.important("Verifying the job title and Apply for This Job button is displayed");
        WebElement jobTitle = acquisition.acquireElementFromPage("jobTitle", "LeverApplicationFormPage");
        base.verifyElementState(jobTitle, ElementState.displayed);
        WebElement applyForThisJobButton = acquisition.acquireElementFromPage("applyForThisJobButton", "LeverApplicationFormPage");
        base.verifyElementState(applyForThisJobButton, ElementState.displayed);
        log.important("Verifying the URL contains: https://jobs.lever.co/useinsider/");
        base.verifyUrlContains("https://jobs.lever.co/useinsider/");
    }

    //TODO: Add your ollama-api key to test.properties file before executing this script.
    @Test @Tag("Case2") @DisplayName("Submit a demo request with LLM generated mock data")
    public void submitDemoForm() {
        base.navigate("https://useinsider.com/");
        log.important("Verifying the URL is https://useinsider.com/");
        base.verifyCurrentUrl("https://useinsider.com/");

        log.important("Accepting cookies");
        WebElement acceptCookieButton = acquisition.acquireElementFromPage("acceptCookieButton", "LandingPage");
        base.clickIfPresent(acceptCookieButton);

        log.important("Verifying the slider banner element called homeLogoReel is displayed");
        WebElement homeLogoReel = acquisition.acquireElementFromPage("homeLogoReel", "LandingPage");
        base.verifyElementState(homeLogoReel, ElementState.displayed);

        log.important("Clicking on getADemoButton from the main navigation");
        WebElement getADemoButton = acquisition.acquireElementFromPage("getADemoButton", "LandingPage");
        base.clickElement(getADemoButton);

        log.info("Generating user data for the demo request form");
        InferenceModel model = new InferenceModel.Builder()
                .model("gemma3:27b")
                .prompt("Generate personal data for submitting a demo request to Insider. Do not include the country code to the phone number.")
                .build();
        PersonalDataModel personalDataModel = ollama.inference(model, PersonalDataModel.class);

        log.important("Filling the firsName form input on DemoRequestPage");
        WebElement firsName = acquisition.acquireElementFromPage("firsName", "DemoRequestPage");
        base.fillInput(firsName, personalDataModel.getFirstName());

        log.important("Filling the lastName form input on DemoRequestPage");
        WebElement lastName = acquisition.acquireElementFromPage("lastName", "DemoRequestPage");
        base.fillInput(lastName, personalDataModel.getLastName());

        log.important("Filling the email form input on DemoRequestPage");
        WebElement email = acquisition.acquireElementFromPage("email", "DemoRequestPage");
        base.fillInput(email, personalDataModel.getBusinessEmail());

        log.important("Filling the industry form input on DemoRequestPage");
        WebElement industry = acquisition.acquireElementFromPage("industry", "DemoRequestPage");
        base.fillInput(industry, personalDataModel.getIndustry());

        log.important("Filling the jobTitle form input on DemoRequestPage");
        WebElement jobTitle = acquisition.acquireElementFromPage("jobTitle", "DemoRequestPage");
        base.fillInput(jobTitle, personalDataModel.getJobTitle());

        log.important("Filling the companyName form input on DemoRequestPage");
        WebElement companyName = acquisition.acquireElementFromPage("companyName", "DemoRequestPage");
        base.fillInput(companyName, personalDataModel.getCompanyName());

        log.important("Filling the phoneNumber form input on DemoRequestPage");
        WebElement phoneNumber = acquisition.acquireElementFromPage("phoneNumber", "DemoRequestPage");
        base.fillInput(phoneNumber, personalDataModel.getPhone());

        log.important("Filling the howDidYouHearAboutUs form input on DemoRequestPage");
        WebElement howDidYouHearAboutUs = acquisition.acquireElementFromPage("howDidYouHearAboutUs", "DemoRequestPage");
        base.fillInput(howDidYouHearAboutUs, personalDataModel.getHowDidYouHearAboutUs());

        log.important("Clicking on submitButton");
        WebElement submitButton = acquisition.acquireElementFromPage("submitButton", "DemoRequestPage");
        base.clickElement(submitButton);

        log.important("Verifying the thankYouForSubmittingText content on the DemoRequestConfirmationPage");
        WebElement thankYouForSubmittingText = acquisition.acquireElementFromPage("thankYouForSubmittingText", "DemoRequestConfirmationPage");
        base.verifyElementText(thankYouForSubmittingText, "Thank you for submitting the form");
        log.success("thankYouForSubmittingText content is verified as: Thank you for submitting the form");
    }

    @Test @Tag("Case3") @DisplayName("Follow the tutorial on the 'Explore Insider'.")
    public void exploreInsider() {
        base.navigate("https://useinsider.com/");
        log.important("Verifying the URL is https://useinsider.com/");
        base.verifyCurrentUrl("https://useinsider.com/");

        log.important("Accepting cookies");
        WebElement acceptCookieButton = acquisition.acquireElementFromPage("acceptCookieButton", "LandingPage");
        base.clickIfPresent(acceptCookieButton);

        log.important("Verifying the slider banner element called homeLogoReel is displayed");
        WebElement homeLogoReel = acquisition.acquireElementFromPage("homeLogoReel", "LandingPage");
        base.verifyElementState(homeLogoReel, ElementState.displayed);

        log.important("Clicking on 'Explore Insider' from the main navigation");
        WebElement exploreInsider = acquisition.acquireElementFromPage("exploreInsider", "LandingPage");
        base.clickElement(exploreInsider);

        log.important("Switching to the new tab");
        base.switchWindowByIndex(1);

        log.important("Clicking on exploreInsiderDemoButton on the ProductDemoPage");
        WebElement exploreInsiderDemoButton = acquisition.acquireElementFromPage("exploreInsiderDemoButton", "ProductDemoPage");
        base.verifyElementState(exploreInsiderDemoButton, ElementState.displayed);
        base.verifyUrlContains("https://useinsider.com/product-demo-hub/");

        base.clickElement(exploreInsiderDemoButton);

        log.important("Clicking on exploreInsiderLaunchButton on the ProductDemoPage");
        WebElement exploreInsiderLaunchButton = acquisition.acquireElementFromPage("exploreInsiderLaunchButton", "ProductDemoPage");
        base.verifyElementState(exploreInsiderDemoButton, ElementState.displayed);
        base.clickElement(exploreInsiderLaunchButton);

        log.important("Switching to the new tab");
        base.switchWindowByIndex(2);

        log.important("Clicking on letsGetStartedButton on the DemoHubAppPage");
        WebElement iframe;
        WebElement letsGetStartedButton;
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        letsGetStartedButton = acquisition.acquireElementFromPage("letsGetStartedButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, letsGetStartedButton);

        log.important("Clicking on gotItButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement gotItButton = acquisition.acquireElementFromPage("gotItButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, gotItButton);

        log.important("Clicking on greatButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement greatButton = acquisition.acquireElementFromPage("greatButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, greatButton);

        log.important("Clicking on letsProceedButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement letsProceedButton = acquisition.acquireElementFromPage("letsProceedButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, letsProceedButton);

        log.important("Clicking on doneButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement doneButton = acquisition.acquireElementFromPage("doneButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, doneButton);

        log.important("Clicking on interestingButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement interestingButton = acquisition.acquireElementFromPage("interestingButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, interestingButton);

        log.important("Clicking on userProfilesMenuItem on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement userProfilesMenuItem = acquisition.acquireElementFromPage("userProfilesMenuItem", "DemoHubAppPage");
        base.clickIframeButton(iframe, userProfilesMenuItem);

        log.important("Clicking on superCoolButton on the DemoHubAppPage");
        WebElement superCoolButton;
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        superCoolButton = acquisition.acquireElementFromPage("superCoolButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, superCoolButton);

        log.important("Clicking on wowInterestingButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement wowInterestingButton = acquisition.acquireElementFromPage("wowInterestingButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, wowInterestingButton);

        log.important("Clicking on letsGoButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement letsGoButton = acquisition.acquireElementFromPage("letsGoButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, letsGoButton);

        log.important("Clicking on architectMenuItem on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement architectMenuItem = acquisition.acquireElementFromPage("architectMenuItem", "DemoHubAppPage");
        base.clickIframeButton(iframe, architectMenuItem);

        log.important("Clicking on superCoolButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        superCoolButton = acquisition.acquireElementFromPage("superCoolButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, superCoolButton);

        log.important("Clicking on startJourneyCreatorCard on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement startJourneyCreatorCard = acquisition.acquireElementFromPage("startJourneyCreatorCard", "DemoHubAppPage");
        base.clickIframeButton(iframe, startJourneyCreatorCard);

        log.important("Clicking on wowSuperCoolButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement wowSuperCoolButton = acquisition.acquireElementFromPage("wowSuperCoolButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, wowSuperCoolButton);

        log.important("Clicking on sendPromptButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement sendPromptButton = acquisition.acquireElementFromPage("sendPromptButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, sendPromptButton);

        log.important("Clicking on looksGreatButton on the DemoHubAppPage");
        WebElement looksGreatButton;
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        looksGreatButton = acquisition.acquireElementFromPage("looksGreatButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, looksGreatButton);

        log.important("Clicking on showReasonsButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement showReasonsButton = acquisition.acquireElementFromPage("showReasonsButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, showReasonsButton);

        log.important("Clicking on letsGetStartedButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        letsGetStartedButton = acquisition.acquireElementFromPage("letsGetStartedButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, letsGetStartedButton);

        log.important("Clicking on letsStartButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement letsStartButton = acquisition.acquireElementFromPage("letsStartButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, letsStartButton);

        log.important("Clicking on onEventBox on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement onEventSignUpBox = acquisition.acquireElementFromPage("onEventSignUpBox", "DemoHubAppPage");
        base.clickIframeButton(iframe, onEventSignUpBox);

        log.important("Clicking on smartSegmentCreatorButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement smartSegmentCreatorButton = acquisition.acquireElementFromPage("smartSegmentCreatorButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, smartSegmentCreatorButton);

        log.important("Clicking on createTheSegmentButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement createTheSegmentButton = acquisition.acquireElementFromPage("createTheSegmentButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, createTheSegmentButton);

        log.important("Clicking on applySegmentationButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement applySegmentationButton = acquisition.acquireElementFromPage("applySegmentationButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, applySegmentationButton);

        log.important("Clicking on wonderfulButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement wonderfulButton = acquisition.acquireElementFromPage("wonderfulButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, wonderfulButton);

        log.important("Clicking on thatsInterestingButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement thatsInterestingButton = acquisition.acquireElementFromPage("thatsInterestingButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, thatsInterestingButton);

        log.important("Clicking on emailBox on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement emailBox = acquisition.acquireElementFromPage("emailBox", "DemoHubAppPage");
        base.clickIframeButton(iframe, emailBox);

        log.important("Clicking on fabulousButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement fabulousButton = acquisition.acquireElementFromPage("fabulousButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, fabulousButton);

        log.important("Clicking on letsContinueButton on the DemoHubAppPage");
        WebElement letsContinueButton;
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        letsContinueButton = acquisition.acquireElementFromPage("letsContinueButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, letsContinueButton);

        log.important("Clicking on letsDoIt on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement letsDoIt = acquisition.acquireElementFromPage("letsDoIt", "DemoHubAppPage");
        base.clickIframeButton(iframe, letsDoIt);

        log.important("Clicking on looksGreatButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        looksGreatButton = acquisition.acquireElementFromPage("looksGreatButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, looksGreatButton);

        log.important("Clicking on interesting100Button on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement interesting100Button = acquisition.acquireElementFromPage("interesting100Button", "DemoHubAppPage");
        base.clickIframeButton(iframe, interesting100Button);

        log.important("Clicking on smsBox on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement smsBox = acquisition.acquireElementFromPage("smsBox", "DemoHubAppPage");
        base.clickIframeButton(iframe, smsBox);

        log.important("Clicking on generateSmsContentButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement generateSmsContentButton = acquisition.acquireElementFromPage("generateSmsContentButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, generateSmsContentButton);

        log.important("Clicking on thatsWonderfulButton on the DemoHubAppPage");
        WebElement thatsWonderfulButton;
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        thatsWonderfulButton = acquisition.acquireElementFromPage("thatsWonderfulButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, thatsWonderfulButton);

        log.important("Clicking on generateCopyButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement generateCopyButton = acquisition.acquireElementFromPage("generateCopyButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, generateCopyButton);

        log.important("Clicking on letsPickOneButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement letsPickOneButton = acquisition.acquireElementFromPage("letsPickOneButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, letsPickOneButton);

        log.important("Clicking on letsContinueButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        letsContinueButton = acquisition.acquireElementFromPage("letsContinueButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, letsContinueButton);

        log.important("Clicking on enableSendTimeOptimizationCheckMark on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement enableSendTimeOptimizationCheckMark = acquisition.acquireElementFromPage("enableSendTimeOptimizationCheckMark", "DemoHubAppPage");
        base.clickIframeButton(iframe, enableSendTimeOptimizationCheckMark);

        log.important("Clicking on thatsWonderfulButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        thatsWonderfulButton = acquisition.acquireElementFromPage("thatsWonderfulButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, thatsWonderfulButton);

        log.important("Clicking on perfectButton on the DemoHubAppPage");
        WebElement perfectButton;
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        perfectButton = acquisition.acquireElementFromPage("perfectButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, perfectButton);

        log.important("Clicking on excellentButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement excellentButton = acquisition.acquireElementFromPage("excellentButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, excellentButton);

        log.important("Clicking on perfectButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        perfectButton = acquisition.acquireElementFromPage("perfectButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, perfectButton);

        log.important("Clicking on switchTheChannelButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement switchTheChannelButton = acquisition.acquireElementFromPage("switchTheChannelButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, switchTheChannelButton);

        log.important("Clicking on appPushMenuItem on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement appPushMenuItem = acquisition.acquireElementFromPage("appPushMenuItem", "DemoHubAppPage");
        base.clickIframeButton(iframe, appPushMenuItem);

        log.important("Clicking on awesomeButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement awesomeButton = acquisition.acquireElementFromPage("awesomeButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, awesomeButton);

        log.important("Clicking on generateImageWithAIButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement generateImageWithAIButton = acquisition.acquireElementFromPage("generateImageWithAIButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, generateImageWithAIButton);

        log.important("Clicking on perfectButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        perfectButton = acquisition.acquireElementFromPage("perfectButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, perfectButton);

        log.important("Clicking on suggestImage on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement suggestImage = acquisition.acquireElementFromPage("suggestImage", "DemoHubAppPage");
        base.clickIframeButton(iframe, suggestImage);

        log.important("Clicking on addDynamicContentOption on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement addDynamicContentOption = acquisition.acquireElementFromPage("addDynamicContentOption", "DemoHubAppPage");
        base.clickIframeButton(iframe, addDynamicContentOption);

        log.important("Clicking on couponMenuItem on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement couponMenuItem = acquisition.acquireElementFromPage("couponMenuItem", "DemoHubAppPage");
        base.clickIframeButton(iframe, couponMenuItem);

        log.important("Clicking on saveButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement saveButton = acquisition.acquireElementFromPage("saveButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, saveButton);

        log.important("Clicking on goToLaunchButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement goToLaunchButton = acquisition.acquireElementFromPage("goToLaunchButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, goToLaunchButton);

        log.important("Clicking on fantasticButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement fantasticButton = acquisition.acquireElementFromPage("fantasticButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, fantasticButton);

        log.important("Clicking on superCoolButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        superCoolButton = acquisition.acquireElementFromPage("superCoolButton", "DemoHubAppPage");
        base.clickIframeButton(iframe, superCoolButton);
    }

}

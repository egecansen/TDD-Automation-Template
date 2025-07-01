
import common.models.PersonalDataModel;
import ollama.models.inference.InferenceModel;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;
import pages.LandingPage;
import pages.QualityAssuranceJobsPage;
import pickleib.enums.ElementState;
import common.BaseTest;
import utils.mapping.MappingUtilities;

public class ApplicationTests extends BaseTest {

    @Test @Tag("Case1") @DisplayName("Navigate through QA job listings, verify the job details.")
    public void verifyQualityAssuranceJobsInIstanbul() {
        interactions.navigate("https://useinsider.com/");
        log.important("Verifying the URL is https://useinsider.com/");
        interactions.verifyCurrentUrl("https://useinsider.com/");

        log.important("Accepting cookies");
        WebElement acceptCookieButton = acquisition.acquireElementFromPage("acceptCookieButton", "LandingPage");
        interactions.clickElement(acceptCookieButton);

        log.important("Verifying the slider banner element called homeLogoReel is displayed");
        WebElement homeLogoReel = acquisition.acquireElementFromPage("homeLogoReel", "LandingPage");
        interactions.verifyElementState(homeLogoReel, ElementState.displayed);

        log.important("Clicking on Career item under the Company menu from the main navigation");
        LandingPage.hoverOverNavigationItemByText("Company");
        WebElement career = acquisition.acquireListedElementFromPage("Career", "companyMenuSubItems", "LandingPage");
        interactions.clickElement(career);

        log.important("Verifying the Locations, Teams and Life at Insider blocks on the Careers page");
        WebElement locationsBlock = acquisition.acquireElementFromPage("locationsBlock", "CareersPage");
        interactions.verifyElementState(locationsBlock, ElementState.displayed);
        WebElement teamsBlock = acquisition.acquireElementFromPage("teamsBlock", "CareersPage");
        interactions.verifyElementState(teamsBlock, ElementState.displayed);
        WebElement lifeAtInsiderBlock = acquisition.acquireElementFromPage("lifeAtInsiderBlock", "CareersPage");
        interactions.verifyElementState(lifeAtInsiderBlock, ElementState.displayed);

        log.important("Clicking on See All Teams button");
        WebElement seeAllTeamsButton = acquisition.acquireElementFromPage("seeAllTeamsButton", "CareersPage");
        interactions.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'instant'});", seeAllTeamsButton);
        interactions.clickElement(seeAllTeamsButton, true);

        log.important("Scrolling through teams and clicking on Quality Assurance");
        WebElement QATeam = acquisition.acquireListedElementFromPage("Quality Assurance", "teams", "CareersPage");
        interactions.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'instant'});", QATeam);
        interactions.clickElement(QATeam, true);

        log.important("Verifying See All QA Jobs button on the Quality Assurance Team page is displayed");
        WebElement seeAllQAJobsButton = acquisition.acquireElementFromPage("seeAllQAJobsButton", "QualityAssuranceTeamPage");
        interactions.verifyElementState(seeAllQAJobsButton, ElementState.displayed);

        log.important("Verifying the URL is https://useinsider.com/careers/quality-assurance/");
        interactions.verifyCurrentUrl("https://useinsider.com/careers/quality-assurance/");
        interactions.log.success("Curren URL verified as: https://useinsider.com/careers/quality-assurance/");

        log.important("Clicking on See All QA Jobs button");
        interactions.clickElement(seeAllQAJobsButton);

        log.important("Waiting until the total number of jobs displayed on the Quality Assurance jobs page");
        WebElement totalNumberOfJobs = acquisition.acquireElementFromPage("totalNumberOfJobs", "QualityAssuranceJobsPage");
        interactions.verifyElementState(totalNumberOfJobs, ElementState.displayed);

        log.important("Selecting 'Istanbul, Turkiye' under the Filter by Location container");
        WebElement filterByLocationContainer = acquisition.acquireElementFromPage("filterByLocationContainer", "QualityAssuranceJobsPage");
        interactions.clickTowards(filterByLocationContainer);
        WebElement istanbul = acquisition.acquireListedElementFromPage("Istanbul, Turkiye", "filterItems", "QualityAssuranceJobsPage");
        interactions.clickElement(istanbul);

        log.important("Selecting 'Quality Assurance' under the Filter by Department container");
        WebElement filterByDepartmentContainer = acquisition.acquireElementFromPage("filterByDepartmentContainer", "QualityAssuranceJobsPage");
        interactions.clickTowards(filterByDepartmentContainer);
        WebElement qualityAssurance = acquisition.acquireListedElementFromPage("Quality Assurance", "filterItems", "QualityAssuranceJobsPage");
        interactions.clickElement(qualityAssurance);

        log.important("Verifying the listed jobs contains 'Quality Assurance' and 'Istanbul, Turkiye'");
        QualityAssuranceJobsPage.verifyTheListedJobConfigurations("qualityassurance", "istanbul-turkiye");

        log.important("Hovering over on job card with title Senior Software Quality Assurance Engineer");
        WebElement softwareQAEngineerRoleCard = acquisition.acquireListedElementByAttribute("innerText", "Senior Software Quality Assurance Engineer", "listedJobTitles", "QualityAssuranceJobsPage");
        interactions.hoverOver(softwareQAEngineerRoleCard);
        log.important("Clicking View Role button");
        WebElement viewRoleButton = acquisition.acquireElementFromPage("viewRoleButton", "QualityAssuranceJobsPage");
        interactions.clickTowards(viewRoleButton);

        log.important("Switching to the new tab");
        interactions.switchWindowByIndex(1);

        log.important("Verifying the job title and Apply for This Job button is displayed");
        WebElement jobTitle = acquisition.acquireElementFromPage("jobTitle", "LeverApplicationFormPage");
        interactions.verifyElementState(jobTitle, ElementState.displayed);
        WebElement applyForThisJobButton = acquisition.acquireElementFromPage("applyForThisJobButton", "LeverApplicationFormPage");
        interactions.verifyElementState(applyForThisJobButton, ElementState.displayed);
        log.important("Verifying the URL contains: https://jobs.lever.co/useinsider/");
        interactions.verifyUrlContains("https://jobs.lever.co/useinsider/");
    }

    //TODO: Add your ollama-api key to test.properties file before executing this script.
    @Test @Tag("Case2") @DisplayName("Submit a demo request with LLM generated mock data")
    public void submitDemoForm() {
        interactions.navigate("https://useinsider.com/");
        log.important("Verifying the URL is https://useinsider.com/");
        interactions.verifyCurrentUrl("https://useinsider.com/");

        log.important("Accepting cookies");
        WebElement acceptCookieButton = acquisition.acquireElementFromPage("acceptCookieButton", "LandingPage");
        interactions.clickElement(acceptCookieButton);

        log.important("Verifying the slider banner element called homeLogoReel is displayed");
        WebElement homeLogoReel = acquisition.acquireElementFromPage("homeLogoReel", "LandingPage");
        interactions.verifyElementState(homeLogoReel, ElementState.displayed);

        log.important("Clicking on getADemoButton from the main navigation");
        WebElement getADemoButton = acquisition.acquireElementFromPage("getADemoButton", "LandingPage");
        interactions.clickElement(getADemoButton);

        log.info("Generating user data for the demo request form");
        InferenceModel model = new InferenceModel.Builder()
                .model("gemma3:27b")
                .prompt("Generate personal data for submitting a demo request to Insider. " +
                        "Do not include the country code to the phone number.")
                .build();
        PersonalDataModel personalDataModel = ollama.inference(model, PersonalDataModel.class);
        log.important("LLM generated mock data: \n" + MappingUtilities.Json.getJsonStringFor(personalDataModel));

        log.important("Filling the firstName form input on DemoRequestPage with " + personalDataModel.getFirstName());
        WebElement firstName = acquisition.acquireElementFromPage("firstName", "DemoRequestPage");
        interactions.fillInput(firstName, personalDataModel.getFirstName());

        log.important("Filling the lastName form input on DemoRequestPage with " + personalDataModel.getLastName());
        WebElement lastName = acquisition.acquireElementFromPage("lastName", "DemoRequestPage");
        interactions.fillInput(lastName, personalDataModel.getLastName());

        log.important("Filling the email form input on DemoRequestPage with " + personalDataModel.getBusinessEmail());
        WebElement email = acquisition.acquireElementFromPage("email", "DemoRequestPage");
        interactions.fillInput(email, personalDataModel.getBusinessEmail());

        log.important("Filling the industry form input on DemoRequestPage with " + personalDataModel.getIndustry().getValue());
        WebElement industry = acquisition.acquireElementFromPage("industry", "DemoRequestPage");
        interactions.fillInput(industry, personalDataModel.getIndustry().getValue());

        log.important("Filling the jobTitle form input on DemoRequestPage with " + personalDataModel.getJobTitle());
        WebElement jobTitle = acquisition.acquireElementFromPage("jobTitle", "DemoRequestPage");
        interactions.fillInput(jobTitle, personalDataModel.getJobTitle());

        log.important("Filling the companyName form input on DemoRequestPage with " + personalDataModel.getCompanyName());
        WebElement companyName = acquisition.acquireElementFromPage("companyName", "DemoRequestPage");
        interactions.fillInput(companyName, personalDataModel.getCompanyName());

        log.important("Filling the phoneNumber form input on DemoRequestPage with " + personalDataModel.getPhone());
        WebElement phoneNumber = acquisition.acquireElementFromPage("phoneNumber", "DemoRequestPage");
        interactions.fillInput(phoneNumber, personalDataModel.getPhone());

        log.important("Filling the howDidYouHearAboutUs form input on DemoRequestPage with " + personalDataModel.getHowDidYouHearAboutUs());
        WebElement howDidYouHearAboutUs = acquisition.acquireElementFromPage("howDidYouHearAboutUs", "DemoRequestPage");
        interactions.fillInput(howDidYouHearAboutUs, personalDataModel.getHowDidYouHearAboutUs());

        log.important("Clicking on submitButton");
        WebElement submitButton = acquisition.acquireElementFromPage("submitButton", "DemoRequestPage");
        interactions.clickElement(submitButton);

        log.important("Verifying the thankYouForSubmittingText content on the DemoRequestConfirmationPage");
        WebElement thankYouForSubmittingText = acquisition.acquireElementFromPage("thankYouForSubmittingText", "DemoRequestConfirmationPage");
        interactions.verifyElementText(thankYouForSubmittingText, "Thank you for submitting the form");
        log.success("thankYouForSubmittingText content is verified as: Thank you for submitting the form");
    }

    @Test @Tag("Case3") @DisplayName("Follow the tutorial on the 'Explore Insider'.")
    public void exploreInsider() {
        interactions.navigate("https://useinsider.com/");
        log.important("Verifying the URL is https://useinsider.com/");
        interactions.verifyCurrentUrl("https://useinsider.com/");

        log.important("Accepting cookies");
        WebElement acceptCookieButton = acquisition.acquireElementFromPage("acceptCookieButton", "LandingPage");
        interactions.clickElement(acceptCookieButton);

        log.important("Verifying the slider banner element called homeLogoReel is displayed");
        WebElement homeLogoReel = acquisition.acquireElementFromPage("homeLogoReel", "LandingPage");
        interactions.verifyElementState(homeLogoReel, ElementState.displayed);

        log.important("Clicking on 'Explore Insider' from the main navigation");
        WebElement exploreInsider = acquisition.acquireElementFromPage("exploreInsider", "LandingPage");
        interactions.clickElement(exploreInsider);

        log.important("Switching to the new tab");
        interactions.switchWindowByIndex(1);

        log.important("Clicking on exploreInsiderDemoButton on the ProductDemoPage");
        WebElement exploreInsiderDemoButton = acquisition.acquireElementFromPage("exploreInsiderDemoButton", "ProductDemoPage");
        interactions.verifyElementState(exploreInsiderDemoButton, ElementState.displayed);
        interactions.verifyUrlContains("https://useinsider.com/product-demo-hub/");

        interactions.clickElement(exploreInsiderDemoButton);

        log.important("Clicking on exploreInsiderLaunchButton on the ProductDemoPage");
        WebElement exploreInsiderLaunchButton = acquisition.acquireElementFromPage("exploreInsiderLaunchButton", "ProductDemoPage");
        interactions.verifyElementState(exploreInsiderDemoButton, ElementState.displayed);
        interactions.clickElement(exploreInsiderLaunchButton);

        log.important("Switching to the new tab");
        interactions.switchWindowByIndex(2);

        log.important("Clicking on letsGetStartedButton on the DemoHubAppPage");
        WebElement iframe;
        WebElement letsGetStartedButton;
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        letsGetStartedButton = acquisition.acquireElementFromPage("letsGetStartedButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, letsGetStartedButton);

        log.important("Clicking on gotItButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement gotItButton = acquisition.acquireElementFromPage("gotItButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, gotItButton);

        log.important("Clicking on greatButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement greatButton = acquisition.acquireElementFromPage("greatButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, greatButton);

        log.important("Clicking on letsProceedButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement letsProceedButton = acquisition.acquireElementFromPage("letsProceedButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, letsProceedButton);

        log.important("Clicking on doneButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement doneButton = acquisition.acquireElementFromPage("doneButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, doneButton);

        log.important("Clicking on interestingButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement interestingButton = acquisition.acquireElementFromPage("interestingButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, interestingButton);

        log.important("Clicking on userProfilesMenuItem on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement userProfilesMenuItem = acquisition.acquireElementFromPage("userProfilesMenuItem", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, userProfilesMenuItem);

        log.important("Clicking on superCoolButton on the DemoHubAppPage");
        WebElement superCoolButton;
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        superCoolButton = acquisition.acquireElementFromPage("superCoolButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, superCoolButton);

        log.important("Clicking on wowInterestingButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement wowInterestingButton = acquisition.acquireElementFromPage("wowInterestingButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, wowInterestingButton);

        log.important("Clicking on letsGoButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement letsGoButton = acquisition.acquireElementFromPage("letsGoButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, letsGoButton);

        log.important("Clicking on architectMenuItem on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement architectMenuItem = acquisition.acquireElementFromPage("architectMenuItem", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, architectMenuItem);

        log.important("Clicking on superCoolButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        superCoolButton = acquisition.acquireElementFromPage("superCoolButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, superCoolButton);

        log.important("Clicking on startJourneyCreatorCard on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement startJourneyCreatorCard = acquisition.acquireElementFromPage("startJourneyCreatorCard", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, startJourneyCreatorCard);

        log.important("Clicking on wowSuperCoolButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement wowSuperCoolButton = acquisition.acquireElementFromPage("wowSuperCoolButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, wowSuperCoolButton);

        log.important("Clicking on sendPromptButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement sendPromptButton = acquisition.acquireElementFromPage("sendPromptButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, sendPromptButton);

        log.important("Clicking on looksGreatButton on the DemoHubAppPage");
        WebElement looksGreatButton;
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        looksGreatButton = acquisition.acquireElementFromPage("looksGreatButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, looksGreatButton);

        log.important("Clicking on showReasonsButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement showReasonsButton = acquisition.acquireElementFromPage("showReasonsButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, showReasonsButton);

        log.important("Clicking on letsGetStartedButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        letsGetStartedButton = acquisition.acquireElementFromPage("letsGetStartedButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, letsGetStartedButton);

        log.important("Clicking on letsStartButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement letsStartButton = acquisition.acquireElementFromPage("letsStartButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, letsStartButton);

        log.important("Clicking on onEventBox on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement onEventSignUpBox = acquisition.acquireElementFromPage("onEventSignUpBox", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, onEventSignUpBox);

        log.important("Clicking on smartSegmentCreatorButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement smartSegmentCreatorButton = acquisition.acquireElementFromPage("smartSegmentCreatorButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, smartSegmentCreatorButton);

        log.important("Clicking on createTheSegmentButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement createTheSegmentButton = acquisition.acquireElementFromPage("createTheSegmentButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, createTheSegmentButton);

        log.important("Clicking on applySegmentationButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement applySegmentationButton = acquisition.acquireElementFromPage("applySegmentationButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, applySegmentationButton);

        log.important("Clicking on wonderfulButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement wonderfulButton = acquisition.acquireElementFromPage("wonderfulButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, wonderfulButton);

        log.important("Clicking on thatsInterestingButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement thatsInterestingButton = acquisition.acquireElementFromPage("thatsInterestingButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, thatsInterestingButton);

        log.important("Clicking on emailBox on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement emailBox = acquisition.acquireElementFromPage("emailBox", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, emailBox);

        log.important("Clicking on fabulousButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement fabulousButton = acquisition.acquireElementFromPage("fabulousButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, fabulousButton);

        log.important("Clicking on letsContinueButton on the DemoHubAppPage");
        WebElement letsContinueButton;
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        letsContinueButton = acquisition.acquireElementFromPage("letsContinueButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, letsContinueButton);

        log.important("Clicking on letsDoIt on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement letsDoIt = acquisition.acquireElementFromPage("letsDoIt", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, letsDoIt);

        log.important("Clicking on looksGreatButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        looksGreatButton = acquisition.acquireElementFromPage("looksGreatButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, looksGreatButton);

        log.important("Clicking on interesting100Button on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement interesting100Button = acquisition.acquireElementFromPage("interesting100Button", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, interesting100Button);

        log.important("Clicking on smsBox on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement smsBox = acquisition.acquireElementFromPage("smsBox", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, smsBox);

        log.important("Clicking on generateSmsContentButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement generateSmsContentButton = acquisition.acquireElementFromPage("generateSmsContentButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, generateSmsContentButton);

        log.important("Clicking on thatsWonderfulButton on the DemoHubAppPage");
        WebElement thatsWonderfulButton;
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        thatsWonderfulButton = acquisition.acquireElementFromPage("thatsWonderfulButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, thatsWonderfulButton);

        log.important("Clicking on generateCopyButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement generateCopyButton = acquisition.acquireElementFromPage("generateCopyButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, generateCopyButton);

        log.important("Clicking on letsPickOneButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement letsPickOneButton = acquisition.acquireElementFromPage("letsPickOneButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, letsPickOneButton);

        log.important("Clicking on letsContinueButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        letsContinueButton = acquisition.acquireElementFromPage("letsContinueButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, letsContinueButton);

        log.important("Clicking on enableSendTimeOptimizationCheckMark on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement enableSendTimeOptimizationCheckMark = acquisition.acquireElementFromPage("enableSendTimeOptimizationCheckMark", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, enableSendTimeOptimizationCheckMark);

        log.important("Clicking on thatsWonderfulButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        thatsWonderfulButton = acquisition.acquireElementFromPage("thatsWonderfulButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, thatsWonderfulButton);

        log.important("Clicking on perfectButton on the DemoHubAppPage");
        WebElement perfectButton;
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        perfectButton = acquisition.acquireElementFromPage("perfectButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, perfectButton);

        log.important("Clicking on excellentButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement excellentButton = acquisition.acquireElementFromPage("excellentButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, excellentButton);

        log.important("Clicking on perfectButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        perfectButton = acquisition.acquireElementFromPage("perfectButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, perfectButton);

        log.important("Clicking on switchTheChannelButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement switchTheChannelButton = acquisition.acquireElementFromPage("switchTheChannelButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, switchTheChannelButton);

        log.important("Clicking on appPushMenuItem on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement appPushMenuItem = acquisition.acquireElementFromPage("appPushMenuItem", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, appPushMenuItem);

        log.important("Clicking on awesomeButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement awesomeButton = acquisition.acquireElementFromPage("awesomeButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, awesomeButton);

        log.important("Clicking on generateImageWithAIButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement generateImageWithAIButton = acquisition.acquireElementFromPage("generateImageWithAIButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, generateImageWithAIButton);

        log.important("Clicking on perfectButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        perfectButton = acquisition.acquireElementFromPage("perfectButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, perfectButton);

        log.important("Clicking on suggestImage on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement suggestImage = acquisition.acquireElementFromPage("suggestImage", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, suggestImage);

        log.important("Clicking on addDynamicContentOption on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement addDynamicContentOption = acquisition.acquireElementFromPage("addDynamicContentOption", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, addDynamicContentOption);

        log.important("Clicking on couponMenuItem on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement couponMenuItem = acquisition.acquireElementFromPage("couponMenuItem", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, couponMenuItem);

        log.important("Clicking on saveButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement saveButton = acquisition.acquireElementFromPage("saveButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, saveButton);

        log.important("Clicking on goToLaunchButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement goToLaunchButton = acquisition.acquireElementFromPage("goToLaunchButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, goToLaunchButton);

        log.important("Clicking on fantasticButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        WebElement fantasticButton = acquisition.acquireElementFromPage("fantasticButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, fantasticButton);

        log.important("Clicking on superCoolButton on the DemoHubAppPage");
        iframe = acquisition.acquireElementFromPage("iframe", "DemoHubAppPage");
        superCoolButton = acquisition.acquireElementFromPage("superCoolButton", "DemoHubAppPage");
        interactions.clickIframeButton(iframe, superCoolButton);
    }

}

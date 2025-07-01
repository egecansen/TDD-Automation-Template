package pages;

import common.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoHubAppPage extends PageObject {

    @FindBy(css = "#main--frame:first-of-type")
    public WebElement iframe;

    @FindBy(css = "[aria-label=\"Let's get started \uD83D\uDE80\"]")
    public WebElement letsGetStartedButton;

    @FindBy(css = "[aria-label=\"Got it \uD83D\uDC4D\"]")
    public WebElement gotItButton;

    @FindBy(css = "[aria-label=\"Great \uD83D\uDC4D\"]")
    public WebElement greatButton;

    @FindBy(css = "[aria-label=\"Let's proceed ✅\"]")
    public WebElement letsProceedButton;

    @FindBy(css = "[aria-label=\"Done \uD83D\uDC4D\"]")
    public WebElement doneButton;

    @FindBy(css = "[aria-label=\"Interesting!\"]")
    public WebElement interestingButton;

    @FindBy(css = "[data-nv-id=\"915\"]")
    public WebElement userProfilesMenuItem;

    @FindBy(css = "[aria-label=\"Super cool! \uD83D\uDC4C\"]")
    public WebElement superCoolButton;

    @FindBy(css = "[aria-label=\"Wow, interesting! \uD83D\uDC4C\"]")
    public WebElement wowInterestingButton;

    @FindBy(css = "[aria-label=\"Let's go \uD83D\uDE80\"]")
    public WebElement letsGoButton;

    @FindBy(css = "[data-nv-id=\"895\"]")
    public WebElement architectMenuItem;

    @FindBy(css = "[data-nv-id=\"633\"]")
    public WebElement startJourneyCreatorCard;

    @FindBy(css = "[aria-label=\"Wow, super cool! \uD83D\uDE0E\"]")
    public WebElement wowSuperCoolButton;

    @FindBy(css = "[data-nv-id=\"130\"]")
    public WebElement sendPromptButton;

    @FindBy(css = "[aria-label=\"Looks great \uD83D\uDE0A\"]")
    public WebElement looksGreatButton;

    @FindBy(css = "[data-nv-id=\"154\"]")
    public WebElement showReasonsButton;

    @FindBy(css = "[aria-label=\"Let's start \uD83D\uDE80\"]")
    public WebElement letsStartButton;

    @FindBy(css = "[data-nv-id=\"73\"]")
    public WebElement onEventSignUpBox;

    @FindBy(css = "[data-nv-id=\"203\"]")
    public WebElement smartSegmentCreatorButton;

    @FindBy(css = "[aria-label=\"Create the segment ➡\uFE0F\"]")
    public WebElement createTheSegmentButton;

    @FindBy(css = "[data-nv-id=\"350\"]")
    public WebElement applySegmentationButton;

    @FindBy(css = "[aria-label=\"Wonderful \uD83D\uDE04\"]")
    public WebElement wonderfulButton;

    @FindBy(css = "[aria-label=\"That's interesting! \uD83D\uDE04\"]")
    public WebElement thatsInterestingButton;

    @FindBy(css = "[data-nv-id=\"165\"]")
    public WebElement emailBox;

    @FindBy(css = "[aria-label=\"Fabulous! ❤\uFE0F\"]")
    public WebElement fabulousButton;

    @FindBy(css = "[aria-label=\"Let's continue ✅\"]")
    public WebElement letsContinueButton;

    @FindBy(css = "[aria-label=\"Let's do it ✅\"]")
    public WebElement letsDoIt;

    @FindBy(css = "[aria-label=\"Interesting! \uD83D\uDCAF\"]")
    public WebElement interesting100Button;

    @FindBy(css = "[data-nv-id=\"262\"]")
    public WebElement smsBox;

    @FindBy(css = "[data-nv-id=\"216\"]")
    public WebElement generateSmsContentButton;

    @FindBy(css = "[aria-label=\"That's wonderful! \uD83D\uDE4C\"]")
    public WebElement thatsWonderfulButton;

    @FindBy(css = "[data-nv-id=\"1549\"]")
    public WebElement generateCopyButton;

    @FindBy(css = "[aria-label=\"Let's pick one  ✅\"]")
    public WebElement letsPickOneButton;

    @FindBy(css = "[data-nv-id=\"1188\"]")
    public WebElement enableSendTimeOptimizationCheckMark;

    @FindBy(css = "[aria-label=\"Perfect! \uD83D\uDC4C\"]")
    public WebElement perfectButton;

    @FindBy(css = "[aria-label=\"Excellent! \uD83E\uDD29\"]")
    public WebElement excellentButton;

    @FindBy(css = "[data-nv-id=\"926\"]")
    public WebElement switchTheChannelButton;

    @FindBy(css = "[data-nv-id=\"1093\"]")
    public WebElement appPushMenuItem;

    @FindBy(css = "[aria-label=\"Awesome! \uD83E\uDD29\"]")
    public WebElement awesomeButton;

    @FindBy(css = "[data-nv-id=\"483\"]")
    public WebElement generateImageWithAIButton;

    @FindBy(css = "[data-nv-id=\"1943\"]")
    public WebElement suggestImage;

    @FindBy(css = "[data-nv-id=\"445\"]")
    public WebElement addDynamicContentOption;

    @FindBy(css = "[data-nv-id=\"1643\"]")
    public WebElement couponMenuItem;

    @FindBy(css = "[data-nv-id=\"1532\"]")
    public WebElement saveButton;

    @FindBy(css = "[data-nv-id=\"949\"]")
    public WebElement goToLaunchButton;

    @FindBy(css = "[aria-label=\"Fantastic! \uD83D\uDE80\"]")
    public WebElement fantasticButton;

}

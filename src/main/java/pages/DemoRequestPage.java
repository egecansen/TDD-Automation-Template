package pages;

import common.BaseObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoRequestPage extends BaseObject {

    @FindBy(css = "[id='firstname-629d30a2-cfe0-4832-8a59-6867f1f0675b']")
    public WebElement firsName;

    @FindBy(css = "[id='lastname-629d30a2-cfe0-4832-8a59-6867f1f0675b']")
    public WebElement lastName;

    @FindBy(css = "[id='email-629d30a2-cfe0-4832-8a59-6867f1f0675b']")
    public WebElement email;

    @FindBy(css = "[id='industry_dropdown-629d30a2-cfe0-4832-8a59-6867f1f0675b']")
    public WebElement industry;

    @FindBy(css = "[id='jobtitle-629d30a2-cfe0-4832-8a59-6867f1f0675b']")
    public WebElement jobTitle;

    @FindBy(css = "[id='company-629d30a2-cfe0-4832-8a59-6867f1f0675b']")
    public WebElement companyName;

    @FindBy(css = "[id='phone-629d30a2-cfe0-4832-8a59-6867f1f0675b']")
    public WebElement phoneNumber;

    @FindBy(css = "[id='how_did_you_hear_about_us_-629d30a2-cfe0-4832-8a59-6867f1f0675b']")
    public WebElement howDidYouHearAboutUs;

    @FindBy(css = "[id='hsForm_629d30a2-cfe0-4832-8a59-6867f1f0675b'] [class='hs_submit hs-submit']")
    public WebElement submitButton;

}

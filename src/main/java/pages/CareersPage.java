package pages;

import common.BaseObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CareersPage extends BaseObject {

    @FindBy(css = "[id='career-our-location']")
    public WebElement locationsBlock;

    @FindBy(css = "[id='career-find-our-calling']")
    public WebElement teamsBlock;

    @FindBy(css = "[class='elementor-column elementor-col-100 elementor-top-column elementor-element elementor-element-87842ec']")
    public WebElement lifeAtInsiderBlock;

    @FindBy(css = "[id='career-find-our-calling'] [class*='btn btn-outline-secondary']")
    public WebElement seeAllTeamsButton;

    @FindBy(css = "[id='career-find-our-calling'] [class='job-item col-12 col-lg-4 mt-5']")
    public List<WebElement> teams;

}

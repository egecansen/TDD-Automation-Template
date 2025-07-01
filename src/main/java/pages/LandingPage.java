package pages;

import common.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class LandingPage extends PageObject {

    @FindBy(css = "[id='home_logo_reel'] [class='container-full-width']")
    public WebElement homeLogoReel;

    @FindBy(css = "[id='wt-cli-accept-all-btn']")
    public WebElement acceptCookieButton;

    @FindBy(css = "[id='navbarDropdownMenuLink']")
    public List<WebElement> mainNavigationItems;

    @FindBy(css = "[class*='dropdown-menu new-menu-dropdown-layout-6'] .dropdown-sub")
    public List<WebElement> companyMenuSubItems;

    @FindBy(css = "[class='nav-link nav-no-dropdown'][id='highlight-en']")
    public WebElement exploreInsider;

    @FindBy(css = "[class='navbar-nav ml-auto'] [class='nav-link btn btn-navy rounded text-nowrap']")
    public WebElement getADemoButton;

    public static void hoverOverNavigationItemByText(String targetMenuItem) {
        List<WebElement> mainNavigationItems = reflections.getElementsFromPage("mainNavigationItems", "LandingPage");
        for (WebElement item : mainNavigationItems) {
            if (item.getText().trim().equals(targetMenuItem)) {
                webInteractions.hoverOver(item);
                break;
            }
        }
    }

}

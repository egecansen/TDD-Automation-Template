package pages;

import common.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class LandingPage extends PageObject {

    @FindBy(css = "#home_logo_reel .container-full-width")
    public WebElement homeLogoReel;

    @FindBy(css = "[id='wt-cli-accept-all-btn']")
    public WebElement acceptCookieButton;

    @FindBy(css = "#navbarDropdownMenuLink")
    public List<WebElement> mainNavigationItems;

    @FindBy(css = "[class*=\"dropdown-menu new-menu-dropdown-layout-6\"] .dropdown-sub")
    public List<WebElement> companyMenuSubItems;

    public void hoverOverNavigationItemByText(String targetMenuItem) {
        List<WebElement> mainNavigationItems = reflections.getElementsFromPage("mainNavigationItems", "LandingPage");
        for (WebElement item : mainNavigationItems) {
            if (item.getText().trim().equals(targetMenuItem)) {
                hoverOver(item);
            }
        }
    }

}

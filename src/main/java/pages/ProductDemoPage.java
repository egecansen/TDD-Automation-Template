package pages;

import common.BaseObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDemoPage extends BaseObject {

    @FindBy(css = "[class='btnDemo'] a")
    public WebElement exploreInsiderDemoButton;

    @FindBy(css = "[id='hero-demo'] [class='listBtn btnLaunch']")
    public WebElement exploreInsiderLaunchButton;
}

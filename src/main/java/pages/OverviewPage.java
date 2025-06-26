package pages;

import common.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OverviewPage extends PageObject {

    @FindBy(css = ".summary_subtotal_label")
    public WebElement itemTotalAmount;

}

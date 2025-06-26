package pages;

import common.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends PageObject {

    @FindBy(id = "checkout")
    public WebElement checkOutButton;
    @FindBy(css = ".cart_quantity_label")
    public WebElement cartQuantityLabel;

}

package pages;

import common.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.components.MainNavigation;

public class ProductPage extends PageObject {

    @FindBy(css = "[data-test='inventory-item-name']")
    public WebElement productTitle;

    @FindBy(id = "add-to-cart")
    public WebElement addToCartButton;

    @FindBy(css = ".inventory_details_price")
    public WebElement productPrice;

    @FindBy(id = "back-to-products")
    public WebElement backToProductsButton;

    @FindBy(css = ".primary_header")
    public MainNavigation mainNavigation;

}

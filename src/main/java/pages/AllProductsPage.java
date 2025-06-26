package pages;

import common.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AllProductsPage extends PageObject {

    @FindBy(css = ".primary_header")
    public WebElement pageTitle;

    @FindBy(css = ".product_sort_container")
    public WebElement sortContainer;

    @FindBy(css = "[data-test='inventory-item-name']")
    public List<WebElement> productTitles;

    @FindBy(css = ".product_sort_container option")
    public List<WebElement> sortContainerOptions;


}

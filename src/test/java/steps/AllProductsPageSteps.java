package steps;

import context.ContextStore;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pages.AllProductsPage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AllProductsPageSteps {

    AllProductsPage allProductsPage = new AllProductsPage();

    @Given("Save the product names and verify they are in reversed alphabetical order")
    public void saveProductNames() {
        List<String> productNames = new ArrayList<>();
        for (WebElement productTitle : allProductsPage.productTitles)
            productNames.add(productTitle.getText());

        ContextStore.put("productNames", productNames);
        List<String> sorted = new ArrayList<>(productNames);
        sorted.sort(Comparator.reverseOrder());
        Assert.assertEquals("The order of the lists are not match!", productNames, sorted);
        allProductsPage.log.success("The product list is in an alphabetical order 2");
    }

}

package steps;

import context.ContextStore;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import pages.OverviewPage;

public class OverviewPageSteps {

    OverviewPage overviewPage = new OverviewPage();

    @Given("Validate the total amount")
    public void validateTheItemTotalAmount() {
        Double expectedAmount = Double.parseDouble(ContextStore.get("Sauce Labs Bike Light Price")) + Double.parseDouble(ContextStore.get("Test.allTheThings() T-Shirt (Red) Price"));
        Double actualAmount = Double.parseDouble(overviewPage.itemTotalAmount.getText().replaceAll("Item total: \\$", ""));
        Assert.assertEquals("Total amounts does not match!", expectedAmount, actualAmount);
        overviewPage.log.success("Total amounts match!");
    }

}

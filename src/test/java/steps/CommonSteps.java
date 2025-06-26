package steps;
import collections.Bundle;
import com.github.webdriverextensions.WebComponent;
import common.ObjectRepository;
import context.ContextStore;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import pickleib.driver.DriverFactory;
import pickleib.enums.Direction;
import pickleib.enums.ElementState;
import pickleib.enums.Navigation;
import pickleib.exceptions.PickleibVerificationException;
import pickleib.utilities.interfaces.PolymorphicUtilities;
import pickleib.utilities.steps.PageObjectStepUtilities;
import pickleib.web.interactions.WebInteractions;
import java.util.ArrayList;
import java.util.List;
import static utils.StringUtilities.Color.*;
import static utils.StringUtilities.*;

public class CommonSteps extends PageObjectStepUtilities<ObjectRepository> {

    WebInteractions webInteractions;

    public CommonSteps() {
        super(ObjectRepository.class);
        webInteractions = new WebInteractions();
    }

    /**
     * Navigates to the specified URL.
     *
     * @param url The URL to navigate to.
     */
    @Given("Navigate to url: {}")
    public void getUrl(String url) {
        webInteractions.getUrl(url);
    }

    /**
     * Switches to the next tab and stores the parent tab handle.
     */
    @Given("Switch to the next tab")
    public void switchTab() {
        String parentHandle = webInteractions.switchWindowByHandle(null);
        ContextStore.put("parentHandle", parentHandle);
    }

    /**
     * Switches back to the parent tab using the stored parent tab handle.
     */
    @Given("Switch back to the parent tab")
    public void switchToParentTab() {
        webInteractions.switchWindowByHandle(ContextStore.get("parentHandle").toString());
    }

    /**
     * Switches to the tab with the specified handle and stores the parent tab handle.
     *
     * @param handle The handle of the tab to switch to.
     */
    @Given("Switch to the tab with handle: {}")
    public void switchTab(String handle) {
        handle = contextCheck(handle);
        String parentHandle = webInteractions.switchWindowByHandle(handle);
        ContextStore.put("parentHandle", parentHandle);
    }

    /**
     * Switches to the specified tab by index and stores the parent tab handle.
     *
     * @param handle The index of the tab to switch to.
     */
    @Given("Switch to the tab number {}")
    public void switchTab(Integer handle) {
        String parentHandle = webInteractions.switchWindowByIndex(handle);
        ContextStore.put("parentHandle", parentHandle);
    }

    /**
     * Save current url to context.
     */
    @Given("Save current url to context")
    public void saveCurrentUrl() {
        String currentUrl = webInteractions.driver.getCurrentUrl();
        ContextStore.put("currentUrl", currentUrl);
        log.info("Current URL is saved to context " + currentUrl);
    }

    /**
     * Adds the specified cookies to the browser.
     *
     * @param cookieTable a DataTable containing the cookies to add
     */
    @Given("Add the following cookies:")
    public void addCookies(DataTable cookieTable) {
        webInteractions.addCookies(cookieTable.asMap());
    }

    /**
     * Updates the specified cookie to the browser.
     *
     * @param cookieName  Target cookie to be updated
     * @param cookieValue New cookie value
     */
    @Given("Update value to {} for cookie named {}")
    public void updateCookie(String cookieValue, String cookieName) {
        webInteractions.updateCookies(cookieValue, cookieName);
    }

    /**
     * Refreshes the current page.
     */
    @Given("Refresh the page")
    public void refresh() {
        webInteractions.refresh();
    }

    /**
     * Deletes all cookies from the browser.
     */
    @Given("Delete cookies")
    public void deleteCookies() {
        webInteractions.driver.manage().deleteAllCookies();
    }

    /**
     * Navigates the browser either forwards or backwards.
     *
     * @param direction the direction to navigate in, either "BACKWARDS" or "FORWARDS"
     */
    @Given("^Navigate browser (BACKWARDS|FORWARDS)$")
    public void browserNavigate(Navigation direction) {
        webInteractions.navigateBrowser(direction);
    }


    /**
     * Clicks the specified button on the page.
     *
     * @param buttonName the name of the button to click
     * @param pageName   the name of the page containing the button
     */
    @Given("Click the {} on the {}")
    public void click(String buttonName, String pageName) {
        WebElement element = objectRepository.acquireElementFromPage(buttonName, pageName);
        webInteractions.clickElement(element, buttonName, pageName);
    }

    /**
     * Clicks on the specified element from a list on the page.
     *
     * @param elementName the name of the element to click on from the list
     * @param listName    the name of the list containing the element
     * @param pageName    the name of the page containing the list
     * @throws WebDriverException if the element cannot be found or clicked
     */
    @Given("Click listed element {} from {} list on the {}")
    public void clickListedButton(String elementName, String listName, String pageName) {
        List<WebElement> elements = pageObjectReflections.getElementsFromPage(listName, pageName);
        WebElement element = getInteractions(elements.get(0)).scrollInList(elementName, elements);
        webInteractions.clickElement(element, elementName, pageName);
    }

    /**
     * Clicks the button with the specified CSS locator.
     *
     * @param text the CSS locator of the button to click
     */
    @Given("Click button with {} css locator")
    public void clickWithLocator(String text) {
        webInteractions.clickByCssSelector(text);
    }

    /**
     * Waits for the specified duration in seconds.
     *
     * @param duration   the duration to wait in seconds
     */
    @Given("^Wait (\\d+) seconds$")
    public void wait(double duration) {
        PolymorphicUtilities.waitFor(duration);
    }

    /**
     * Scrolls in the specified direction.
     *
     * @param direction  The direction to scroll in, either "up" or "down".
     */
    @Given("Scroll (up|down)")
    public void scrollTo(Direction direction) {
        webInteractions.scrollInDirection(direction);
    }


    /**
     * Acquires the specified attribute value from the specified element on the page.
     *
     * @param attributeName the name of the attribute to acquire
     * @param elementName   the name of the element to acquire the attribute value from
     * @param pageName      the name of the page containing the element
     */
    @Given("Acquire the {} attribute of {} on the {}")
    public void getAttributeValue(String attributeName, String elementName, String pageName) {
        WebElement element = objectRepository.acquireElementFromPage(elementName, pageName);
        webInteractions.saveAttributeValue(element, attributeName, elementName, pageName);
    }

    /**
     * Centers the specified element on the page in the viewport.
     *
     * @param elementName the name of the element to center
     * @param pageName    the name of the page containing the element
     */
    @Given("Center the {} on the {}")
    public void center(String elementName, String pageName) {
        WebElement element = objectRepository.acquireElementFromPage(elementName, pageName);
        webInteractions.centerElement(element, elementName, pageName);
    }

    /**
     * Centers the specified element on the page in the viewport.
     *
     * @param elementName the name of the element to center
     * @param elementListName the name of the element list that includes the element
     * @param pageName    the name of the page containing the element
     */
    @Given("Center element named {} on the {} from {}")
    public void centerListedElement(String elementName, String elementListName, String pageName) {
        elementName = contextCheck(elementName);
        WebElement element = objectRepository.acquireListedElementFromPage(elementName, elementListName, pageName);
        webInteractions.centerElement(element, elementName, pageName);
    }

    /**
     * Clicks the specified element of a component on the page.
     *
     * @param elementName   the name of the element to click
     * @param componentName the name of the component containing the element
     * @param pageName      the name of the page containing the component
     */
    @Given("Click component element {} of {} component on the {}")
    public void componentClick(String elementName, String componentName, String pageName) {
        WebElement element = objectRepository.acquireElementFromComponent(elementName, componentName, pageName);
        webInteractions.clickElement(element, elementName, pageName, true);
    }

    /**
     * Centers the specified element of a component on the page in the viewport.
     *
     * @param elementName   the name of the element to center
     * @param componentName the name of the component containing the element
     * @param pageName      the name of the page containing the component
     */
    @Given("Center component element {} of {} component on the {}")
    public void center(String elementName, String componentName, String pageName) {
        WebElement element = objectRepository.acquireElementFromComponent(elementName, componentName, pageName);
        webInteractions.centerElement(element, elementName, pageName);
    }

    /**
     * Performs a JS click on the specified element of a component on the page.
     *
     * @param elementName the name of the element to perform the JS click on
     * @param pageName    the name of the page containing the component
     */
    @Given("Perform a JS click on element named {} on the {}")
    public void performJSClick(String elementName, String pageName) {
        WebElement element = objectRepository.acquireElementFromPage(elementName, pageName);
        webInteractions.centerElement(element, elementName, pageName);
        webInteractions.performJSClick(element, elementName, pageName);
    }

    /**
     * Performs a JS click on the specified element of a component on the page.
     *
     * @param elementName the name of the element to perform the JS click on
     * @param listName    The name of the list that contains the element.
     * @param pageName    the name of the page containing the component
     */
    @Given("Perform JS click on element named {} on the {} list from {}")
    public void performListedJSClick(String elementName, String listName, String pageName) {
        WebElement element = objectRepository.acquireListedElementFromPage(elementName, listName, pageName);
        webInteractions.centerElement(element, elementName, pageName);
        webInteractions.performJSClick(element, elementName, pageName);
    }

    /**
     * This method performs a JavaScript click on the given component element on the given page.
     *
     * @param elementName   The name of the component element to perform the click.
     * @param componentName The name of the component that contains the element.
     * @param pageName      The name of the page on which the component is located.
     */
    @Given("Perform a JS click on component element {} of {} component on the {}")
    public void componentPerformJSClick(String elementName, String componentName, String pageName) {
        WebElement element = objectRepository.acquireElementFromComponent(elementName, componentName, pageName);
        webInteractions.centerElement(element, elementName, pageName);
        webInteractions.performJSClick(element, elementName, pageName);
    }

    /**
     * Scrolls through a list until an element containing a given text is found
     *
     * @param listName    the name of the list containing the element
     * @param pageName    the name of the page containing the list and component
     * @param elementText the name of the component element to click on from the list
     */
    @Given("Scroll in {} container and click {} element from {} list on {}")
    public void scrollContainerElements(String containerName, String elementText, String listName, String pageName) {
        containerName = firstLetterDeCapped(containerName);
        listName = firstLetterDeCapped(listName);
        pageName = firstLetterDeCapped(pageName);
        elementText = contextCheck(elementText);
        List<WebElement> elements = pageObjectReflections.getElementsFromPage(listName, pageName);
        WebElement container = pageObjectReflections.getElementFromPage(containerName, pageName);
        log.info("Scrolling elements...");
        WebElement targetElement = webInteractions.scrollInContainer(container, elements, elementText);
        webInteractions.clickElement(targetElement);
    }

    /**
     * Scrolls through a list of elements until an element containing a given text is found
     *
     * @param listName    the name of the list containing the element
     * @param pageName    the name of the page containing the list and component
     * @param elementText the name of the component element to click on from the list
     */
    @Given("Scroll through {} list on the {} and acquire {}")
    public void scrollContainerElements(String listName, String pageName, String elementText) {
        listName = firstLetterDeCapped(listName);
        pageName = firstLetterDeCapped(pageName);
        elementText = contextCheck(elementText);
        List<WebElement> elements = pageObjectReflections.getElementsFromPage(
                listName,
                pageName
        );
        log.info("Scrolling elements...");
        webInteractions.scrollInList(elementText, elements);
        log.info("Element named " + markup(BLUE, elementText) + " is acquired");
    }

    /**
     * Fills in a form on the page with the given input values.
     *
     * @param pageName the name of the page containing the form
     * @param table    a DataTable containing the input values and corresponding element names
     * @throws WebDriverException if any input fields cannot be found or filled
     */
    @Given("Fill form input on the {}")
    public void fillForm(String pageName, DataTable table) {
        List<Bundle<WebElement, String, String>> inputBundles = objectRepository.acquireElementList(table.asMaps(), pageName);
        webInteractions.fillForm(inputBundles, pageName);
    }

    /**
     * Verifies that the text of the specified element on the specified page matches the expected text.
     *
     * @param elementName  the name of the element whose text is to be verified
     * @param pageName     the name of the page containing the element
     * @param expectedText the expected text of the element
     */
    @Given("Verify the text of {} on the {} to be: {}")
    public void verifyText(String elementName, String pageName, String expectedText) {
        expectedText = contextCheck(expectedText);
        WebElement element = objectRepository.acquireElementFromPage(elementName, pageName);
        webInteractions.centerElement(element, elementName, pageName);
        pageName = firstLetterDeCapped(pageName);
        webInteractions.verifyText(element, elementName, pageName, expectedText);
    }

    /**
     * Verifies that the text of the specified element on the specified page contains the expected text.
     *
     * @param elementName  the name of the element whose text is to be verified
     * @param pageName     the name of the page containing the element
     * @param expectedText the expected text of the element
     */
    @Given("Verify the text of {} on the {} contains: {}")
    public void verifyContainsText(String elementName, String pageName, String expectedText) {
        WebElement element = objectRepository.acquireElementFromPage(elementName, pageName);
        webInteractions.centerElement(element, elementName, pageName);
        pageName = firstLetterDeCapped(pageName);
        webInteractions.verifyContainsText(element,elementName,pageName,expectedText);
    }

    /**
     * Verifies that the text of the specified element in the specified component on the specified page matches the expected text.
     *
     * @param elementName   the name of the element whose text is to be verified
     * @param componentName the name of the component containing the element
     * @param pageName      the name of the page containing the component
     * @param expectedText  the expected text of the element
     */
    @Given("Verify text of the component element {} of {} on the {} to be: {}")
    public void componentVerifyText(String elementName, String componentName, String pageName, String expectedText) {
        WebElement element = objectRepository.acquireElementFromComponent(elementName, componentName, pageName);
        webInteractions.waitUntilVisible(element, elementName, pageName);
        webInteractions.centerElement(element, elementName, pageName);
        webInteractions.verifyElementText(element, expectedText);
    }

    /**
     * Verifies that the text contained within the specified component element on the specified page matches the expected text.
     *
     * @param elementName   The name or identifier of the component element to verify.
     * @param componentName The name or identifier of the component containing the element.
     * @param pageName      The name or identifier of the page where the component element is located.
     * @param expectedText  The expected text that the component element should contain.
     */
    @Given("Verify text of the component element {} of {} on the {} contains: {}")
    public void verifyComponentElementContainsText(String elementName, String componentName, String pageName, String expectedText) {
        WebElement element = objectRepository.acquireElementFromComponent(elementName, componentName, pageName);
        webInteractions.waitUntilVisible(element, elementName, pageName);
        webInteractions.centerElement(element, elementName, pageName);
        webInteractions.verifyElementContainsText(element, expectedText);
    }

    /**
     * Verifies that the specified element is present on the specified page.
     *
     * @param elementName the name of the element to be verified
     * @param pageName    the name of the page containing the element
     */
    @Given("Verify presence of element {} on the {}")
    public void verifyPresence(String elementName, String pageName) {
        WebElement element = objectRepository.acquireElementFromPage(elementName, pageName);
        webInteractions.verifyElementState(element, elementName, pageName, ElementState.displayed);
    }

    /**
     * Verifies that the specified element in the specified component is present on the specified page.
     *
     * @param elementName   the name of the element to be verified
     * @param componentName the name of the component containing the element
     * @param pageName      the name of the page containing the component
     */
    @Given("Verify presence of the component element {} of {} on the {}")
    public void componentVerifyPresence(String elementName, String componentName, String pageName) {
        WebElement element = objectRepository.acquireElementFromComponent(elementName, componentName, pageName);
        webInteractions.verifyElementState(element, elementName, pageName, ElementState.displayed);
    }

    /**
     * This method verifies if the given element on the given page is in the expected state.
     *
     * @param elementName   The name of the element to be verified.
     * @param pageName      The name of the page on which the element is located.
     * @param expectedState The expected state of the element.
     */
    @Given("Verify that element {} on the {} is in {} state")
    public void verifyState(String elementName, String pageName, ElementState expectedState) {
        WebElement element = objectRepository.acquireElementFromPage(elementName, pageName);
        webInteractions.verifyElementState(element, elementName, pageName, expectedState);
    }

    /**
     * This method verifies if the given component element on the given page is in the expected state.
     *
     * @param elementName   The name of the component element to be verified.
     * @param componentName The name of the component that contains the element.
     * @param pageName      The name of the page on which the component is located.
     * @param expectedState The expected state of the element.
     */

    @Given("Verify that component element {} of {} on the {} is in {} state")
    public void verifyState(String elementName, String componentName, String pageName, ElementState expectedState) {
        WebElement element = objectRepository.acquireElementFromComponent(elementName, componentName, pageName);
        webInteractions.verifyElementState(element, elementName, pageName, expectedState);
    }

    /**
     * This method waits until the given element is absent from the given page.
     *
     * @param elementName The name of the element to wait for absence.
     * @param pageName    The name of the page on which the element is located.
     */
    @Given("Wait for absence of element {} on the {}")
    public void waitUntilAbsence(String elementName, String pageName, DriverFactory.DriverType driverType) {
        WebElement element = objectRepository.acquireElementFromPage(elementName, pageName);
        webInteractions.waitUntilAbsence(element, elementName, pageName);
    }

    /**
     * Waits until the specified component element is absent from the specified page.
     *
     * @param elementName   The name or identifier of the component element to wait for its absence.
     * @param componentName The name or identifier of the component containing the element.
     * @param pageName      The name or identifier of the page where the component element is located.
     */
    @Given("Wait for absence of component element {} of {} on the {}")
    public void componentWaitUntilAbsence(String elementName, String componentName, String pageName) {
        WebElement element = objectRepository.acquireElementFromComponent(elementName, componentName, pageName);
        webInteractions.waitUntilAbsence(element, elementName, pageName);
    }

    /**
     * Wait for an element on a specific page to be visible.
     *
     * @param elementName the name of the element to be verified
     * @param pageName    the name of the page containing the element
     */
    @Given("Wait for element {} on the {} to be visible")
    public void waitUntilVisible(String elementName, String pageName) {
        WebElement element = objectRepository.acquireElementFromPage(elementName, pageName);
        webInteractions.waitUntilVisible(element, elementName, pageName);
    }

    /**
     * Wait for a component element on a specific page to be visible.
     *
     * @param elementName   the name of the element to be verified
     * @param componentName the name of the component containing the element
     * @param pageName      the name of the page containing the component
     */
    @Given("Wait for component element {} of {} on the {} to be visible")
    public void componentWaitUntilVisible(String elementName, String componentName, String pageName) {
        WebElement element = objectRepository.acquireElementFromComponent(elementName, componentName, pageName);
        webInteractions.waitUntilVisible(element, elementName, pageName);
    }

    /**
     * Waits until all elements with the specified name are visible within all components in the specified component list on the specified page.
     *
     * @param elementName       The name or identifier of the element to wait for its visibility within components.
     * @param componentListName The name or identifier of the component list containing the components.
     * @param pageName          The name or identifier of the page where the component list is located.
     */
    @Given("Wait for listed component element {} of {} on the {} to be visible")
    public void componentListWaitUntilVisible(String elementName, String componentListName, String pageName) {
        pageName = firstLetterDeCapped(pageName);
        componentListName = firstLetterDeCapped(componentListName);
        elementName = contextCheck(elementName);
        List<WebComponent> componentList = pageObjectReflections.getComponentsFromPage(componentListName, pageName);
        List<WebElement> webElements = new ArrayList<>();
        for (WebComponent component : componentList) {
            WebElement element = pageObjectReflections.getElementFromComponent(elementName, component);
            webElements.add(element);
        }
        String finalElementName = elementName;
        String finalPageName = pageName;
        webElements.forEach(webElement ->
                webInteractions.waitUntilVisible(webElement, finalElementName, finalPageName));
    }


    /**
     * Verifies if an element contains a specified text.
     *
     * @param elementName  the name of the element to be verified.
     * @param elementName  the name of the element to be verified.
     * @param pageName     the name of the page containing the list.
     * @param expectedText the expected text to be verified in the element.
     */
    @Given("Verify text of (.+?(?:\\s+.+?)*) element on the (.+?(?:\\s+.+?)*) is contains (.+?(?:\\s+.+?)*)$")
    public void verifyElementContainsText(
            String elementName,
            String pageName,
            String expectedText) {
        WebElement element = objectRepository.acquireElementFromPage(elementName, pageName);
        webInteractions.centerElement(element, elementName, pageName);
        webInteractions.verifyContainsText(element, elementName, pageName, expectedText);
    }

    /**
     * Verifies if the current URL contains a specified text.
     *
     * @param text the text to be verified in the current URL.
     */
    @Given("Verify the page is redirecting to the page {}")
    @Given("Verify the url contains with the text {}")
    public void verifyTextUrl(String text) {
        webInteractions.verifyUrlContains(text);
        log.success("The url contains '" + text + "'");
    }

    /**
     * Verifies that the actual value matches the expected value.
     *
     * <p>
     * This method compares the expected value with the actual value and asserts their equality.
     * If the values do not match, an AssertionError is thrown.
     * </p>
     *
     * @param expectedValue The expected value to be compared with the actual value.
     * @param actualValue   The actual value to be compared with the expected value.
     */
    @Given("Assert that value of {} is equal to {}")
    public void verifyText(String expectedValue, String actualValue) {
        log.info("Checking values...");
        expectedValue = contextCheck(expectedValue).replaceAll(",", "");
        actualValue = contextCheck(actualValue).replaceAll(",", "");
        Assert.assertEquals("Values not match!", expectedValue, actualValue);
        log.success("Values verified as: " + actualValue);
    }

    /**
     * Verifies that the actual value matches the expected value.
     *
     * <p>
     * This method compares the expected value with the actual value and asserts their equality.
     * If the values do not match, an AssertionError is thrown.
     * </p>
     *
     * @param expectedValue The expected value to be compared with the actual value.
     * @param actualValue   The actual value to be compared with the expected value.
     */
    @Given("Assert that value of {} is contains {}")
    public void assertContains(String expectedValue, String actualValue) {
        log.info("Checking values...");
        expectedValue = contextCheck(expectedValue).replaceAll(",", "");
        actualValue = contextCheck(actualValue).replaceAll(",", "");
        if (actualValue.contains(expectedValue)) log.success("Values verified as: " + actualValue);
        else throw new PickleibVerificationException("'" + actualValue + "' not contains '" + expectedValue + "'");
    }

    @Given("Assert that value of {} is not equal to {}")
    public void verifyNoText(String expectedValue, String actualValue) {
        log.info("Checking values...");
        expectedValue = contextCheck(expectedValue).replaceAll(",", "");
        actualValue = contextCheck(actualValue).replaceAll(",", "");
        Assert.assertNotEquals("Values should not match!", expectedValue, actualValue);
        log.success("Values verified as: " + actualValue);
    }

    @Given("Assert the value of {} attribute for {} element on {} is equal to {}")
    public void assertAttribute(String attributeName, String elementName, String pageName, String actualValue) {
        log.info("Acquiring the" + attributeName + " value...");
        WebElement element = objectRepository.acquireElementFromPage(elementName, pageName);
        String value = element.getAttribute(attributeName);
        Assert.assertEquals("Values not match!", value, actualValue);
        log.success("Values verified as: " + actualValue);
    }

    @Given("Assert the value of {} attribute for {} element from {} component on {} is equal to {}")
    public void componentAssertAttribute(String attributeName, String elementName, String componentName, String pageName, String actualValue) {
        log.info("Acquiring the" + attributeName + " value...");
        WebElement element = objectRepository.acquireElementFromComponent(elementName, componentName, pageName);
        String expectedValue = element.getAttribute(attributeName);
        actualValue = contextCheck(actualValue);
        Assert.assertEquals("Values not match!", expectedValue, actualValue);
        log.success("Values verified as: " + actualValue);
    }

    /**
     * Updates the context with a new key-value pair.
     *
     * @param key   the key for the new context data.
     * @param value the value for the new context data.
     */
    @Given("Update context {} -> {}")
    public void updateContext(String key, String value) {
        log.info("Updating context " + markup(BLUE, key) + " to " + markup(BLUE, value));
        ContextStore.put(key, contextCheck(value));
    }

    /**
     * Given the text of a web element inside a WebComponent,
     * this method selects the component and then clicks on a specified element within it.
     *
     * @param elementFieldName       The field name of the desired element in the WebComponent.
     * @param elementText            The text that the desired element should contain.
     * @param componentListName      The name of the list where the component can be found.
     * @param pageName               The name of the page where the component is located.
     * @param targetElementFieldName The field name of the element within the component that should be clicked.
     *                               <p>
     *                               The function first retrieves the WebComponent from the specified list on the given page,
     *                               based on the provided field name and text. Then it identifies the target element within the component using
     *                               the provided targetElementFieldName. Finally, it performs a click interaction on the identified target element.
     *                               <p>
     *                               The method assumes that the necessary components and elements exist and are accessible.
     */
    @Given("Select component by {} named {} from {} component list on the {} and click the {} element")
    public void selectComponentByText(
            String elementFieldName,
            String elementText,
            String componentListName,
            String pageName,
            String targetElementFieldName) {
        elementText = contextCheck(elementText);
        WebComponent component = objectRepository.acquireExactNamedListedComponent(
                elementFieldName,
                elementText,
                componentListName,
                pageName
        );
        WebElement button = pageObjectReflections.getElementFromComponent(targetElementFieldName, component);
        webInteractions.clickElement(button, targetElementFieldName, pageName);
    }

}

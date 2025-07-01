package common;

import context.ContextStore;
import driver.Driver;
import ollama.Ollama;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import pickleib.utilities.element.acquisition.ElementAcquisition;
import pickleib.utilities.screenshot.ScreenCaptureUtility;
import pickleib.web.interactions.WebInteractions;
import utils.Printer;
import static common.StatusWatcher.TestStatus.*;

@ExtendWith(StatusWatcher.class)
public class BaseTest {

    public static Printer log = new Printer(BaseTest.class);
    public static Ollama ollama;
    public WebInteractions interactions;
    public RemoteWebDriver driver;
    public FluentWait<RemoteWebDriver> wait;
    public ElementAcquisition.PageObjectModel<ObjectRepository> acquisition;

    @BeforeAll
    public static void setup(){
        ContextStore.loadProperties("test.properties");
        log.info("Properties loaded");
        ollama = new Ollama(
                ContextStore.get("ollama-url").toString(),
                ContextStore.get("ollama-token").toString()
        );
    }

    @BeforeEach
    public void before(TestInfo testInfo){
        driver = Driver.setup(BrowserType.valueOf(ContextStore.get("browser", "chrome")));
        wait = new FluentWait<>(driver);
        acquisition = new ElementAcquisition.PageObjectModel<>(ObjectRepository.class);
        interactions = new WebInteractions(driver, wait);
        log.warning("RUNNING: " + testInfo.getDisplayName());
    }

    @AfterEach
    public void after(TestInfo testInfo){
        if (isFailed()) ScreenCaptureUtility.captureScreen(
                testInfo.getTags().stream().findFirst().orElse("NoTag"), "png", Driver.driver
        );
        Driver.quitDriver();
    }

}

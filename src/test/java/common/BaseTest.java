package common;

import context.ContextStore;
import driver.Driver;
import ollama.Ollama;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.RemoteWebDriver;
import pickleib.utilities.element.acquisition.ElementAcquisition;
import pickleib.utilities.screenshot.ScreenCaptureUtility;
import utils.Printer;
import static common.StatusWatcher.TestStatus.*;

@ExtendWith(StatusWatcher.class)
public class BaseTest {

    public static Ollama ollama;
    public BaseObject base = new BaseObject();
    public static Printer log = new Printer(BaseTest.class);
    public ElementAcquisition.PageObjectModel<ObjectRepository> acquisition = new ElementAcquisition.PageObjectModel<>(ObjectRepository.class);
    public static RemoteWebDriver driver;

    @BeforeAll
    public static void globalSetup() {
        log.warning("Initializing the driver...");
        ContextStore.loadProperties("test.properties");
        log.info("Properties loaded");
        driver = Driver.setup(BrowserType.valueOf(ContextStore.get("browser", "chrome")));
        ollama = new Ollama(
                ContextStore.get("ollama-url").toString(),
                ContextStore.get("ollama-token").toString()
        );
    }

    @AfterAll
    public static void globalTeardown() {
        Driver.quitDriver();
    }

    @BeforeEach
    public void beforeScenario(TestInfo testInfo) {
        driver = Driver.setup(BrowserType.valueOf(ContextStore.get("browser", "chrome")));
        log.warning("RUNNING: " + testInfo.getDisplayName());
        Driver.driver.manage().deleteAllCookies();
        StatusWatcher.TestStatus.clear();
    }

    @AfterEach
    public void afterScenario(TestInfo testInfo) {
        if (isFailed()) ScreenCaptureUtility.captureScreen(
                testInfo.getTags().stream().findFirst().orElse("NoTag"), "png", Driver.driver
        );
        Driver.quitDriver();
    }

}

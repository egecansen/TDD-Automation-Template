package steps;

import common.BrowserType;
import common.ObjectRepository;
import common.StatusWatcher;
import context.ContextStore;
import driver.Driver;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import pickleib.utilities.element.acquisition.ElementAcquisition;
import pickleib.utilities.screenshot.ScreenCaptureUtility;
import utils.Printer;
import static common.StatusWatcher.TestStatus.*;

@ExtendWith(StatusWatcher.class)
public class BaseTest {

    static Printer log = new Printer(BaseTest.class);
    public ElementAcquisition.PageObjectModel<ObjectRepository> acquisition = new ElementAcquisition.PageObjectModel<>(ObjectRepository.class);

    @BeforeAll
    public static void globalSetup() {
        Driver.setup(BrowserType.valueOf(ContextStore.get("browser", "chrome")));
    }

    @AfterAll
    public static void globalTeardown() {
        Driver.quitDriver();
    }

    @BeforeEach
    public void beforeScenario(TestInfo testInfo) {
        log.warning("RUNNING: " + testInfo.getDisplayName());
    }

    @AfterEach
    public void afterScenario(TestInfo testInfo) {
        if (isFailed()) ScreenCaptureUtility.captureScreen(
                testInfo.getTags().stream().findFirst().orElse("NoTag"), "png", Driver.driver
        );
    }

}

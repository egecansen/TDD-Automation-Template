package driver;
import common.BrowserType;
import context.ContextStore;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import utils.Printer;
import java.time.Duration;
public class Driver {

    public static RemoteWebDriver driver;
    static Printer log = new Printer(Driver.class);

    public static void setup(BrowserType browserType) {
        log.warning("Initializing the driver...");
        ContextStore.loadProperties("test.properties");
        log.info("Properties loaded");

        boolean headless = Boolean.parseBoolean(ContextStore.get("headless","false"));
        int frameWidth = Integer.parseInt(ContextStore.get("frame-width"));
        int frameHeight = Integer.parseInt(ContextStore.get("frame-height"));
        boolean maximize = Boolean.parseBoolean(ContextStore.get("maximize", "false"));

        switch (browserType) {
            case chrome -> {
                ChromeOptions options = new ChromeOptions();
                if (headless) {
                    options.addArguments("--headless=new");
                    options.addArguments(String.format("--window-size=%d,%d", frameWidth, frameHeight));
                }
                driver = new ChromeDriver(options);
            }
            case firefox -> driver = new FirefoxDriver();
            case safari -> driver = new SafariDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        if (!headless) {
            if (maximize) driver.manage().window().maximize();
            else driver.manage().window().setSize(new Dimension(frameWidth, frameHeight));
        }
    }

    public static void quitDriver() {
        log.warning("Terminating the driver...");
        driver.quit();
    }

}

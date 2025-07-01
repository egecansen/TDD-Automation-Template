package common;
import driver.Driver;
import org.openqa.selenium.support.ui.FluentWait;
import pickleib.utilities.element.acquisition.ElementAcquisition;
import pickleib.web.PickleibPageObject;
import pickleib.web.interactions.WebInteractions;

public class BaseObject extends PickleibPageObject {

    public static ElementAcquisition.Reflections<ObjectRepository> reflections;
    public static WebInteractions webInteractions;
    public static ElementAcquisition.PageObjectModel<ObjectRepository> acquisition;

    public BaseObject() {
        super(Driver.driver);
        webInteractions = new WebInteractions(Driver.driver, new FluentWait<>(Driver.driver));
        reflections = new ElementAcquisition.Reflections<>(ObjectRepository.class);
        acquisition = new ElementAcquisition.PageObjectModel<>(ObjectRepository.class);
    }

}

package common;

import driver.Driver;
import pickleib.utilities.element.acquisition.ElementAcquisition;
import pickleib.web.PickleibPageObject;

public class PageObject extends PickleibPageObject {
    public ElementAcquisition.Reflections<ObjectRepository> reflections = new ElementAcquisition.Reflections<>(ObjectRepository.class);
    public PageObject() {
        super(Driver.driver);
    }

}

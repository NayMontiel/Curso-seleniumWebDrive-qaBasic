package pageObjects;

import Util.driver.DriverManager;
import org.openqa.selenium.support.PageFactory;

public class AbstractPagesObjects {
    protected AbstractPagesObjects(){
        PageFactory.initElements(DriverManager.getDriver(), this);
    }
}

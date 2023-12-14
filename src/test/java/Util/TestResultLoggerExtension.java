package Util;

import Util.driver.DriverManager;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestResultLoggerExtension implements TestWatcher, TestResultLoggerExtension2 {

    private Photographer photographer = new Photographer();
    @Override
    public void testSuccessFul(ExtensionContext context){
        DriverManager.quit();
    }
    @Override
    public void testFailed( ExtensionContext context, Throwable cause ){
        photographer.takePhoto();
        DriverManager.quit();
    }
}

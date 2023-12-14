package Util.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public enum DriverFactory {
    CHROME{
        @Override
        public WebDriver createDriver(){
            WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
            return new  ChromeDriver();
        }
    };
    public abstract WebDriver createDriver();
}

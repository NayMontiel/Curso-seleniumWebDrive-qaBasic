package pageObjects;

import Util.Photographer;
import Util.driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class ResultPages extends AbstractPagesObjects {

    @FindBy(css = "ol[class*='ui-search-layout'] li")
    private List<WebElement> results;


    private final By expectedPrice = By.cssSelector(" div[class*= 'ui-search-price__second-line'] span[class*= 'andes-money-amount__fraction']");

    private final By expectedName = By.cssSelector("h2");

    private WebElement randomElement;
    private final Photographer photographer;

    public ResultPages(){

        photographer = new Photographer();
    }
   @Step("Obteniendo un item aleatorio")
    public WebElement getRandomResults(){
        photographer.takePhoto();
        Random rand = new Random();
         randomElement = results.get(rand.nextInt(results.size()));
        return randomElement;
    }
    @Step("Seleccionando un item aleatorio")
    public ResultModel clickOnRandomItem(){
        photographer.takePhoto();
        WebElement randomElement = getRandomResults();

        ResultModel expectedResult = new ResultModel();
        expectedResult.setName(randomElement.findElement(expectedName).getText());
        expectedResult.setPrice(randomElement.findElement( expectedPrice).getText());

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView();", randomElement);
        randomElement.findElement(expectedName).click();
        return expectedResult;

    }

}

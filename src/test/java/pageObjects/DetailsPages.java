package pageObjects;

import Util.Photographer;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class DetailsPages extends AbstractPagesObjects {

    @FindBy(css = "div[class*= 'ui-pdp-price__second-line'] span[class= 'andes-money-amount__fraction']")
     private WebElement priceInDetails;
    @FindBy(css = "h1[class= 'ui-pdp-title']")
     private WebElement nameInDetails;

    private Photographer photographer;
    public DetailsPages() {

        photographer = new Photographer();
    }
   @Step("obteniendo Informacion Detallada")
   public ResultModel getDetails() {
        photographer.takePhoto();
        ResultModel resultModel = new  ResultModel();
        resultModel.setName(nameInDetails.getText());
        resultModel.setPrice(priceInDetails.getText());
        return resultModel;
    }

}

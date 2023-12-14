package pageObjects;

import Util.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactUs extends AbstractPagesObjects {


    private Wait wait;
    @FindBy(id="id_contact")
    private WebElement subjectLocator;
    @FindBy(css = "input#email")
    private WebElement emailLocator;
    @FindBy(id = "id_order")
    private WebElement orderLocator;
    @FindBy(css = "textarea#message")
    private WebElement messageLocator;
    @FindBy(id = "submitMessage")
    private WebElement buttonLocator;
    @FindBy(xpath = "//p[contains(@class, 'alert')]")
    private WebElement alertLocator;

    public ContactUs() {

            wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10L));

    }
    //select
    public void writeSubjectHeading(String subject){
        Select selectSubjectHeading = new Select(subjectLocator);
        selectSubjectHeading.selectByVisibleText(subject);
    }
    //email
    public void writeEmailAddress(String email) {
        emailLocator.sendKeys(email);
    }
    //N# de order
    public void writeOrderReference(String order) {
        orderLocator.sendKeys(order);
    }
    //messages
   public void writeMessages(String message) {
       messageLocator.sendKeys(message);
   }
   //button
    public void writeSubmitMessage(){
        buttonLocator.click();
    }

    //alert
    public void writeAlertMessage(String expectedText) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(@class, 'alert')]")));
        assertTrue(alertLocator.getText().contains(expectedText));


    }



}

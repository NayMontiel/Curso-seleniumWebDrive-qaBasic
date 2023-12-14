import Util.driver.DriverManager;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NavigationTest extends BaseWebTest {


    @Test
    @Description("navegando a facebook e ingresando usuario y contraseña")
    public void navigationToFacebook() throws InterruptedException {

        DriverManager.getDriver().get("https://www.facebook.com");

        DriverManager.getDriver().findElement(By.xpath("//input[@id='email']")).sendKeys("Nay.0721");

        DriverManager.getDriver().findElement(By.cssSelector("input#pass")).sendKeys("1234567");

        DriverManager.getDriver().findElement(By.cssSelector("button[name= 'login']")).click();

        Thread.sleep(3000);


    }

    @Test
    @Description("navegando a mercado libre y escongiendo un pais")
    public void navigateToMercadoLibre() throws InterruptedException {

        DriverManager.getDriver().get("https://mercadolibre.com/");

        DriverManager.getDriver().findElement(By.xpath("//nav/ul/li/a[contains(.,'Chile')]")).click();

        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        String expectedUrl = "https://www.mercadolibre.cl";

        assertTrue(currentUrl.contains(expectedUrl), "se esperaba que la url contenga" + expectedUrl + "pero se obtuvo" + currentUrl );

        Thread.sleep(3000);


    }

    @Test
    @Description("navegando a por la pagina de prueba de qa espeficicamente probando su select")
    public void navigateToContact() throws InterruptedException {
        DriverManager.getDriver().get("http://www.automationpractice.pl/index.php?controller=contact");
        WebElement select = DriverManager.getDriver().findElement(By.id("id_contact"));

        Select selectWeb = new Select(select);

        selectWeb.selectByIndex(1);
        Thread.sleep(2000);

        selectWeb.selectByIndex(2);
        Thread.sleep(2000);

        selectWeb.selectByIndex(0);
        Thread.sleep(2000);

        selectWeb.selectByVisibleText("Webmaster");
        Thread.sleep(2000);

        selectWeb.selectByValue("2");
        Thread.sleep(3000);

    }

    @Test
    @Description("navegando a google para probar el implicit wait")
   public void implicitWaitExample() {

       DriverManager.getDriver().get("http://www.google.com");
       DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));

       Instant start = Instant.now();
       try{
           DriverManager.getDriver().findElement(By.id("algo"));
        } catch (Exception exc){
          Instant end = Instant.now();
         Duration timeElapsed = Duration.between(start, end);

          System.out.println("******");
          System.out.println("Time 1: "+ timeElapsed.getSeconds() + "seconds");
         System.out.println("******");

      }

        start = Instant.now();
       try{
            DriverManager.getDriver().findElement(By.id("algo2"));
       } catch (Exception exc){
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);

          System.out.println("******");
            System.out.println("Time 2: "+ timeElapsed.getSeconds() + "seconds");
          System.out.println("******");

       }


    }

    @Test
    @Description("navegando a google para probar el explicit wait")
    public void ExplicitWaitExample() {
        DriverManager.getDriver().get("http://www.google.com");
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5L));

        Instant start = Instant.now();
        try{
            DriverManager.getDriver().findElement(By.id("algo"));
        } catch (Exception exc){
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);

            System.out.println("******");
            System.out.println("Time 1: "+ timeElapsed.getSeconds() + "seconds");
            System.out.println("******");

        }

        Instant start2 = Instant.now();

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10L));

        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("algo3")));
        } catch (Exception exc){
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start2, end);

            System.out.println("******");
            System.out.println("Time 1: "+ timeElapsed.getSeconds() + "seconds");
            System.out.println("******");

        }
    }

    @Test
    @Description("navegando a google para probar el fluent wait")
    public void fluentWaitExample(){

        DriverManager.getDriver().get("http://www.google.com");

        FluentWait<WebDriver> fluentWait = new FluentWait<>(DriverManager.getDriver());

        fluentWait.withTimeout(Duration.ofSeconds(10L));
        fluentWait.pollingEvery(Duration.ofSeconds(2L));

        fluentWait.ignoring(NoSuchElementException.class);

        Instant start2 = Instant.now();

        try{
            fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.id("algo2")));
        } catch (Exception exc){
            System.out.println("***Exception***" + exc.getMessage());

        }
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start2, end);

        System.out.println("******");
        System.out.println("Time 1: "+ timeElapsed.getSeconds() + "seconds");
        System.out.println("******");

    }

    @Test
    @Description("navegando a por la pagina de prueba de qa espeficicamente en la ruta de contact")
    public void automatingFormContact() throws InterruptedException {
        DriverManager.getDriver().get("http://www.automationpractice.pl/index.php?controller=contact");
        //select
        WebElement subjectHeading = DriverManager.getDriver().findElement(By.id("id_contact"));
        Select selectSubjectHeading = new Select(subjectHeading);
        selectSubjectHeading.selectByVisibleText("Customer service");

        //email
        WebElement emailAddress = DriverManager.getDriver().findElement(By.cssSelector("input#email"));
        emailAddress.sendKeys("maria@mail.com");

        //N# de order
        WebElement orderReference = DriverManager.getDriver().findElement(By.id("id_order"));
        orderReference.sendKeys("123456");

        //messages
        WebElement messages = DriverManager.getDriver().findElement(By.cssSelector("textarea#message"));
        messages.sendKeys("Hello world");

        //button
        WebElement submitMessage = DriverManager.getDriver().findElement(By.id("submitMessage"));
        submitMessage.click();

        //alert
        WebElement alertMessage = DriverManager.getDriver().findElement(By.xpath("//p[contains(@class, 'alert')]"));

        assertTrue(alertMessage.getText().contains("Your message has been successfully sent to our team."), "The text is not expected");

        Thread.sleep(3000);

    }

    @Test
    @Description("navegando a por la pagina de prueba de qa espeficicamente en la ruta de contact version con codigo más limpio y separado")
    public void automatingFormContact2() {
        DriverManager.getDriver().get("http://www.automationpractice.pl/index.php?controller=contact");

        ContactUs contactUs = new ContactUs();

        //select
        contactUs.writeSubjectHeading("Customer service");
        //email
        contactUs.writeEmailAddress("maria@mail.com");
        //N# de order
        contactUs.writeOrderReference("12345667");
        //messages
        contactUs.writeMessages("Hello world my name is Maria how are you");
        //button
        contactUs.writeSubmitMessage();
        //alert
        contactUs.writeAlertMessage("Your message has been successfully sent to our team");



    }

    @Test
    @Description("navegando a mercado libre y probando el input de busqueda")
    public void searchToMercadoLibre()  {

        DriverManager.getDriver().get("https://www.mercadolibre.cl");

       WebElement searchBox =  DriverManager.getDriver().findElement(By.id("cb1-edit"));
       searchBox.sendKeys("guitarra electrica");
       searchBox.sendKeys(Keys.ENTER);


       List<WebElement> results = DriverManager.getDriver().findElements(By.cssSelector("ol[class*='ui-search-layout'] li"));

       System.out.println("El número de resultados es: " + results.size());

        By priceBy = By.cssSelector("div[class*='ui-search-price__second-line'] span[class*= 'andes-money-amount__fraction']");
        By nameBy = By.cssSelector("h2");

        for(WebElement result : results){

            System.out.println("----------");
            System.out.println("El Precio es: " + result.findElement(priceBy).getText());
            System.out.println("El Nombre es: " + result.findElement(nameBy).getText());
            System.out.println("----------");

        }

    }

    @Test
    @Description("navegando a mercado libre y probando el input de busqueda condigo más limpio y separado")
    public void searchToMercadoLibre2()  {

        DriverManager.getDriver().get("https://www.mercadolibre.cl");

        //search
        HomePages homePages = new HomePages();
        homePages.searchFor("Guitarra electrica");

        //Result

        ResultPages resultPages = new ResultPages();
        ResultModel expectedResult = resultPages.clickOnRandomItem();

        //details

        DetailsPages detailsPages = new DetailsPages();
        ResultModel actualResultModel = detailsPages.getDetails();

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedResult.getPrice(), actualResultModel.getPrice(), "prices don't match"),
                () -> Assertions.assertEquals(expectedResult.getName(), actualResultModel.getName(), "product names don't match")
        );

        }

    @Test
    @Description("navegando a mercado libre y probando el input de busqueda en este caso con otro articulo")
    public void searchToMercadoLibre3()  {

        DriverManager.getDriver().get("https://www.mercadolibre.cl");

        //search
        HomePages homePages = new HomePages();
        homePages.searchFor("iphone");

        //Result

        ResultPages resultPages = new ResultPages();
        ResultModel expectedResult = resultPages.clickOnRandomItem();

        //details

        DetailsPages detailsPages = new DetailsPages();
        ResultModel actualResultModel = detailsPages.getDetails();

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedResult.getPrice(), actualResultModel.getPrice(), "prices don't match"),
                () -> Assertions.assertEquals(expectedResult.getName(), actualResultModel.getName(), "product names don't match")
        );

    }

    }


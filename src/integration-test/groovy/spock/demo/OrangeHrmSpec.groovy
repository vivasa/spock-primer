package spock.demo

import grails.testing.mixin.integration.Integration
import grails.transaction.*

import spock.lang.*
import geb.spock.*

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * ./gradlew integrationTest -DintegrationTest.single='*demo/OrangeHrm'
 */

@Integration
@Rollback
class OrangeHrmSpec extends GebSpec {

    private WebDriver driver;
    private WebDriverWait wait;

    def setupSpec(){
        System.setProperty("webdriver.chrome.driver", "/projects/chromedriver");
    }

    def setup() {
        ChromeOptions o = new ChromeOptions();
        o.addArguments("--headless");
        o.addArguments("--no-sandbox");
        o.addArguments("window-size=1200,1100");
        driver = new ChromeDriver(o);
        wait = new WebDriverWait(driver,10);
    }

    def cleanup() {
        driver.quit();
    }

    def "loginToAppIsSuccess"() {
        when:"User navigates to Login page"
            driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
        then:"User expects that login page is loaded"
            driver.getTitle() == "OrangeHRM";
            driver.getCurrentUrl() == "https://opensource-demo.orangehrmlive.com/index.php/auth/login";

        when:"Find an element by its Id"
            String loginPanelHeader=driver.findElement(By.id("logInPanelHeading")).getText();
        then:"Text of the element should match known value"
            loginPanelHeader == "LOGIN Panel"

        when:"Login credentials are entered and the form is submitted"
            driver.findElement(By.id("txtUsername")).sendKeys("Admin");
            driver.findElement(By.name("txtPassword")).sendKeys("admin123");
            driver.findElement(By.cssSelector(".button")).click();
        then:"Url of the new page points to the User Dashboard"
            driver.getCurrentUrl() == "https://opensource-demo.orangehrmlive.com/index.php/dashboard";

        and:"Quick launch shortcuts are found"
            String quickLaunchTitle= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='panel_resizable_0_0']/legend"))).getText();
            quickLaunchTitle == "Quick Launch";
    }

}
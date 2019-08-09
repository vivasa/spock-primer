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

@Integration
@Rollback
class OrangeHrmSpec extends GebSpec {

    private WebDriver driver;
    private WebDriverWait wait;

    def setupSpec(){
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
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
        when:"Login page"
            driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
        then:"verify that, login page is loaded"
            assert driver.getTitle() == "OrangeHRM";
            assert driver.getCurrentUrl() == "https://opensource-demo.orangehrmlive.com/index.php/auth/login";

        when:"check element exist and get the text"
            String loginPanelHeader=driver.findElement(By.id("logInPanelHeading")).getText();
        then:"verify with expected text"
            assert loginPanelHeader == "LOGIN Panel"

        when:"enter username & password and press login button"
            driver.findElement(By.id("txtUsername")).sendKeys("Admin");
            driver.findElement(By.name("txtPassword")).sendKeys("admin123");
            driver.findElement(By.cssSelector(".button")).click();
        then:"verify it landed in dashboard page"
            assert driver.getCurrentUrl() == "https://opensource-demo.orangehrmlive.com/index.php/dashboard";

        when:"check the element exist"
            String quickLaunchTitle= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='panel_resizable_0_0']/legend"))).getText();
        then:
            assert quickLaunchTitle == "Quick Launch";
    }

}
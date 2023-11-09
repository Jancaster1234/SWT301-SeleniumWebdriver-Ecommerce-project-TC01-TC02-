package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderMenuPage {
    WebDriver driver;

    By ordersLocator = By.cssSelector("li.level1 a[href*='sales_order'] span");

    By saleMenuLocator = By.xpath("//li[@class='parent level0']");
    By ordersLinkLocator = By.xpath("//*[@id=\"nav\"]/li[1]/ul/li[1]/a/span");
    By orderIdInputLocator = By.xpath("//*[@id=\"sales_order_grid_filter_real_order_id\"]");
    By fromDateInputLocator = By.xpath("/html/body/div[1]/div[4]/div/div[3]/div/div[2]/div/table/thead/tr[2]/th[3]/div/div[1]/input");
    By toDateInputLocator = By.xpath("/html/body/div[1]/div[4]/div/div[3]/div/div[2]/div/table/thead/tr[2]/th[3]/div/div[2]/input");
    By searchButtonLocator = By.xpath("/html/body/div[1]/div[4]/div/div[3]/div/table/tbody/tr/td[3]/button[2]/span/span/span");
    By orderDropdownListLocator = By.xpath("//*[@id=\"nav\"]/li[1]/a");

    public void selectOrdersLink(String xpath){
        WebElement a = driver.findElement(orderDropdownListLocator);
        Select select = new Select(a);
        WebElement option = driver.findElement(By.xpath(xpath));
        select.selectByVisibleText(option.getText());
    }
    public void enterOrderId(String a) {
        WebElement orderIdLocatorElement = driver.findElement(orderIdInputLocator);
        orderIdLocatorElement.clear();
        orderIdLocatorElement.sendKeys(a);
    }

    public void enterFromDateInputLocator(String a) {
        WebElement fromDateInputLocatorElement = driver.findElement(fromDateInputLocator);
        fromDateInputLocatorElement.clear();
        fromDateInputLocatorElement.sendKeys(a);
    }

    public void enterToDateInputLocator(String a) {
        WebElement toDateInputLocatorElement = driver.findElement(toDateInputLocator);
        toDateInputLocatorElement.clear();
        toDateInputLocatorElement.sendKeys(a);
    }

    public void clickOrdersLinkLocator() {
        WebElement closeButton = driver.findElement(By.xpath("//*[@id='message-popup-window']/div[1]/a/span"));
        closeButton.click();
        WebElement ordersLink = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/ul/li[1]/a/span"));
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout as needed
        wait.until(ExpectedConditions.elementToBeClickable(ordersLink));
        actions.moveToElement(ordersLink).perform();
        WebElement o = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/ul/li[1]/ul/li[1]/a/span"));
        o.click();
    }
    public void clickSearchButtonLocator() {
        driver.findElement(searchButtonLocator).click();
    }
    public OrderMenuPage(WebDriver driver) {
        this.driver = driver;
    }
}

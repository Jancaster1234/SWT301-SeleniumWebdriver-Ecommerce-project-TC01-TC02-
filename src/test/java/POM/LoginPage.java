package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;
    By emailAddressInputLocator = By.xpath("//*[@id=\"email\"]");
    By passwordInputLocator = By.xpath("//*[@id=\"pass\"]");
    By loginButtonLocator = By.xpath("//*[@id=\"send2\"]");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmailAddress(String emailAddress) {
        WebElement emailAddressElement = driver.findElement(emailAddressInputLocator);
        emailAddressElement.clear();
        emailAddressElement.sendKeys(emailAddress);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = driver.findElement(passwordInputLocator);
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButtonLocator).click();
    }
}

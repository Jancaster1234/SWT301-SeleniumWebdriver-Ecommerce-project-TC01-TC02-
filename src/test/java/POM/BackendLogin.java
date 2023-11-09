package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BackendLogin {
    WebDriver driver;

    By userNameInputLocator = By.xpath("//*[@id=\"username\"]");
    By passwordInputLocator = By.xpath("//*[@id=\"login\"]");
    By loginButton = By.xpath("//*[@id=\"loginForm\"]/div/div[5]/input");
    public BackendLogin(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        WebElement userNameElement = driver.findElement(userNameInputLocator);
        userNameElement.clear();
        userNameElement.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = driver.findElement(passwordInputLocator);
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}

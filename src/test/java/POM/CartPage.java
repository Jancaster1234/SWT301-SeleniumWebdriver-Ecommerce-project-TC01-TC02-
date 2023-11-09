package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;

public class CartPage {
    WebDriver driver;
    By countryDropdown = By.id("country");
    By stateDropdown = By.id("region_id");
    By zipCodeInput = By.id("postcode");
    By estimateShippingButton = By.xpath("//*[@id=\"shipping-zip-form\"]/div/button/span/span");
    By quantityLocator = By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[4]/input");
    By flatRateShippingOption = By.xpath("//*[@id=\"co-shipping-method-form\"]/dl/dd/ul/li/label");
    By updateTotalButton = By.xpath("//*[@id=\"co-shipping-method-form\"]/div/button/span/span");
    By totalCostLabel = By.xpath("//*[@id=\"shopping-cart-totals-table\"]/tfoot/tr/td[2]/strong/span");
    By proceedToCheckoutButton = By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div/div/div[3]/div/ul/li[1]/button/span/span");
    By subtotalLabel = By.xpath("//*[@id=\"shopping-cart-totals-table\"]/tbody/tr[1]/td[2]/span");
    By shippingHandlingLabel = By.xpath("//*[@id=\"shopping-cart-totals-table\"]/tbody/tr[2]/td[2]/span");
    By countryChosenOption = By.xpath("//*[@id='country']/option[234]");
    By regionChosenOption = By.xpath("//*[@id='region_id']/option[13]");

    By grandTotal = By.cssSelector("strong span[class='price']");
    By updateQuantityButton = By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[4]/button/span/span");

    By applyDiscount = By.cssSelector("button[title='Apply'] span span");
    By discount = By.id("coupon_code");
    By iphoneAddToCart = By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > ul:nth-child(2) > li:nth-child(1) > div:nth-child(2) > div:nth-child(4) > button:nth-child(1) > span:nth-child(1) > span:nth-child(1)");

    By discountGenerated = By.cssSelector("tbody tr:nth-child(2) td:nth-child(2) span:nth-child(1)");
    public String getInitGrandTotal(){
        return  driver.findElement(grandTotal).getText().replaceAll("[$,]", "");

    }

    public void addIphoneToCart(){
        WebElement btn = driver.findElement(iphoneAddToCart);
        btn.click();
    }

    public void enterDiscountCode(String key){
        WebElement discountCode = driver.findElement(discount);
        discountCode.sendKeys(key);
    }

    public void clickApplyDiscount(){
        WebElement apply = driver.findElement(applyDiscount);
        apply.click();
    }

    public String discountGenerated(){
        WebElement discount = driver.findElement(discountGenerated);
        String discountStr = discount.getText().replaceAll("[-$,]", "");
        return (discountStr);
    }

    public void verifyDiscount(double oldGrand){
        double expected = oldGrand - Double.parseDouble(discountGenerated());
        double actual = Double.parseDouble(getInitGrandTotal());
        AssertJUnit.assertEquals(expected,actual);
    }

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectCountryByXPath(String xpath) {
        WebElement countryDropdow = driver.findElement(countryDropdown);
        Select dropdown = new Select(countryDropdow);
        WebElement option = driver.findElement(By.xpath(xpath));
        dropdown.selectByVisibleText(option.getText());
    }


    public void selectStateByXPath(String xpath) {
        WebElement stateDropdow = driver.findElement(stateDropdown);
        Select dropdown = new Select(stateDropdow);
        WebElement option = driver.findElement(By.xpath(xpath));
        dropdown.selectByVisibleText(option.getText());
    }

    public void inputValueQuantityLocator(String value){
        WebElement quantityInput = driver.findElement(quantityLocator);
        quantityInput.clear();
        quantityInput.sendKeys(value);
    }

    public String getValueOfQuantityLocator() {
        WebElement quantityInput = driver.findElement(quantityLocator);
        return quantityInput.getAttribute("value");
    }

    public String getValueOfFlatRateShippingOption() {
        WebElement x = driver.findElement(flatRateShippingOption);
        return x.getText();
    }

    public void enterZipCode(String zipCode) {
        driver.findElement(zipCodeInput).clear();
        driver.findElement(zipCodeInput).sendKeys(zipCode);
    }

    public void clickEstimateShipping() {
        driver.findElement(estimateShippingButton).click();
    }

    public void clickUpdateQuantityButton() {
        driver.findElement(updateQuantityButton).click();
    }
//    public String getShippingCost() {
//        return driver.findElement(shippingCostLabel).getText();
//    }

    public void selectFlatRateShipping() {
        driver.findElement(flatRateShippingOption).click();
    }

    public void clickUpdateTotal() {
        driver.findElement(updateTotalButton).click();
    }

    public String getTotalCost() {
        return driver.findElement(totalCostLabel).getText();
    }

    public void clickProceedToCheckout() {
        driver.findElement(proceedToCheckoutButton).click();
    }

    public String getSubtotalLabel() {
        return driver.findElement(subtotalLabel).getText();
    }

    public String getShippingHandlingLabel() {
        return driver.findElement(shippingHandlingLabel).getText();
    }

    public void inputZipCode(String postCode) {
        WebElement usernameElem = driver.findElement(zipCodeInput);
        usernameElem.sendKeys(postCode);
    }

    public String getSelectedCountryOption() {
        WebElement countryDropdow = driver.findElement(countryDropdown);
        Select select = new Select(countryDropdow);
        WebElement selectedOption = driver.findElement(countryChosenOption);
        if (select.getFirstSelectedOption().equals(selectedOption)) {
            return selectedOption.getText();
        } else {
            return "No match found";
        }
    }

    public String getSelectedRegionOption() {
        WebElement regionDropdown = driver.findElement(stateDropdown);
        Select select = new Select(regionDropdown);
        WebElement selectedOption = driver.findElement(regionChosenOption);
        if (select.getFirstSelectedOption().equals(selectedOption)) {
            return selectedOption.getText();
        } else {
            return "No match found";
        }
    }
}

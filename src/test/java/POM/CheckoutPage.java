package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage {
    WebDriver driver;

    By addressInputLocator = By.xpath("//*[@id='billing:street1']");
    By cityInputLocator = By.xpath("//*[@id='billing:city']");
    By stateDropdownListLocator = By.xpath("//*[@id='billing:region_id']");
    By zipInputLocator = By.xpath("//*[@id='billing:postcode']");
    By countryDropdownListLocator = By.xpath("//*[@id='billing:country_id']");
    By telephoneInputLocator = By.xpath("//*[@id='billing:telephone']");
    By continueButtonLocator = By.xpath("//*[@id='billing-buttons-container']/button/span/span");
    By x = By.xpath("//*[@id=\"billing-address-select\"]");
    By shipToThisAddressButton = By.xpath("//*[@id=\"co-billing-form\"]/div/ul/li[3]/label");
    By billingInformationButtonLocator = By.xpath("//*[@id=\"billing-buttons-container\"]/button");
    By shippingInformationLinkLocator = By.xpath("//*[@id='opc-shipping']/div[1]/h2");
    By shippingInformationContinueButtonLocator = By.xpath("//*[@id=\"shipping-buttons-container\"]/button");
    By shippingMethodContinueButtonLocator = By.xpath("//*[@id=\"shipping-method-buttons-container\"]/button/span/span");
    By check_moneyOrderRadioButtonLocator = By.xpath("//*[@id=\"dt_method_checkmo\"]/label");
    By paymentInformationContinueButtonLocator = By.xpath("//*[@id=\"payment-buttons-container\"]/button/span/span");
    By placeOrderButtonLocator = By.xpath("//*[@id=\"review-buttons-container\"]/button/span/span");
    By orderVerifyMessageLocator = By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div/div[1]/h1");
    By orderNumberLinkLocator = By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div/p[1]/a");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterBillingAddress(String address) {
        driver.findElement(addressInputLocator).clear();
        driver.findElement(addressInputLocator).sendKeys(address);
    }
    public void selectXByXPath() {
        WebElement countryDropdow = driver.findElement(x);
        Select dropdown = new Select(countryDropdow);
        dropdown.selectByValue("");
    }
    public void enterBillingCity(String city) {
        driver.findElement(cityInputLocator).clear();
        driver.findElement(cityInputLocator).sendKeys(city);
    }

    public void selectBillingState(String xpath) {
        WebElement stateDropdown = driver.findElement(stateDropdownListLocator);
        Select select = new Select(stateDropdown);
        WebElement option = driver.findElement(By.xpath(xpath));
        select.selectByVisibleText(option.getText());
    }

    public void enterBillingZipCode(String zipCode) {
        driver.findElement(zipInputLocator).clear();
        driver.findElement(zipInputLocator).sendKeys(zipCode);
    }

    public void clickShipToThisAddressButton() {
        driver.findElement(shipToThisAddressButton).click();
    }

    public void selectBillingCountry(String xpath) {
        WebElement stateDropdown = driver.findElement(countryDropdownListLocator);
        Select select = new Select(stateDropdown);
        WebElement option = driver.findElement(By.xpath(xpath));
        select.selectByVisibleText(option.getText());
    }
    public void enterBillingTelephone(String telephone) {
        driver.findElement(telephoneInputLocator).clear();
        driver.findElement(telephoneInputLocator).sendKeys(telephone);
    }

    public void clickContinueButton() {
        driver.findElement(continueButtonLocator).click();
    }

    public void clickShippingInformationLink() {
        driver.findElement(shippingInformationLinkLocator).click();
    }

    public void clickBillingInformationButtonLocator() {
        driver.findElement(billingInformationButtonLocator).click();
    }

    public void clickShippingInformationContinueButton() {
        driver.findElement(shippingInformationContinueButtonLocator).click();
    }

    public void clickShippingMethodContinueButton() {
        driver.findElement(shippingMethodContinueButtonLocator).click();
    }

    public void selectPaymentMethodCheckMoneyOrder() {
        driver.findElement(check_moneyOrderRadioButtonLocator).click();
    }

    public void clickPaymentInformationContinueButton() {
        driver.findElement(paymentInformationContinueButtonLocator).click();
    }

    public void clickPlaceOrderButton() {
        driver.findElement(placeOrderButtonLocator).click();
    }

    public String getOrderVerificationMessage() {
        return driver.findElement(orderVerifyMessageLocator).getText();
    }

    public String getOrderNumberLinkText() {
        return driver.findElement(orderNumberLinkLocator).getText();
    }

    public void clickOrderNumberLink() {
        driver.findElement(orderNumberLinkLocator).click();
    }

    public String getAddressInputValue() {
        return driver.findElement(addressInputLocator).getAttribute("value");
    }

    public String getCityInputValue() {
        return driver.findElement(cityInputLocator).getAttribute("value");
    }

    public String getZipInputValue() {
        return driver.findElement(zipInputLocator).getAttribute("value");
    }

    public String getTelephoneInputValue() {
        return driver.findElement(telephoneInputLocator).getAttribute("value");
    }

    public String getSelectedStateOption() {
        return driver.findElement(stateDropdownListLocator).getText();
    }

    public String getSelectedCountryOption() {
        return driver.findElement(countryDropdownListLocator).getText();
    }

}

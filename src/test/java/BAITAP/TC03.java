package BAITAP;
 /*
 “Verify that you cannot add more product in cart than the product available in store”


Test Steps:

1. Go to http://live.techpanda.org/

2. Click on �MOBILE� menu

    3. In the list of all mobile , click on �ADD TO CART� for Sony Xperia mobile

4. Change �QTY� value to 1000 and click �UPDATE� button. Expected that an error is displayed

"The requested quantity for "Sony Xperia" is not available.

5. Verify the error message

6. Then click on �EMPTY CART� link in the footer of list of all mobiles. A message "SHOPPING CART IS EMPTY" is shown.

7. Verify cart is empty
  */

import driver.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

public class TC03 {
    @Test
    public static void testTC03() {
        // 0. Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            // 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            // 2. Click on MOBILE menu
            By mobileMenuSelector = By.cssSelector("a[href='http://live.techpanda.org/index.php/mobile.html']");
            driver.findElement(mobileMenuSelector).click();

            // 3. Click on "ADD TO CART" for Sony Xperia mobile
            By addToCartButton = By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[2]/div/div[3]/button");
            driver.findElement(addToCartButton).click();

            // 4. Change "QTY" value to 1000 and click "UPDATE" button
            By qtyInput = By.xpath("/html/body/div/div/div[2]/div/div/div/form/table/tbody/tr/td[4]/input");
            WebElement qtyInputElement = driver.findElement(qtyInput);
            qtyInputElement.clear();
            qtyInputElement.sendKeys("1000");
            By updateButton = By.xpath("/html/body/div/div/div[2]/div/div/div/form/table/tbody/tr/td[4]/button");
            driver.findElement(updateButton).click();

            // Wait for the error message
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            By errorMessage = By.xpath("/html/body/div/div/div[2]/div/div/div/ul/li/ul");
            WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            String errorMessageText = errorMessageElement.getText();
            if (errorMessageText.contains("The requested quantity for \"Sony Xperia\" is not available.")) {
                System.out.println("Error message is displayed. Test passed!");
            } else {
                System.out.println("Error message is not as expected. Test failed.");
            }
            //AssertJUnit.assertEquals();

            // 5. Verify the error message

            // 6. Click on "EMPTY CART" link
            By emptyCartButton = By.xpath("/html/body/div/div/div[2]/div/div/div/form/table/tfoot/tr/td/button[2]");
            driver.findElement(emptyCartButton).click();

            // Wait for the "Shopping Cart is Empty" message
            By emptyCartMessage = By.xpath("//*[@id='top']/body/div/div/div[2]/div/div/div[1]/h1");
            WebElement emptyCartMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage));

            if (emptyCartMessageElement.getText().equalsIgnoreCase("Shopping Cart is Empty")) {
                System.out.println("Shopping Cart is empty. Test passed!");
            } else {
                System.out.println("Shopping Cart is not empty. Test failed.");
            }


            // 7. Verify cart is empty


            // Capture a screenshot
            captureScreenshot(driver, "TC03.png");

            // Debug purpose only
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 8. Quit browser session
        driver.quit();
    }

    // Include the captureScreenshot method from TC01
    public static void captureScreenshot(WebDriver driver, String fileName) {
        if (driver instanceof TakesScreenshot) {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(screenshotFile.toPath(), new File(fileName).toPath());
                System.out.println("Screenshot saved as: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
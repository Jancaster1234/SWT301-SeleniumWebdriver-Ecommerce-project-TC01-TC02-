package BAITAP;

//The client wants you to verify the following scenario: “Verify that cost of product in list page and details page are equal”.

import driver.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

/*

Test Steps:

1. Goto http://live.techpanda.org/

2. Click on �MOBILE� menu

3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)

4. Click on Sony Xperia mobile

5. Read the Sony Xperia mobile from detail page.

6. Compare Product value in list and details page should be equal ($100).

*/
public class TC02 {
    @Test
    public static void testTC02() {
        // 0. Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            // 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            // 2. Click on MOBILE menu
            By mobileMenuSelector = By.cssSelector("a[href='http://live.techpanda.org/index.php/mobile.html']");
            driver.findElement(mobileMenuSelector).click();

// Step 3: Read the cost of Sony Xperia mobile
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            By sonyXperiaPriceSelector = By.xpath("//h2[@class='product-name']/a[contains(text(), 'Sony Xperia')]/following::span[@class='price']");
            WebElement sonyXperiaPriceElement = wait.until(ExpectedConditions.presenceOfElementLocated(sonyXperiaPriceSelector));
            String listPagePrice = sonyXperiaPriceElement.getText();

// Step 4: Click on Sony Xperia mobile
            By sonyXperiaLinkSelector = By.cssSelector("a[title='Sony Xperia']");
            driver.findElement(sonyXperiaLinkSelector).click();

// Step 5: Read the Sony Xperia mobile from detail page
            By detailPagePriceSelector = By.cssSelector("div.price-box span.price");
            WebElement detailPagePriceElement = driver.findElement(detailPagePriceSelector);
            String detailPagePrice = detailPagePriceElement.getText();

// Step 6: Compare Product value in list and details page
            if (listPagePrice.equals(detailPagePrice)) {
                System.out.println("The product cost in the list page and details page is equal. Test passed!");
            } else {
                System.out.println("The product cost in the list page and details page is not equal. Test failed.");
            }


            // Capture a screenshot
            captureScreenshot(driver, "TC02.png");

            // Debug purpose only
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 7. Quit browser session
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

package BAITAP;

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
The next scenario is “Verify that you are able to compare two product”

This will need you to work with pop-ups.

/*

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on �MOBILE� menu

3. In mobile products list , click on �Add To Compare� for 2 mobiles (Sony Xperia & Iphone)

4. Click on �COMPARE� button. A popup window opens

5. Verify the pop-up window and check that the products are reflected in it

Heading "COMPARE PRODUCTS" with selected products in it.

6. Close the Popup Windows

*/
public class TC04 {
    @Test
    public static void testTC04() {
        // 0. Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            // 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            // 2. Click on MOBILE menu
            By mobileMenuSelector = By.cssSelector("a[href='http://live.techpanda.org/index.php/mobile.html']");
            driver.findElement(mobileMenuSelector).click();

            // 3. In mobile products list , click on �Add To Compare� for 2 mobiles (Sony Xperia & Iphone)
            By sonyXperiaCompareLink = By.xpath("/html/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[2]/div/div[3]/ul/li[2]/a");
            driver.findElement(sonyXperiaCompareLink).click();

            By iPhoneCompareLink = By.xpath("/html/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[3]/div/div[3]/ul/li[2]/a");
            driver.findElement(iPhoneCompareLink).click();

            // 4. Click on "COMPARE" button. A popup window opens
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            By compareButton = By.xpath("//button[@title='Compare']");
            WebElement compareButtonElement = wait.until(ExpectedConditions.elementToBeClickable(compareButton));
            compareButtonElement.click();

            // Switch to the popup window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            // 5. Verify the pop-up window and check that the products are reflected in it
            WebElement compareHeading = driver.findElement(By.cssSelector("h1"));
            String compareHeadingText = compareHeading.getText();
            if (compareHeadingText.equals("COMPARE PRODUCTS")) {
                System.out.println("Popup window opened with the correct heading. Test passed!");
            } else {
                System.out.println("Popup window heading is not as expected. Test failed.");
            }

            // Verify the presence of Sony Xperia and iPhone links in the popup window
            By sonyXperiaLink = By.xpath("/html/body/div/table/tbody[1]/tr[1]/td[1]/h2/a");
            By iPhoneLink = By.xpath("/html/body/div/table/tbody[1]/tr[1]/td[2]/h2/a");

            if (driver.findElement(sonyXperiaLink).isDisplayed() && driver.findElement(iPhoneLink).isDisplayed()) {
                System.out.println("Sony Xperia and iPhone links are displayed in the popup window. Test passed!");
            } else {
                System.out.println("Sony Xperia and iPhone links are not displayed in the popup window. Test failed.");
            }


            // Capture a screenshot
            captureScreenshot(driver, "TC04.png");

            // 6. Close the Popup Window
            driver.close();

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

package BAITAP;
import POM.CartPage;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TC09 {
    @Test
    public void TC09() {

        //1. Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        CartPage cartPage = new CartPage(driver);
        try {
            //Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/index.php/");

            //Step 2. Click on �MOBILE� menu
            WebElement mobile = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > header:nth-child(2) > div:nth-child(1) > div:nth-child(4) > nav:nth-child(1) > ol:nth-child(1) > li:nth-child(1) > a:nth-child(1)"));
            mobile.click();
            Thread.sleep(1000);
            cartPage.addIphoneToCart();

            //Step 3. Enter Coupon Code
            double oldGrand = Double.parseDouble(cartPage.getInitGrandTotal());
            cartPage.enterDiscountCode("GURU50");
            cartPage.clickApplyDiscount();

            //Step 4. Verify the discount generated
            System.out.println(cartPage.discountGenerated());
            AssertJUnit.assertFalse(cartPage.discountGenerated().isEmpty());
            Thread.sleep(1000);
            captureScreenshot(driver, "TC09.png");
            cartPage.verifyDiscount(oldGrand);

            Thread.sleep(2000);
            captureScreenshot(driver, "TC09.png");

        }catch (Exception e){
            e.printStackTrace();
        }
        //7. Quit browser session
        driver.quit();
    }
    public static void captureScreenshot(WebDriver driver, String fileName) {
        if (driver instanceof TakesScreenshot) {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File targetFile = new File(fileName);

            try {
                // Check if the target file already exists, and if so, delete it.
                if (targetFile.exists()) {
                    targetFile.delete();
                }

                Files.copy(screenshotFile.toPath(), targetFile.toPath());
                System.out.println("Screenshot saved as: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

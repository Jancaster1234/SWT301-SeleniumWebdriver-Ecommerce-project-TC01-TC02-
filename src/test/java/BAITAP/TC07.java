package BAITAP;
import POM.LoginPage;
import POM.RegisterPage;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/*

Test Steps

1. Go to http://live.techpanda.org/

2. Click on My Account link

3. Login in application using previously created credential

4. Click on 'My Orders'

5. Click on 'View Order'

6. Click on 'Print Order' link

*/
public class TC07 {
    @Test
    public static void TC07() {

        //1. Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        try {
            //Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/index.php/");
            //Step 2. Click on My Account link
            registerPage.clickMyAccountLink();
            //Step 3. Login in application using previously created credential
            loginPage.enterEmailAddress("testuser101@example.com");
            loginPage.enterPassword("password");
            loginPage.clickLoginButton();
            Thread.sleep(1000);

            //Step 4. Click on 'My Orders'
            WebElement myOrder = driver.findElement(By.linkText(("MY ORDERS")));
            myOrder.click();
            Thread.sleep(2000);
            //Step 5. Click on 'View Order'
            WebElement viewOrder = driver.findElement(By.xpath("//tr[@class='first odd']//a[text()='View Order']"));
            viewOrder.click();
            //Step 6. Click on 'Print Order' link
            WebElement print = driver.findElement(By.cssSelector(".link-print"));
            print.click();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(1000);
            captureScreenshot(driver, "TC07.png");

        }catch (Exception e){
            e.printStackTrace();
        }
        //7. Quit browser session
        driver.quit();
    }
    // Include the captureScreenshot method from TC01
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
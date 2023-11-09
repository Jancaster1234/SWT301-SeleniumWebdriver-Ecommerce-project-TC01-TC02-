package BAITAP;

import POM.BackendLogin;
import POM.LoginPage;
import POM.OrderMenuPage;
import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TC10 {
    @Test
    public  void TC10() {
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            // 1. Go to http://live.techpanda.org/index.php/backendlogin
            driver.get("http://live.techpanda.org/index.php/backendlogin");

            // 2. Login the credentials provided
            BackendLogin loginPage = new BackendLogin(driver);
            loginPage.enterUsername("user01");
            loginPage.enterPassword("guru99com");
            loginPage.clickLoginButton();

            // 3. Go to Sales-> Orders menu
            OrderMenuPage o = new OrderMenuPage(driver);
            //o.selectOrdersLink("//*[@id=\"nav\"]/li[1]/ul/li[1]/a/span");
            o.clickOrdersLinkLocator();

            // 4. Input OrderId and FromDate -> ToDate
            o.enterOrderId("100021247");
            o.enterFromDateInputLocator("11/7/2023");
            o.enterToDateInputLocator("11/10/2023");

            // 5. Click Search button
            o.clickSearchButtonLocator();
            Thread.sleep(2000);

            // 6. Screenshot capture.
            captureScreenshot(driver, "TC10.png");

            Thread.sleep(2000);

        }  catch (Exception e) {
        e.printStackTrace();
    }

    // 11. Quit browser session
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

    public static double parseCurrencyToDouble(String currencyString) {
        // Remove currency symbols, commas, and other non-numeric characters
        String cleanedString = currencyString.replaceAll("[^0-9.]", "");
        // Parse the cleaned string as a double
        return Double.parseDouble(cleanedString);
    }
}

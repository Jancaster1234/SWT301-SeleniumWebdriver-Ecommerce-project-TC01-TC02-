package BAITAP;

import driver.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLOutput;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*

Test Steps

Step 1. Go to http://live.techpanda.org/

Step 2. Verify Title of the page

Step 3. Click on -> MOBILE -> menu

Step 4. In the list of all mobile , select SORT BY -> dropdown as name

Step 5. Verify all products are sorted by name

*/

public class TC01 {
    @Test
    public static void testTC01() {
        //0. Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            //1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //2. Verify the title of the page
            By titleSelector = By.tagName("title");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement titleElement = wait.until(ExpectedConditions.presenceOfElementLocated(titleSelector));
            String titleText = titleElement.getText();
            if (titleText.trim().contains("Home page")) {
                System.out.println("Title contains 'Home page'. Test passed!");
            } else {
                System.out.println("Title does not contain 'Home page'. Test failed.");
            }

            //3. Click on MOBILE menu
            By linkSelector = By.cssSelector("a[href='http://live.techpanda.org/index.php/mobile.html']");
            driver.findElement(linkSelector).click();

            //4. Select SORT BY dropdown option as "Name"
            By dropdownSelector = By.cssSelector("select[title='Sort By']");
            WebElement sortDropdown = driver.findElement(dropdownSelector);
            Select dropdown = new Select(sortDropdown);
            dropdown.selectByValue("http://live.techpanda.org/index.php/mobile.html?dir=asc&order=name");

            //5. Verify if all products are sorted by name
            By productListSelector = By.cssSelector("ul.products-grid li.item h2.product-name a");
            List<WebElement> productElements = driver.findElements(productListSelector);
            List<String> productNames = new ArrayList<>();

            for (WebElement productElement : productElements) {
                productNames.add(productElement.getText());
            }

            List<String> sortedProductNames = new ArrayList<>(productNames);
            Collections.sort(sortedProductNames);

            if (productNames.equals(sortedProductNames)) {
                System.out.println("The list of products is sorted by name. Test passed!");
            } else {
                System.out.println("The list of products is not sorted by name. Test failed.");
            }

            // Capture a screenshot
            captureScreenshot(driver, "TC01.png");

            //debug purpose only
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //6. Quit browser session
        driver.quit();
    }
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

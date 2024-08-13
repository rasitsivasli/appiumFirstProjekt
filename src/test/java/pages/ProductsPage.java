package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import static org.testng.Assert.assertTrue;

public class ProductsPage {
    AndroidDriver driver;

    public  ProductsPage(AndroidDriver androidDriver) {
        driver=androidDriver;
    }
    public By resultPopup = By.id("trendyol.com:id/textViewTooltipTitle");

    public void checkResultPopup() {
        assertTrue(driver.findElement(resultPopup).isDisplayed());
    }
}

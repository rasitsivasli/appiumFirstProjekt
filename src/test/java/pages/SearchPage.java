package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;

public class SearchPage {
    AndroidDriver driver;
    public SearchPage(AndroidDriver androidDriver){
        driver = androidDriver;
    }
    By searchBox = By.id("trendyol.com:id/edittext_search_view");

    public void searching(String keyword) {
        driver.findElement(searchBox).click();
        driver.findElement(searchBox).sendKeys(keyword);
        driver.pressKey(new KeyEvent(AndroidKey.SEARCH));
    }
}

package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KampanyaPage {
    public KampanyaPage(AndroidDriver androidDriver) {
        PageFactory.initElements(androidDriver, this);
    }
    @FindBy(id="trendyol.com:id/imageButtonClose")
    public WebElement closeButton;
}

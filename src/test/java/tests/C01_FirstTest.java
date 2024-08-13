package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class C01_FirstTest {
    AndroidDriver androidDriver;
    GenderPage gp;
    KampanyaPage kp;
    SearchPage sp;
    HomePage hp;
    ProductsPage pp;
    @BeforeMethod
    public void setup() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setUdid("emulator-5554")
                .setAppPackage("trendyol.com")
                .setAppActivity("com.trendyol.common.splash.impl.ui.SplashActivity")
                .setNoReset(false);

        androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        gp = new GenderPage(androidDriver);
        kp = new KampanyaPage(androidDriver);
        hp = new HomePage();
        sp = new SearchPage(androidDriver);
        pp = new ProductsPage(androidDriver);
    }
    @Test
    public void test4(){
        //saat araması yap, aramanın doğru şekilde yapıldığını doğrula.
        gp.womanButton.click();
        kp.closeButton.click();
        androidDriver.findElement(hp.popupCloseButton).click();
        sp.searching("saat");
        pp.checkResultPopup();
    }
}

package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utilities.ReusableMethods;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class C02_UsageOfReusableMethods {

    AndroidDriver androidDriver;
    GenderPage gp;
    KampanyaPage kp;

    SearchPage sp;

    HomePage hp;

    ProductsPage pp;

    ReusableMethods methods;

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
        methods = new ReusableMethods(androidDriver);
    }
    @AfterMethod
    public void tearDown(){
        androidDriver.quit();
    }
    @Test
    public  void test1() throws InterruptedException {
        methods.volumeUp();
        Thread.sleep(2000);
        androidDriver.pressKey(new KeyEvent(AndroidKey.VOLUME_UP));
        Thread.sleep(2000);
        androidDriver.pressKey(new KeyEvent(AndroidKey.VOLUME_UP));
        Thread.sleep(2000);
        methods.volumeDown();
        Thread.sleep(2000);
        androidDriver.pressKey(new KeyEvent(AndroidKey.VOLUME_DOWN));
        Thread.sleep(2000);
        methods.clickBackButton();
        Thread.sleep(2000);
        methods.clickHomeButton();
        Thread.sleep(2000);
        methods.clickAppSwitchButton();
        Thread.sleep(2000);

        androidDriver.pressKey(new KeyEvent(AndroidKey.CAMERA));
        Thread.sleep(2000);
        androidDriver.pressKey(new KeyEvent(AndroidKey.KEYCODE_ZOOM_IN));
        Thread.sleep(2000);
        androidDriver.pressKey(new KeyEvent(AndroidKey.KEYCODE_ZOOM_IN));
        Thread.sleep(2000);
        androidDriver.pressKey(new KeyEvent(AndroidKey.KEYCODE_ZOOM_OUT));
        Thread.sleep(2000);
    }
    @Test
    public void test2(){
        methods.appYuklumu("com.pozitron.hepsiburada");

        methods.activateApp("trendyol.com");
        methods.terminateApp("trendyol.com");
        methods.installApp("C:\\Users\\sivas\\Downloads\\Amazon Shopping_28.15.0.100_APKPure.xapk");
    }
    @Test
    public void test4() throws InterruptedException {
        methods.activateApp("com.amazon.mShop.android.shopping");
        methods.arkaplandaCalistir(5);
        Thread.sleep(2000);
        androidDriver.lockDevice();
        if (methods.cihazKapalimi()){
            androidDriver.unlockDevice();
        }
        Thread.sleep(2000);
        androidDriver.lockDevice(Duration.ofSeconds(5));
    }
    @Test
    public void test5() throws InterruptedException {
        methods.bildirimleriAc();
        methods.wifi();
        methods.konum();
        methods.ucakModu();
        Thread.sleep(2000);
        methods.ucakModu();
        methods.wifi();
        methods.konum();
    }
    @Test
    public void test6() throws InterruptedException {
        //methods.sendFakeMessage("05323333333","Hello TR4");
        methods.fakeAramaYap("05051111000");
        Thread.sleep(6000);
        //methods.aramayiKapat("05051111000");
    }
    @Test
    public void test7() throws InterruptedException {
        gp.womanButton.click();
        kp.closeButton.click();
        androidDriver.findElement(hp.popupCloseButton).click();
        //methods.scrollDown();
        //methods.scrollDown();
        //methods.scrollDown();
        //methods.scrollDown();
        //methods.scrollUp();
        //methods.scrollUp();
        //methods.scrollUp();
        //methods.scrollUp();
        //methods.scrollUp();
        //methods.scrollUp();
        //methods.scrollUp();
        methods.finalScrollMethod(hp.ikinciEl);
    }
}

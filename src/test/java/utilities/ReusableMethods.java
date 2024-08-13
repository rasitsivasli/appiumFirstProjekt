package utilities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.GsmCallActions;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.ScriptKey;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static java.time.Duration.ofMillis;

public class ReusableMethods {
    AndroidDriver driver;
    TouchAction touchAction1; // Bu class deprecated olmuştur ve ileride kaldırılacaktır. Kullanılması tavsiye edilmez
                             // burada göstermek amacıyla yer verilmiştir.
    TouchAction touchAction2;

    MultiTouchAction multiTouch;
    public ReusableMethods(AndroidDriver webDriver) {
        driver = webDriver;
        touchAction1 = new TouchAction(webDriver);
        touchAction2 = new TouchAction(webDriver);
        multiTouch = new MultiTouchAction(webDriver);
    }

    // Temel PRESSKEY actions SendKeys ile yapamadıklarımız
    public void clickSearchButton(){
        driver.pressKey(new KeyEvent(AndroidKey.SEARCH));
    }
    public void clickEnterButton(){
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }
    public void volumeUp(){
        driver.pressKey(new KeyEvent(AndroidKey.VOLUME_UP));
    }
    public void volumeDown(){
        driver.pressKey(new KeyEvent(AndroidKey.VOLUME_DOWN));
    }
    public void clickBackButton(){
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }
    public void clickHomeButton(){
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }
    public void clickAppSwitchButton(){
        driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
    }

    public boolean appYuklumu(String appPackage){
        return driver.isAppInstalled(appPackage);
    }
    public void activateApp(String appPackage){
        //Kapalı olan Appi açar.
        driver.activateApp(appPackage);
    }
    public void terminateApp(String appPackage){
        //Açık olan Appi kapatır
        driver.terminateApp(appPackage);
    }
    public boolean keyboardAcikmi(){
        return driver.isKeyboardShown();
    }
    public void keyboardKapat(){
        //klavyeyi kapatır.
        driver.hideKeyboard();
    }
    public void removeApp(String appPackage){
        driver.removeApp(appPackage);
    }
    public void installApp(String path){
        //path: apk dosyasının yolu.
        driver.installApp(path);
    }
    public void arkaplandaCalistir(int seconds){
        //İçerisine yazdığım süre boyunca uygulamayı arka plana atar daha sonrasında tekrar öne getirir.
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }
    public boolean cihazKapalimi(){
        //false dönerse cihaz açık demektir.
        return driver.isDeviceLocked();
    }
    public void cihazKapat(){
        //cihazı kapatır.
        driver.lockDevice();
    }
    public void cihazAc(){
        //cihazı açar.
        driver.unlockDevice();
    }
    public void sifreGir(String password){
        driver.unpin(new ScriptKey(password));
    }
    public void ekraniYatayYap(){
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }
    public void ekraniDikeyYap(){
        driver.rotate(ScreenOrientation.PORTRAIT);
    }
    public void bildirimleriAc(){
        driver.openNotifications();
    }
    public void wifi(){
        //wifi açmaya veya kapatmaya yarar.
        driver.toggleWifi();
    }
    public void mobileData(){
        //cihaz internetini açmaya veya kapatmaya yarar.
        driver.toggleData();
    }
    public void konum(){
        //konumu açmaya veya kapatmaya yarar.
        driver.toggleLocationServices();
    }
    public void ucakModu(){
        //uçak modunu açmaya veya kapatmaya yarar.
        driver.toggleAirplaneMode();
    }
    public void sendFakeMessage(String phoneNumber, String message){
        driver.sendSMS(phoneNumber,message);
    }
    public void fakeAramaYap(String phoneNumber){
        //Aramaları yönetebilirim. Fake arama yapabilir, Aramayı Kabul Edebilir, Reddedebilir, Bekletebilirim
        driver.makeGsmCall(phoneNumber, GsmCallActions.CALL);
    }
    public void aramayiKapat(String phoneNumber){
        //Aramaları yönetebilirim. Fake arama yapabilir, Aramayı Kabul Edebilir, Reddedebilir, Bekletebilirim
        driver.makeGsmCall(phoneNumber, GsmCallActions.CANCEL);
    }
    public void scrollDown() {
        int startX = driver.manage().window().getSize().getWidth() / 2; //250
        int startY = driver.manage().window().getSize().getHeight() * 4 / 5; //1500
        int endx = driver.manage().window().getSize().getWidth() / 2; //250
        int endy = driver.manage().window().getSize().getHeight() / 5; //500

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "parmak");
        Sequence scroll = new Sequence(finger, 0);
        scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(finger.createPointerMove(ofMillis(500), PointerInput.Origin.viewport(), endx, endy));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(scroll));
    }

    public void scrollUp() {
        int startX = driver.manage().window().getSize().getWidth() / 2; //250
        int startY = driver.manage().window().getSize().getHeight() / 5; //500
        int endx = driver.manage().window().getSize().getWidth() / 2; //250
        int endy = driver.manage().window().getSize().getHeight() * 4 / 5; //1500

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "parmak");
        Sequence scroll = new Sequence(finger, 0);
        scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(finger.createPointerMove(ofMillis(500), PointerInput.Origin.viewport(), endx, endy));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(scroll));
    }

    public void swipeRight() {
        int startX = driver.manage().window().getSize().getWidth() / 5; //100
        int startY = driver.manage().window().getSize().getHeight() / 2; //1000
        int endx = driver.manage().window().getSize().getWidth() * 4 / 5; //400
        int endy = driver.manage().window().getSize().getHeight() / 2; //1000

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "sdadsaads");
        Sequence scroll = new Sequence(finger, 0);
        scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(finger.createPointerMove(ofMillis(500), PointerInput.Origin.viewport(), endx, endy));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(scroll));
    }

    public void swipeLeft() {
        int startX = driver.manage().window().getSize().getWidth() * 4 / 5; //400
        int startY = driver.manage().window().getSize().getHeight() / 2; //1000
        int endx = driver.manage().window().getSize().getWidth() * 1 / 5; //100
        int endy = driver.manage().window().getSize().getHeight() / 2; //1000

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "parmak");
        Sequence scroll = new Sequence(finger, 0);
        scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(finger.createPointerMove(ofMillis(500), PointerInput.Origin.viewport(), endx, endy));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(scroll));
    }
    public void baseScrollMethod(int startX, int startY, int endx, int endy) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "Scroll");
        Sequence scroll = new Sequence(finger, 0);
        scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(finger.createPointerMove(ofMillis(500), PointerInput.Origin.viewport(), endx, endy));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(scroll));
    }
    public void scrollDownInElement(By by) {
        WebElement element = driver.findElement(by);
        int startX = element.getRect().getX() + element.getSize().getWidth() / 2; //250
        int startY = element.getRect().getY() + element.getSize().getHeight() * 3 / 4; //1500
        int endx = element.getRect().getX() + element.getSize().getWidth() / 2; //250
        int endy = element.getRect().getY() + element.getSize().getHeight() * 1 / 4; //500
        baseScrollMethod(startX, startY, endx, endy);
    }
    public void swipeLeftInElement(By by) {
        WebElement element = driver.findElement(by);
        int startX = element.getRect().getX() + element.getSize().getWidth() * 3 / 4; //250
        int startY = element.getRect().getY() + element.getSize().getHeight() / 2; //1500
        int endx = element.getRect().getX() + element.getSize().getWidth() * 1 / 4; //250
        int endy = element.getRect().getY() + element.getSize().getHeight() / 2; //500
        baseScrollMethod(startX, startY, endx, endy);
    }
    public void finalScrollMethod(By by){
        // elementi buldumu bulmadı mı kontrol ediyorum. eğer elementi bulduysa false a çekiyor. bu sayede while dan çıkabiliyorum.
        boolean check = true;
        // sayfanın en sonundamıyım kontrol etmek için kullanıyorum. Şuanki page source u alıp daha kaydırdıktan sonraki page source ile kıyaslamak için.
        String pageSourceBeforeScrolling = driver.getPageSource();
        String pageSourceAfterScrolling;
        WebElement element = null;
        //Scroll sayımı kontrol etmek için. Sonsuz bi sayfa varsa sonsuza kadar kaydırmasın diye. Scroll sayısını filtreliyorum.
        int i = 0;
        do {
            // elementi bulmaya çalışıyorum. Bulamazsam findElement metodu hata verdiği için try catchin içine koyduk.
            try {
                element = driver.findElement(by);
            } catch (Exception e) {
            }
            //eğer elementi bulmuşsam checki false yap. Bu sayede while ın içinden çıkabil.
            if (element != null) {
                check = false;
            }
            // eğer elementi bulamamışsa scroll işlemlerime başla
            else {
                //Scroll işlemi
                scrollDown();
                //Scroll işleminden sonraki page source u alıyorum
                pageSourceAfterScrolling = driver.getPageSource();
                // Scrolldan sonraki page source ile önceki page source eşitse demekki sayfa sonuna geldim.
                // Veya i 10 olmuşsa 10 sefer ekranı kaydırmışım daha fazla gerek yok
                if (pageSourceBeforeScrolling.equals(pageSourceAfterScrolling) || i == 15) {
                    Assert.fail("Element bulunamadı");
                }
                //şimdiki page sourcum bir sonki işlem için previus olmak zorunda.
                else {
                    pageSourceBeforeScrolling = pageSourceAfterScrolling;
                }
            }
            //Scroll sayısı kontrolü yapmak için her döngüde 1 arttırıyorum
            i++;
        } while (check);
    }

    public void scrollUntilFindElement(AppiumBy by) {
        boolean check = true;
        String pageSourceBeforeScrolling = driver.getPageSource();
        String pageSourceAfterScrolling;
        WebElement element = null;
        int i = 0;

        do {
            try {
                element = driver.findElement(by);
            } catch (Exception e) {

            }
            if (element != null) {
                check = false;
            } else {
                scrollDown();
                pageSourceAfterScrolling = driver.getPageSource();
                if (pageSourceBeforeScrolling.equals(pageSourceAfterScrolling) || i == 10) {
                    Assert.fail("Element bulunamadı");
                } else {
                    pageSourceBeforeScrolling = pageSourceAfterScrolling;
                }
            }
            i++;
        } while (check);
    }
    public void elementZoomIn(By elementLocator){
        // Zoom yapmak istediğiniz öğeyi bulun (Örneğin, bir resim veya harita)
        WebElement element = driver.findElement(elementLocator);

        // Elementin konumu ve boyutunu alın
        int centerX = element.getLocation().getX() + (element.getSize().getWidth() / 2);
        int centerY = element.getLocation().getY() + (element.getSize().getHeight() / 2);

        // Zoom yapma işlemi için parmakların pozisyonları
        int startX1 = centerX - 100; // İlk parmak pozisyonu
        int startY1 = centerY - 100;
        int endX1 = centerX - 50;   // İlk parmak hareketi
        int endY1 = centerY - 50;

        int startX2 = centerX + 100; // İkinci parmak pozisyonu
        int startY2 = centerY + 100;
        int endX2 = centerX + 50;   // İkinci parmak hareketi
        int endY2 = centerY + 50;

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

        Sequence pinchAndZoom = new Sequence(finger1, 0);
        pinchAndZoom.addAction(finger1.createPointerMove(ofMillis(0), PointerInput.Origin.viewport(), startX1, startY1));
        pinchAndZoom.addAction(finger2.createPointerMove(ofMillis(0), PointerInput.Origin.viewport(), startX2, startY2));
        pinchAndZoom.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        pinchAndZoom.addAction(new Pause(finger1, ofMillis(100)));
        pinchAndZoom.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        pinchAndZoom.addAction(new Pause(finger1, ofMillis(1000))); // wait for a second
        pinchAndZoom.addAction(finger1.createPointerMove(ofMillis(600), PointerInput.Origin.viewport(), endX1, endY1));
        pinchAndZoom.addAction(finger2.createPointerMove(ofMillis(600), PointerInput.Origin.viewport(), endX2, endY2));
        pinchAndZoom.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        pinchAndZoom.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(pinchAndZoom));
    }
    //TouchAction ile dokunma
    public void touchActionTap(){
        touchAction1.tap(PointOption.point(100, 200)).perform();
    }
    // Yukarı kaydırma
    public void touchActionScrollDown(){
        touchAction1.press(PointOption.point(500, 1000)).moveTo(PointOption.point(500, 500)).release().perform();
    }
    // Aşağı kaydırma
    public void touchActionScrollUp(){
        touchAction1.press(PointOption.point(500, 500)).moveTo(PointOption.point(500, 1000)).release().perform();
    }
    // Uzun basma işlemi
    public void touchActionLongPress() {
        touchAction1.longPress(LongPressOptions.longPressOptions().withPosition(PointOption.point(300, 600)).withDuration(ofMillis(1000))).release().perform();
    }
    // Multi Touch
    public void multiTouchAction() {
        // İlk dokunma eylemi
        touchAction1.press(PointOption.point(100, 200)).release();

        // İkinci dokunma eylemi
        touchAction2.press(PointOption.point(300, 400)).release();

        // MultiTouchAction oluştur
        multiTouch.add(touchAction1).add(touchAction2).perform();
    }
    // TouchAction ile ZOOM
    public void touchActionZoom() {

        // İlk parmak pozisyonu
        PointOption firstFingerStart = PointOption.point(500, 500);
        // İkinci parmak pozisyonu
        PointOption secondFingerStart = PointOption.point(600, 600);
        // İlk parmak pozisyonu - zoom yaparken
        PointOption firstFingerEnd = PointOption.point(400, 400);
        // İkinci parmak pozisyonu - zoom yaparken
        PointOption secondFingerEnd = PointOption.point(700, 700);

        // İlk parmağı bas
        touchAction1.press(firstFingerStart).waitAction().moveTo(firstFingerEnd).release();

        // İkinci parmağı bas
        touchAction2.press(secondFingerStart).waitAction().moveTo(secondFingerEnd).release();

        // MultiTouchAction oluştur
        multiTouch.add(touchAction1).add(touchAction2).perform();
    }
}
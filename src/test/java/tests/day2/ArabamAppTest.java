package tests.day2;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ArabamAppTest {
AndroidDriver<AndroidElement> driver;

@BeforeTest
public void setUp() throws MalformedURLException {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel 2");
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"10.0");
    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
    capabilities.setCapability("appPackage", "com.dogan.arabam");
    // calisacak oldugumuz uygulamayi baslatabilmek icin kullandigimiz capability. Buraya uygulamanin kimlik bilgisini girerek uzerinde
    // calisacak oldugumuz uygulamayi baslatabiliriz
    capabilities.setCapability("appActivity","com.dogan.arabam.presentation.feature.home.HomeActivity");
    // appPackage i belirledikten sonra uzerinde calisacak oldugumuz uygulamanin hangi sayfasindan baslayacagimizi belirlemis oldugumuz
    // capability degeri
    driver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);


}


@Test
    public void arabamTest() throws InterruptedException {

    /*
    driver.activateApp("com.dogan.arabam");
     bu method uygulamanin ilk acilir penceresinden baslar

     */

    // 1. yol olarak uygulamanin kimlik bilgisi uzerinden uygulama driver methoduyla aktif hale getirilebilir.
    // uygulaminin basarili bir sekilde acildigi dogrulanir
    Assert.assertTrue(driver.isAppInstalled("com.dogan.arabam"));
    // uygulaminin basarili bir sekilde acildigi dogrulanir
    Assert.assertTrue(driver.findElementByAccessibilityId("İlan Ver").isDisplayed());
    // alt menuden ilan ara butonuna tiklanir
    driver.findElementByXPath("//*[@text='İlan Ara']").click();
    // kategori olarak otomobil secilir
    driver.findElementByXPath("//*[@text='Otomobil']").click();
    Thread.sleep(2000);
    // arac olarak Volkswagen secilir
    TouchAction action = new TouchAction<>(driver);
    action
            .press(PointOption.point(530,1553)) // ekranda kaydirma islemini baslatmak icin belirledigimiz ilk baslangic noktasi
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
            // kaydirma islemi gerceklesirken baslangic noktasi ile (press) ile son (moveTo) arasinda gecen zaman
            // Eger bu sure arttilirsa gidilen yol miktari kisalir yani daha az yol kat ederiz
            // Eger bu sure azaltilirsa gidilen yol mesafe miktari artar daha fazla yol kat etmis oluruz.
            .moveTo(PointOption.point(530,336)) // ekranda kaydirma islemini bitirmek icin kaydirdigimiz son nokta
            .release() // parmagimizi ekrandan kaldirma ve kaydirma islemini sonlandirma
            .perform(); // verilen action gorevlerini yerine getirme

       // driver.findElementByXPath("//*[@text='Volkswagen']").click();
        // 230 1289
        action.
                press(PointOption.point(230,1289))
                .release()
                .perform();

    // arac markasi olarak passat secilir
    Thread.sleep(2000);
    driver.findElementByXPath("//*[@text='Passat']").click();
    // 1.4 TSI BlueMotion secilir
    driver.findElementByXPath("//*[@text='1.4 TSi BlueMotion']").click();
    // Paket secimi yapilir
    driver.findElementByXPath("//*[@text='Highline']").click();
    // Ucuzdan pahaliya siralama yaparak filtreleme yapilir
    driver.findElementById("com.dogan.arabam:id/textViewSorting").click();
    driver.findElementByXPath("//*[@text='Fiyat - Ucuzdan Pahalıya']").click();
    // Gelen en ucuz aracin 500.000 tl den buyuk oldugu dogrulanir
    String enUcuzAracFiyati = driver.findElementByXPath("(//*[@resource-id='com.dogan.arabam:id/tvPrice'])[1]").getText();
    System.out.println(enUcuzAracFiyati); // 760.000 TL

    enUcuzAracFiyati=enUcuzAracFiyati.replaceAll("\\D","");
    System.out.println(enUcuzAracFiyati); // 760000

    Assert.assertTrue(Integer.parseInt(enUcuzAracFiyati)>500000);




}



}

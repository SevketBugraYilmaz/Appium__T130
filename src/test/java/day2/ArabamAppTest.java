package day2;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
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
    public void arabamTest() {

    /*
    driver.activateApp("com.dogan.arabam");
     bu method uygulamanin ilk acilir penceresinden baslar

     */

    // 1. yol olarak uygulamanin kimlik bilgisi uzerinden uygulama driver methoduyla aktif hale getirilebilir.
    // uygulaminin basarili bir sekilde acildigi dogrulanir
    Assert.assertTrue(driver.isAppInstalled("com.dogan.arabam"));
    // alt menuden ilan ara butonuna tiklanir
    Assert.assertTrue(driver.findElementByAccessibilityId("İlan Ver").isDisplayed());
    // kategori olarak otomobil secilir
    // arac olarak Volkswagen secilir
    // arac markasi olarak passat secilir
    // 1.4 TSI BlueMotion secilir
    // Paket secimi yapilir
    // Ucuzdan pahaliya siralama yaparak filtreleme yapilir
    // Gelen en ucuz aracin 500.000 tl den buyuk oldugu dogrulanir







}



}

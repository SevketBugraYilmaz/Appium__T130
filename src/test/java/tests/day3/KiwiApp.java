package tests.day3;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.KiwiPage;
import utils.ConfigReader;
import utils.Driver;
import utils.ReusableMethods;

public class KiwiApp {

    AndroidDriver<AndroidElement> driver = Driver.getAndroidDriver();
    KiwiPage page = new KiwiPage();


@Test
public void kiwiAppTest() throws InterruptedException {

    // uygulamanin yuklendigi dogrulanir
   Assert.assertTrue(driver.isAppInstalled(ConfigReader.getProperty("kiwiKimlik")));
    // uygulamanin basariyla acildigi dogrulanir
    Assert.assertTrue(page.misafirButonu.isDisplayed());
    // misafir olarak devam et e tiklanir
    page.misafirButonu.click();
    // ardinda gelecek olan 3 adimada yesil butona basilarak devam edilir
    TouchAction action= new TouchAction<>(driver);
    for (int i = 0; i < 3; i++) {
        ReusableMethods.koordinatTiklamaMethodu(541,1695);
    }

    // Trip type,one way olarak secilir
    Thread.sleep(2000);
    ReusableMethods.koordinatTiklamaMethodu(296,621);
    ReusableMethods.koordinatTiklamaMethodu(205,1454);
    // kalkis ulkesi secenegine tiklanir ve default olan ulke kaldirilir
    ReusableMethods.koordinatTiklamaMethodu(497,774);
    ReusableMethods.koordinatTiklamaMethodu(1020,139);
    // kalkis yapilacak ulke/sehir girilir ve sec e tiklanir
    if (driver.isKeyboardShown()){
        driver.getKeyboard().pressKey("Istanbul");
    }else{
        page.sehirEkleKutusu.sendKeys("Ankara");
    }
    Thread.sleep(2000);
    ReusableMethods.koordinatTiklamaMethodu(460,296);
    page.chooseButton.click();
    // varis ulkesi secenegine tiklanir ve gidilecek ulke girilir
    ReusableMethods.koordinatTiklamaMethodu(311,921);
    driver.getKeyboard().pressKey("Antalya");
    Thread.sleep(2000);
    ReusableMethods.koordinatTiklamaMethodu(292,289);
    page.chooseButton.click();
    // gidis tarihi eylul ayinin 21 i olarak secilir ve set date e tiklanir
    ReusableMethods.koordinatTiklamaMethodu(424,1052);
    ReusableMethods.koordinatTiklamaMethodu(966,1134);
    page.setDateButton.click();
    // search butonuna tiklanir
    page.searchButton.click();
    // en  ucuz ve aktarmasiz filtrelemeleri yapilir
    ReusableMethods.koordinatTiklamaMethodu(270,259);
    ReusableMethods.koordinatTiklamaMethodu(522,595);

    ReusableMethods.koordinatTiklamaMethodu(522,259);
    ReusableMethods.koordinatTiklamaMethodu(431,1458);

    Thread.sleep(3000);


    // gelen bilet fiyati kaydedilir ve kullanicin telefonuna sms olarak gonderilir
    String enUcuzTicketText=page.enUcuzTicket.getText();
    driver.sendSMS("555555555555","Kiwi uygulamasindaki en ucuz bilet fiyati:"+enUcuzTicketText);
    }
}

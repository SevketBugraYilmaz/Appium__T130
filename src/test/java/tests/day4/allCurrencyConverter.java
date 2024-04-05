package tests.day4;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AllCurrencyPage;
import utils.Driver;
import utils.ReusableMethods;

import java.io.File;
import java.io.IOException;

public class allCurrencyConverter {

    AndroidDriver<AndroidElement>driver= Driver.getAndroidDriver();
    AllCurrencyPage page = new AllCurrencyPage();



    @Test
    public void testAllCurrencyApp() throws InterruptedException, IOException {

     // all currency uygulamasinin yuklendigini dogrulanir
        Assert.assertTrue(driver.isAppInstalled("com.smartwho.SmartAllCurrencyConverter"));
    // uygulamanin acildigi dogrulanir
        Assert.assertTrue(page.toolBar.isDisplayed());
    // cevirmek istedigimiz para birimi zloty olarak secilir
        ReusableMethods.koordinatTiklamaMethodu(424,330);
        ReusableMethods.scrollWithUiScrollableAndClick("PLN");
    // cevirelecek olan para birimi Tl olarak secilir
        ReusableMethods.koordinatTiklamaMethodu(424,497);
        ReusableMethods.scrollWithUiScrollableAndClick("Turkish Lira");

        ReusableMethods.scrollWithUiScrollableAndClick("1");
        for (int i = 0; i < 3; i++) {
            page.sifir.click();
        }
    // cevrilen tutar screenShot olarak kaydedilir


        File ekranFotografi =driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(ekranFotografi,new File("plnToTry.jpg"));


        // ReusableMethods.getScreenshot("plnToZlotyNew");

        // Ardindan zloty nin tl karsiligi olan tl degeri kaydedilir

        String tlKarsiligi = page.sonucYazisi.getText();

        // kullaniciya sms olarak bildirilir

        driver.sendSMS("555555","1000 Zloty'nin TL karsiligi:"+ tlKarsiligi);

    }
}

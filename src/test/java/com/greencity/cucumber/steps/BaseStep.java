package com.greencity.cucumber.steps;

import com.greencity.modules.GreenCityGuest;
import com.greencity.modules.GreenCityLogged;
import com.greencity.utils.LocalStorageJS;
import com.greencity.utils.TestValueProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class BaseStep {

    protected static WebDriver driver;
    protected static List<String> createdPartners = new ArrayList<>();
    protected TestValueProvider testValueProvider = new TestValueProvider();
    protected static LocalStorageJS localStorageJS;
    protected static GreenCityGuest greencityGuest;
    protected static GreenCityLogged greencityLogged;

    @Step("init ChromeDriver")
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        for (String option : testValueProvider.getChromeOptions()) {
            options.addArguments(option);
        }

//        options.addArguments("--disable-notifications");
//        options.addArguments("--disable-popup-blocking");
//        options.addArguments("--headless");
//        options.addArguments("--user-data-dir=" + testValueProvider.getUserProfile().replace("%HOMEPATH%", System.getenv("HOMEPATH")));

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(testValueProvider.getImplicitlyWait()));

        localStorageJS = new LocalStorageJS(driver);
        greencityGuest = new GreenCityGuest(driver);
        greencityLogged = new GreenCityLogged(driver);
    }

    protected void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

//    @Step("set AccessToken")
//    public void setAccessToken() {
//        WebStorage webStorage = (WebStorage) new Augmenter().augment(driver);
//        LocalStorage localStorage = webStorage.getLocalStorage();
//        localStorage.setItem("AccessToken", provider.getAccessToken());
//        localStorage.setItem("RefreshToken", provider.getRefreshToken());
//    }


}

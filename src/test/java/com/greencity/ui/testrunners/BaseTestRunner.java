package com.greencity.ui.testrunners;

import com.greencity.data.LoginDto;
import com.greencity.modules.GreenCityGuest;
import com.greencity.modules.GreenCityLogged;
import com.greencity.ui.GreenCityLoginTest;
import com.greencity.ui.page.BasePage;
import com.greencity.ui.page.homepage.HomePage;
import com.greencity.utils.LocalStorageJS;
import com.greencity.utils.TestValueProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


public class BaseTestRunner {
    private static final String BASE_URL = "https://www.greencity.cx.ua/#/ubs";
    private static final Long ONE_SECOND_DELAY = 1000L;
    protected static Boolean isTestSuccessful = false;
    protected static LocalStorageJS localStorageJS;
    protected static GreenCityGuest greencityGuest;
    protected static GreenCityLogged greencityLogged;
    protected static GreenCityLoginTest greenCityLoginTest;
    protected static WebDriver driver;
    protected static TestValueProvider testValueProvider;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss-S";
    protected HomePage homePage;

    public static void presentationSleep() {
        presentationSleep(1);
    }

    // Overload
    public static void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY); // For Presentation ONLY
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void takeScreenShot() {
        String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./" + currentTime + "_screenshot.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            // Use Custom Exception
        }
    }

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
        testValueProvider = new TestValueProvider();
        initDriver();
    }

    public void setLocalStorage(LoginDto loginDto) {
        localStorageJS.setItemInLocalStorage("accessToken", loginDto.getAccessToken());
        localStorageJS.setItemInLocalStorage("language", "en");
        localStorageJS.setItemInLocalStorage("name", loginDto.getName());
        localStorageJS.setItemInLocalStorage("refreshToken", loginDto.getRefreshToken());
        localStorageJS.setItemInLocalStorage("userId", String.valueOf(loginDto.getUserId()));
    }

    @Step("init ChromeDriver")
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

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

    @BeforeClass
    public void beforeClass() {
        if (driver == null) {
            initDriver();
        }
        driver.get(testValueProvider.getBaseUIUrl());
        homePage = new HomePage(driver);
    }

    @AfterClass()
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void afterSuite() {
        if (driver != null) {
            driver.close();
        }
    }

    @Step("Clear Browser Memory Cookies and LocalStorage.")
    public void clearBrowserMemory() {
        driver.manage().deleteAllCookies();
        localStorageJS.clearLocalStorage();
        driver.navigate().refresh();
    }


    public HomePage loadApplication() {
        HomePage homePage = new HomePage(driver).goToHomePage();
        return homePage;
    }

}

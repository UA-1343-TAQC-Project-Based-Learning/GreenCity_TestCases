package com.greencity.ui.testrunners;

import com.greencity.ui.page.homepage.HomePage;
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
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


public class BaseTestRunner {
    private static final String BASE_URL = "https://www.greencity.cx.ua/#/ubs";
    private static final Long ONE_SECOND_DELAY = 1000L;
    private final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss-S";
    protected static Boolean isTestSuccessful = false;
    //
    protected static com.greencity.utils.LocalStorageJS localStorageJS;
    protected static com.greencity.modules.GreenCityGuest greencityGuest;
    protected static com.greencity.modules.GreenCityLogged greencityLogged;
    protected  final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected static WebDriver driver;

    protected static TestValueProvider testValueProvider;
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
    private void takeScreenShot() {
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

    private void takePageSource() {
        String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        String pageSource = driver.getPageSource();
        byte[] strToBytes = pageSource.getBytes();
        Path path = Paths.get("./" + currentTime + "_source.html");
        try {
            Files.write(path, strToBytes, StandardOpenOption.CREATE);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
        testValueProvider = new TestValueProvider();
        initDriver();
    }

    @Step("init ChromeDriver")
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
         ChromeOptions options = new ChromeOptions();

//        options.addArguments("--disable-notifications");
//        options.addArguments("--disable-popup-blocking");
//        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(testValueProvider.getImplicitlyWait()));

        localStorageJS = new com.greencity.utils.LocalStorageJS(driver);
        greencityGuest = new com.greencity.modules.GreenCityGuest(driver);
        greencityLogged = new com.greencity.modules.GreenCityLogged(driver);
        System.out.println("@BeforeAll executed");
    }




    @BeforeClass
    public void beforeClass() {
        if (driver == null){
            initDriver();
        }
        driver.get(testValueProvider.getBaseUIUrl());
        homePage = new HomePage(driver);
    }

    @AfterClass()
    public void afterClass() {
        if (driver != null) {
            driver.close();
        }
    }
    @AfterSuite
    public void afterSuite() {
        if (driver != null) {
            driver.close();
        }
    }
    @AfterTest
    public void tearThis(ITestContext testContext) {
        if (!isTestSuccessful) {
            logger.error("Test_Name = {} fail",  testContext.getName());
            // delete session
            takeScreenShot();
            takePageSource(); // Default sources
            // TODO JS sources
        }

        // Sign out
        // delete All Cookies;
        driver.manage().deleteAllCookies();
        // Clear session
        // delete All Tokens;
        //localStorageJS.clearLocalStorage();
        localStorageJS.removeItemFromLocalStorage("accessToken");
        localStorageJS.removeItemFromLocalStorage("refreshToken");
        //
        driver.navigate().refresh();
        presentationSleep(4); // For Presentation
        logger.info("\t@AfterTest executed");
    }
    protected void loadApplication() {
       driver.get(BASE_URL);
    }
}

package com.greencity.ui.testrunners;

import com.greencity.ui.page.homepage.HomePage;
import com.greencity.utils.TestValueProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.time.Duration;


public class BaseTestRunner {
    protected WebDriver driver;
    protected static TestValueProvider testValueProvider;
    protected HomePage homePage;

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
        testValueProvider = new TestValueProvider();
        initDriver();
    }

    @Step("init ChromeDriver")
    public void initDriver() {
        ChromeOptions options = new ChromeOptions();
        String userProfile = System.getenv("HOMEPATH") + "\\AppData\\Local\\Google\\Chrome\\User Data";
//        options.addArguments("--disable-notifications");
//        options.addArguments("--disable-popup-blocking");
//        options.addArguments("--headless");

        options.addArguments("--user-data-dir=" + userProfile);


        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(testValueProvider.getImplicitlyWait()));
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
            driver.quit();
        }
    }
    @AfterSuite
    public void afterSuite() {
        if (driver != null) {
            driver.quit();
        }
    }
}

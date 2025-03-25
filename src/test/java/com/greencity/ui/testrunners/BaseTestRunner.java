package com.greencity.ui.testrunners;

import com.greencity.ui.page.BasePage;
import com.greencity.ui.page.homepage.HomePage;
import com.greencity.utils.TestValueProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.time.Duration;


public class BaseTestRunner {
    protected WebDriver driver;
    protected static TestValueProvider testValueProvider;
    protected HomePage homePage;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
        testValueProvider = new TestValueProvider();
        initDriver();
    }

    @Step("init ChromeDriver")
    public void initDriver() {
        ChromeOptions options = new ChromeOptions();


//        options.addArguments("--disable-notifications");
//        options.addArguments("--disable-popup-blocking");
//        options.addArguments("--headless");

        options.addArguments("--user-data-dir=/home/hembei/.config/google-chrome-selenium/");
        options.addArguments("--profile-directory=Default");
//        options.addArguments("--user-data-dir=" + testValueProvider.getUserProfile().replace("%HOMEPATH%", System.getenv("HOMEPATH")));


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

    public HomePage loadApplication() {
        HomePage homePage = new HomePage(driver).goToHomePage();
        return new HomePage(driver);
    }

}

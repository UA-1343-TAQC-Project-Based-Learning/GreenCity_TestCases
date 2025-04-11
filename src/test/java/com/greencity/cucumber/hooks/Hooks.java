package com.greencity.cucumber.hooks;

import com.greencity.utils.TestValueProvider;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import java.time.Duration;


public class Hooks {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Getter
    private WebDriver driver;

    @Getter
    private TestValueProvider testValueProvider = new TestValueProvider();

    @Getter
    private SoftAssert softAssert;

    @Before
    public void driverSetup() {
        if (driver == null) {
            initDriver();
        }
        logger.info("WebDriver started");

        softAssert = new SoftAssert();
        driver.get(testValueProvider.getBaseUIUrl());
    }

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
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        logger.info("Driver closed");

        softAssert.assertAll();
    }

}

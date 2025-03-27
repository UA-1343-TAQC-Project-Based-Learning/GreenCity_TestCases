package com.greencity.ui.page;

import com.greencity.ui.Base;
import com.greencity.ui.component.footer.FooterComponent;
import com.greencity.ui.component.header.HeaderComponent;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.user.UsersHeaderComponent;
import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;


public abstract class BasePage extends Base {
    @Getter
    protected HeaderComponent header;
    @Getter
    protected UsersHeaderComponent loggedHeader;
    @Getter
    protected FooterComponent footer;

    @Getter
    @FindBy(xpath = "//app-header")
    private WebElement headerRoot;
    @FindBy(xpath = "//footer")
    private WebElement FooterRoot;

    public BasePage(WebDriver driver) {
        super(driver);
        header = new HeaderComponent(driver, headerRoot);
        footer = new FooterComponent(driver, FooterRoot);
        loggedHeader = new UsersHeaderComponent(driver, headerRoot);
    }


    private int getContentHeight() {
        return ((Number) Objects.requireNonNull(threadJs.executeScript("return document.body.scrollHeight;"))).intValue();
    }


    public void waitForPageToLoad(long timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds)).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public Boolean isElementInvisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public EcoNewsPage gotoEcoNewsPage(){
        header.clickEcoNewsLink();
        return new EcoNewsPage(driver);
    }
}
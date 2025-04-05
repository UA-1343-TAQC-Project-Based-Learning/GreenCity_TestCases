package com.greencity.ui.page.homepage;

import com.greencity.ui.component.homePage.EventsHomeComponent;
import com.greencity.ui.component.homePage.MainHomeComponent;
import com.greencity.ui.component.homePage.StatsHomeComponent;
import com.greencity.ui.component.homePage.SubscriptionHomeComponent;
import com.greencity.ui.page.BasePage;
import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @Getter
    private final MainHomeComponent mainHome;

    @Getter
    private final StatsHomeComponent statsHome;

    @Getter
    private final EventsHomeComponent eventsHome;

    @Getter
    private final SubscriptionHomeComponent subscriptionHome;

    @Getter
    @FindBy(xpath = "//div[@id='main-content']")
    private WebElement mainHomeRoot;

    @Getter
    @FindBy(xpath = "//section[@id='stats']")
    private WebElement statsHomeRoot;

    @Getter
    @FindBy(xpath = "//section[@id='events']")
    private WebElement eventsHomeRoot;

    @Getter
    @FindBy(xpath = "//section[@id='subscription']")
    private WebElement subscriptionHomeRoot;

    public HomePage(WebDriver driver) {
        super(driver);
        this.mainHome = new MainHomeComponent(driver, mainHomeRoot);
        this.statsHome = new StatsHomeComponent(driver, statsHomeRoot);
        this.eventsHome = new EventsHomeComponent(driver, eventsHomeRoot);
        this.subscriptionHome = new SubscriptionHomeComponent(driver, subscriptionHomeRoot);
    }

    public MainHomeComponent getMainHome() {
        return mainHome;
    }

    public StatsHomeComponent getStatsHome() {
        return statsHome;
    }

    public EventsHomeComponent getEventsHome() {
        return eventsHome;
    }

    public SubscriptionHomeComponent getSubscriptionHome() {
        return subscriptionHome;
    }

    public CreateEditNewsPage goToCreateEcoNewsPage() {
        return openEcoNewsPage().clickCreateNewsButton();
    }

    public EcoNewsPage openEcoNewsPage() {
         header.clickEcoNewsLink();
        return new EcoNewsPage(driver);
    }
}
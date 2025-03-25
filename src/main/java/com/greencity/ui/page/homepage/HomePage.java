package com.greencity.ui.page.homepage;

import com.greencity.ui.modal.LoginModal;
import com.greencity.ui.page.BasePage;
import com.greencity.ui.page.econewspage.CreateNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.page.UbsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {

    //main-content
    @Getter
    @FindBy(xpath = "//div[@id='main-content']/h1")
    private WebElement headerTitle;

    @Getter
    @FindBy(xpath = "//div[@id='main-content']/p")
    private WebElement headerParagraph;

    @Getter
    @FindBy(xpath = "//div[@id='main-content']/button[@class='primary-global-button btn']")
    private WebElement headerButton;

    @Getter
    @FindBy(xpath = "//img[@id='guy-image']")
    private WebElement guyImage;

    // Stat-section
    @Getter
    @FindBy(xpath = "//section[@id='stats'] //h2[@class='section-caption']")
    private WebElement statHeader;

    @Getter
    @FindBy(xpath = "//div[contains(@style, 'margin-left')]//img[@alt='stat-icon']")
    private WebElement bagImage;

    @Getter
    @FindBy(xpath = "//div[contains(@style, 'margin-right')]//img[@alt='stat-icon']")
    private WebElement cupImage;

    // margin-right
    @Getter
    @FindBy(xpath = "//div[contains(@style, 'margin-right')]/h3")
    private WebElement statRightHeaderBags;

    @Getter
    @FindBy(xpath = "//div[contains(@style, 'margin-right')]/p")
    private WebElement statRightParagraphBags;

    @Getter
    @FindBy(xpath = "//div[contains(@style, 'margin-right')]//span")
    private WebElement statRightSpanBags;

    @Getter
    @FindBy(xpath = "//div[contains(@style, 'margin-right')]/button[@class='primary-global-button btn']")
    private WebElement statRightButton;

    @Getter
    @FindBy(xpath = "//div[contains(@style, 'margin-right')]//img[@alt='location-image']")
    private WebElement statRightIcon;

    @Getter
    @FindBy(xpath = "//div[contains(@style, 'margin-right')]//a[@class='tertiary-global-button btn-link']")
    private WebElement statRightLink;

    // margin-left
    @Getter
    @FindBy(xpath = "//section[@id='stats'] //h2[@class='section-caption']")
    private WebElement statLeftHeader;

    @Getter
    @FindBy(xpath = "//div[contains(@style, 'margin-left')]/h3")
    private WebElement statLeftHeaderCups;

    @Getter
    @FindBy(xpath = "//div[contains(@style, 'margin-left')]/p")
    private WebElement statLeftParagraphCups;

    @Getter
    @FindBy(xpath = "//div[contains(@style, 'margin-left')]//span")
    private WebElement statLeftSpanCups;

    @Getter
    @FindBy(xpath = "//div[contains(@style, 'margin-left')]/button[@class='primary-global-button btn']")
    private WebElement statLeftButton;

    @Getter
    @FindBy(xpath = "//div[contains(@style, 'margin-left')]//img[@alt='location-image']")
    private WebElement statLeftIcon;

    @Getter
    @FindBy(xpath = "//div[contains(@style, 'margin-left')]//a[@class='tertiary-global-button btn-link']")
    private WebElement statLeftLink;

    // Event-section
    @Getter
    @FindBy(xpath = "//section[@id='events'] //h2[@class='section-caption']")
    private WebElement eventsHeader;

    @Getter
    @FindBy(xpath = "//section[@id='events'] //a[@aria-label='link to eco-news page']")
    private WebElement eventsLink;

    @Getter
    @FindBy(xpath = "//section[@id='events'] //img[@alt='arrow']")
    private WebElement eventsArrowLink;

    // Subscription-section
    @Getter
    @FindBy(xpath = "//section[@id='subscription']//h2")
    private WebElement subscriptionHeader;

    @Getter
    @FindBy(xpath = "//section[@id='subscription']//p[not(@id='validation-error')]")
    private WebElement subscriptionParagraph;

    @Getter
    @FindBy(xpath = "//section[@id='subscription']//img[contains(@alt, 'Scan this QR-code')]")
    private WebElement subscriptionImgQrCode;

    @Getter
    @FindBy(xpath = "//div[@class='form-input']//input[@type='email']")
    private WebElement subscriptionInputEmail;

    @Getter
    @FindBy(xpath = "//div[@id='form-wrapper']//p[@id='validation-error']")
    private WebElement subscriptionError;

    @Getter
    @FindBy(xpath = "//div[@id='form-wrapper']//button[@class='primary-global-button btn']")
    private WebElement subscriptionButton;

    @Getter
    @FindBy(xpath = "//mat-dialog-container")
    private WebElement loginModalRoot;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    //Main-header
    public String getHeaderTitleText() {
        return headerTitle.getText();
    }

    public String getHeaderParagraphText() {
        return headerParagraph.getText();
    }

    public String getHeaderButtonText() {
        return headerButton.getText();
    }

    public LoginModal clickHeaderButton() {
        waitUntilElementClickable(headerButton);
        headerButton.click();
        return new LoginModal(driver, loginModalRoot);
    }

    public boolean isGuyImageDisplayed() {
        return guyImage.isDisplayed();
    }


    // Stat-section
    public String getStatHeaderText() {
        return statHeader.getText();
    }

    public boolean isBagImageDisplayed() {
        return bagImage.isDisplayed();
    }

    public boolean isCupImageDisplayed() {
        return cupImage.isDisplayed();
    }

    // margin-right
    public String getStatRightHeaderBagsText() {
        return statRightHeaderBags.getText();
    }

    public String getStatRightParagraphBagsText() {
        return statRightParagraphBags.getText();
    }

    public String getStatRightSpanBagsText() {
        return statRightSpanBags.getText();
    }

    public String getStatRightButtonText() {
        return statRightButton.getText();
    }

    public UbsPage clickStatRightButton() {
        waitUntilElementClickable(statRightButton);
        statRightButton.click();
        return new UbsPage(driver);

    }

    public boolean isStatRightIconDisplayed() {
        return statRightIcon.isDisplayed();
    }

    public String getStatRightLinkText() {
        return statRightLink.getText();
    }

    public UbsPage clickStatRightLink() {
        waitUntilElementClickable(statRightLink);
        statRightLink.click();
        return new UbsPage(driver);
    }


    // margin-left
    public String getStatLeftHeaderCupsText() {
        return statLeftHeaderCups.getText();
    }

    public String getStatLeftParagraphCupsText() {
        return statLeftParagraphCups.getText();
    }

    public String getStatLeftSpanCupsText() {
        return statLeftSpanCups.getText();
    }


    public String getStatLeftButtonText() {
        return statLeftButton.getText();
    }

    public UbsPage clickStatLeftButton() {
        waitUntilElementClickable(statLeftButton);
        statLeftButton.click();
        return new UbsPage(driver);
    }

    public boolean isStatLeftIconDisplayed() {
        return statLeftIcon.isDisplayed();
    }

    public String getStatLeftLinkText() {
        return statLeftLink.getText();
    }

    public UbsPage clickStatLeftLink() {
        waitUntilElementClickable(statLeftLink);
        statLeftLink.click();
        return new UbsPage(driver);
    }

    // Event-section
    public String getEventsHeaderText() {
        return eventsHeader.getText();
    }

    public String getEventsLinkText() {
        return eventsLink.getText();
    }

    public EcoNewsPage clickEventsLink() {
        waitUntilElementClickable(eventsLink);
        eventsLink.click();
        return new EcoNewsPage(driver);
    }

    public EcoNewsPage clickEventsArrowLink() {
        waitUntilElementClickable(eventsArrowLink);
        eventsArrowLink.click();
        return new EcoNewsPage(driver);
    }


    // Subscription-section
    public String getSubscriptionHeaderText() {
        return subscriptionHeader.getText();
    }

    public String getSubscriptionParagraphText() {
        return subscriptionParagraph.getText();
    }

    public boolean isSubscriptionImgQrCodeDisplayed() {
        return subscriptionImgQrCode.isDisplayed();
    }

    public void enterSubscriptionEmail(String email) {
        waitUntilElementClickable(subscriptionInputEmail);
        subscriptionInputEmail.clear();
        subscriptionInputEmail.sendKeys(email);
        // verification that text was entered correctly
    }

    public String getSubscriptionErrorText() {
        return subscriptionError.getText();
    }


    public String getSubscriptionButtonText() {
        return subscriptionButton.getText();
    }

    public HomePage clickSubscriptionButton() {
        waitUntilElementClickable(subscriptionButton);
        subscriptionButton.click();
        return this;
    }

    public CreateNewsPage goToCreateEcoNewsPage() {
        return new HomePage(driver)
                .gotoEcoNewsPage()
                .clickCreateNewsButton();
    }

}

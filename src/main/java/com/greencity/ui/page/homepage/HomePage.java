package com.greencity.ui.page.homepage;

import com.greencity.ui.page.BasePage;
import com.greencity.ui.page.EcoNewsPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    //Main-header
    @Getter
    @FindBy(xpath = "//header //h1")
    private WebElement headerTitle;

    @Getter
    @FindBy(xpath = "//header //p")
    private WebElement headerParagraph;

    @Getter
    @FindBy(xpath = "//header //button[@class='primary-global-button btn']")
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


    public HomePage(WebDriver driver) {
        super(driver);
    }

    //Main-header
    public String getHeaderTitle() {
        waitUntilElementVisible(headerTitle);
        return headerTitle.getText();
    }

    public String getHeaderParagraph() {
        waitUntilElementVisible(headerParagraph);
        return headerParagraph.getText();
    }

    public String getHeaderButtonText() {
        waitUntilElementVisible(headerButton);
        return headerButton.getText();
    }

    public void clickHeaderButton() {
        waitUntilElementClickable(headerButton);
        headerButton.click();
    }

    public boolean isGuyImageDisplayed() {
        waitUntilElementVisible(guyImage);
        return guyImage.isDisplayed();
    }


    // Stat-section
    public String getStatHeaderText() {
        waitUntilElementVisible(statHeader);
        return statHeader.getText();
    }

    public boolean isBagImageDisplayed() {
        waitUntilElementVisible(bagImage);
        return bagImage.isDisplayed();
    }

    public boolean isCupImageDisplayed() {
        waitUntilElementVisible(cupImage);
        return cupImage.isDisplayed();
    }

    // margin-right
    public String getStatRightHeaderBagsText() {
        waitUntilElementVisible(statRightHeaderBags);
        return statRightHeaderBags.getText();
    }

    public String getStatRightParagraphBagsText() {
        waitUntilElementVisible(statRightParagraphBags);
        return statRightParagraphBags.getText();
    }

    public String getStatRightSpanBagsText() {
        waitUntilElementVisible(statRightSpanBags);
        return statRightSpanBags.getText();
    }

    public String getStatRightButtonText() {
        waitUntilElementVisible(statRightButton);
        return statRightButton.getText();
    }

    public void clickStatRightButton() {
        waitUntilElementClickable(statRightButton);
        statRightButton.click();
    }

    public boolean isStatRightIconDisplayed() {
        waitUntilElementVisible(statRightIcon);
        return statRightIcon.isDisplayed();
    }

    public String getStatRightLinkText() {
        waitUntilElementVisible(statRightLink);
        return statRightLink.getText();
    }

    public void clickStatRightLink() {
        waitUntilElementClickable(statRightLink);
        statRightLink.click();
    }


    // margin-left
    public String getStatLeftHeaderCupsText() {
        waitUntilElementVisible(statLeftHeaderCups);
        return statLeftHeaderCups.getText();
    }

    public String getStatLeftParagraphCupsText() {
        waitUntilElementVisible(statLeftParagraphCups);
        return statLeftParagraphCups.getText();
    }

    public String getStatLeftSpanCupsText() {
        waitUntilElementVisible(statLeftSpanCups);
        return statLeftSpanCups.getText();
    }


    public String getStatLeftButtonText() {
        waitUntilElementVisible(statLeftButton);
        return statLeftButton.getText();
    }

    public void clickStatLeftButton() {
        waitUntilElementClickable(statLeftButton);
        statLeftButton.click();
    }

    public boolean isStatLeftIconDisplayed() {
        waitUntilElementVisible(statLeftIcon);
        return statLeftIcon.isDisplayed();
    }

    public String getStatLeftLinkText() {
        waitUntilElementVisible(statLeftLink);
        return statLeftLink.getText();
    }

    public void clickStatLeftLink() {
        waitUntilElementClickable(statLeftLink);
        statLeftLink.click();
    }

    // Event-section
    public String getEventsHeaderText() {
        waitUntilElementVisible(eventsHeader);
        return eventsHeader.getText();
    }

    public String getEventsLinkText() {
        waitUntilElementVisible(eventsLink);
        return eventsLink.getText();
    }

    public void clickEventsLink() {
        waitUntilElementClickable(eventsLink);
        eventsLink.click();
    }

    public void clickEventsArrowLink() {
        waitUntilElementClickable(eventsArrowLink);
        eventsArrowLink.click();
    }


    // Subscription-section
    public String getSubscriptionHeaderText() {
        waitUntilElementVisible(subscriptionHeader);
        return subscriptionHeader.getText();
    }

    public String getSubscriptionParagraphText() {
        waitUntilElementVisible(subscriptionParagraph);
        return subscriptionParagraph.getText();
    }

    public boolean isSubscriptionImgQrCodeDisplayed() {
        waitUntilElementVisible(subscriptionImgQrCode);
        return subscriptionImgQrCode.isDisplayed();
    }

    public void enterSubscriptionEmail(String email) {
        waitUntilElementClickable(subscriptionInputEmail);
        subscriptionInputEmail.clear();
        subscriptionInputEmail.sendKeys(email);
        // verification that text was entered correctly
    }

    public String getSubscriptionErrorText() {
        waitUntilElementVisible(subscriptionError);
        return subscriptionError.getText();
    }


    public String getSubscriptionButtonText() {
        waitUntilElementVisible(subscriptionButton);
        return subscriptionButton.getText();
    }

    public void clickSubscriptionButton() {
        waitUntilElementClickable(subscriptionButton);
        subscriptionButton.click();
    }


    // Business Logic

   //  clickEventsArrowLink navigate to EcoNewsPage
    public EcoNewsPage openEcoNewsPageByEventsArrowLink() {
        clickEventsArrowLink();
        return new EcoNewsPage(driver);
    }


   //  clickEventsLink navigate to EcoNewsPage
    public EcoNewsPage openEcoNewsPageByEventsLink () {
        clickEventsLink();
        return new EcoNewsPage(driver);
    }

}

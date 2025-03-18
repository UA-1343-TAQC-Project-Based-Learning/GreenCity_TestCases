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


    public HomePage(WebDriver driver) {
        super(driver);
    }

    //Main-header
    public String getHeaderTitle() {
        return headerTitle.getText();
    }

    public String getHeaderParagraph() {
              return headerParagraph.getText();
    }

    public String getHeaderButtonText() {
        return headerButton.getText();
    }

    public void clickHeaderButton() {
        waitUntilElementClickable(headerButton);
        headerButton.click();
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

    public void clickStatRightButton() {
        waitUntilElementClickable(statRightButton);
        statRightButton.click();
    }

    public boolean isStatRightIconDisplayed() {
        return statRightIcon.isDisplayed();
    }

    public String getStatRightLinkText() {
        return statRightLink.getText();
    }

    public void clickStatRightLink() {
        waitUntilElementClickable(statRightLink);
        statRightLink.click();
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

    public void clickStatLeftButton() {
        waitUntilElementClickable(statLeftButton);
        statLeftButton.click();
    }

    public boolean isStatLeftIconDisplayed() {
        return statLeftIcon.isDisplayed();
    }

    public String getStatLeftLinkText() {
        return statLeftLink.getText();
    }

    public void clickStatLeftLink() {
        waitUntilElementClickable(statLeftLink);
        statLeftLink.click();
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

    public void clickSubscriptionButton() {
        waitUntilElementClickable(subscriptionButton);
        subscriptionButton.click();
    }



}

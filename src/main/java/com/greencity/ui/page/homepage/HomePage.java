package com.greencity.ui.page.homepage;

import com.greencity.ui.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    private WebDriver driver;


    @FindBy(xpath = "//header //h1")
    private WebElement headerTitle;

    @FindBy(xpath = "//header //p")
    private WebElement headerParagraph;

    @FindBy(xpath = "//header //button[@class='primary-global-button btn']")
    private WebElement headerButton;

    @FindBy(xpath = "//img[@id='guy-image']")
    private WebElement guyImage;


    @FindBy(xpath = "//section[@id='stats'] //h2[@class='section-caption']")
    private WebElement statHeader;

    @FindBy(xpath = "//div[contains(@style, 'margin-left')]//img[@alt='stat-icon']")
    private WebElement bagImage;

    @FindBy(xpath = "//div[contains(@style, 'margin-right')]/h3")
    private WebElement statRightHeaderBags;

    @FindBy(xpath = "//div[contains(@style, 'margin-right')]/p")
    private WebElement statRightParagraphBags;

    @FindBy(xpath = "//div[contains(@style, 'margin-right')]//span")
    private WebElement statRightSpanBags;

    @FindBy(xpath = "//div[contains(@style, 'margin-right')]/button[@class='primary-global-button btn']")
    private WebElement statRightButton;

    @FindBy(xpath = "//div[contains(@style, 'margin-right')]//img[@alt='location-image']")
    private WebElement statRightIcon;

    @FindBy(xpath = "//div[contains(@style, 'margin-right')]//a[@class='tertiary-global-button btn-link']")
    private WebElement statRightLink;


    @FindBy(xpath = "//section[@id='stats'] //h2[@class='section-caption']")
    private WebElement statLeftHeader;

    @FindBy(xpath = "//div[contains(@style, 'margin-right')]//img[@alt='stat-icon']")
    private WebElement cupImage;

    @FindBy(xpath = "//div[contains(@style, 'margin-left')]/h3")
    private WebElement statLeftHeaderCups;

    @FindBy(xpath = "//div[contains(@style, 'margin-left')]/p")
    private WebElement statLeftParagraphCups;

    @FindBy(xpath = "//div[contains(@style, 'margin-left')]//span")
    private WebElement statLeftSpanCups;

    @FindBy(xpath = "//div[contains(@style, 'margin-left')]/button[@class='primary-global-button btn']")
    private WebElement statLeftButton;

    @FindBy(xpath = "//div[contains(@style, 'margin-left')]//img[@alt='location-image']")
    private WebElement statLeftIcon;

    @FindBy(xpath = "//div[contains(@style, 'margin-left')]//a[@class='tertiary-global-button btn-link']")
    private WebElement statLeftLink;


    @FindBy(xpath = "//section[@id='events'] //h2[@class='section-caption']")
    private WebElement eventsHeader;

    @FindBy(xpath = "//section[@id='events'] //a[@aria-label='link to eco-news page']")
    private WebElement eventsLink;

    @FindBy(xpath = "//section[@id='events'] //img[@alt='arrow']")
    private WebElement eventsArrowLink;


    @FindBy(xpath = "//section[@id='subscription']//h2")
    private WebElement subscriptionHeader;

    @FindBy(xpath = "//section[@id='subscription']//p[not(@id='validation-error')]")
    private WebElement subscriptionParagraph;

    @FindBy(xpath = "//section[@id='subscription']//img[contains(@alt, 'Scan this QR-code')]")
    private WebElement subscriptionImgQrCode;

    @FindBy(xpath = "//div[@class='form-input']//input[@type='email']")
    private WebElement subscriptionInputEmail;

    @FindBy(xpath = "//div[@id='form-wrapper']//p[@id='validation-error']")
    private WebElement subscriptionError;

    @FindBy(xpath = "//div[@id='form-wrapper']//button[@class='primary-global-button btn']")
    private WebElement subscriptionButton;


    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
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

    public void clickStartHeaderButton() {
        headerButton.click();
    }

    public boolean isGuyImageDisplayed() {
        return guyImage.isDisplayed();
    }


    // Stat-section
    public String getStatHeaderText() {
        return statHeader.getText();
    }

    // margin-right

    public boolean isBagImageDisplayed() {
        return bagImage.isDisplayed();
    }

    public String getStatRightHeaderBagsText() {
        return statRightHeaderBags.getText();
    }

    public String getStatRightParagraphBagsText() {
        return statRightParagraphBags.getText();
    }

    public String getStatRightSpanBagsText() {
        return statRightSpanBags.getText();                   //// ??
    }

    public String getStatRightButtonText() {
        return statRightButton.getText();
    }

    public void clickStatRightButton() {
        statRightButton.click();
    }

    public boolean isStatRightIconDisplayed() {
        return statRightIcon.isDisplayed();
    }

    public String getStatRightLinkText() {
        return statRightLink.getText();
    }

    public void clickStatRightLink() {
        statRightLink.click();
    }


    // margin-left
    public boolean isCupImageDisplayed() {
        return cupImage.isDisplayed();
    }

    public String getStatLeftHeaderCupsText() {
        return statLeftHeaderCups.getText();
    }

    public String getStatLeftParagraphCupsText() {
        return statLeftParagraphCups.getText();
    }

    public String getStatLeftSpanCupsText() {
        return statLeftSpanCups.getText();                        // ???
    }


    public String getStatLeftButtonText() {
        return statLeftButton.getText();
    }

    public void clickStatLeftButton() {
        statLeftButton.click();
    }

    public boolean isStatLeftIconDisplayed() {
        return statLeftIcon.isDisplayed();
    }

    public String getStatLeftLinkText() {
        return statLeftLink.getText();
    }

    public void clickStatLeftLink() {
        statLeftLink.click();
    }

    // Event-section


    public String getEventsHeaderText() {
        return eventsHeader.getText();
    }


    public String getEventsLinkText() {
        return eventsLink.getText();
    }

    public void clickEventsLink() {
        eventsLink.click();
    }

    public void clickEventsArrowLink() {
        eventsArrowLink.click();
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
        subscriptionInputEmail.clear();
        subscriptionInputEmail.sendKeys(email);                  //// ?????
    }

    public String getSubscriptionErrorText() {
        return subscriptionError.getText();                      //// ?????
    }


    public String getSubscriptionButtonText() {
        return subscriptionButton.getText();
    }

    public void clickSubscriptionButton() {
        subscriptionButton.click();
    }


}

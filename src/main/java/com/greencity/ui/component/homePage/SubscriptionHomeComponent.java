package com.greencity.ui.component.homePage;

import com.greencity.ui.component.BaseComponent;
import com.greencity.ui.page.homepage.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import lombok.Getter;


public class SubscriptionHomeComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//h2")
    private WebElement header;

    @Getter
    @FindBy(xpath = ".//p[not(@id='validation-error')]")
    private WebElement paragraph;

    @Getter
    @FindBy(xpath = ".//img[contains(@alt, 'Scan this QR-code')]")
    private WebElement imgQrCode;

    @Getter
    @FindBy(xpath = ".//input[@type='email']")
    private WebElement inputEmail;

    @Getter
    @FindBy(xpath = ".//p[@id='validation-error']")
    private WebElement error;

    @Getter
    @FindBy(xpath = ".//button[@class='primary-global-button btn']")
    private WebElement button;

    public SubscriptionHomeComponent(WebDriver driver, WebElement subscriptionHomeRoot) {
        super(driver,subscriptionHomeRoot);
    }

    public String getHeaderText() {
        return header.getText();
    }

    public String getParagraphText() {
        return paragraph.getText();
    }

    public boolean isImgQrCodeDisplayed() {
        return imgQrCode.isDisplayed();
    }

    public void enterEmail(String email) {
        scrollToElement(inputEmail);
        waitUntilElementClickable(inputEmail);
        inputEmail.clear();
        inputEmail.sendKeys(email);
    }

    public String getErrorText() {
        return error.getText();
    }

    public String getButtonText() {
        return button.getText();
    }

    public HomePage clickButton() {
        scrollToElement(button);
        waitUntilElementClickable(button);
        button.click();
        return new HomePage(driver);
    }
}

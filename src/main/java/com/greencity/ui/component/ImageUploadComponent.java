package com.greencity.ui.component;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ImageUploadComponent extends BaseComponent {

    @FindBy(xpath = ".//h3[normalize-space()='Picture (optional)']")
    private WebElement titleText;

    @FindBy(xpath = ".//div[@class='dropzone warning-background']")
    private WebElement imageDropzoneField;

    @FindBy(xpath = ".//span[normalize-space()='browse']")
    private WebElement imageBrowseLink;

    @Getter
    @FindBy(xpath = ".//input[@type='file']")
    private WebElement imageUploadLink;

    @FindBy(xpath = ".//button[@class='secondary-global-button s-btn']")
    private WebElement cancelButton;

    @FindBy(xpath = ".//button[normalize-space()='Submit']")
    private WebElement submitButton;

    @FindBy(xpath = ".//div[@class='dropzone warning-background']//p[1]")
    private WebElement uploadFieldWarningText;

    @Getter
    @FindBy(xpath = ".//div[@class='ngx-ic-move']")
    private WebElement presentationImageWindow;

    @Getter
    @FindBy(xpath = ".//p[@class='warning']")
    private WebElement imageFormatMessage;

    @Getter
    @FindBy(xpath = ".//p[@class='warning warning-color']")
    private WebElement imageFormatWarningMessage;

    public ImageUploadComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getTitleText() {
        waitUntilElementVisible(titleText);
        return titleText.getText();
    }

    @Step("Click Cancel Button")
    public ImageUploadComponent clickCancelButton() {
        waitUntilElementClickable(cancelButton);
        cancelButton.click();
        return this;
    }

    @Step("Click Submit Button")
    public ImageUploadComponent clickSubmitButton() {
        waitUntilElementClickable(submitButton);
        submitButton.click();
        return this;
    }

    public String getUploadFieldWarningText() {
        waitUntilElementVisible(uploadFieldWarningText);
        return uploadFieldWarningText.getText();
    }

    public String getImageFormatWarningMessageText() {
        waitUntilElementVisible(imageFormatWarningMessage);
        return imageFormatWarningMessage.getText();
    }

    public String getImageBrowseLinkText() {
        return imageBrowseLink.getText();
    }

    @Step("Upload Image with path: {imagePath}")
    public ImageUploadComponent uploadImage(String imagePath) {
        imageUploadLink.sendKeys(imagePath);
        return this;
    }

    public String getImageDropzoneFieldColor() {

        return imageDropzoneField.getCssValue("background-color");
    }
}

package com.greencity.ui.component;

import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ImageUploadComponent extends BaseComponent{

    @FindBy(xpath = ".//h3[normalize-space()='Picture (optional)']")
    private WebElement titleText;

    @FindBy(xpath = ".//div[@class='text-wrapper']")
    private WebElement imageUploadField;

    @FindBy(xpath = ".//span[normalize-space()='browse']")
    private WebElement imageBrowseLink;

    @FindBy(xpath = ".//input[@type='file']")
    private WebElement imageUploadLink;

    @FindBy(xpath = ".//button[@class='secondary-global-button s-btn']")
    private WebElement cancelButton;

    @FindBy(xpath = ".//button[normalize-space()='Submit']")
    private WebElement submitButton;

    @FindBy(xpath = ".//div[@class='dropzone warning-background']//p[1]")
    private WebElement uploadFieldWarningText;

    @FindBy(xpath = ".//p[@class='warning warning-color']")
    private WebElement imageFormatWarningMessageText;

    public ImageUploadComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);

    }


    public String getTitleText() {
        waitUntilElementVisible(titleText);
        return titleText.getText();
    }

    @Step("Click Cancel Button")
    public void clickCancelButton() {
        waitUntilElementClickable(cancelButton);
        cancelButton.click();
    }

    @Step("Click Submit Button")
    public void clickSubmitButton() {
        waitUntilElementClickable(submitButton);
        submitButton.click();
    }

    public String getUploadFieldWarningText() {
        waitUntilElementVisible(uploadFieldWarningText);
        return uploadFieldWarningText.getText();
    }

    public String getImageFormatWarningMessageText() {
        waitUntilElementVisible(imageFormatWarningMessageText);
        return imageFormatWarningMessageText.getText();
    }


    @Step("Upload Image with path: {imagePath}")
    public void uploadImage(String imagePath) {
            waitUntilElementClickable(imageUploadLink);
            imageUploadLink.sendKeys(imagePath);
    }

    public String getImageBrowseLinkText(){
        return imageBrowseLink.getText();
    }

}

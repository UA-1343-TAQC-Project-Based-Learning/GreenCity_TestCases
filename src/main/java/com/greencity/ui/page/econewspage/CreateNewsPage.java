package com.greencity.ui.page.econewspage;

import com.greencity.ui.component.ImageUploadComponent;
import com.greencity.ui.component.ContentOfNews;
import com.greencity.ui.component.ecoNewsTag.EcoNewsTagFilterComponent;
import com.greencity.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewsPage extends BasePage {
    private ImageUploadComponent imageUploadComponent;
    private ContentOfNews contentOfNews;

    @FindBy(xpath = ".//div[@class='image-block']")
    private WebElement imageBlockRoot;

    @FindBy(xpath = ".//h2[@class='title-header']")
    private WebElement titleHeaderText;

    @FindBy(xpath = "//p[@class='title-description']")
    private WebElement titleHeaderDescriptionText;

    @FindBy(xpath = "//h3[normalize-space()='Title']")
    private WebElement titleText;

    @FindBy(xpath = "//span[@class='field-info']")
    private WebElement titleFieldInfoText;

    @FindBy(xpath = "//textarea[@placeholder='e.g. Coffee takeaway with 20% discount']")
    private WebElement titleInputTextField;

    @FindBy(xpath = "//h3[normalize-space()='Pick tags for news']")
    private WebElement pickTagsForNewsText;

    @FindBy(xpath = "//p[normalize-space()='Only 3 tags can be added']")
    private WebElement onlyThreeTagsCanBeAddedText;

    @FindBy(xpath = "//input[@placeholder='Link to external source']")
    private WebElement externalSourceLinkInputField;

    @FindBy(xpath = "//h3[normalize-space()='Source (optional)']")
    private WebElement externalSourceInputFieldTitle;

    @FindBy(xpath = "//span[@class='span field-info']")
    private WebElement externalSourceInputFieldInfoText;

    @FindBy(xpath = "//div[@class='wrapper']//div[@class='tags-box']")
    private WebElement newsFilterRoot;

    @Getter
    private EcoNewsTagFilterComponent ecoNewsTagFilterComponent;

    public CreateNewsPage(WebDriver driver) {
        super(driver);
        imageUploadComponent = new ImageUploadComponent(driver, getHeaderRoot());
        ecoNewsTagFilterComponent = new EcoNewsTagFilterComponent(driver, newsFilterRoot);
    }

    public String getTitleHeaderText() {
        return titleHeaderText.getText();
    }

    public String getTitleHeaderDescriptionText() {
        return titleHeaderDescriptionText.getText();
    }

    public String getTitleText() {
        return titleText.getText();
    }

    public String getTitleFieldInfoText() {
        return titleFieldInfoText.getText();
    }

    public void clickTitleInputTextField() {
        titleInputTextField.click();
    }

    public void clearTitleInputTextField() {
        titleInputTextField.clear();
    }

    public void fillTitleInputTextField(String titleText) {
        titleInputTextField.sendKeys(titleText);
    }

    public String getTitleInputTextFieldPlaceholderText() {
        return titleInputTextField.getDomAttribute("placeholder");
    }

    public String getPickTagsForNewsText() {
        return pickTagsForNewsText.getText();
    }

    public String getOnlyThreeTagsCanBeAddedText() {
        return onlyThreeTagsCanBeAddedText.getText();
    }

    public String getExternalSourceInputFieldTitle() {
        return externalSourceInputFieldTitle.getText();
    }

    public String getExternalSourceInputFieldInfoText() {
        return externalSourceInputFieldInfoText.getText();
    }

    public void clickExternalSourceLinkInputField() {
        externalSourceLinkInputField.click();
    }

    public void clearExternalSourceLinkInputField() {
        externalSourceLinkInputField.clear();
    }

    public void fillExternalSourceLinkInputField(String text) {
        externalSourceLinkInputField.sendKeys(text);
    }

    public String getExternalSourceLinkInputFieldPlaceholderText() {
        return externalSourceLinkInputField.getDomAttribute("placeholder");
    }

}

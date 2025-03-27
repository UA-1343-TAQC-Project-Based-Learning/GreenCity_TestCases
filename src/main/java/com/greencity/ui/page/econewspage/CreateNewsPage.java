package com.greencity.ui.page.econewspage;

import com.greencity.ui.component.ImageUploadComponent;
import com.greencity.ui.component.TextContentComponent;
import com.greencity.ui.component.ecoNewsTag.EcoNewsTagFilterComponent;
import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;

public class CreateNewsPage extends BasePage {
    private EcoNewsTagFilterComponent ecoNewsTagFilterComponent;
    private ImageUploadComponent imageUploadComponent;
    private TextContentComponent textContentComponent;



    @FindBy(xpath = "//div[@class='image-block']")
    private WebElement imageBlockRoot;

    @FindBy(xpath = "//div[@class='tags-box']")
    private WebElement filterTagsRoot;

    @FindBy(xpath = "//div[@class='textarea-wrapper']")
    private WebElement textAreaRoot;

    @FindBy(xpath = "//h2[@class='title-header']")
    private WebElement titleHeaderText;

    @FindBy(xpath = "//p[@class='title-description']")
    private WebElement titleHeaderDescriptionText;

    @FindBy(xpath = "//h3[normalize-space()='Title' or normalize-space()='Назва']")
    private WebElement titleText;

    @Getter
    @FindBy(xpath = "//span[@class='field-info']")
    private WebElement titleFieldCharacterCounter;

    @Getter
    @FindBy(xpath = "//span[@class='field-info warning']")
    private WebElement titleFieldCharacterCounterWarning;

    @Getter
    @FindBy(xpath = "//textarea[@placeholder='e.g. Coffee takeaway with 20% discount' or @placeholder='напр. Кава з собою зі знижкою 20%']")
    private WebElement titleInputTextField;

    @FindBy(xpath = "//h3[normalize-space()='Pick tags for news' or normalize-space()='Оберіть теги для новин']")
    private WebElement pickTagsForNewsText;

    @FindBy(xpath = "//p[normalize-space()='Only 3 tags can be added' or normalize-space()='Оберіть не більше 3-х тегів']")
    private WebElement onlyThreeTagsCanBeAddedText;

    @FindBy(xpath = "//input[@placeholder='Link to external source' or @placeholder='Посилання на зовнішнє джерело']")
    private WebElement externalSourceLinkInputField;

    @FindBy(css = "div[class='source-block'] h3")
    private WebElement externalSourceInputFieldTitle;

    @FindBy(xpath = "//span[@class='span field-info']")
    private WebElement externalSourceInputFieldInfoText;

    @Getter
    @FindBy(xpath = ".//div[@class = 'date']//span[normalize-space() = 'Author:']")
    private WebElement authorOfNew;

    @FindBy(xpath = "//button[@class='tertiary-global-button']")
    private WebElement exitButton;

    @FindBy(xpath = "//button[@class='secondary-global-button']")
    private WebElement previewButton;

    @Getter
    @FindBy(xpath = "//button[@class='primary-global-button']")
    private WebElement publishButton;

    public CreateNewsPage(WebDriver driver) {
        super(driver);
        ecoNewsTagFilterComponent = new EcoNewsTagFilterComponent(driver, filterTagsRoot);
        imageUploadComponent = new ImageUploadComponent(driver, imageBlockRoot);
        textContentComponent = new TextContentComponent(driver, textAreaRoot);
    }

    public String getTitleFieldCharacterCounterTextColor() {
        return titleFieldCharacterCounter.getCssValue("color");
    }

    public String getTitleFieldCharacterCounterWarningText() {
        return titleFieldCharacterCounterWarning.getText();
    }

    public String getTitleFieldCharacterCounterWarningTextColor() {
        return titleFieldCharacterCounterWarning.getCssValue("color");
    }
    
    public CreateNewsPage clickTitleHeaderText() {
        titleHeaderText.click();
        return this;
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

    public String getTitleFieldCharacterCounterText() {
        return titleFieldCharacterCounter.getText();
    }

    public CreateNewsPage clickTitleInputTextField() {
        titleInputTextField.click();
        return this;
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

    public void clickExitButton() {
        exitButton.click();
    }

    public void clickPreviewButton() {
        previewButton.click();
    }
    public String getTitleInputTextFieldValue() {
        return titleInputTextField.getAttribute("value");
    }
    public CreateNewsPage clearTitleInputTextField() {
        titleInputTextField.clear();
        return this;
    }

    public CreateNewsPage fillTitleInputTextField(String titleText) {
        clickTitleInputTextField()
                .clearTitleInputTextField()
                .titleInputTextField.sendKeys(titleText);
        return this;
    }
    public EcoNewsPage clickPublishButton() {
        publishButton.click();
        return new EcoNewsPage(driver);
    }

    public CreateNewsPage clickTextIntoTextContentField() {
        textContentComponent.clickContentInputTextField();
        return this;
    }

    public CreateNewsPage enterTextIntoTextContentField(String text) {
        textContentComponent.fillContentTextAreaField(text);
        return this;
    }
    public String getTitleInputFieldBorderColor() {
        return titleInputTextField.getCssValue("border-color").toString();
    }

    public String getContentText(){
        return textContentComponent.getContentText();
    }

    public boolean isPresentContentInputTextField(){
        return textAreaRoot.isDisplayed();
    }

    public String getContentCharacterCountText(){
        return textContentComponent.getContentCounterText();
    }
    public long getContentInputTextFieldText(){
        return  textContentComponent.getContentInputTextFieldText().length();
    }

    public String getAuthorLabelText(){
        return authorOfNew.getText();
    }
    public  boolean isCancelButtonPresent(){
        return exitButton.isDisplayed();
    }

    public  boolean isPreviewButtonPresent(){
        return previewButton.isDisplayed();
    }

    public  boolean isPublishButtonPresent(){
        return publishButton.isDisplayed();
    }

    public CreateNewsPage clickTagFilterButton(TagButton tagButton) {
        ecoNewsTagFilterComponent.clickTagButton(tagButton);
        return this;
    }
    public List<String> getListOfAllTagButtonsText() {
        List<String> tagButtonsText = new ArrayList<>();
        TagButton[] tagButtons = TagButton.values();
        for(TagButton tagButton : tagButtons){
            tagButtonsText.add(ecoNewsTagFilterComponent.getTagButtonText(tagButton));
        }
        return tagButtonsText;
    }

    public boolean isAllSelectedTagsChangeAppearance() {
        TagButton[] tagButtons = TagButton.values();
        boolean result = true;
        for (TagButton tagButton : tagButtons) {
            ecoNewsTagFilterComponent.clickTagButton(tagButton);
            result = ecoNewsTagFilterComponent.isTagButtonSelected(tagButton);
            ecoNewsTagFilterComponent.clickTagButton(tagButton);
            if (!result) break;
        }
        return result;
    }

}

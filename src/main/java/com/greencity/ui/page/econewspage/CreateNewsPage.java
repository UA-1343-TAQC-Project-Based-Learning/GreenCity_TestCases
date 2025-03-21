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

public class CreateNewsPage extends BasePage {
    private EcoNewsTagFilterComponent ecoNewsTagFilterComponent;
    private ImageUploadComponent imageUploadComponent;
    private TextContentComponent textContentComponent;

    private TextContentComponent contentOfNews;

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

    public String getTitleFieldCharacterCounterTextColor() {
        return titleFieldCharacterCounter.getCssValue("color");
    }

    public String getTitleFieldCharacterCounterWarningText() {
        return titleFieldCharacterCounterWarning.getText();
    }

    public void clickTitleInputTextField() {
        titleInputTextField.click();
    }

    public void clearTitleInputTextField() {
        titleInputTextField.clear();
    }

    public CreateNewsPage fillTitleInputTextField(String titleText) {
        titleInputTextField.sendKeys(titleText);
        return this;
    }

    public String getTitleInputFieldBorderColor() {
        return titleInputTextField.getCssValue("border-color").toString();
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


    public CreateNewsPage fillTitleInputFieldWithCustomNumberOfCharacters(int charactersNumber) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 171; i++) {
            stringBuilder.append("A");
        }

        clickTitleInputTextField();
        fillTitleInputTextField(stringBuilder.toString());
        return this;
    }

    public CreateNewsPage clickTagFilterButton(TagButton tagButton) {
        ecoNewsTagFilterComponent.clickTagButton(tagButton);
        return this;
    }

    public CreateNewsPage clickTagFilterButton(TagButton tagButton1, TagButton tagButton2) {
        ecoNewsTagFilterComponent
                .clickTagButton(tagButton1)
                .clickTagButton(tagButton2);
        return this;
    }

    public CreateNewsPage clickTagFilterButton(TagButton tagButton1, TagButton tagButton2, TagButton tagButton3) {
        ecoNewsTagFilterComponent
                .clickTagButton(tagButton1)
                .clickTagButton(tagButton2)
                .clickTagButton(tagButton3);
        return this;
    }

    public CreateNewsPage enterTextIntoTextContentField(String text) {
        textContentComponent.fillTextAreaField(text);
        return this;
    }

}

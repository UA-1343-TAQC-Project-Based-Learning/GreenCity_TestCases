package com.greencity.ui.page.econewspage;

import com.greencity.ui.component.ImageUploadComponent;
import com.greencity.ui.component.TextContentComponent;
import com.greencity.ui.component.ecoNewsTag.EcoNewsTagFilterComponent;
import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.modal.CancelModal;
import com.greencity.ui.page.BasePage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public class CreateEditNewsPage extends BasePage {

    private final EcoNewsTagFilterComponent ecoNewsTagFilterComponent;
    private final ImageUploadComponent imageUploadComponent;
    private final TextContentComponent textContentComponent;

    public List<String> tagFilters = new ArrayList<>(List.of("News", "Events", "Education", "Initiatives", "Ads"));

    @Getter
    @FindBy(xpath = "//div[@class='image-block']")
    private WebElement imageBlockRoot;

    @Getter
    @FindBy(xpath = "//div[@class='tags-box']")
    private WebElement filterTagsRoot;

    @Getter
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

    @Getter
    @FindBy(xpath = "//input[@placeholder='Link to external source' or @placeholder='Посилання на зовнішнє джерело']")
    private WebElement externalSourceLinkInputField;

    @FindBy(css = "div[class='source-block'] h3")
    private WebElement externalSourceInputFieldTitle;

    @FindBy(xpath = "//span[contains(@class,'span field-info')]")
    private WebElement externalSourceInputFieldInfoText;


    @Getter
    @FindBy(xpath = ".//div[@class = 'date']//span[normalize-space() = 'Author:']//following-sibling::span")
    private WebElement authorOfNew;

    @Getter
    @FindBy(xpath = "//button[@class='tertiary-global-button']")
    private WebElement exitButton;

    @Getter
    @FindBy(xpath = "//button[@class='secondary-global-button']")
    private WebElement previewButton;

    @Getter
    @FindBy(xpath = "//button[@class='primary-global-button']")
    private WebElement publishButton;

    @Getter
    @FindBy(xpath = "//span[contains(text(), 'Author') or contains(text(), 'Автор')]/following-sibling::span")
    private WebElement authorLabel;

    @Getter
    @FindBy(xpath = "//span[contains(text(), 'Date') or contains(text(), 'Дата')]/following-sibling::span")
    private WebElement dataLabel;

    @Getter
    @FindBy(xpath = "//button[@class='primary-global-button']")
    private WebElement editButton;

    @Getter
    @FindBy(xpath = "//mat-dialog-container")
    private WebElement cancelModalRoot;

    public CreateEditNewsPage(WebDriver driver) {
        super(driver);
        ecoNewsTagFilterComponent = new EcoNewsTagFilterComponent(driver, filterTagsRoot);
        imageUploadComponent = new ImageUploadComponent(driver, imageBlockRoot);
        textContentComponent = new TextContentComponent(driver, textAreaRoot);
    }
public String getAuthorName(){return authorLabel.getText();}
    public String getTitleFieldCharacterCounterTextColor() {
        return titleFieldCharacterCounter.getCssValue("color");
    }

    public String getTitleFieldCharacterCounterWarningText() {
        return titleFieldCharacterCounterWarning.getText();
    }

    public String getTitleFieldCharacterCounterWarningTextColor() {
        return titleFieldCharacterCounterWarning.getCssValue("color");
    }

    @Step("Click 'Title' header")
    public CreateEditNewsPage clickTitleHeaderText() {
        titleHeaderText.click();
        return this;
    }

    public String getTitleHeaderText() {
        waitUntilElementVisible(titleHeaderText);
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

    @Step("Click 'Title Input' text field")
    public CreateEditNewsPage clickTitleInputTextField() {
        titleInputTextField.click();
        return this;
    }

    @Step("Clear 'Title Input' text field using keyboard shortcut")
    public void actionClearTitleInputTextField() {
        Actions actions = new Actions(driver);
        actions.click(titleInputTextField).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
    }

    @Step("Clear 'Title Input' text field")
    public CreateEditNewsPage clearTitleInputTextField() {
        actionClearTitleInputTextField();
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

    @Step("Click 'External Source Link' input field")
    public void clickExternalSourceLinkInputField() {
        externalSourceLinkInputField.click();
    }

    @Step("Clear 'External Source Link' input field")
    public void clearExternalSourceLinkInputField() {
        externalSourceLinkInputField.clear();
    }

    @Step("Fill '{text}' into 'External Source Link' input field")
    public void fillExternalSourceLinkInputField(String text) {
        externalSourceLinkInputField.sendKeys(text);
    }

    public String getExternalSourceLinkInputFieldPlaceholderText() {
        return externalSourceLinkInputField.getDomAttribute("placeholder");
    }

    @Step("Click 'Cancel' button")
    public CancelModal clickExitButton() {
        exitButton.click();
        return new CancelModal(driver, cancelModalRoot);
    }

    @Step("Click 'Preview' button")
    public PreviewNewsPage clickPreviewButton() {
        previewButton.click();
        return new PreviewNewsPage(driver);
    }

    public String getTitleInputTextFieldValue() {
        return titleInputTextField.getAttribute("value");
    }
    public String getContentInputFieldText() {
        return textContentComponent.getContentInputTextFieldText();
    }
    public String getContentWarningCounterText(){
        return textContentComponent.getContentWarningCounterText();
    }

    @Step("Fill '{titleText}' into 'Title Input' text field")
    public CreateEditNewsPage fillTitleInputTextField(String titleText) {
        clickTitleInputTextField().clearTitleInputTextField().titleInputTextField.sendKeys(titleText);
        return this;
    }

    @Step("Click 'Publish' button")
    public EcoNewsPage clickPublishButton() {
        publishButton.click();
        return new EcoNewsPage(driver);
    }

    @Step("Click 'TextContent' field")
    public CreateEditNewsPage clickTextIntoTextContentField() {
        textContentComponent.clickContentInputTextField();
        return this;
    }

    @Step("Enter '{text}' into 'TextContent' field")
    public CreateEditNewsPage enterTextIntoTextContentField(String text) {
        textContentComponent.fillContentTextAreaField(text);
        return this;
    }
    public CreateEditNewsPage enterTextIntoTextContentField(Stream<String> text) {

        text.forEach(line -> {
            sleep(100);
            textContentComponent.fillTextAreaField(line);
        });

        return this;
    }


    public String getTitleInputFieldBorderColor() {
        return titleInputTextField.getCssValue("border-color");
    }
    public String getTextAreaDescriptionWarningsColor() {
        return textContentComponent.getTextAreaDescriptionWarningsColor();
    }

    public boolean isPresentContentInputTextField() {
        return textAreaRoot.isDisplayed();
    }

    public String getContentCharacterCountText() {
        return textContentComponent.getContentCounterText();
    }

    public long getContentInputTextFieldText() {
        return textContentComponent.getContentInputTextFieldText().length();
    }

    public String getAuthorLabelText() {
        return authorOfNew.getText();
    }

    public boolean isCancelButtonPresent() {
        return exitButton.isDisplayed();
    }
    public boolean isCancelButtonEnabled() {
        return exitButton.isEnabled();
    }

    public boolean isPreviewButtonPresent() {
        return previewButton.isDisplayed();
    }

    public boolean isPreviewButtonEnabled() {
        return previewButton.isEnabled();
    }

    public boolean isPublishButtonPresent() {
        return publishButton.isDisplayed();
    }
    public boolean isPublishButtonEnabled() {
        return publishButton.isEnabled();
    }

    @Step("Click '{tagButton}' tag in filter")
    public CreateEditNewsPage clickTagFilterButton(TagButton tagButton) {
        ecoNewsTagFilterComponent.clickTagButton(tagButton);
        return this;
    }

    public List<String> getListOfAllTagButtonsText() {
        List<String> tagButtonsText = new ArrayList<>();
        TagButton[] tagButtons = TagButton.values();
        for (TagButton tagButton : tagButtons) {
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

    public String getImageBrowseLinkText() {
        return imageUploadComponent.getImageBrowseLinkText();
    }

    public boolean isPresentTitleInputTextField() {
        return titleInputTextField.isDisplayed();
    }

    public String getContentText() {
        return textContentComponent.getContentText();
    }

    public boolean isAuthorLabelNotEditable() {
        String isContentEditable = authorLabel.getAttribute("contenteditable");
        return (isContentEditable == null || isContentEditable.equals("false"));
    }

    public String getDataLabelText() {
        return dataLabel.getText();
    }

    public LocalDate getDataLabelFormating(Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", locale);
        return LocalDate.parse(getDataLabelText(), formatter);
    }

    public boolean isDataLabelNotEditable() {
        String isContentEditable = dataLabel.getAttribute("contenteditable");
        return (isContentEditable == null || isContentEditable.equals("false"));
    }

    public boolean isElementsOrderCorrect(WebElement first, WebElement second) {
        return first.getLocation().getY() < second.getLocation().getY();
    }

    public boolean areElementsOnSameLine(WebElement first, WebElement second) {
        return first.getLocation().getY() == second.getLocation().getY();
    }

    public boolean isTagSelected(TagButton button) {
        return ecoNewsTagFilterComponent.isTagButtonSelected(button);
    }

    public String getTagButtonColor(TagButton tag) {
        return ecoNewsTagFilterComponent.getTagButtonColor(tag);
    }

    @Step("Fill '{content}' into content input field")
    public CreateEditNewsPage fillContentInput(String content) {
        textContentComponent.fillContentTextAreaField(content);
        return this;
    }

    @Step("Fill '{url}' into source input field")
    public CreateEditNewsPage fillSourceInput(String url) {
        clearExternalSourceLinkInputField();
        clickExternalSourceLinkInputField();
        fillExternalSourceLinkInputField(url);
        return this;
    }

    @Step("Click '{NEWS}' tag button")
    public CreateEditNewsPage clickNewsTagButton() {
        ecoNewsTagFilterComponent.clickTagButton(TagButton.NEWS);
        return this;
    }

    public String getExternalSourceInputFieldInfoTextColor() {
        return externalSourceInputFieldInfoText.getCssValue("color");
    }

    public String getExternalSourceInputFieldBorderColor() {
        return externalSourceLinkInputField.getCssValue("border");
    }



    public ImageUploadComponent switchToImageUploadComponent() {
        return imageUploadComponent;
    }

    public String getEditButtonText() {
        return editButton.getText();
    }

    @Step("Click 'Edit' button")
    public EcoNewsPage clickEditButton() {
        publishButton.click();
        return new EcoNewsPage(driver);
    }

    public boolean isEditButtonEnabled() {
        return editButton.isEnabled();
    }

    public CreateEditNewsPage clickOnlyUnselectedTagFilterButton(TagButton tagButton){
        if(!isTagSelected(tagButton)) ecoNewsTagFilterComponent.clickTagButton(tagButton);
        return this;
    }

    public String getContentHeaderText() {
        return textContentComponent.getContentHeaderText();
    }

    public String getContentDescriptionWarning() {
        return textContentComponent.getAreaDescriptionWarningsText();
    }


}

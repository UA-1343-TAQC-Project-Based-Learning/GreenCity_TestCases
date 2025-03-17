package com.greencity.ui.page.econewspage;

import com.greencity.ui.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewsPage extends BasePage {

    @FindBy(xpath = "//h2[@class='title-header']")
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

    /**
     * author Khrystyna Martynova
     **/

    @FindBy(xpath = "//h3[normalize-space()='Content']")
    private WebElement content;

    @FindBy(xpath = "//p[@class = 'textarea-description warning']")
    private WebElement textAreaDescriptionWarnings;

    @FindBy(xpath = "//div[@data-placeholder = 'e.g. Short description of news, agenda for event']")
    private WebElement descriptionOfNews;

    @FindBy(xpath = "//div[@class = 'date']//span[normalize-space() = 'Date:']")
    private WebElement dateOfNew;

    @FindBy(xpath = "//div[@class = 'date']//span[normalize-space() = 'Author:']")
    private WebElement authorOfNew;

    @FindBy(xpath = "//div[@class = 'submit-buttons']/button[normalize-space() = 'Cancel']")
    private WebElement cancelButton;

    @FindBy(xpath = "//div[@class = 'submit-buttons']/button[normalize-space() = 'Preview']")
    private WebElement previewButton;

    @FindBy(xpath = "//div[@class = 'submit-buttons']/button[normalize-space() = 'Publish']")
    private WebElement publishButton;

    @FindBy(xpath = "//div[@class = 'ql-container ql-snow']")
    private WebElement contentNewsForm;

    @FindBy(xpath = "//div[@class = 'ql-toolbar ql-snow']//button[@class='ql-bold ql-active']")
    private WebElement boldTextButton;

    @FindBy(xpath = "//div[@class = 'ql-toolbar ql-snow']//button[@class='ql-italic']")
    private WebElement italicTextButton;

    @FindBy(xpath = "//div[@class = 'ql-toolbar ql-snow']//button[@class='ql-underline']")
    private WebElement underLineTextButton;

    @FindBy(xpath = "//div[@class = 'ql-toolbar ql-snow']//button[@class='ql-strike']")
    private WebElement strikeTextTextButton;

    @FindBy(xpath = "//span[@class = 'ql-formats']//button[@class='ql-blockquote']")
    private WebElement blockquoteTextButton;

    @FindBy(xpath = "//span[@class = 'ql-formats']//button[@class='ql-code-block']")
    private WebElement blackFonTextButton;

    @FindBy(xpath = "//span[@class = 'ql-formats']//button[@class='ql-header'][@value='1']")
    private WebElement header1Button;

    @FindBy(xpath = "//span[@class = 'ql-formats']//button[@class='ql-header ql-active'][@value='2']")
    private WebElement header2Button;

    @FindBy(xpath = "//span[@class = 'ql-formats']//button[@class = 'ql-script']")
    private WebElement raiseToPowerButton;

    @FindBy(xpath = "//span[@class = 'ql-formats']//button[@class = 'ql-script ql-active']")
    private WebElement indexButton;

    @FindBy(xpath = "//span[@class = 'ql-formats']//button[@class = 'ql-indent'][@value='-1']")
    private WebElement leftAlignmentButton;

    @FindBy(xpath = "//span[@class = 'ql-formats']//button[@class = 'ql-indent'][@value='+1']")
    private WebElement rightAlignmentButton;

    @FindBy(xpath = "//span[@class = 'ql-formats']//button[@class = 'ql-direction']")
    private WebElement directionButton;

    @FindBy(xpath = "//span[@class = 'ql-formats']//span[@class = 'ql-size ql-picker ql-expanded']")
    private WebElement styleButton;

    public String getTitleContentText() {
        return content.getText();
    }
    public String getTitleAreaDescriptionWarningsText() {
        return textAreaDescriptionWarnings.getText();
    }
    public String getTitleDateText() {
        return dateOfNew.getText();
    }
    public String getTitleAuthorText() {
        return authorOfNew.getText();
    }
    public String getTitleCancelButtonText() {
        return cancelButton.getText();
    }
    public String getTitlePreviewButtonText() {
        return previewButton.getText();
    }
    public String getTitlePublishButtonText() {
        return publishButton.getText();
    }
    public String getTitleDescriptionOfNewsText() {
        return descriptionOfNews.getText();
    }
    public void clickContentNewsForm() {
         contentNewsForm.click();
    }
    public void clearContentNewsForm() {
        contentNewsForm.clear();
    }
    public void fillContentNewsForm(String textContent) {
       contentNewsForm.sendKeys(textContent);
    }
    public CreateNewsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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

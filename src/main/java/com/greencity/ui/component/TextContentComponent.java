package com.greencity.ui.component;

import com.greencity.ui.component.header.DropdownTextSize;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class TextContentComponent extends BaseComponent {

    protected final String OPTION_NULL_MESSAGE = "Dropdown is null";
    private DropdownTextSize dropdownTextSize;


    @Getter
    @FindBy(xpath = ".//h3[normalize-space()='Content']")
    private WebElement content;

    @Getter
    @FindBy(xpath = ".//div[contains(@class,'ql-editor')]")
    private WebElement textAreaField;
    @Getter
    @FindBy(xpath = ".//p[@class = 'field-info']")
    private WebElement textAreaDescriptionWarnings;
    @Getter
    @FindBy(xpath = ".//div[@data-placeholder = 'e.g. Short description of news, agenda for event']")
    private WebElement contentInputTextField;
    @Getter
    @FindBy(xpath = ".//p[@class='quill-counter quill-valid']")
    private WebElement textContentCounter;

    @FindBy(xpath = ".//p[@class='quill-counter warning']")
    private WebElement contentWarningCounter;

    @Getter
    @FindBy(xpath = ".//div[@class = 'ql-container ql-snow']")
    private WebElement contentNewsForm;
    @Getter
    @FindBy(xpath = ".//div[@class = 'ql-toolbar ql-snow']//button[@class='ql-bold ql-active']")
    private WebElement boldTextButton;
    @Getter
    @FindBy(xpath = ".//div[@class = 'ql-toolbar ql-snow']//button[@class='ql-italic']")
    private WebElement italicTextButton;
    @Getter
    @FindBy(xpath = ".//div[@class = 'ql-toolbar ql-snow']//button[@class='ql-underline']")
    private WebElement underLineTextButton;
    @Getter
    @FindBy(xpath = ".//div[@class = 'ql-toolbar ql-snow']//button[@class='ql-strike']")
    private WebElement strikeTextTextButton;
    @Getter
    @FindBy(xpath = ".//span[@class = 'ql-formats']//button[@class='ql-blockquote']")
    private WebElement blockquoteTextButton;
    @Getter
    @FindBy(xpath = ".//span[@class = 'ql-formats']//button[@class='ql-code-block']")
    private WebElement blackFonTextButton;
    @Getter
    @FindBy(xpath = ".//span[@class = 'ql-formats']//button[@class='ql-header'][@value='1']")
    private WebElement header1Button;
    @Getter
    @FindBy(xpath = ".//span[@class = 'ql-formats']//button[@class='ql-header ql-active'][@value='2']")
    private WebElement header2Button;
    @Getter
    @FindBy(xpath = ".//span[@class = 'ql-formats']//button[@class = 'ql-script']")
    private WebElement raiseToPowerButton;
    @Getter
    @FindBy(xpath = ".//span[@class = 'ql-formats']//button[@class = 'ql-script ql-active']")
    private WebElement indexButton;
    @Getter
    @FindBy(xpath = ".//span[@class = 'ql-formats']//button[@class = 'ql-indent'][@value='-1']")
    private WebElement leftAlignmentButton;
    @Getter
    @FindBy(xpath = ".//span[@class = 'ql-formats']//button[@class = 'ql-indent'][@value='+1']")
    private WebElement rightAlignmentButton;
    @Getter
    @FindBy(xpath = ".//span[@class = 'ql-formats']//button[@class = 'ql-direction']")
    private WebElement directionButton;
    @Getter
    @FindBy(xpath = ".//span[@class = 'ql-formats']//span[@class = 'ql-size ql-picker ql-expanded']")
    private WebElement styleButton;

    public TextContentComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    // dropdownText
    protected DropdownTextSize getDropdownTextSize() {
        if (dropdownTextSize == null) {
            throw new RuntimeException(OPTION_NULL_MESSAGE);
        }
        return dropdownTextSize;
    }

    @Step("Create dropdown text size")
    public DropdownTextSize createDropdownTextSize() {
        dropdownTextSize = new DropdownTextSize(driver, rootElement);
        return getDropdownTextSize();
    }

    @Step("Click on dropdown small text size")
    public TextContentComponent clickDropdownSmallTextSize() {
        getDropdownTextSize().clickSmallStyleButton();
        return this;
    }

    @Step("Click on dropdown normal text size")
    public TextContentComponent clickDropdownNormalTextSize() {
        getDropdownTextSize().clickNormalStyleButton();
        return this;
    }
    public String getContentWarningCounterText(){
       return contentWarningCounter.getText();
    }

    public String getAreaDescriptionWarningsText() {
        return textAreaDescriptionWarnings.getText();
    }

    public String getDescriptionOfNewsText() {
        return contentInputTextField.getText();

    }

    @Step("Click on content input text field")
    public void clickContentInputTextField() {
        contentInputTextField.click();
    }

    @Step("Clear content input text field")
    public void clearContentInputTextField() {
        contentInputTextField.clear();
    }

    public String getContentInputTextFieldValue() {
        return contentInputTextField.getAttribute("value");
    }

    public String getTextAreaDescriptionWarningsColor() {
        return textAreaDescriptionWarnings.getCssValue("border-color");
    }

    public String getTitleInputTextFieldPlaceholderText() {
        return contentInputTextField.getDomAttribute("placeholder");
    }

    public String getContentInputTextFieldText() {
        return contentInputTextField.getText();
               // getText();
    }

    public String getContentCounterText() {
        return textContentCounter.getText();
    }



    @Step("Click on dropdown huge text size")
    public TextContentComponent clickDropdownHugeTextSize() {
        getDropdownTextSize().clickHugeStyleButton();
        return this;
    }

    @Step("Click on dropdown large text size")
    public TextContentComponent clickDropdownLargeTextSize() {
        getDropdownTextSize().clickLargeStyleButton();
        return this;
    }

    public String getContentText() {
        return textAreaField.getText();
    }

    @Step("Click on text area field")
    public void clickTextAreaField() {
        contentInputTextField.click();
    }

    @Step("Click text area field")
    public void clearTextAreaField() {
        contentInputTextField.clear();
    }

    @Step("Fill text area field with: {text}")
    public void fillTextAreaField(String text) {
        contentInputTextField.sendKeys(text);
    }
    @Step("Fill content text area field with: {text}")
    public TextContentComponent fillContentTextAreaField(String text) {
        clickTextAreaField();
        clearTextAreaField();
        fillTextAreaField(text);
        return this;
    }
}

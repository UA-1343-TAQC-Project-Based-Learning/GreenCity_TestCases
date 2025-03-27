package com.greencity.ui.component;
import com.greencity.ui.component.header.DropdownTextSize;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class TextContentComponent extends BaseComponent {
    protected final String OPTION_NULL_MESSAGE = "Dropdown is null";
    private DropdownTextSize dropdownTextSize;
    @Getter
    @FindBy(xpath = ".//h3[normalize-space()='Content']")
    private WebElement contentTitleText;
    @Getter
    @FindBy(xpath = ".//p[@class = 'textarea-description warning']")
    private WebElement areaDescriptionWarningsText;
    @Getter
    @FindBy(xpath = ".//div[@data-placeholder = 'e.g. Short description of news, agenda for event']")
    private WebElement inputTextAreaPlaceholder;

    @Getter
    @FindBy(xpath = ".//p[@class='field-info']")
    private WebElement contentCharacterCountText;

    @Getter
    @FindBy(xpath = ".//div[@class='ql-editor ql-blank']")
    private WebElement textAreaField;

    @Getter
    @FindBy(xpath = ".//div[@class = 'date']//span[normalize-space() = 'Date:']")
    private WebElement dateOfNew;
    @Getter
    @FindBy(xpath = ".//div[@class = 'date']//span[normalize-space() = 'Author:']")
    private WebElement authorOfNew;
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
    public DropdownTextSize createDropdownTextSize() {
        dropdownTextSize = new DropdownTextSize(driver, rootElement);
        return getDropdownTextSize();
    }
    public TextContentComponent clickDropdownSmallTextSize() {
        getDropdownTextSize().clickSmallStyleButton();
        return this;
    }
    public TextContentComponent clickDropdownNormalTextSize() {
        getDropdownTextSize().clickNormalStyleButton();
        return this;
    }
    public TextContentComponent clickDropdownHugeTextSize() {
        getDropdownTextSize().clickHugeStyleButton();
        return this;
    }
    public TextContentComponent clickDropdownLargeTextSize() {
        getDropdownTextSize().clickLargeStyleButton();
        return this;
    }

    public String getContentText() {
        return textAreaField.getText();
    }

    public TextContentComponent fillTextAreaField(String text) {
        textAreaField.sendKeys(text);
        return this;
    }

}
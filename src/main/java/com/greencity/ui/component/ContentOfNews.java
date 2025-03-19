package com.greencity.ui.component;
import com.greencity.ui.component.header.DropdownTextSize;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ContentOfNews extends BaseComponent {
    protected final String OPTION_NULL_MESSAGE = "Dropdown is null";
    private DropdownTextSize dropdownTextSize;

    public ContentOfNews(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
    @Getter
    @FindBy(xpath = ".//h3[normalize-space()='Content']")
    private WebElement content;

    @Getter
    @FindBy(xpath = ".//p[@class = 'textarea-description warning']")
    private WebElement textAreaDescriptionWarnings;

    @Getter
    @FindBy(xpath = ".//div[@data-placeholder = 'e.g. Short description of news, agenda for event']")
    private WebElement descriptionOfNews;

    @Getter
    @FindBy(xpath = ".//div[@class = 'date']//span[normalize-space() = 'Date:']")
    private WebElement dateOfNew;

    @Getter
    @FindBy(xpath = ".//div[@class = 'date']//span[normalize-space() = 'Author:']")
    private WebElement authorOfNew;

    @Getter
    @FindBy(xpath = ".///div[@class = 'submit-buttons']/button[normalize-space() = 'Cancel']")
    private WebElement cancelButton;

    @Getter
    @FindBy(xpath = ".//div[@class = 'submit-buttons']/button[normalize-space() = 'Preview']")
    private WebElement previewButton;

    @Getter
    @FindBy(xpath = ".//div[@class = 'submit-buttons']/button[normalize-space() = 'Publish']")
    private WebElement publishButton;

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

    // dropdownText
    protected DropdownTextSize getDropdownTextSize() {
        if (dropdownTextSize == null) {
            throw new RuntimeException(OPTION_NULL_MESSAGE);
        }
        return dropdownTextSize;
    }

    private DropdownTextSize createDropdownTextSize() {
        dropdownTextSize = new DropdownTextSize(driver,rootElement);
        return getDropdownTextSize();
    }
    private ContentOfNews clickDropdownSmallTextSize() {
        getDropdownTextSize().clickSmallStyleButton();
        return this;
    }
    private ContentOfNews clickDropdownNormalTextSize() {
        getDropdownTextSize().clickNormalStyleButton();
        return this;
    }
    private ContentOfNews clickDropdownHugeTextSize() {
        getDropdownTextSize().clickHugeStyleButton();
        return this;
    }
    private ContentOfNews clickDropdownLargeTextSize() {
        getDropdownTextSize().clickLargeStyleButton();
        return this;
    }
    public void clickCancelButton() {
        cancelButton.click();
    }
    public void clickPreviewButton() {
        previewButton.click();
    }

    public void clickPublishButton() {
        waitUntilElementClickable(publishButton);
        publishButton.click();
    }



}

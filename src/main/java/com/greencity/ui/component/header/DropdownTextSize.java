package com.greencity.ui.component.header;
import com.greencity.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DropdownTextSize extends BaseComponent {


    public DropdownTextSize(WebDriver driver,WebElement rootElement) {
        super(driver, rootElement);
    }
    @Getter
    @FindBy(xpath = ".//span[@class = 'ql-size ql-picker ql-expanded']//span[@data-value = 'small']")
    private WebElement smallStyleButton;

    @Getter
    @FindBy(xpath = ".//span[@id = 'ql-picker-options-6']//span[2]")
    private WebElement normalStyleButton;

    @Getter
    @FindBy(xpath = ".//span[@class = 'ql-size ql-picker ql-expanded']//span[@data-value = 'large']")
    private WebElement hugeStyleButton;

    @Getter
    @FindBy(xpath = ".//span[@class = 'ql-size ql-picker ql-expanded']//span[@data-value = 'huge']")
    private WebElement largeStyleButton;


    public String getSmallStyleButtonText() {
        return smallStyleButton.getText();
    }
    public String getNormalStyleButtonText() {
        return smallStyleButton.getText();
    }
    public String getHugeStyleButtonText() {
        return smallStyleButton.getText();
    }
    public String getLargeStyleButtonText() {
        return smallStyleButton.getText();
    }
    public void clickSmallStyleButton() {
        smallStyleButton.click();
    }
    public void clickNormalStyleButton() {
        normalStyleButton.click();
    }
    public void clickHugeStyleButton() {
        hugeStyleButton.click();
    }
    public void clickLargeStyleButton() {
        largeStyleButton.click();
    }
}

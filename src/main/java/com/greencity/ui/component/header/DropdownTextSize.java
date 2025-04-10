package com.greencity.ui.component.header;
import com.greencity.ui.component.BaseComponent;
import io.qameta.allure.Step;
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
    private WebElement largeStyleButton;

    @Getter
    @FindBy(xpath = ".//span[@class = 'ql-size ql-picker ql-expanded']//span[@data-value = 'huge']")
    private WebElement hugeStyleButton;

    public String getSmallStyleButtonText() {
        return getSmallStyleButton().getText();
    }
    public String getNormalStyleButtonText() {
        return getNormalStyleButton().getText();
    }
    public String getHugeStyleButtonText() {
        return getHugeStyleButton().getText();
    }
    public String getLargeStyleButtonText() {
        return getLargeStyleButton().getText();
    }

    @Step("Click the 'Small style' button")
    public void clickSmallStyleButton() {
        smallStyleButton.click();
    }

    @Step("Click the 'Normal style' button")
    public void clickNormalStyleButton() {
        normalStyleButton.click();
    }

    @Step("Click the 'Huge style' button")
    public void clickHugeStyleButton() {
        hugeStyleButton.click();
    }

    @Step("Click the 'Large style' button")
    public void clickLargeStyleButton() {
        largeStyleButton.click();
    }
}

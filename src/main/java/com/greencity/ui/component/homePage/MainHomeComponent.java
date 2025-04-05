
package com.greencity.ui.component.homePage;

import com.greencity.ui.component.BaseComponent;
import com.greencity.ui.modal.LoginModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import lombok.Getter;

public class MainHomeComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//h1")
    private WebElement headerTitle;

    @Getter
    @FindBy(xpath = ".//p")
    private WebElement headerParagraph;

    @Getter
    @FindBy(xpath = ".//button[@class='primary-global-button btn']")
    private WebElement headerButton;

    @Getter
    @FindBy(xpath = ".//img[@id='guy-image']")
    private WebElement guyImage;

    @Getter
    @FindBy(xpath = "//mat-dialog-container")
    private WebElement loginModalRoot;

    public MainHomeComponent(WebDriver driver, WebElement mainHomeRoot) {
        super(driver, mainHomeRoot);
    }

    public String getHeaderTitleText() {
        return headerTitle.getText();
    }

    public String getHeaderParagraphText() {
        return headerParagraph.getText();
    }

    public String getHeaderButtonText() {
        return headerButton.getText();
    }

    public LoginModal clickHeaderButton() {
        waitUntilElementClickable(headerButton);
        headerButton.click();
        return new LoginModal(driver, loginModalRoot);
    }

    public boolean isGuyImageDisplayed() {
        return guyImage.isDisplayed();
    }

}

package com.greencity.ui.modal;

import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.page.econewspage.NewsCardPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CancelModal extends BaseModal {

    @Getter
    @FindBy(xpath = ".//button[@class= 'secondary-global-button ng-star-inserted']")
    private WebElement continueEditingButton;
    @Getter
    @FindBy(xpath = ".//button[@class='m-btn primary-global-button']")
    private WebElement yesCancelButton;

    @Getter
    @FindBy(xpath = ".//img[@alt='close']")
    private WebElement closeIcon;


    public CancelModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getNoButtonText() {
        return continueEditingButton.getText();
    }
    public String getYesButtonText() {
        return yesCancelButton.getText();
    }

    public CreateEditNewsPage clickContinueEditingButton() {
        waitUntilElementClickable(continueEditingButton);
        continueEditingButton.click();
        return new CreateEditNewsPage(driver);
    }

    public EcoNewsPage clickYesCancelButton() {
        waitUntilElementClickable(yesCancelButton);
        yesCancelButton.click();
        return new EcoNewsPage(driver);
    }

    public CreateEditNewsPage clickCloseIcon() {
        waitUntilElementClickable(closeIcon);
        closeIcon.click();
        return new CreateEditNewsPage(driver);
    }
}

package com.greencity.ui.modal;

import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.page.econewspage.NewsCardPage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteModal extends BaseModal {

    @Getter
    @FindBy(xpath = ".//div[@class= 'warning-title ng-star-inserted']")
    private WebElement modalTitle;

    @Getter
    @FindBy(xpath = ".//button[@class= 'secondary-global-button ng-star-inserted']")
    private WebElement noButton;

    @Getter
    @FindBy(xpath = ".//button[@class= 'primary-global-button']")
    private WebElement yesButton;


    public DeleteModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getModalTitleText() {
        return modalTitle.getText();
    }

    public String getNoButtonText() {
        return noButton.getText();
    }

    public String getYesButtonText() {
        return yesButton.getText();
    }

    @Step("Click 'No' button")
    public NewsCardPage clickNoButton() {
        waitUntilElementClickable(noButton);
        noButton.click();
        return new NewsCardPage(driver);
    }

    @Step("Click 'Yes' button")
    public EcoNewsPage clickYesButton() {
        waitUntilElementClickable(yesButton);
        yesButton.click();
        return new EcoNewsPage(driver);
    }


}

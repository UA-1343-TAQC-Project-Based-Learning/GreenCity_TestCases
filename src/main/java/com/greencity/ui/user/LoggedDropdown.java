package com.greencity.ui.user;

import com.greencity.ui.component.BaseComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoggedDropdown extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//ul[contains(@class, 'dropdown-list drop-down-item')]//li[@aria-label='notifications']")
    private WebElement notifications;
    @Getter
    @FindBy(xpath = ".//ul[contains(@class, 'dropdown-list drop-down-item')]//li[@aria-label='sign-out']")
    private WebElement signOut;
    @Getter
    @FindBy(xpath = ".//ul[contains(@class, 'dropdown-list drop-down-item')]//a[@href='#/ubs/user/orders']")
    private WebElement ubsUser;

    public LoggedDropdown(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
    @Step("Click on the Notifications dropdown item")
    public void clickNotifications() {
        notifications.click();
    }

    @Step("Click on the Sign Out dropdown item")
    public void clickSignOut() {
        signOut.click();
    }

    @Step("Click on the UBS User dropdown item")
    public void clickUbsUser() {
        ubsUser.click();
    }
}

package com.greencity.ui.user;

import com.greencity.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoggedDropdown extends BaseComponent {

    public LoggedDropdown(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Getter
    @FindBy(xpath =".//ul[contains(@class, 'dropdown-list drop-down-item')]//li[@aria-label='notifications']")
    private WebElement notifications;

    @Getter
    @FindBy(xpath =".//ul[contains(@class, 'dropdown-list drop-down-item')]//li[@aria-label='sign-out']")
    private WebElement signOut;

    @Getter
    @FindBy(xpath =".//ul[contains(@class, 'dropdown-list drop-down-item')]//a[@href='#/ubs/user/orders']")
    private WebElement ubsUser;

    public void clickNotifications() {
        notifications.click();
    }
    public void clickSignOut() {
        signOut.click();
    }
    public void clickUbsUser() {
        ubsUser.click();
    }






}

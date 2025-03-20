package com.greencity.ui.user;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoggedDropdown {

    @Getter
    @FindBy(xpath =".//ul[@id='header_user-wrp']")
    private WebElement userName;

    @Getter
    @FindBy(xpath =".//ul[contains(@class, 'dropdown-list drop-down-item')]//li[@aria-label='notifications']")
    private WebElement notifications;

    @Getter
    @FindBy(xpath =".//ul[contains(@class, 'dropdown-list drop-down-item')]//li[@aria-label='sign-out']")
    private WebElement signOut;

    @Getter
    @FindBy(xpath =".//ul[contains(@class, 'dropdown-list drop-down-item')]//a[@href='#/ubs/user/orders']")
    private WebElement ubsUser;

    public void clickAddUserName() {

    }
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

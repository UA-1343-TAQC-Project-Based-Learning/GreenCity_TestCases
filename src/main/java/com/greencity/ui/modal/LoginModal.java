package com.greencity.ui.modal;

import com.greencity.ui.page.MySpacePage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginModal extends BaseModal {
    @Getter
    @FindBy(xpath = ".//input[@id='email']")
    private WebElement emailInput;

    @Getter
    @FindBy(xpath = ".//input[@id='password']")
    private WebElement passwordInput;

    @Getter
    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement signInButton;

    public LoginModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Clear 'Email' input")
    public void clearEmailInput() {
        emailInput.clear();
    }

    @Step("Click 'Email' input")
    public void clickEmailInput() {
        emailInput.click();
    }

    @Step("Set Email Input {email}")
    public void setEmailInput(String email) {
        emailInput.sendKeys(email);
    }

    @Step("Clear 'Password' input")
    public void clearPasswordInput() {
        passwordInput.clear();
    }

    @Step("Click 'Password' input")
    public void clickPasswordInput() {
        passwordInput.click();
    }

    @Step("Set Password Input {password}")
    public void setPasswordInput(String password) {
        passwordInput.sendKeys(password);
    }

    @Step("Click 'Sign In' button")
    public void clickSignInButton() {
        signInButton.click();
    }

    @Step("Enter Email Input {email}")
    private void enterEmailInput(String email) {
        clickEmailInput();
        clearEmailInput();
        setEmailInput(email);
    }

    @Step("Enter Password Input {password}")
    private void enterPasswordInput(String password) {
        clickPasswordInput();
        clearPasswordInput();
        setPasswordInput(password);
    }

    @Step("Fill Email Input with {email}, Password Input with {password}")
    public void fillLogin(String email, String password) {
        enterEmailInput(email);
        enterPasswordInput(password);
        clickSignInButton();
    }

    @Step("Login with Email: {email}, Password: {password}")
    public MySpacePage successfulLogin(String email, String password) {
        fillLogin(email, password);
        return new MySpacePage(driver);
    }
}

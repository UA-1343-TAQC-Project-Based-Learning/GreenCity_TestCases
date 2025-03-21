package com.greencity.ui.modal;

import com.greencity.ui.page.MySpacePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginModal extends BaseModal{
    @Getter
    @FindBy(xpath = ".//input[@id='email']")
    private WebElement emailInput;

    @Getter
    @FindBy(xpath = ".//input[@id='password']")
    private WebElement passwordInput;

    @Getter
    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement signInButton;

    public LoginModal(WebDriver driver, WebElement rootElement){
        super(driver, rootElement);
    }

    public void clearEmailInput() {
        emailInput.clear();
    }

    public void clickEmailInput() {
        emailInput.click();
    }

    public void setEmailInput(String email) {
        emailInput.sendKeys(email);
    }

    public void clearPasswordInput() {
        passwordInput.clear();
    }

    public void clickPasswordInput() {
        passwordInput.click();
    }

    public void setPasswordInput(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickSignInButton() {
        waitUntilElementClickable(signInButton);
        signInButton.click();
    }

    private void enterEmailInput(String email) {
        clickEmailInput();
        clearEmailInput();
        setEmailInput(email);
    }

    private void enterPasswordInput(String password) {
        clickPasswordInput();
        clearPasswordInput();
        setPasswordInput(password);
    }

    public void fillLogin(String email, String password) {
        enterEmailInput(email);
        enterPasswordInput(password);
        clickSignInButton();
    }

    public MySpacePage successfulLogin(String email, String password) {
        fillLogin(email, password);
        return new MySpacePage(driver);
    }
}

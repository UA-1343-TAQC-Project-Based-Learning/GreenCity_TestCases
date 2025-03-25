package com.greencity.ui.modal;

import com.greencity.ui.page.MySpacePage;
import com.greencity.ui.page.econewspage.CreateNewsPage;
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

    public LoginModal clearEmailInput() {
        emailInput.clear();
        return this;
    }

    public LoginModal clickEmailInput() {
        emailInput.click();
        return this;
    }

    public LoginModal setEmailInput(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public void clearPasswordInput() {
        passwordInput.clear();
    }

    public LoginModal clickPasswordInput() {
        passwordInput.click();
        return this;
    }

    public void setPasswordInput(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickSignInButton() {
        signInButton.click();
    }

    private LoginModal enterEmailInput(String email) {
        clickEmailInput()
                .clearEmailInput()
                .setEmailInput(email);
        return this;
    }

    private LoginModal enterPasswordInput(String password) {
        clickPasswordInput();
        clearPasswordInput();
        setPasswordInput(password);
        return this;
    }

    public void fillLogin(String email, String password) {
        enterEmailInput(email)
                .enterPasswordInput(password)
                .clickSignInButton();
    }

    public MySpacePage successfulLogin(String email, String password) {
        fillLogin(email, password);
        return new MySpacePage(driver);
    }

}

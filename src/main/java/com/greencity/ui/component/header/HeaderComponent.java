package com.greencity.ui.component.header;

import com.greencity.ui.component.BaseComponent;
import com.greencity.ui.page.homepage.HomePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HeaderComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//img[@src='assets/img/logo.svg']")
    private WebElement logo;

    @Getter
    @FindBy(xpath = ".//div[@class='header_navigation-menu']//a[contains(text(),'Еко новини')]")
    private WebElement ecoNewsLink;

    @Getter
    @FindBy(xpath = ".//div[@class='header_navigation-menu']//a[contains(text(),'Події')]")
    private WebElement eventLink;

    @Getter
    @FindBy(xpath = ".//div[@class='header_navigation-menu']//a[contains(text(),'Карта')]")
    private WebElement placeLink;

    @Getter
    @FindBy(xpath = ".//div[@class='header_navigation-menu']//a[contains(text(),'Про нас')]")
    private WebElement aboutUsLink;

    @Getter
    @FindBy(xpath = ".//div[@class='header_navigation-menu']//a[contains(text(),'Мій кабінет')]")
    private WebElement myProfileLink;

    @Getter
    @FindBy(xpath = ".//div[@class='header_navigation-menu']//a[contains(text(),'UBS')]")
    private WebElement ubsCourierLink;

    @Getter
    @FindBy(xpath = ".//img[@src='assets/img/search.svg']")
    private WebElement searchIcon;

    @Getter
    @FindBy(xpath = ".//li[@class='lang-option']")
    private WebElement languageSwitcherIcon;

    @Getter
    @FindBy(xpath = ".//li[@class='lang-option']")
    private WebElement signInButton;

    @Getter
    @FindBy(xpath = ".//span[contains(text(),'Зареєструватися')]")
    private WebElement registrationButton;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
    public WebElement getLogo() {
        return logo;
    }
    public String getLogoText() {
        return getLogo().getText();
    }
    public WebElement getEcoNewsLink() {
        return ecoNewsLink;
    }
    public String getEcoNewsLinkText() {
        return getEcoNewsLink().getText();
    }
    public WebElement getEventLink() {
        return eventLink;
    }
    public String getEventLinkText() {
        return getEventLink().getText();
    }

    public WebElement getPlaceLink() {
        return placeLink;
    }
    public String getPlaceLinkText() {
        return getPlaceLink().getText();
    }
    public WebElement getMyProfileLink() {
        return myProfileLink;
    }
    public String getMyProfileLinkText() {
        return getMyProfileLink().getText();
    }

    public WebElement getAboutUsLink() {
        return aboutUsLink;
    }
    public String getAboutUsLinkText() {
        return getAboutUsLink().getText();
    }

    public WebElement getUbsCourierLink() {
        return ubsCourierLink;
    }
    public String getUbsCourierLinkText() {
        return getUbsCourierLink().getText();
    }

    public WebElement getSearchIcon() {
        return searchIcon;
    }
    public String getSearchIconText() {
        return getSearchIcon().getText();
    }
    public WebElement getLanguageSwitcherIcon() {
        return languageSwitcherIcon;
    }
    public String getLanguageSwitcherIconText() {
        return getLanguageSwitcherIcon().getText();
    }
    public WebElement getSignInButton() {
        return signInButton;
    }
    public String getSignInButtonText() {
        return getSignInButton().getText();
    }
    public WebElement getRegistrationButton() {
        return registrationButton;
    }
    public String getRegistrationButtonText() {
        return getRegistrationButton().getText();
    }
    public void clickEcoNews(){
        ecoNewsLink.click();
    }
    public void clickEventLink(){
        eventLink.click();
    }
    public void clickPlaceLink(){
        placeLink.click();
    }
    public void clickAboutUsLink(){
        aboutUsLink.click();
    }
    public void clickMyProfileLink(){
        myProfileLink.click();
    }

    public void clickUbsCourierLink(){
        ubsCourierLink.click();
    }
    public void clickSearchIcon(){
        searchIcon.click();
    }

    public void clickLanguageSwitcherIcon(){
        languageSwitcherIcon.click();
    }

    public void clickSignIn(){
        signInButton.click();
    }
    public void clickRegistrationButton(){
        registrationButton.click();
    }

/*
    public EcoNewsPage gotoEcoNewsPage() {
        clickEcoNews();
        return new EcoNewsPage(driver);
    }

 */
}

package com.greencity.ui.component.header;
import com.greencity.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public  class HeaderComponent extends BaseComponent {
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
    @FindBy(xpath = ".//img[@src='../assets/img/events/user.svg']")
    private WebElement signInButton;

    @Getter
    @FindBy(xpath = ".//span[contains(text(),'Зареєструватися')]")
    private WebElement registrationButton;

    public HeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
    public String getLogoText() {
        return getLogo().getText();
    }
    public String getEcoNewsLinkText() {
        return ecoNewsLink.getText();
    }
    public String getEventLinkText() {
        return getEventLink().getText();
    }
    public String getPlaceLinkText() {
        return getPlaceLink().getText();
    }
    public String getMyProfileLinkText() {
        return getMyProfileLink().getText();
    }
    public String getAboutUsLinkText() {
        return getAboutUsLink().getText();
    }
    public String getUbsCourierLinkText() {
        return getUbsCourierLink().getText();
    }
    public String getSearchIconText() {
        return getSearchIcon().getText();
    }
    public String getLanguageSwitcherIconText() {
        return getLanguageSwitcherIcon().getText();
    }
    public String getSignInButtonText() {
        return getSignInButton().getText();
    }
    public String getRegistrationButtonText() {
        return getRegistrationButton().getText();
    }
    public void clickEcoNewsLink(){
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
}
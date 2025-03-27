package com.greencity.ui.component.header;

import com.greencity.ui.component.BaseComponent;
import com.greencity.ui.modal.LoginModal;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.page.homepage.HomePage;
import com.greencity.ui.user.LoggedDropdown;
import com.greencity.ui.user.UsersHeaderComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HeaderComponent extends BaseComponent {
    protected final String OPTION_NULL_MESSAGE = "Dropdown is null";
    protected final String LIST_LANGUAGE_XPATH_SELECTOR = ".//ul[contains(@class, 'header_lang-switcher-wrp header_navigation-menu-right-lang add-shadow')]";

    private DropdownComponent dropdownComponent;
    private UsersHeaderComponent usersHeaderComponent;
    private LoggedDropdown dropdownLogged;
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
    @FindBy(xpath = ".//img[@class='header_arrow reverse']")
    private WebElement languageSwitcherIcon;


    @Getter
    @FindBy(xpath=".//ul[contains(@class, 'header_lang-switcher-wrp header_navigation-menu-right-lang add-shadow')]//span[text()='Ua' or text()='En']")
    private List<WebElement> languagesList;

    @Getter
    @FindBy(xpath = ".//img[@src='../assets/img/events/user.svg']")
    private WebElement userIcon;

    @Getter
    @FindBy(xpath = ".//span[contains(text(),'Зареєструватися')]")
    private WebElement registrationButton;

    @Getter
    @FindBy(xpath = ".//img[@src='../assets/img/events/user.svg']")
    private WebElement signInButton;


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

    public String getLanguageSwitcherIconText() {
        return getLanguageSwitcherIcon().getText();
    }

    public String getSignInButtonText() {
        return getSignInButton().getText();
    }

    public String getRegistrationButtonText() {
        return getRegistrationButton().getText();
    }

    public void clickEcoNewsLink() {
        ecoNewsLink.click();
    }

    public void clickEventLink() {
        eventLink.click();
    }

    public void clickPlaceLink() {
        placeLink.click();
    }

    public void clickAboutUsLink() {
        aboutUsLink.click();
    }

    public void clickMyProfileLink() {
        myProfileLink.click();
    }

    public void clickUbsCourierLink() {
        ubsCourierLink.click();
    }

    public void clickSearchIcon() {
        searchIcon.click();
    }

    public void clickLanguageSwitcherIcon() {
        languageSwitcherIcon.click();
    }
    public void clickUserIcon() {
        userIcon.click();
    }

    public void clickSignIn() {
        signInButton.click();
    }

    public void clickRegistrationButton() {
        registrationButton.click();
    }

    public WebElement getLanguageDropdown() {
        return languageSwitcherIcon;
    }

    public void clickLanguageDropdown() {
        getLanguageDropdown().click();
    }

    protected DropdownComponent getDropdownComponent() {
        if (dropdownComponent == null) {

            throw new RuntimeException(OPTION_NULL_MESSAGE);
        }
        return dropdownComponent;
    }

    private DropdownComponent createDropdownComponent(By searchLocator) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        dropdownComponent = new DropdownComponent(driver, searchLocator);

        return getDropdownComponent();
    }

    private void openLanguageDropdownComponent() {
        clickLanguageDropdown();
        createDropdownComponent(By.cssSelector(LIST_LANGUAGE_XPATH_SELECTOR));
    }

    protected LoggedDropdown getDropdownLogged() {
        if (dropdownLogged == null) {

            throw new RuntimeException(OPTION_NULL_MESSAGE);
        }
        return dropdownLogged;
    }

    private LoggedDropdown createDropdownLogged() {
        dropdownLogged = new LoggedDropdown(driver, rootElement);
        return getDropdownLogged();
    }

    private void clickDropdownLoggedNotifications() {
        getDropdownLogged().clickNotifications();
        dropdownLogged = null;
    }

    private void clickDropdownLoggedSignOut() {
        getDropdownLogged().clickSignOut();
        dropdownLogged = null;
    }

    private void clickDropdownLoggedUbsUser() {
        getDropdownLogged().clickUbsUser();
        dropdownLogged = null;
    }

    private void closeDropdownLogged() {
        usersHeaderComponent.clickUserName();
        dropdownLogged = null;
    }
    public LoginModal openLoginModal() {
        clickUserIcon();
        return new LoginModal(driver,rootElement);
    }

    public HomePage signOutUser() {
        usersHeaderComponent.clickUserName();
        createDropdownLogged();
        clickDropdownLoggedSignOut();
        return new HomePage(driver);
    }

    public EcoNewsPage gotoEcoNewsPage() {
        clickEcoNewsLink();
        return new EcoNewsPage(driver);
    }


}

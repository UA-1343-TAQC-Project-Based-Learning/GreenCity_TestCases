package com.greencity.ui.user;
import com.greencity.ui.component.header.HeaderComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UsersHeaderComponent extends HeaderComponent {

    public UsersHeaderComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Getter
    @FindBy(xpath = ".//li[@class='bookmark-icon ng-star-inserted']")
    private WebElement bookMarkIcon;

    @Getter
    @FindBy(xpath = ".//li[@class='notification-icon ng-star-inserted']")
    private WebElement notificationIcon;

    @Getter
    @FindBy(xpath = ".//li[@class='chat-icon']")
    private WebElement chatIcon;


    @Getter
    @FindBy(xpath =".//ul[@id='header_user-wrp']")
    private WebElement userName;

    public String getUserNameText() {
        return userName.getText();
    }

    public void clickUserName(){
        userName.click();
    }

}

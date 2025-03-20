package com.greencity.ui.page.econewspage;

import com.greencity.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class EcoNewsPage extends BasePage {

    @Getter
    @FindBy(xpath = ".//h1[@class='main-header']")
    private WebElement headerText;

    @Getter
    @FindBy(xpath = ".//span[@id='create-button-text']")
    private WebElement createNewsButton;

    public EcoNewsPage(WebDriver driver) {
        super(driver);
    }


    public String getHeaderText() {
        waitUntilElementVisible(headerText);
        return headerText.getText();
    }

    public CreateNewsPage clickCreateNewsButton() {
        waitUntilElementClickable(createNewsButton);
        createNewsButton.click();
        return new CreateNewsPage(driver);
    }

}

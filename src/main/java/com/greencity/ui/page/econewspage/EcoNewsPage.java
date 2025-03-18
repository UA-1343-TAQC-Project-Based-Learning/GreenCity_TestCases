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

    @FindBy(xpath = ".//span[@class='filter']")
    private WebElement filterByTitleText;

    @Getter
    @FindBy(xpath = ".//span[@id='create-button-text']")
    private WebElement createNewsButton;

    @FindBy(xpath = ".//h2[contains(text(), 'items found') or contains(text(), 'новин знайдено')]")
    private WebElement newsCounterText;

    public EcoNewsPage(WebDriver driver) {
        super(driver);
    }

    public String getHeaderText() {
        waitUntilElementVisible(headerText);
        return headerText.getText();
    }

    public String getFilterByTitleText() {
        return filterByTitleText.getText();
    }

    public CreateNewsPage clickCreateNewsButton() {
        waitUntilElementClickable(createNewsButton);
        createNewsButton.click();
        return new CreateNewsPage(driver);
    }


}

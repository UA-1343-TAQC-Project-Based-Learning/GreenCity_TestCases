package com.greencity.ui.page;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EcoNewsPage extends BasePage {
//    protected ToolbarComponent toolbarComponent;
//    protected EcoNewsCardContainer ecoNewsCardContainer;

    @Getter
    @FindBy(xpath = ".//h1[@class='main-header']")
    private WebElement headerText;

    @Getter
    @FindBy(xpath = ".//span[@id='create-button-text']")
    private WebElement createNewsButton;

    public EcoNewsPage(WebDriver driver) {
        super(driver);
//        toolbarComponent = new ToolbarComponent(driver);
//        ecoNewsCardContainer = new EcoNewsCardContainer();
    }

    public String getHeaderText() {
        return headerText.getText();
    }

    public void clickCreateNewsButton() {
        createNewsButton.click();
    }


}

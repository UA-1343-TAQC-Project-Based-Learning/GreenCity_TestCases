package com.greencity.ui.page.econewspage;
import com.greencity.ui.component.ToolbarComponent;
import com.greencity.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class EcoNewsPage extends BasePage {
    private ToolbarComponent toolbarComponent;

    @Getter
    @FindBy(xpath = ".//h1[@class='main-header']")
    private WebElement headerText;

    @Getter
    @FindBy(xpath = ".//span[@id='create-button-text']")
    private WebElement createNewsButton;

    public EcoNewsPage(WebDriver driver) {
        super(driver);
        toolbarComponent = new ToolbarComponent(driver, getHeaderRoot());
    }


    public String getHeaderText() {
        waitUntilElementVisible(headerText);
        return headerText.getText();
    }

    public void clickCreateNewsButton() {
        waitUntilElementClickable(createNewsButton);
        createNewsButton.click();
    }


    public CreateNewsPage goToCreateNewsPage() {
        waitUntilElementClickable(createNewsButton);
        createNewsButton.click();
        return new CreateNewsPage(driver);
    }

}

package com.greencity.ui.page.econewspage;

import com.greencity.ui.component.ecoNewsTag.EcoNewsTagFilterComponent;
import com.greencity.ui.container.NewsCardsContainer;
import com.greencity.ui.elements.ToolbarElement;
import com.greencity.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EcoNewsPage extends BasePage {

    protected ToolbarElement toolbarElement;
    protected EcoNewsTagFilterComponent ecoNewsTagFilterComponent;
    protected NewsCardsContainer newsCardsContainer;

    public static final String SUCCESSFUL_CREATE_CARD_MESSAGE = "Your news has been successfully published";

    @FindBy(xpath = "//div[@class='create-container']")
    private WebElement toolbarRoot;

    @FindBy(xpath = "//div[@class='wrapper']/div[@class='ul-eco-buttons']")
    private WebElement newsFilterRoot;

    @FindBy(xpath = "//div[@class='list-wrapper']")
    private WebElement newsContainerRoot;

    @Getter
    @FindBy(xpath = "//h1[@class='main-header']")
    private WebElement headerText;

    @FindBy(xpath = "//span[@class='filter']")
    private WebElement filterByTitleText;

    @Getter
    @FindBy(xpath = "//span[@id='create-button-text']")
    private WebElement createNewsButton;

    @FindBy(xpath = "//h2[contains(text(),'items found') or contains(text(),'новин знайдено')]")
    private WebElement newsCounterText;

    @Getter
    @FindBy(xpath="//div[contains(@class,'mdc-snackbar__label')]")
    private WebElement createCardMessage;

    public EcoNewsPage(WebDriver driver) {
        super(driver);
        toolbarElement = new ToolbarElement(driver, toolbarRoot);
        ecoNewsTagFilterComponent = new EcoNewsTagFilterComponent(driver, newsFilterRoot);
        newsCardsContainer = new NewsCardsContainer(driver, newsContainerRoot);
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

    public String getNewsCounterText() {
        return newsCounterText.getText();
    }

    public String getCreateCardMessageText(){
        return createCardMessage.getText();
    }

    public boolean isExistCardComponentByPartialTitle(String partialTitle){
       return newsCardsContainer.isExistCardComponentByPartialTitle(partialTitle);
    }
}

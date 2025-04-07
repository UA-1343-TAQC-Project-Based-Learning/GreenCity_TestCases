package com.greencity.ui.page.econewspage;

import com.greencity.ui.component.ecoNewsTag.EcoNewsTagFilterComponent;
import com.greencity.ui.container.NewsCardsContainer;
import com.greencity.ui.elements.ToolbarElement;
import com.greencity.ui.page.BasePage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class EcoNewsPage extends BasePage {

    protected ToolbarElement toolbarElement;
    protected EcoNewsTagFilterComponent ecoNewsTagFilterComponent;
    @Getter
    protected NewsCardsContainer newsCardsContainer;

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
    @FindBy(xpath = "//div[contains(@class,'mdc-snackbar__label')]")
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

    @Step("Click 'Create news' button")
    public CreateEditNewsPage clickCreateNewsButton() {
        waitUntilElementClickable(createNewsButton);
        createNewsButton.click();
        return new CreateEditNewsPage(driver);
    }

    public String getNewsCounterText() {
        return newsCounterText.getText();
    }

    public String getCreateCardMessageText() {
        return createCardMessage.getText();
    }

    public boolean isExistCardComponentByPartialTitle(String partialTitle) {
        return newsCardsContainer.isExistCardComponentByPartialTitle(partialTitle);
    }

    @Step("Open News Card page clicking by title '{title}'")
    public NewsCardPage goToNewsCardPage(String title) {
        newsCardsContainer.clickComponentByTitle(title);
        return new NewsCardPage(driver);
    }

    public String getFirstCardTitle() {
        return newsCardsContainer.getCardComponentTitles().getFirst();
    }

    public LocalDate getDataLabelFormating(Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", locale);
        return LocalDate.parse(newsCardsContainer.getCardComponentDatesOfCreation().getFirst(), formatter);
    }

}

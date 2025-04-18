package com.greencity.ui.container;

import com.greencity.ui.component.cards.NewsCardListViewComponent;
import com.greencity.ui.component.cards.NewsCardTableViewComponent;
import com.greencity.ui.page.econewspage.NewsCardPage;
import io.qameta.allure.Step;
import lombok.Getter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;



public class NewsCardsContainer extends BaseContainer {
    @Getter
    private List<NewsCardListViewComponent> newsCardListViewComponents;
    @Getter
    private List<NewsCardTableViewComponent> newsCardTableViewComponents;

    @FindBy(xpath = "//em[@class='fa fa-th-large']")
    private WebElement tableViewButton;

    @FindBy(xpath = "//em[@class='fa fa-bars']")
    private WebElement listViewButton;
    @Getter
    @FindBy(xpath = ".//div[@class='list-gallery']")
    private List<WebElement> newsCardTableViewComponentRoots;
    @Getter
    @FindBy(xpath = ".//div[@class='eco-news_list-view-wrp']")
    private List<WebElement> newsCardListViewComponentRoots;


    public NewsCardsContainer(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        initElements();
    }

    @Step("Init elements of NewsCardsContainer")
    private void initElements() {
        if (isTableViewButtonActive()) {
            newsCardTableViewComponents = new ArrayList<>();
            for (WebElement current : newsCardTableViewComponentRoots) {
                newsCardTableViewComponents.add(new NewsCardTableViewComponent(driver, current));
            }
        } else {
            newsCardListViewComponents = new ArrayList<>();
            for (WebElement current : newsCardListViewComponentRoots) {
                newsCardListViewComponents.add(new NewsCardListViewComponent(driver, current));
            }
        }
    }

    public List<String> getCardComponentTitles() {
        List<String> cardComponentNames = new ArrayList<>();
        if (isTableViewButtonActive()) {
            for (NewsCardTableViewComponent current : getNewsCardTableViewComponents()) {
                cardComponentNames.add(current.getTitleLabelText());
            }
        } else {
            for (NewsCardListViewComponent current : getNewsCardListViewComponents()) {
                cardComponentNames.add(current.getTitleLabelText());
            }
        }
        return cardComponentNames;
    }

    public List<String> getCardComponentDatesOfCreation() {
        List<String> cardComponentDates = new ArrayList<>();
        if (isTableViewButtonActive()) {
            for (NewsCardTableViewComponent current : getNewsCardTableViewComponents()) {
                cardComponentDates.add(current.getDateOfCreationLabelText());
            }
        } else {
            for (NewsCardListViewComponent current : getNewsCardListViewComponents()) {
                cardComponentDates.add(current.getDateOfCreationLabelText());
            }
        }
        return cardComponentDates;
    }

    @Step("Check if CardComponent by partial title [{partialTitle}] exists")
    public boolean isExistCardComponentByPartialTitle(String partialTitle) {
        boolean isFound = false;
        for (String current : getCardComponentTitles()) {
            if (current.toLowerCase().contains(partialTitle.toLowerCase())) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    @Step("Click component by title: {partialTitle}")
    public void clickComponentByTitle(String partialTitle) {
        if (isTableViewButtonActive()) {
            for (NewsCardTableViewComponent currentCard : newsCardTableViewComponents) {
                if (currentCard.getTitleLabelText().contains(partialTitle)) {
                    currentCard.titleLabelClick();
                    break;
                }
            }
        } else {
            for (NewsCardListViewComponent currentCard : newsCardListViewComponents) {
                if (currentCard.getTitleLabelText().contains(partialTitle)) {
                        currentCard.titleLabelClick();
                        break;
                }
            }
        }
    }


    @Step("Click table view button")
    public NewsCardsContainer clickTableViewButton() {
        waitUntilElementClickable(tableViewButton);
        tableViewButton.click();
        return new NewsCardsContainer(driver, rootElement);
    }

    @Step("Check if table view button is active")
    public boolean isTableViewButtonActive() {
        return tableViewButton.isEnabled();
    }

    @Step("Check if list view button is active")
    public boolean isListViewButtonActive() {
        return listViewButton.isEnabled();
    }

    @Step("Click list view button")
    public NewsCardsContainer clickListViewButton() {
        waitUntilElementClickable(listViewButton);
        listViewButton.click();
        return new NewsCardsContainer(driver, rootElement);
    }
}

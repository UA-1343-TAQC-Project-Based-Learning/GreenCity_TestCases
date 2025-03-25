package com.greencity.ui.container;

import com.greencity.ui.component.cards.NewsCardListViewComponent;
import com.greencity.ui.component.cards.NewsCardTableViewComponent;
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
    boolean isTableViewButtonActive;

    @FindBy(xpath = ".//div[@class='list-gallery']")
    private List<WebElement> newsCardTableViewComponentRoots;

    @FindBy(xpath = ".//div[@class='eco-news_list-view-wrp']")
    private List<WebElement> newsCardListViewComponentRoots;


    public NewsCardsContainer(WebDriver driver, WebElement rootElement, boolean isTableViewButtonActive) {
        super(driver, rootElement);
        this.isTableViewButtonActive = isTableViewButtonActive;
        initElements();
    }

    private void initElements() {
        if (isTableViewButtonActive) {
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
        if (isTableViewButtonActive) {
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


}


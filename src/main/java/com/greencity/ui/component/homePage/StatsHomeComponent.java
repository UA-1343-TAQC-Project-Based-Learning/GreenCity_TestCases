package com.greencity.ui.component.homePage;

import com.greencity.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import lombok.Getter;


public class StatsHomeComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//h2[@class='section-caption']")
    private WebElement header;

    @Getter
    @FindBy(xpath = ".//div[contains(@style, 'margin-left')]")
    private WebElement leftItemRoot;

    @Getter
    @FindBy(xpath = ".//div[contains(@style, 'margin-right')]")
    private WebElement rightItemRoot;

    @Getter
    private StatItemHomeComponent leftItem;

    @Getter
    private StatItemHomeComponent rightItem;

    public StatsHomeComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.leftItem = new StatItemHomeComponent(driver, leftItemRoot);
        this.rightItem = new StatItemHomeComponent(driver, rightItemRoot);
    }

    public String getHeaderText() {
        return header.getText();
    }

}

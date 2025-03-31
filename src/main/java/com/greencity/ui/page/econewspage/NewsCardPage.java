package com.greencity.ui.page.econewspage;

import com.greencity.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class NewsCardPage extends BasePage {

    @Getter
    @FindBy(xpath = "//div[@class='edit-news']/..")
    private WebElement editButton;

    @Getter
    @FindBy(xpath = "//div[@class='tags']")
    private List<WebElement> filtersTag;

    @Getter
    @FindBy(xpath = "//div[@class='news-title word-wrap']")
    private WebElement titleLabel;

    @Getter
    @FindBy(xpath = "//div[@class= 'ql-editor']")
    private WebElement contentLabel;

    @Getter
    @FindBy(xpath = "//div[@class='news-info-date']")
    private WebElement dateOfCreationLabel;

    @Getter
    @FindBy(xpath = "//div[@class='news-info-author']")
    private WebElement usernameLabel;

    public NewsCardPage(WebDriver driver) {
        super(driver);
        driver.navigate().refresh();
    }

    public String getEditButtonText() {
        return editButton.getText();
    }

    public List<String> getFiltersTagText() {
        List<String> filtersTagNames = new ArrayList<>();
        for (WebElement current : filtersTag) {
            filtersTagNames.add(current.getText());
        }
        return filtersTagNames;
    }

    public String getTitleLabelText() {
        return titleLabel.getText();
    }
    public String getContentLabelText() {
        return contentLabel.getText();
    }

    public String getDateOfCreationLabelText() {
        return dateOfCreationLabel.getText();
    }

    public String getUsernameLabelText() {
        return usernameLabel.getText();
    }


    public CreateEditNewsPage clickEditButton() {
        waitUntilElementClickable(editButton);
        editButton.click();
        return new CreateEditNewsPage(driver) ;
    }

}

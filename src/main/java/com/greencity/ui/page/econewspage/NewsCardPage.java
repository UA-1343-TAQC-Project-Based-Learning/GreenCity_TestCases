package com.greencity.ui.page.econewspage;

import com.greencity.ui.modal.DeleteModal;
import com.greencity.ui.page.BasePage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NewsCardPage extends BasePage {

    @Getter
    @FindBy(xpath = "//a[@class= 'button-link']")
    private WebElement backButton;

    @Getter
    @FindBy(xpath = "//button[@class= 'secondary-global-button delete-news-button']")
    private WebElement deleteButton;

    @Getter
    @FindBy(xpath = "//div[@class='edit-news']/..")
    private WebElement editButton;

    @Getter
    @FindBy(xpath = "//div[contains(@class,'tags-item')]")
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

    @Getter
    @FindBy(xpath = "//mat-dialog-container")
    private WebElement deleteModalRoot;

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

    public CreateEditNewsPage clickEditButton()  {
    @Step("Click 'Edit' button")
    public CreateEditNewsPage clickEditButton() {
        waitUntilElementClickable(editButton);
        editButton.click();
        return new CreateEditNewsPage(driver);
    }

    public String getBackButtonText() {
        return backButton.getText();
    }

    @Step("Click 'Back to News' button")
    public EcoNewsPage clickBackButton() {
        waitUntilElementClickable(backButton);
        backButton.click();
        return new EcoNewsPage(driver);
    }

    public String getDeleteButtonText() {
        return deleteButton.getText();
    }

    @Step("Click 'Delete' button")
    public DeleteModal clickDeleteButton() {
        waitUntilElementClickable(deleteButton);
        deleteButton.click();
        return new DeleteModal(driver, deleteModalRoot);
    }

    public LocalDate getDataLabelFormating(Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", locale);
        return LocalDate.parse(getDateOfCreationLabelText(), formatter);
    }

}

package com.greencity.ui.createNewsPage;

import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.page.econewspage.CreateNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class CreateNewsPageSteps extends BaseTestRunner {
    protected SoftAssert softAssert = new SoftAssert();
    CreateNewsPage createNewsPage;

    public CreateNewsPageSteps goToCreateEcoNewsPage() {
        createNewsPage = new CreateNewsPage(driver);

        loadApplication()
                .openEcoNewsPage()
                .clickCreateNewsButton();
        return this;
    }

    public CreateNewsPageSteps checkTitleInputFieldBorderColor(String color) {
        createNewsPage
                .clickTitleInputTextField()
                .clickTitleHeaderText();

        logger.info("The actual border color is: " + createNewsPage.getTitleInputFieldBorderColor());
        softAssert.assertTrue(createNewsPage.getTitleInputFieldBorderColor().equals(color),
                "The border color should be red: " + color);

        return this;
    }

    public CreateNewsPageSteps checkTitleFieldCharacterCounterText(String counterText) {
        softAssert.assertTrue(createNewsPage.getTitleFieldCharacterCounterText().equals(counterText),
                "The number of characters should equal: " + counterText);
        return this;
    }

    public CreateNewsPageSteps checkTitleFieldCharacterCounterWarningTextColor(String color) {
        logger.info("The actual counter text color is: " + createNewsPage.getTitleFieldCharacterCounterWarningTextColor());
        softAssert.assertTrue(createNewsPage.getTitleFieldCharacterCounterWarningTextColor().equals(color),
                "The counter text color should be: " + color);
        return this;
    }

    public CreateNewsPageSteps checkTitleInputTextFieldCharactersNumberValue(int charactersNumber) {
        logger.info("The actual text value of Title text field is: " + createNewsPage.getTitleInputTextFieldValue().length());
        softAssert.assertTrue(createNewsPage.getTitleInputTextFieldValue().length() == charactersNumber,
                "The text should equal " + charactersNumber + " characters.");
        return this;
    }

    public CreateNewsPageSteps checkPublishButtonIsDisabled() {
        softAssert.assertFalse(createNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");
        return this;
    }

    public CreateNewsPageSteps checkPublishButtonIsEnabled() {
        softAssert.assertTrue(createNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be enabled when all required fields are filled out");
        return this;
    }

    public CreateNewsPageSteps fillTitleInputTextField(String titleText) {
        createNewsPage
                .clickTitleInputTextField()
                .clearTitleInputTextField()
                .getTitleInputTextField().sendKeys(titleText);
        return this;
    }

    public CreateNewsPageSteps enterTextIntoTextContentField(String text) {
        createNewsPage.enterTextIntoTextContentField(text);
        return this;
    }

    public CreateNewsPageSteps clickTagFilterButton(TagButton tagButton) {
        createNewsPage.clickTagFilterButton(tagButton);
        logger.info("The " + tagButton + " tag button has been clicked");
        return this;
    }

    public CreateNewsPageSteps assertAll() {
        softAssert.assertAll();
        return this;
    }

}

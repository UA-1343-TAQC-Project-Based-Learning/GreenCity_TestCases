package com.greencity.ui.createnewspage;

import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.asserts.SoftAssert;

public class CreateNewsPageSteps extends BaseTestRunner {
    protected SoftAssert softAssert = new SoftAssert();
    private CreateEditNewsPage createEditNewsPage;

    public CreateNewsPageSteps goToCreateEcoNewsPage() {
        createEditNewsPage = new CreateEditNewsPage(driver);

        loadApplication()
                .gotoEcoNewsPage()
                .clickCreateNewsButton();
        return this;
    }

    public CreateNewsPageSteps checkTitleInputFieldBorderColor(String color) {
        createEditNewsPage
                .clickTitleInputTextField()
                .clickTitleHeaderText();

        logger.info("The actual border color is: {}", createEditNewsPage.getTitleInputFieldBorderColor());
        softAssert.assertTrue(createEditNewsPage.getTitleInputFieldBorderColor().equals(color),
                "The border color should be: " + color);

        return this;
    }

    public CreateNewsPageSteps checkTitleFieldCharacterCounterText(String counterText) {
        softAssert.assertTrue(createEditNewsPage.getTitleFieldCharacterCounterText().equals(counterText),
                "The number of characters should equal: " + counterText);
        return this;
    }

    public CreateNewsPageSteps checkTitleFieldCharacterCounterTextColor(String color) {
        logger.info("The actual counter text color is: {}", createEditNewsPage.getTitleFieldCharacterCounterTextColor());
        softAssert.assertTrue(createEditNewsPage.getTitleFieldCharacterCounterTextColor().equals(color),
                "The counter text color should be: " + color);
        return this;
    }

    public CreateNewsPageSteps checkTitleFieldCharacterCounterWarningTextColor(String warningColor) {
        logger.info("The actual counter text color is: {}", createEditNewsPage.getTitleFieldCharacterCounterWarningTextColor());
        softAssert.assertTrue(createEditNewsPage.getTitleFieldCharacterCounterWarningTextColor().equals(warningColor),
                "The counter text warning color should be: " + warningColor);
        return this;
    }

    public CreateNewsPageSteps checkTitleInputTextFieldCharactersNumberValue(int charactersNumber) {
        logger.info("The actual text value of Title text field is: {}", createEditNewsPage.getTitleInputTextFieldValue().length());
        softAssert.assertTrue(createEditNewsPage.getTitleInputTextFieldValue().length() == charactersNumber,
                "The text should equal " + charactersNumber + " characters.");
        return this;
    }

    public CreateNewsPageSteps checkPublishButtonIsDisabled() {
        softAssert.assertFalse(createEditNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");
        return this;
    }

    public CreateNewsPageSteps checkPublishButtonIsEnabled() {
        softAssert.assertTrue(createEditNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be enabled when all required fields are filled out");
        return this;
    }

    public CreateNewsPageSteps fillTitleInputTextField(String titleText) {
        createEditNewsPage
                .clickTitleInputTextField()
                .clearTitleInputTextField()
                .getTitleInputTextField().sendKeys(titleText);
        return this;
    }

    public CreateNewsPageSteps enterTextIntoTextContentField(String text) {
        createEditNewsPage.enterTextIntoTextContentField(text);
        return this;
    }

    public CreateNewsPageSteps clickTagFilterButton(TagButton tagButton) {
        createEditNewsPage.clickTagFilterButton(tagButton);
        logger.info("The {} tag button has been clicked", tagButton);
        return this;
    }

    public CreateNewsPageSteps assertAll() {
        softAssert.assertAll();
        return this;
    }

}

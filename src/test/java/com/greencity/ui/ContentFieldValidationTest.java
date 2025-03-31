package com.greencity.ui;

import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import com.greencity.ui.data.Colors;

import org.testng.asserts.SoftAssert;


public class ContentFieldValidationTest  extends GreenCityLoginTest {

    protected SoftAssert softAssert = new SoftAssert();
    private String contentCharacterProvider(int numberOfCharacters) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < numberOfCharacters; i++) {
            stringBuilder.append("Content Eco.");
        }
        return stringBuilder.toString();
    }


    @Test
    public void checkContentFormNegative(){
        CreateEditNewsPage createEditNewsPage = homePage
                .goToCreateEcoNewsPage()
                .clickTextIntoTextContentField()
                .clickTitleHeaderText();

        createEditNewsPage.fillTitleInputTextField("Eco News");
        logger.info("The actual counter text color is: {}", createEditNewsPage.getTitleFieldCharacterCounterWarningTextColor());
        softAssert.assertTrue(createEditNewsPage.getTitleFieldCharacterCounterWarningTextColor().equals(Colors.ERROR_RED),
                "The counter text color should be red when the Title field exceeding the limit");
        logger.info("The actual text value of Title text field is: {}", createEditNewsPage.getTitleInputTextFieldValue().length());
        softAssert.assertTrue(createEditNewsPage.getTitleInputTextFieldValue().length() == 150,
                "The text should equal 170 characters.");

        createEditNewsPage.clickTagFilterButton(TagButton.NEWS);
        softAssert.assertFalse(createEditNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");

        softAssert.assertFalse(createEditNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");

        createEditNewsPage.enterTextIntoTextContentField(contentCharacterProvider(63207));
        softAssert.assertTrue(createEditNewsPage.getContentCharacterCountText().length() == 63206,
                  "Поле повинно містити не менше 20 та не більше 63 206 символів");
        softAssert.assertTrue(createEditNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");

        createEditNewsPage.enterTextIntoTextContentField(contentCharacterProvider(10));
        softAssert.assertTrue(createEditNewsPage.getContentCharacterCountText().length() == 10,
                "Поле повинно містити не менше 20 та не більше 63 206 символів");
        softAssert.assertFalse(createEditNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");
        softAssert.assertAll();

    }
    @Description("Verify the validation of the ' Main field title'  (mandatory, maximum 63206 characters) and that the " +
            "'Publish' button the button should not be available" +
            " until  Title, Main Text (Content) fields are filled and tag must be selected.")
    @Severity(SeverityLevel.MINOR)
    @Issue("14")
    @Link(name = "Link goto site", url = "http://localhost:4205/#/greenCity")

    @Test
    public void checkContentFormPositive(){
        CreateEditNewsPage createEditNewsPage = homePage
                .goToCreateEcoNewsPage()
                .clickTextIntoTextContentField()
                .clickTitleHeaderText();

        createEditNewsPage.fillTitleInputTextField("Eco News");
        logger.info("The actual counter text color is: {}", createEditNewsPage.getTitleFieldCharacterCounterWarningTextColor());
        softAssert.assertTrue(createEditNewsPage.getTitleFieldCharacterCounterWarningTextColor().equals(Colors.ERROR_RED),
                "The counter text color should be red when the Title field exceeding the limit");
        logger.info("The actual text value of Title text field is: {}", createEditNewsPage.getTitleInputTextFieldValue().length());
        softAssert.assertTrue(createEditNewsPage.getTitleInputTextFieldValue().length() == 150,
                "The text should equal 170 characters.");

        createEditNewsPage.clickTagFilterButton(TagButton.NEWS);
        softAssert.assertFalse(createEditNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");

        softAssert.assertFalse(createEditNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");

        createEditNewsPage.enterTextIntoTextContentField(contentCharacterProvider(25));
        softAssert.assertTrue(createEditNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");
        createEditNewsPage.clickPublishButton();
        softAssert.assertAll();
    }

}

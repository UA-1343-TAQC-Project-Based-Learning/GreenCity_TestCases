package com.greencity.ui;

import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.page.econewspage.CreateNewsPage;
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
        CreateNewsPage createNewsPage = homePage
                .goToCreateEcoNewsPage()
                .clickTextIntoTextContentField()
                .clickTitleHeaderText();

        createNewsPage.fillTitleInputTextField("Eco News");
        logger.info("The actual counter text color is: {}", createNewsPage.getTitleFieldCharacterCounterWarningTextColor());
        softAssert.assertTrue(createNewsPage.getTitleFieldCharacterCounterWarningTextColor().equals(Colors.CREATE_NEWS_TITLE_FIELD_COUNTER_TEXT_COLOR.warningColor()),
                "The counter text color should be red when the Title field exceeding the limit");
        logger.info("The actual text value of Title text field is: {}", createNewsPage.getTitleInputTextFieldValue().length());
        softAssert.assertTrue(createNewsPage.getTitleInputTextFieldValue().length() == 150,
                "The text should equal 170 characters.");

        createNewsPage.clickTagFilterButton(TagButton.NEWS);
        softAssert.assertFalse(createNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");

        softAssert.assertFalse(createNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");

        createNewsPage.enterTextIntoTextContentField(contentCharacterProvider(63207));
        softAssert.assertTrue(createNewsPage.getContentCharacterCountText().length() == 63206,
                  "Поле повинно містити не менше 20 та не більше 63 206 символів");
        softAssert.assertTrue(createNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");

        createNewsPage.enterTextIntoTextContentField(contentCharacterProvider(10));
        softAssert.assertTrue(createNewsPage.getContentCharacterCountText().length() == 10,
                "Поле повинно містити не менше 20 та не більше 63 206 символів");
        softAssert.assertFalse(createNewsPage.getPublishButton().isEnabled(),
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
        CreateNewsPage createNewsPage = homePage
                .goToCreateEcoNewsPage()
                .clickTextIntoTextContentField()
                .clickTitleHeaderText();

        createNewsPage.fillTitleInputTextField("Eco News");
        logger.info("The actual counter text color is: {}", createNewsPage.getTitleFieldCharacterCounterWarningTextColor());
        softAssert.assertTrue(createNewsPage.getTitleFieldCharacterCounterWarningTextColor().equals(Colors.CREATE_NEWS_TITLE_FIELD_COUNTER_TEXT_COLOR.warningColor()),
                "The counter text color should be red when the Title field exceeding the limit");
        logger.info("The actual text value of Title text field is: {}", createNewsPage.getTitleInputTextFieldValue().length());
        softAssert.assertTrue(createNewsPage.getTitleInputTextFieldValue().length() == 150,
                "The text should equal 170 characters.");

        createNewsPage.clickTagFilterButton(TagButton.NEWS);
        softAssert.assertFalse(createNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");

        softAssert.assertFalse(createNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");

        createNewsPage.enterTextIntoTextContentField(contentCharacterProvider(25));
        softAssert.assertTrue(createNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");
        createNewsPage.clickPublishButton();
        softAssert.assertAll();
    }

}

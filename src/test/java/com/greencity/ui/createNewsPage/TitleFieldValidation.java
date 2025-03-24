package com.greencity.ui.createNewsPage;

import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.data.Colors;
import com.greencity.ui.page.econewspage.CreateNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.AssertJUnit.assertEquals;

public class TitleFieldValidation extends CreateNewsPageSteps {

    private String titleCharacterProvider(int numberOfCharacters) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < numberOfCharacters; i++) {
            stringBuilder.append("A");
        }
        return stringBuilder.toString();
    }

    @Test
    public void checkPublishButton() {
        String greyColor = "rgb(156, 167, 176)";
        String redColor = "rgb(255, 0, 0)";

        CreateNewsPage createNewsPage = loadApplication()
                .goToCreateEcoNewsPage()
                .clickTitleInputTextField()
                .clickTitleHeaderText();
        logger.info("The actual border color is: " + createNewsPage.getTitleInputFieldBorderColor());
        softAssert.assertTrue(createNewsPage.getTitleInputFieldBorderColor().equals(redColor),
                "The border color should be red when the Title field is empty");
        softAssert.assertFalse(createNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");
        softAssert.assertTrue(createNewsPage.getTitleFieldCharacterCounterText().equals("0/170"),
                "The number of characters should equal 0 when field is empty");

        createNewsPage.fillTitleInputTextField(titleCharacterProvider(171));
        logger.info("The actual counter text color is: " + createNewsPage.getTitleFieldCharacterCounterWarningTextColor());
        softAssert.assertTrue(createNewsPage.getTitleFieldCharacterCounterWarningTextColor().equals("rgba(235, 24, 13, 1)"),
                "The counter text color should be red when the Title field exceeding the limit");
        logger.info("The actual text value of Title text field is: " + createNewsPage.getTitleInputTextFieldValue().length());
        softAssert.assertTrue(createNewsPage.getTitleInputTextFieldValue().length() == 170,
                "The text should equal 170 characters.");

        createNewsPage.fillTitleInputTextField("Test News");
        logger.info("The actual character counter value is: " + createNewsPage.getTitleFieldCharacterCounterText());
        softAssert.assertTrue(createNewsPage.getTitleFieldCharacterCounterText().equals("9/170"),
                "The counter should displays '9/170'");
        logger.info("The actual border color is: " + createNewsPage.getTitleInputFieldBorderColor());
        softAssert.assertTrue(createNewsPage.getTitleInputFieldBorderColor().equals(greyColor),
                "The border color should be grey and " + greyColor);

        createNewsPage.clickTagFilterButton(TagButton.NEWS);
        softAssert.assertFalse(createNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");

        createNewsPage.enterTextIntoTextContentField("Test Text Field Content");
        softAssert.assertTrue(createNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be enabled when all required fields are filled out");
        softAssert.assertAll();
    }


    @Test
    public void checkPublishButton2() {
        goToCreateEcoNewsPage()
                .checkTitleInputFieldBorderColor(Colors.getRED_COLOR_BORDER())
                .checkPublishButtonIsDisabled()
                .checkTitleFieldCharacterCounterText("0/170")
                .fillTitleInputTextField(titleCharacterProvider(171))
                .checkTitleFieldCharacterCounterWarningTextColor(Colors.getRED_COLOR_COUNTER())
                .checkTitleInputTextFieldCharactersNumberValue(170)
                .fillTitleInputTextField("Test News")
                .checkTitleFieldCharacterCounterText("9/170")
                .checkTitleInputFieldBorderColor(Colors.getGREY_COLOR_BORDER())
                .clickTagFilterButton(TagButton.NEWS)
                .checkPublishButtonIsDisabled()
                .enterTextIntoTextContentField("Test Text Field Content")
                .checkPublishButtonIsEnabled()
                .assertAll();
    }

}

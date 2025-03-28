package com.greencity.ui.createnewspage;

import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.data.Colors;
import com.greencity.ui.page.econewspage.CreateNewsPage;
import io.qameta.allure.*;
import org.testng.annotations.Test;

public class TitleFieldValidation extends CreateNewsPageSteps {

    private String titleCharacterProvider(int numberOfCharacters) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < numberOfCharacters; i++) {
            stringBuilder.append("A");
        }
        return stringBuilder.toString();
    }

    @Description("Verify the validation of the 'Title' field (mandatory, maximum 170 characters) and that the " +
            "'Publish' button remains disabled until both Title and Main Text (Content) fields are filled.")
    @Severity(SeverityLevel.MINOR)
    @Issue("14")
    @Link(name = "Link goto site", url = "http://localhost:4205/#/greenCity")
    @Test
    public void checkPublishButton() {
        CreateNewsPage createNewsPage = loadApplication()
                .goToCreateEcoNewsPage()
                .clickTitleInputTextField()
                .clickTitleHeaderText();
        logger.info("The actual border color is: {}", createNewsPage.getTitleInputFieldBorderColor());
        softAssert.assertTrue(createNewsPage.getTitleInputFieldBorderColor().equals(Colors.RED),
                "The border color should be red when the Title field is empty");
        softAssert.assertFalse(createNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");
        softAssert.assertTrue(createNewsPage.getTitleFieldCharacterCounterText().equals("0/170"),
                "The number of characters should equal 0 when field is empty");

        createNewsPage.fillTitleInputTextField(titleCharacterProvider(171));
        logger.info("The actual counter text color is: {}", createNewsPage.getTitleFieldCharacterCounterWarningTextColor());
        softAssert.assertTrue(createNewsPage.getTitleFieldCharacterCounterWarningTextColor().equals(Colors.ERROR_RED),
                "The counter text color should be red when the Title field exceeding the limit");
        logger.info("The actual text value of Title text field is: {}", createNewsPage.getTitleInputTextFieldValue().length());
        softAssert.assertTrue(createNewsPage.getTitleInputTextFieldValue().length() == 170,
                "The text should equal 170 characters.");

        createNewsPage.fillTitleInputTextField("Test News");
        logger.info("The actual character counter value is: {}", createNewsPage.getTitleFieldCharacterCounterText());
        softAssert.assertTrue(createNewsPage.getTitleFieldCharacterCounterText().equals("9/170"),
                "The counter should displays '9/170'");
        logger.info("The actual border color is: {}", createNewsPage.getTitleInputFieldBorderColor());
        softAssert.assertTrue(createNewsPage.getTitleInputFieldBorderColor().equals(Colors.QUINTYNARY_LIGHT_GREY),
                "The border color should be grey and " + Colors.QUINTYNARY_LIGHT_GREY);

        createNewsPage.clickTagFilterButton(TagButton.NEWS);
        softAssert.assertFalse(createNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");

        createNewsPage.enterTextIntoTextContentField("Test Text Field Content");
        softAssert.assertTrue(createNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be enabled when all required fields are filled out");
        softAssert.assertAll();
    }


    @Description("Verify the validation of the 'Title' field (mandatory, maximum 170 characters) and that the " +
            "'Publish' button remains disabled until both Title and Main Text (Content) fields are filled.")
    @Severity(SeverityLevel.MINOR)
    @Issue("14")
    @Link(name = "Link goto site", url = "http://localhost:4205/#/greenCity")
    @Test
    public void checkPublishButton2() {
        goToCreateEcoNewsPage()
                .checkTitleInputFieldBorderColor(Colors.RED.getColor())
                .checkPublishButtonIsDisabled()
                .checkTitleFieldCharacterCounterText("0/170")
                .fillTitleInputTextField(titleCharacterProvider(171))
                .checkTitleFieldCharacterCounterWarningTextColor(Colors.ERROR_RED.getColor())
                .checkTitleInputTextFieldCharactersNumberValue(170)
                .fillTitleInputTextField("Test News")
                .checkTitleFieldCharacterCounterText("9/170")
                .checkTitleInputFieldBorderColor(Colors.RED.getColor())
                .clickTagFilterButton(TagButton.NEWS)
                .checkPublishButtonIsDisabled()
                .enterTextIntoTextContentField("Test Text Field Content")
                .checkPublishButtonIsEnabled()
                .assertAll();
    }

}

package com.greencity.ui;

import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.page.econewspage.CreateNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class TitleFieldValidation extends BaseTestRunner {

    @Test(description = "Test the border style of the Title empty field")
    public void checkBorderStyleOfEmptyTitleField() {
        String greyColor = "rgb(135, 135, 135)";

        String borderColor = loadAppliacation()
                .goToCreateEcoNewsPage(testValueProvider.getUserEmail(), testValueProvider.getUserPassword())
                .getTitleInputFieldBorderColor();

        Assert.assertEquals(borderColor, greyColor,
                "The border color should be grey when the Title field is empty");
    }


    @Test(description = "Test the border style of the Title field when exceeding the limit.")
    public void checkBorderStyleOfTheTitleFieldWhenExceedingTheLimit() {
        String redColor = "rgb(240, 49, 39)";

        CreateNewsPage createNewsPage = loadAppliacation()
                .goToCreateEcoNewsPage(testValueProvider.getUserEmail(), testValueProvider.getUserPassword())
                .fillTitleInputFieldWithCustomNumberOfCharacters(171);

        Assert.assertEquals(createNewsPage.getTitleInputFieldBorderColor(), redColor,
                "The border color should be red when the Title field exceeding the limit");

    }

    @Test(description = "Test the character counter color and value")
    public void checkCharacterCounterValue() {
        CreateNewsPage createNewsPage = loadAppliacation()
                .goToCreateEcoNewsPage(testValueProvider.getUserEmail(), testValueProvider.getUserPassword())
                .fillTitleInputTextField("Test News");

        Assert.assertEquals(createNewsPage.getTitleFieldCharacterCounterText(), "9/170",
                "The counter should displays '9/170'");
        Assert.assertEquals(createNewsPage.getTitleFieldCharacterCounterTextColor(), "rgb(135, 135, 135)",
                "The counter text color should be grey");
    }


    @Test(description = "Test that Publish button is enabled only after valid input is provided")
    public void checkPublishButtonIsEnable() {
        CreateNewsPage createNewsPage = loadAppliacation()
                .goToCreateEcoNewsPage(testValueProvider.getUserEmail(), testValueProvider.getUserPassword())
                .fillTitleInputTextField("Test News")
                .clickTagFilterButton(TagButton.NEWS)
                .enterTextIntoTextContentField("Main Text");

        Assert.assertTrue(createNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be enabled when all required fields are filled out");



    }









}

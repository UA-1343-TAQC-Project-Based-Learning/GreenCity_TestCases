package com.greencity.ui;

import com.greencity.ui.data.Colors;
import com.greencity.ui.page.econewspage.CreateNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import io.restassured.internal.common.assertion.Assertion;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.UUID;

public class SourceFieldValidationTest extends BaseTestRunner {

    @Test(description = "Verify the validation of the 'Source' field")
    public void checkSourceFieldValidation() {
        String titleForFirstTest = "TestTitle-" + UUID.randomUUID();
        SoftAssert assertion = new SoftAssert();

        EcoNewsPage ecoNewsPageWithoutSourceField = homePage.gotoEcoNewsPage().
                clickCreateNewsButton()
                .fillTitleInputTextField(titleForFirstTest)
                .fillContentInput("Creating Card without filling the source field")
                .fillSourceInput("")
                .clickNewsTagButton()
                .clickPublishButton();

        assertion.assertEquals(ecoNewsPageWithoutSourceField.getCreateCardMessageText(),ecoNewsPageWithoutSourceField.SUCCESSFUL_CREATE_CARD_MESSAGE,"'Your news has been successfully published' message should be present");
        assertion.assertTrue(ecoNewsPageWithoutSourceField.isExistCardComponentByPartialTitle(titleForFirstTest),"A card should be created without any error");
        assertion.assertAll();

        String titleForSecondTest = "TestTitle-" + UUID.randomUUID();

        CreateNewsPage createNewsPage = homePage.gotoEcoNewsPage().
                clickCreateNewsButton()
                .fillTitleInputTextField(titleForSecondTest)
                .fillContentInput("Creating Card with invalid URL in the source field")
                .fillSourceInput("example.com");

        assertion.assertTrue(createNewsPage.getExternalSourceInputFieldInfoText().contains(createNewsPage.SOURCE_Field_INFO_MESSAGE), "Info message should be present");
        assertion.assertEquals(createNewsPage.getExternalSourceInputFieldInfoTextColor(), Colors.ERROR_RED.getColor(), "A color of info message should be red");
        assertion.assertTrue(createNewsPage.getExternalSourceInputFieldBorderColor().contains(Colors.RED.getColor()), "A border of the 'Source' text field should be red");
        assertion.assertFalse(createNewsPage.isPublishButtonEnabled(), "Publish button should be disabled");
        assertion.assertAll();

        createNewsPage.fillSourceInput("https://example.com");

        assertion.assertTrue(createNewsPage.getExternalSourceInputFieldInfoText().contains(createNewsPage.SOURCE_Field_INFO_MESSAGE),"Info message should be present");
        assertion.assertEquals(createNewsPage.getExternalSourceInputFieldInfoTextColor(), Colors.SECONDARY_GREY.getColor(),"A color of info message should be grey");
        assertion.assertTrue(createNewsPage.getExternalSourceInputFieldBorderColor().contains(Colors.QUINTYNARY_LIGHT_GREY.getColor()),"A border of the 'Source' text field should be grey");
        assertion.assertTrue(createNewsPage.isPublishButtonEnabled(),"Publish button should be enabled");
        assertion.assertAll();

        EcoNewsPage ecoNewsPageWithSourceField = createNewsPage.clickPublishButton();

        assertion.assertEquals(ecoNewsPageWithSourceField.getCreateCardMessageText(),ecoNewsPageWithSourceField.SUCCESSFUL_CREATE_CARD_MESSAGE,"'Your news has been successfully published' message should be present");
        assertion.assertTrue(ecoNewsPageWithSourceField.isExistCardComponentByPartialTitle(titleForSecondTest), "A card should be created without any error");
        assertion.assertAll();
    }
}


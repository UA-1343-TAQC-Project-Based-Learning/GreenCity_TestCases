package com.greencity.ui;

import com.greencity.ui.data.Colors;
import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.page.econewspage.NewsCardPage;
import com.greencity.ui.testrunners.BaseTestRunner;
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

        assertion.assertEquals(ecoNewsPageWithoutSourceField.getCreateCardMessageText(),"Your news has been successfully published","'Your news has been successfully published' message should be present");
        assertion.assertTrue(ecoNewsPageWithoutSourceField.isExistCardComponentByPartialTitle(titleForFirstTest),"A card should be created without any error");
        assertion.assertAll();

                NewsCardPage newsCardPage = ecoNewsPageWithoutSourceField.goToNewsCardPage(titleForFirstTest);
        newsCardPage.getTitleLabelText();


        String titleForSecondTest = "TestTitle-" + UUID.randomUUID();

        CreateEditNewsPage createEditNewsPage = homePage.gotoEcoNewsPage().
                clickCreateNewsButton()
                .fillTitleInputTextField(titleForSecondTest)
                .fillContentInput("Creating Card with invalid URL in the source field")
                .fillSourceInput("example.com");

        assertion.assertTrue(createEditNewsPage.getExternalSourceInputFieldInfoText().contains("Please add the link of original article/news/post. Link must start with http(s)://"), "Info message should be present");
        assertion.assertEquals(createEditNewsPage.getExternalSourceInputFieldInfoTextColor(), Colors.ERROR_RED.getColor(), "A color of info message should be red");
        assertion.assertTrue(createEditNewsPage.getExternalSourceInputFieldBorderColor().contains(Colors.RED.getColor()), "A border of the 'Source' text field should be red");
        assertion.assertFalse(createEditNewsPage.isPublishButtonEnabled(), "Publish button should be disabled");
        assertion.assertAll();

        createEditNewsPage.fillSourceInput("https://example.com");

        assertion.assertTrue(createEditNewsPage.getExternalSourceInputFieldInfoText().contains("Please add the link of original article/news/post. Link must start with http(s)://"),"Info message should be present");
        assertion.assertEquals(createEditNewsPage.getExternalSourceInputFieldInfoTextColor(), Colors.SECONDARY_GREY.getColor(),"A color of info message should be grey");
        assertion.assertTrue(createEditNewsPage.getExternalSourceInputFieldBorderColor().contains(Colors.QUINTYNARY_LIGHT_GREY.getColor()),"A border of the 'Source' text field should be grey");
        assertion.assertTrue(createEditNewsPage.isPublishButtonEnabled(),"Publish button should be enabled");
        assertion.assertAll();

        EcoNewsPage ecoNewsPageWithSourceField = createEditNewsPage.clickPublishButton();

        assertion.assertEquals(ecoNewsPageWithSourceField.getCreateCardMessageText(),"Your news has been successfully published","'Your news has been successfully published' message should be present");
        assertion.assertTrue(ecoNewsPageWithSourceField.isExistCardComponentByPartialTitle(titleForSecondTest), "A card should be created without any error");
        assertion.assertAll();
    }
}


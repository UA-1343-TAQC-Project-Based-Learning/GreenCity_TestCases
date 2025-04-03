package com.greencity.ui;

import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.data.Colors;
import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.page.econewspage.NewsCardPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.UUID;

public class EditingEmptyTitleFieldTest extends BaseTestRunner {

    @Test
    public void verifyNewsCannotBeSubmittedWithoutTitle() {
        String newsTitle = "TestNews_" + UUID.randomUUID();
        String newsContent = "Content_" + UUID.randomUUID();

        EcoNewsPage ecoNewsPage = homePage
                .gotoEcoNewsPage()
                .clickCreateNewsButton()
                .clickTitleInputTextField()
                .fillTitleInputTextField(newsTitle)
                .clickTagFilterButton(TagButton.NEWS)
                .enterTextIntoTextContentField(newsContent)
                .clickPublishButton();

        NewsCardPage newsCardPage = ecoNewsPage.goToNewsCardPage(newsTitle);

        CreateEditNewsPage createEditNewsPage = newsCardPage.clickEditButton();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(createEditNewsPage.getTitleHeaderText(), "Edit news", "Header text mismatch on news editing page.");

        createEditNewsPage.clearTitleInputTextField();
        createEditNewsPage.clickTitleHeaderText();

        boolean isEditButtonEnabled = createEditNewsPage.isEditButtonEnabled();
        softAssert.assertEquals(createEditNewsPage.getEditButtonText(), "Edit", "The 'Edit' button text does not match the expected value.");
        softAssert.assertFalse(isEditButtonEnabled,
                "Edit button should be disabled when title is empty");

        createEditNewsPage.clickEditButton();

        String actualTitleFieldBorderColor = createEditNewsPage.getTitleInputFieldBorderColor();
        softAssert.assertEquals(actualTitleFieldBorderColor, Colors.ERROR_RED.getColor(),
                "Title field should be highlighted with error color when empty");


        softAssert.assertAll();
    }
}
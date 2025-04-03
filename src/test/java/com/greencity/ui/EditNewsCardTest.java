package com.greencity.ui;

import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.page.econewspage.NewsCardPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.UUID;

public class EditNewsCardTest extends BaseTestRunner {

    @Test
    public void verifyEditButtonVisibleToAuthor() {
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


        NewsCardPage newsCardPage = ecoNewsPage
                .goToNewsCardPage(newsTitle);

        String actualUserName = homePage.getLoggedHeader().getUserNameText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(newsCardPage.getTitleLabelText(), newsTitle,
                "Published news title doesn't match the created one");
        softAssert.assertEquals(newsCardPage.getUsernameLabelText(), "by " + actualUserName,
                "Published news author name doesn't match the created one");

        softAssert.assertEquals(newsCardPage.getEditButtonText(), "Edit news", "Edit button is not visible on the Edit News page.");

        CreateEditNewsPage createEditNewsPage = newsCardPage.clickEditButton();
        softAssert.assertEquals(createEditNewsPage.getTitleHeaderText(), "Edit news", "Incorrect header on the Edit news page.");

        softAssert.assertAll();


    }
}

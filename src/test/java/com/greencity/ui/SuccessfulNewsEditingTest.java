package com.greencity.ui;

import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.page.econewspage.NewsCardPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

public class SuccessfulNewsEditingTest extends BaseTestRunner {

    @Test(description = "Successful news editing by the author")
    public void SuccessfulNewsEditing() {
        SoftAssert assertion = new SoftAssert();

        EcoNewsPage ecoNewsPage = homePage.gotoEcoNewsPage();
        String title = ecoNewsPage.getFirstCardTitle();
        LocalDate dateOfCreation = ecoNewsPage.getDataLabelFormating(Locale.ENGLISH);

        String changedTitle = "TestTitle-" + UUID.randomUUID();

        NewsCardPage newsCardPage = ecoNewsPage
                .goToNewsCardPage(title)
                .clickEditButton()
                .fillTitleInputTextField(changedTitle)
                .fillContentInput("updating a content input")
                .clickTagFilterButton(TagButton.EVENTS)
                .clickPublishButton()
                .goToNewsCardPage(changedTitle);

        assertion.assertEquals(newsCardPage.getTitleLabelText(), changedTitle, "Title should be updated");
        assertion.assertEquals(newsCardPage.getContentLabelText(), "updating a content input", "Content should be updated");
        assertion.assertTrue(newsCardPage.getFiltersTagText().contains("News"), "News filter should be present");
        assertion.assertTrue(newsCardPage.getFiltersTagText().contains("Events"), "Events filter should be present");
        assertion.assertEquals(newsCardPage.getDataLabelFormating(Locale.ENGLISH), dateOfCreation, "Date should be the same, as created card");
        assertion.assertAll();
    }
}

package com.greencity.ui;

import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.page.econewspage.NewsCardPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

public class SuccessfulNewsEditingTest extends BaseTestRunner {

    @Description("Successful news editing by the author")
    @Epic("Edit News")
    @Feature("Successful editing an Eco News")
    @Issue("99")
    @Test()
    public void successfulNewsEditing() {
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
                .clickOnlyUnselectedTagFilterButton(TagButton.EVENTS)
                .clickOnlyUnselectedTagFilterButton(TagButton.NEWS)
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

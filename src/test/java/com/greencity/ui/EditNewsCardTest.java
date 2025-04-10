package com.greencity.ui;

import com.greencity.ui.component.cards.NewsCardTableViewComponent;
import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.container.NewsCardsContainer;
import com.greencity.ui.data.Colors;
import com.greencity.ui.modal.CancelModal;
import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.page.econewspage.NewsCardPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.UUID;

public class EditNewsCardTest extends BaseTestRunner {
    private static final String EDIT_NEWS_HEADER = "Edit news";
    private static final String EDIT_BUTTON_TEXT = "Edit";
    private static final String EDIT_NEWS_BUTTON_TEXT = "Edit news";
    private static final String BY_AUTHOR_PREFIX = "by ";

    private String newsTitle;
    private String newsContent;
    private String newsTitleAfterEditing;
    private NewsCardPage newsCardPage;
    private EcoNewsPage ecoNewsPage;

    @BeforeMethod
    public void createTestNews() {
        newsTitle = "TestNews_" + UUID.randomUUID();
        newsContent = "Content_" + UUID.randomUUID();

        ecoNewsPage = homePage
                .gotoEcoNewsPage()
                .clickCreateNewsButton()
                .clickTitleInputTextField()
                .fillTitleInputTextField(newsTitle)
                .clickTagFilterButton(TagButton.NEWS)
                .enterTextIntoTextContentField(newsContent)
                .clickPublishButton();

        newsCardPage = ecoNewsPage.goToNewsCardPage(newsTitle);
    }

    @Test
    public void verifyNewsCannotBeSubmittedWithoutTitle() {
        CreateEditNewsPage createEditNewsPage = newsCardPage.clickEditButton();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(createEditNewsPage.getTitleHeaderText(), EDIT_NEWS_HEADER,
                "Header text mismatch on news editing page.");

        createEditNewsPage.clearTitleInputTextField()
                .clickTitleHeaderText();

        boolean isEditButtonEnabled = createEditNewsPage.isEditButtonEnabled();
        softAssert.assertEquals(createEditNewsPage.getEditButtonText(), EDIT_BUTTON_TEXT,
                "The 'Edit' button text does not match the expected value.");
        softAssert.assertFalse(isEditButtonEnabled,
                "Edit button should be disabled when title is empty");

        softAssert.assertEquals(createEditNewsPage.getTitleInputFieldBorderColor(), Colors.RED.getColor(),
                "The border color should be red when the Title field is empty");

        EcoNewsPage ecoNewsPageAfterCancel = createEditNewsPage.clickExitButton().clickYesCancelButton();

        softAssert.assertTrue(ecoNewsPageAfterCancel.isExistCardComponentByPartialTitle(newsTitle), "News post should remain unchanged title after canceling edit");

        softAssert.assertAll();

    }

    @Test
    public void verifyEditButtonVisibleToAuthor() {
        String actualUserName = homePage.getLoggedHeader().getUserNameText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(newsCardPage.getTitleLabelText(), newsTitle,
                "Published news title doesn't match the created one");
        softAssert.assertEquals(newsCardPage.getUsernameLabelText(), BY_AUTHOR_PREFIX + actualUserName,
                "Published news author name doesn't match the created one");

        softAssert.assertEquals(newsCardPage.getEditButtonText(), EDIT_NEWS_BUTTON_TEXT,
                "Edit button is not visible on the Edit News page.");

        CreateEditNewsPage createEditNewsPage = newsCardPage.clickEditButton();
        softAssert.assertEquals(createEditNewsPage.getTitleHeaderText(), EDIT_NEWS_HEADER,
                "Incorrect header on the Edit news page.");
        createEditNewsPage.clickEditButton();

        softAssert.assertAll();
    }


    @Test
    public void verifyCancelEditing() {
        newsTitleAfterEditing = "TestNews after editing" + UUID.randomUUID();

        CreateEditNewsPage createEditNewsPage = newsCardPage
                .clickEditButton();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(createEditNewsPage.getTitleInputTextFieldValue(), newsTitle, "Initial title in edit form should match original news title");

        createEditNewsPage.clearTitleInputTextField()
                .clickTitleHeaderText()
                .fillTitleInputTextField(newsTitleAfterEditing);

        softAssert.assertEquals(createEditNewsPage.getTitleInputTextFieldValue(), newsTitleAfterEditing, "Input field should display the new edited title");

        EcoNewsPage ecoNewsPageAfterCancel = createEditNewsPage
                .clickExitButton()
                .clickYesCancelButton();

        softAssert.assertEquals(ecoNewsPage.getHeaderText(), "Eco news", "After canceling edit, user should be redirected to EcoNews page");

        softAssert.assertTrue(ecoNewsPageAfterCancel.isExistCardComponentByPartialTitle(newsTitle), "News card should display original title after canceling edit");

        softAssert.assertAll();
    }


    @AfterMethod
    public void deleteTestNews() {
        ecoNewsPage = homePage
                .gotoEcoNewsPage()
                .goToNewsCardPage(newsTitle)
                .clickDeleteButton()
                .clickYesButton();
        ecoNewsPage.refreshPage();
    }
}

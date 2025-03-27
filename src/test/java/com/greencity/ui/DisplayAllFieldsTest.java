package com.greencity.ui;

import com.greencity.ui.page.econewspage.CreateNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DisplayAllFieldsTest extends BaseTestRunner {

    @Test(description = "Verify that the 'Create News' form displays all the necessary fields in the correct order.")

    public void checkOfDisplayingAllFieldsTest() {
//        CreateNewsPage newsPage = homePage.clickHeaderButton()
//                .successfulLogin(testValueProvider.getUserEmail(), testValueProvider.getUserPassword())
//                .gotoEcoNewsPage()
//                .clickCreateNewsButton();


        CreateNewsPage newsPage = homePage.gotoEcoNewsPage().clickCreateNewsButton();

        SoftAssert assertion = new SoftAssert();

        assertion.assertEquals(newsPage.getTitleText(), "Title","Title should be present in the 'Create News' page");
        assertion.assertTrue(newsPage.isPresentTitleInputTextField(),"Title input text field should be present in the 'Create News' page");
        assertion.assertTrue(newsPage.getTitleFieldCharacterCounterText().contains("0/170"), "Character count should be present in the 'Create News' page");
        assertion.assertTrue(newsPage.getOnlyThreeTagsCanBeAddedText().contains("Only 3 tags can be added"));
        assertion.assertEquals(newsPage.getListOfAllTagButtonsText(),newsPage.tagFilters,"All tags should be present in the 'Create News' page");
        assertion.assertTrue(newsPage.isAllSelectedTagsChangeAppearance(),"All tags should change an appearance after selecting");
        assertion.assertEquals(newsPage.getImageBrowseLinkText(),"browse","link for uploading an image should be present in the 'Create News' page");
        assertion.assertEquals(newsPage.getContentText(),"Content");
        assertion.assertTrue(newsPage.isPresentContentInputTextField(),"Content input text field should be present in the 'Create News' page");
        assertion.assertTrue(newsPage.getContentCharacterCountText().contains("Must be minimum 20 and maximum 63 206 symbols"));
        assertion.assertEquals(newsPage.getAuthorLabelText(),testValueProvider.getUserName().toLowerCase(), "userName should be present in the 'Create News' page" );
        assertion.assertTrue(newsPage.isAuthorLabelNotEditable(), "Username label should not be editable");
        assertion.assertEquals(newsPage.getDataLabelFormating(Locale.ENGLISH), LocalDate.now(), "current date should be present");
        assertion.assertTrue(newsPage.isDataLabelNotEditable(),"Date label should not be editable");
        assertion.assertEquals(newsPage.getExternalSourceInputFieldTitle(),"Source (optional)", "Source (optional) should be present in the 'Create News' page");
        assertion.assertTrue(newsPage.getExternalSourceInputFieldInfoText().contains("Please add the link of original article/news/post. Link must start with http(s)://"),
                "Source info text should be present in the 'Create News' page");
        assertion.assertEquals(newsPage.getExternalSourceLinkInputFieldPlaceholderText(),"Link to external source",
                "Placeholder Source Field should be equals 'Link to external source'");
        assertion.assertTrue(newsPage.isCancelButtonPresent(),"Cancel button should be present in the 'Create News' page");
        assertion.assertTrue(newsPage.isPreviewButtonPresent(),"Preview button should be present in the 'Create News' page");
        assertion.assertTrue(newsPage.isPublishButtonPresent(),"Publish button should be present in the 'Create News' page");

        assertion.assertAll();
    }
    @Test(description = "Check for the order of elements")
    public void checkForOrderOfElementsTest(){
//        CreateNewsPage newsPage = homePage.clickHeaderButton()
//                .successfulLogin(testValueProvider.getUserEmail(), testValueProvider.getUserPassword())
//                .gotoEcoNewsPage()
//                .clickCreateNewsButton();


        CreateNewsPage newsPage = homePage.gotoEcoNewsPage().clickCreateNewsButton();

        SoftAssert assertion = new SoftAssert();
        assertion.assertTrue(newsPage.isElementsOrderCorrect(newsPage.getTitleInputTextField(),newsPage.getFilterTagsRoot()),"Incorrect order between titleInputTextField and filterTagsRoot");
        assertion.assertTrue(newsPage.isElementsOrderCorrect(newsPage.getFilterTagsRoot(),newsPage.getExternalSourceLinkInputField()), "Incorrect order between filterTagsRoot and externalSourceLinkInputField");
        assertion.assertTrue(newsPage.isElementsOrderCorrect(newsPage.getImageBlockRoot(),newsPage.getExternalSourceLinkInputField()),"Incorrect order between imageBlockRoot and externalSourceLinkInputField");
        assertion.assertTrue(newsPage.isElementsOrderCorrect(newsPage.getExternalSourceLinkInputField(),newsPage.getTextAreaRoot()),"Incorrect order between externalSourceLinkInputField and textAreaRoot");
        assertion.assertTrue(newsPage.isElementsOrderCorrect(newsPage.getTextAreaRoot(),newsPage.getDataLabel()),"Incorrect order between dtaLabel and textAreaRoot");
        assertion.assertTrue(newsPage.areElementsOnSameLine(newsPage.getDataLabel(), newsPage.getAuthorLabel()),"Incorrect order between dtaLabel and authorLabel");
        assertion.assertTrue(newsPage.isElementsOrderCorrect(newsPage.getAuthorLabel(),newsPage.getExitButton()),"Incorrect order between exitButton and authorLabel");
        assertion.assertTrue(newsPage.areElementsOnSameLine(newsPage.getExitButton(), newsPage.getPreviewButton()),"Incorrect order between exitButton and previewButton");
        assertion.assertTrue(newsPage.areElementsOnSameLine(newsPage.getPublishButton(), newsPage.getPreviewButton()),"Incorrect order between publishButton and previewButton");

        assertion.assertAll();
    }

}


package com.greencity.ui;

import com.greencity.ui.modal.CancelModal;
import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CancelButtonBehaviorTest extends BaseTestRunner {
    @Test(description = "Cancel Button Behavior")
    public void cancelButtonBehavior() {
        SoftAssert assertion = new SoftAssert();

        CancelModal cancelModal = homePage
                .gotoEcoNewsPage()
                .clickCreateNewsButton()
                .fillTitleInputTextField("Title")
                .fillContentInput("Test content with 20 chars")
                .clickExitButton();


        assertion.assertTrue(cancelModal.getWarningText().contains("All created content will be lost."));
        assertion.assertTrue(cancelModal.getWarningText().contains("Do you still want to cancel news creating?"));

        EcoNewsPage ecoNewsPage = cancelModal.clickYesCancelButton();
        assertion.assertEquals(ecoNewsPage.getHeaderText(), "Eco news");

        CreateEditNewsPage createEditNewsPage = ecoNewsPage
                .clickCreateNewsButton()
                .fillTitleInputTextField("Title")
                .fillContentInput("Test content with 20 chars")
                .clickExitButton()
                .clickContinueEditingButton();

        assertion.assertEquals(createEditNewsPage.getTitleHeaderText(),"Create news");
        assertion.assertEquals(createEditNewsPage.getTitleInputTextFieldValue(),"Title");
        assertion.assertEquals(createEditNewsPage.getContentInputTextFieldText(),"Test content with 20 chars");
    }
}

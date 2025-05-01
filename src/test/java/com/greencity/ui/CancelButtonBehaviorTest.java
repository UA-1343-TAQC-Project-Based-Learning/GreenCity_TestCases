package com.greencity.ui;

import com.greencity.ui.modal.CancelModal;
import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CancelButtonBehaviorTest extends BaseTestRunner {

    @Description("Cancel Button Behavior")
    @Epic("Create News")
    @Feature("Cancel Button Behavior")
    @Issue("19")
    @Owner("Maria Markovych")
    @Test()
    public void cancelButtonBehavior() {
        SoftAssert assertion = new SoftAssert();

        CancelModal cancelModal = homePage
                .gotoEcoNewsPage()
                .clickCreateNewsButton()
                .fillTitleInputTextField("Title")
                .fillContentInput("Test content with 20 chars")
                .clickExitButton();


        assertion.assertTrue(cancelModal.getWarningText().contains("All created content will be lost."),"Warning text should be present");
        assertion.assertTrue(cancelModal.getWarningText().contains("Do you still want to cancel news creating?"),"Warning text should be present");

        EcoNewsPage ecoNewsPage = cancelModal.clickYesCancelButton();
        assertion.assertEquals(ecoNewsPage.getHeaderText(), "Eco news","EcoNews page should be opened");

        CreateEditNewsPage createEditNewsPage = ecoNewsPage
                .clickCreateNewsButton()
                .fillTitleInputTextField("Title")
                .fillContentInput("Test content with 20 chars")
                .clickExitButton()
                .clickContinueEditingButton();



        assertion.assertEquals(createEditNewsPage.getTitleHeaderText(),"Create news","CreateNewsPage should be opened");
        assertion.assertEquals(createEditNewsPage.getTitleInputTextFieldValue(),"Title", "Title must contain the 'Title' text");
        assertion.assertEquals(createEditNewsPage.getContentText(),"Test content with 20 chars", "Content must contain the 'Test content with 20 chars' text");

        assertion.assertAll();
    }
}

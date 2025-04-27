package com.greencity.ui;

import com.greencity.ui.component.ImageUploadComponent;
import com.greencity.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class ValidationImageSizeTest extends BaseTestRunner {

    @Description("Verify that an image larger than 10MB is not accepted.")
    @Epic("Edit News")
    @Feature("Add a picture for creating a new Eco News")
    @Issue("103")
    @Test()
    public void ValidationImageSizeTest() {
        SoftAssert assertion = new SoftAssert();

        ImageUploadComponent imageUploadComponent = homePage
                .gotoEcoNewsPage()
                .clickCreateNewsButton()
                .switchToImageUploadComponent()
                .uploadImage(testValueProvider.getFilePathByResources("images/GreenCity11mb.png"));

        assertion.assertEquals(imageUploadComponent.getUploadFieldWarningText(),"The image isn't uploaded","'The image isn`t uploaded' warning should appears");
        assertion.assertEquals(imageUploadComponent.getImageFormatWarningMessageText(),"Upload only PNG or JPG. File size must be less than 10MB","'Upload only PNG or JPG. File size must be less than 10MB' warning should appears");

        assertion.assertAll();
    }
}

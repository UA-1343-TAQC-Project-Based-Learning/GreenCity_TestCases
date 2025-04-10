package com.greencity.ui;

import com.greencity.ui.component.ImageUploadComponent;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class ValidationImageSizeTest extends BaseTestRunner {

    @Test(description = "Verify that an image larger than 10MB is not accepted.")
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

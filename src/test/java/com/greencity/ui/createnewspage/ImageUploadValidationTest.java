package com.greencity.ui.createnewspage;

import com.greencity.ui.component.ImageUploadComponent;
import com.greencity.ui.data.Colors;
import com.greencity.ui.testrunners.BaseTestRunner;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ImageUploadValidationTest extends BaseTestRunner {
    private SoftAssert softAssert = new SoftAssert();

    @Test
    @Owner("Serhii Hembei")
    @Description("Verify the validation of the 'Upload Image' field (accepted formats: PNG/JPG, maximum size: 10MB).")
    @Severity(SeverityLevel.MINOR)
    @Epic("Create news")
    @Feature("EcoNews page image upload")
    @Issue("16")
    @Link(name = "Website", url = "http://localhost:4205/#/greenCity")
    public void checkUploadImage() {
        ImageUploadComponent imageUploadComponent = homePage
                .gotoEcoNewsPage()
                .clickCreateNewsButton()
                .switchToImageUploadComponent()
                .uploadImage(testValueProvider.getFilePath("images/GreenCity5mb.png"));

        softAssert.assertTrue(imageUploadComponent.getPresentationImageWindow().isDisplayed(),
                "The presentation window should be displayed on the uploaded image");

        imageUploadComponent
                .clickCancelButton()
                .uploadImage(testValueProvider.getFilePath("images/GreenCity1mb.gif"));
        softAssert.assertTrue(imageUploadComponent.getImageDropzoneFieldColor().equals(Colors.IMAGE_DROPZONE_WARNING_BACKGROUND.getColor()),
                "The background color of the dropzone field should be: " + Colors.IMAGE_DROPZONE_WARNING_BACKGROUND.getColor());
        softAssert.assertTrue(imageUploadComponent.getImageFormatWarningMessage().isEnabled(),
                "The image upload warning message should be displayed.");
        softAssert.assertTrue(imageUploadComponent.getUploadFieldWarningText().equals("The image isn't uploaded"),
                "The image isn't uploaded' message should be displayed");


        imageUploadComponent.uploadImage(testValueProvider.getFilePath("images/GreenCity15mb.jpg"));
        softAssert.assertTrue(imageUploadComponent.getImageDropzoneFieldColor().equals(Colors.IMAGE_DROPZONE_WARNING_BACKGROUND.getColor()),
                "The background color of the dropzone field should be: " + Colors.IMAGE_DROPZONE_WARNING_BACKGROUND.getColor());
        softAssert.assertTrue(imageUploadComponent.getImageFormatWarningMessage().isEnabled(),
                "The image upload warning message should be displayed.");

        softAssert.assertAll();
    }

}

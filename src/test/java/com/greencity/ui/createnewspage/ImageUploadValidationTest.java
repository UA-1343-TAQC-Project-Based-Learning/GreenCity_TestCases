package com.greencity.ui.createnewspage;

import com.greencity.ui.component.ImageUploadComponent;
import com.greencity.ui.data.Colors;
import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.nio.file.Paths;

public class ImageUploadValidationTest extends BaseTestRunner {
    private SoftAssert softAssert = new SoftAssert();

    private String pngImagePAth = Paths.get("src/test/resources/images/GreenCity5mb.png").toAbsolutePath().toString();
    private String gifImagePath = Paths.get("src/test/resources/images/GreenCity1mb.gif").toAbsolutePath().toString();
    private String jpegImagePath = Paths.get("src/test/resources/images/GreenCity15mb.jpg").toAbsolutePath().toString();

    @Description("Verify the validation of the 'Upload Image' field (accepted formats: PNG/JPG, maximum size: 10MB).")
    @Issue("16")
    @Test
    public void checkUploadImage() {
        ImageUploadComponent imageUploadComponent = homePage
                .gotoEcoNewsPage()
                .clickCreateNewsButton()
                .switchToImageUploadComponent()
                .uploadImage(pngImagePAth);

        softAssert.assertTrue(imageUploadComponent.getPresentationImageWindow().isDisplayed(),
                "The presentation window should be displayed on the uploaded image");

        imageUploadComponent
                .clickCancelButton()
                .uploadImage(gifImagePath);
        softAssert.assertTrue(imageUploadComponent.getImageDropzoneFieldColor().equals(Colors.IMAGE_DROPZONE_WARNING_BACKGROUND.getColor()),
                "The background color of the dropzone field should be: " + Colors.IMAGE_DROPZONE_WARNING_BACKGROUND.getColor());
        softAssert.assertTrue(imageUploadComponent.getImageFormatWarningMessage().isEnabled(),
                "The image upload warning message should be displayed.");
        softAssert.assertTrue(imageUploadComponent.getUploadFieldWarningText().equals("The image isn't uploaded"),
                "'The image isn't uploaded' message should be displayed");


        imageUploadComponent.uploadImage(jpegImagePath);
        softAssert.assertTrue(imageUploadComponent.getImageDropzoneFieldColor().equals(Colors.IMAGE_DROPZONE_WARNING_BACKGROUND.getColor()),
                "The background color of the dropzone field should be: " + Colors.IMAGE_DROPZONE_WARNING_BACKGROUND.getColor());
        softAssert.assertTrue(imageUploadComponent.getImageFormatWarningMessage().isEnabled(),
                "The image upload warning message should be displayed.");

        softAssert.assertAll();
    }

}

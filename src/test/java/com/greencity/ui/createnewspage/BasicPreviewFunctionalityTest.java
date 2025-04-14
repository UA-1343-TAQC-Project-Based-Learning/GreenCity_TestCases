package com.greencity.ui.createnewspage;

import com.greencity.ui.component.ImageUploadComponent;
import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.page.econewspage.PreviewNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BasicPreviewFunctionalityTest extends BaseTestRunner {

    private SoftAssert softAssert = new SoftAssert();

    @Test
    public void checkPreviewMode() {
        String titleTestValue = "Test Preview";
        String newsTextContent = "This is a test preview content";

        PreviewNewsPage previewNewsPage = homePage
                .gotoEcoNewsPage()
                .clickCreateNewsButton()
                .fillTitleInputTextField(titleTestValue)
                .enterTextIntoTextContentField(newsTextContent)
                .clickPreviewButton();

        softAssert.assertEquals(previewNewsPage.getNewsTitle().getText(), titleTestValue,
                "News title mismatch: Expected " + titleTestValue + ", but found: " + previewNewsPage.getNewsTitle().getText());

        softAssert.assertEquals(previewNewsPage.getNewsTextContent().getText(), newsTextContent,

                "News content mismatch: Expected " + newsTextContent + ", but found " + previewNewsPage.getNewsTextContent().getText());
        softAssert.assertEquals(previewNewsPage.getNewsInfoDate().getText(), LocalDate.now().format(DateTimeFormatter.ofPattern("MMM d, yyyy")),

                "News date mismatch: Expected " + LocalDate.now().format(DateTimeFormatter.ofPattern("MMM d, yyyy")) + ", but found " + previewNewsPage.getNewsInfoDate().getText());

        softAssert.assertEquals(previewNewsPage.getNewsAuthor().getText(), "by " + previewNewsPage.getLoggedHeader().getUserNameText(),
                "News author mismatch: Expected " + "by " + previewNewsPage.getLoggedHeader().getUserNameText() + " but found " + previewNewsPage.getNewsAuthor().getText());

        softAssert.assertTrue(previewNewsPage.getBackToEditingButton().isEnabled(),
                "Back to Editing button is not enabled.");

        softAssert.assertAll();

    }

}

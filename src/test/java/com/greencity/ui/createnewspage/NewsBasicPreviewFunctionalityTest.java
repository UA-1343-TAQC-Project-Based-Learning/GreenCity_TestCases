package com.greencity.ui.createnewspage;

import com.greencity.ui.page.econewspage.PreviewNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NewsBasicPreviewFunctionalityTest extends BaseTestRunner {

    private SoftAssert softAssert = new SoftAssert();

    @Test
    @Owner("Serhii Hembei")
    @Description("Verify that the user can preview news content after entering valid data and that the preview matches the input.")
    @Severity(SeverityLevel.MINOR)
    @Epic("Preview page")
    @Feature("News Page")
    @Issue("56")
    @Link(name = "Website", url = "http://localhost:4205/#/greenCity")

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
                "News title mismatch: Expected " + titleTestValue + ", but found: " +
                        previewNewsPage.getNewsTitle().getText());

        softAssert.assertEquals(previewNewsPage.getNewsTextContent().getText(), newsTextContent,
                "News content mismatch: Expected " + newsTextContent + ", but found " +
                        previewNewsPage.getNewsTextContent().getText());

        softAssert.assertEquals(previewNewsPage.getNewsInfoDate().getText(), LocalDate.now().format(DateTimeFormatter.ofPattern("MMM d, yyyy")),
                "News date mismatch: Expected " + LocalDate.now().format(DateTimeFormatter.ofPattern("MMM d, yyyy")) + ", but found " +
                        previewNewsPage.getNewsInfoDate().getText());

        softAssert.assertEquals(previewNewsPage.getNewsAuthor().getText(), "by " + previewNewsPage.getLoggedHeader().getUserNameText(),
                "News author mismatch: Expected " + "by " + previewNewsPage.getLoggedHeader().getUserNameText() + " but found " +
                        previewNewsPage.getNewsAuthor().getText());

        softAssert.assertTrue(previewNewsPage.getBackToEditingButton().isEnabled(),
                "Back to Editing button is not enabled.");

        softAssert.assertAll();

    }

}

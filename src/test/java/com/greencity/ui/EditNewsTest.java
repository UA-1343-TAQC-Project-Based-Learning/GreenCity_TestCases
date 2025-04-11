package com.greencity.ui;

import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.data.Colors;
import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class EditNewsTest extends BaseTestRunner {
    protected SoftAssert softAssert = new SoftAssert();
    public String getDataFormating(Locale locale){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM d, yyyy",locale );
        return currentDate.format(dateFormat);
    }
    @Test
    public void checkEditNewsTest() {
        CreateEditNewsPage createEditNewsPage = homePage
                .gotoEcoNewsPage()
                .goToNewsCardPage("My Test Eco News")
                . clickEditButton();
        softAssert.assertEquals(createEditNewsPage.getTitleHeaderText(),"Edit news", " Title should be equal 'Edit news' ");

        softAssert.assertEquals(createEditNewsPage.getTitleInputTextFieldValue(), "My Test Eco News",
                "The text should equal : My Test Eco News ");
        softAssert.assertTrue(createEditNewsPage.getTitleInputTextFieldValue().length() == 16,
                "The text should equal 170 characters.");

        softAssert.assertTrue(createEditNewsPage.isTagSelected(TagButton.NEWS),
                "The tag should be already selected ");
        softAssert.assertEquals(createEditNewsPage.getTagButtonColor(TagButton.NEWS), Colors.PRIMARY_GREEN.getColor(),
                "The tag should have green color when selected");

        softAssert.assertTrue(createEditNewsPage.isTagSelected(TagButton.EDUCATION),
                "The tag should be already selected ");
        softAssert.assertEquals(createEditNewsPage.getTagButtonColor(TagButton.EDUCATION), Colors.PRIMARY_GREEN.getColor(),
                "The tag should have green color when selected");

        softAssert.assertEquals(createEditNewsPage.getExternalSourceInputFieldTitle(),"Source (optional)", "Source (optional) should be present in the 'Edit News' page");
        softAssert.assertTrue(createEditNewsPage.getExternalSourceInputFieldInfoText().contains("Please add the link of original article/news/post. Link must start with http(s)://"),
                "Source info text should be present in the 'Create News' page");
        softAssert.assertEquals(createEditNewsPage.getExternalSourceLinkInputFieldPlaceholderText(),"Link to external source",
                "Placeholder Source Field should be equals 'Link to external source'");

        softAssert.assertEquals(createEditNewsPage.getContentInputFieldText(),"My Test Eco News My Test Eco News");

        softAssert.assertEquals(createEditNewsPage.getAuthorName(),testValueProvider.getUserName(), "userName should be  TestUser" );
        softAssert.assertTrue(createEditNewsPage.isAuthorLabelNotEditable(), "Username label should not be editable");

        softAssert.assertEquals(createEditNewsPage.getDataLabelText(),getDataFormating(Locale.ENGLISH), " the date must be today's");
        softAssert.assertTrue(createEditNewsPage.isDataLabelNotEditable(),"Date label should not be editable");

        softAssert.assertTrue(createEditNewsPage.isCancelButtonPresent(),"Cancel button should be present in the 'Edit News' page");
        softAssert.assertTrue(createEditNewsPage.isCancelButtonEnabled(),"Cancel button should be enabled in the 'Edit News' page");

        softAssert.assertTrue(createEditNewsPage.isPreviewButtonPresent(),"Preview button should be present in the 'Edit News' page");
        softAssert.assertTrue(createEditNewsPage.isPreviewButtonEnabled(),"Preview button should be enabled in the 'Edit News' page");

        softAssert.assertTrue(createEditNewsPage.isPublishButtonPresent(),"Publish button should be present in the 'Edit News' page");
        softAssert.assertTrue(createEditNewsPage.isPublishButtonEnabled(),"Publish button should be enabled in the 'Edit News' page");

        softAssert.assertAll();
    }


}

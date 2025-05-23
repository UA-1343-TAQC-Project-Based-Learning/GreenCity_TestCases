package com.greencity.ui;

import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import com.greencity.utils.FileReaderData;
import io.qameta.allure.*;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import com.greencity.ui.data.Colors;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.stream.Stream;

public class ContentFieldValidationTest  extends BaseTestRunner {

    protected SoftAssert softAssert = new SoftAssert();
    private String contentCharacterProvider(int numberOfCharacters) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < numberOfCharacters; i++) {
            stringBuilder.append("Content Eco.");
        }
        return stringBuilder.toString();
    }
    @Description("Validate the main text field by entering 63,207 characters.")
    @Owner("Khrystyna Martynova")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Edit News")
    @Issue("17")
    @Test
    public void checkContentFormNegative(){
        CreateEditNewsPage createEditNewsPage = homePage
                .gotoEcoNewsPage()
                .clickCreateNewsButton()
                .clickTitleInputTextField()
                .fillTitleInputTextField(contentCharacterProvider(1));

        softAssert.assertTrue(createEditNewsPage.getTitleInputTextFieldValue().length() == 12,
                "The text should equal not more then 170 characters.");
        createEditNewsPage.clickTagFilterButton(TagButton.NEWS);
        softAssert.assertTrue(createEditNewsPage.isTagSelected(TagButton.NEWS),
                "The tag should be selected after clicking");
        softAssert.assertEquals(createEditNewsPage.getTagButtonColor(TagButton.NEWS), Colors.PRIMARY_GREEN.getColor(),
                "The tag should have green color when selected");

        softAssert.assertFalse(createEditNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");
        FileReaderData example = new FileReaderData();
        try (Stream<String> lines = example.readTextFromFileGenerator("D:/Java_projects/file1.txt")) {
            createEditNewsPage.enterTextIntoTextContentField(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        softAssert.assertTrue(createEditNewsPage.getContentCharacterCountText().length() == 63206,
                  "Поле повинно містити не менше 20 та не більше 63 206 символів");
        softAssert.assertTrue(createEditNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");

        softAssert.assertTrue(createEditNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");

        softAssert.assertAll();

    }
    @Description("Validate the main text field with a positive scenario.")
    @Owner("Khrystyna Martynova")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Edit News")
    @Issue("17")
    @Test
    public void checkContentFormPositive(){
        CreateEditNewsPage createEditNewsPage = homePage
                .gotoEcoNewsPage()
                .clickCreateNewsButton()
                .clickTitleInputTextField()
                .fillTitleInputTextField(contentCharacterProvider(10));

        softAssert.assertTrue(createEditNewsPage.getTitleInputTextFieldValue().length() == 120,
                "The text should equal 170 characters.");

        createEditNewsPage.clickTagFilterButton(TagButton.NEWS);
        softAssert.assertTrue(createEditNewsPage.isTagSelected(TagButton.NEWS),
                "The tag should be selected after clicking");
        softAssert.assertEquals(createEditNewsPage.getTagButtonColor(TagButton.NEWS), Colors.PRIMARY_GREEN.getColor(),
                "The tag should have green color when selected");

        softAssert.assertFalse(createEditNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");

        createEditNewsPage.enterTextIntoTextContentField(contentCharacterProvider(25));
        softAssert.assertTrue(createEditNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");
        createEditNewsPage.clickPublishButton();
        softAssert.assertAll();
    }

}

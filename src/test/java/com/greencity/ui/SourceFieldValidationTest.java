package com.greencity.ui;

import com.greencity.ui.page.econewspage.CreateNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SourceFieldValidationTest extends BaseTestRunner {

    @DataProvider(name = "requiredFieldsDataProvider")
    public Object[][] provideRequiredFieldsData() {
        return new Object[][]{
                {"First testing title", "new content for testing", ""},
                {"Second testing title", "next content for testing", "https://example.com"}
        };
    }

    @Test(description = "Verify the validation of the 'Source' field", dataProvider = "requiredFieldsDataProvider")
    public void checkSourceFieldValidation(String title, String content, String source) {
        EcoNewsPage ecoNewsPage = homePage.gotoEcoNewsPage().
                clickCreateNewsButton()
                .fillTitleInputTextField(title)
                .fillContentInput(content)
                .fillSourceInput(source)
                .clickNewsTagButton()
                .clickPublishButton();
    }
}


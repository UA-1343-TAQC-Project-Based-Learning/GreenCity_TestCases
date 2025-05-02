package com.greencity.ui;


import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.data.Colors;
import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.UUID;


public class CheckingFourTag extends BaseTestRunner {


    @Description("Verify that a fourth tag cannot be selected.")
    @Epic("Create News")
    @Feature("Checking Tag Selection")
    @Issue("15")
    @Owner("Nataliia Hrusha")
    @Test
    public void verifyCantSelectFourTag() {
        String newsTitle = "TestNews_" + UUID.randomUUID();
        String newsContent = "Content_" + UUID.randomUUID();

        CreateEditNewsPage createEditNewsPage = homePage
                .gotoEcoNewsPage()
                .clickCreateNewsButton()
                .clickTitleInputTextField()
                .fillTitleInputTextField(newsTitle)
                .enterTextIntoTextContentField(newsContent)
                .clickTagFilterButton(TagButton.NEWS)
                .clickTagFilterButton(TagButton.EVENTS)
                .clickTagFilterButton(TagButton.EDUCATION);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(createEditNewsPage.isTagSelected(TagButton.NEWS),
                "The tag should be selected after clicking");
        softAssert.assertEquals(createEditNewsPage.getTagButtonColor(TagButton.NEWS), Colors.PRIMARY_GREEN.getColor(),
                "The tag should have green color when selected");

        softAssert.assertTrue(createEditNewsPage.isTagSelected(TagButton.EVENTS),
                "The tag should be selected after clicking");


        softAssert.assertEquals(createEditNewsPage.getTagButtonColor(TagButton.EVENTS), Colors.PRIMARY_GREEN.getColor(),
                "The tag should have green color when selected");


        softAssert.assertTrue(createEditNewsPage.isTagSelected(TagButton.EDUCATION),
                "The tag should be selected after clicking");
        softAssert.assertEquals(createEditNewsPage.getTagButtonColor(TagButton.EDUCATION), Colors.PRIMARY_GREEN.getColor(),
                "The tag should have green color when selected");

        createEditNewsPage.clickTagFilterButton(TagButton.INITIATIVES);

        softAssert.assertFalse(createEditNewsPage.isTagSelected(TagButton.INITIATIVES),
                "The tag should NOT be selected when trying to select fourth tag");

        softAssert.assertEquals(createEditNewsPage.getTagButtonColor(TagButton.INITIATIVES), Colors.PRIMARY_WHITE.getColor(),
                "The tag should have white color when not selected");

        softAssert.assertAll();
    }
}

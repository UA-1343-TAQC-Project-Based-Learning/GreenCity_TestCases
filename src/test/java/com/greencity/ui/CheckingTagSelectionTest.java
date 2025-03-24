package com.greencity.ui;

import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.page.econewspage.CreateNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckingTagSelectionTest extends BaseTestRunner {


    @Test
    public void verifyOneTagSelection() {
//         Preconditions: The user is logged into the system.
//        CreateNewsPage createNewsPage = homePage.clickHeaderButton()
//                .successfulLogin(testValueProvider.getUserEmail(), testValueProvider.getUserPassword())


        //  The "Create News" form is open. Fill in the required fields
        CreateNewsPage createNewsPage = homePage
                .gotoEcoNewsPage()
                .clickCreateNewsButton()
                .clickTitleInputTextField()
                .fillTitleInputTextField("test verifyTagSelection")
                .enterTextIntoTextContentField("123456789012345678901")
                // Select one tag
                .clickTagFilterButton(TagButton.NEWS);

        // Verify the tag is successfully selected
        Assert.assertTrue(createNewsPage.isTagSelected(TagButton.NEWS),
                "News tag should be selected after clicking");
        // Verify that the "Publish" button is clickable
        Assert.assertTrue(createNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be enabled when all required fields are filled out");

        // Click "Publish" and get the EcoNewsPage
        EcoNewsPage ecoNewsPage = createNewsPage.clickPublishButton();


        // Verify that at least one news card exists
        // Get the first news card


        // Verify that the news is published with the "News" tag


    }


    @Test
    public void verifyThreeTagSelection() {
//        Preconditions: The user is logged into the system.
//        CreateNewsPage createNewsPage = homePage.clickHeaderButton()
//                .successfulLogin(testValueProvider.getUserEmail(), testValueProvider.getUserPassword())


        //  The "Create News" form is open. Fill in the required fields
        CreateNewsPage createNewsPage = homePage
                .gotoEcoNewsPage()
                .clickCreateNewsButton()
                .clickTitleInputTextField()
                .fillTitleInputTextField("test verifyTagSelection")
                .enterTextIntoTextContentField("123456789012345678901")
// Select three tags
                .clickTagFilterButton(TagButton.NEWS)
                .clickTagFilterButton(TagButton.EVENTS)
                .clickTagFilterButton(TagButton.EDUCATION);

        // Verify all three tags are successfully selected
        Assert.assertTrue(createNewsPage.isTagSelected(TagButton.NEWS),
                "News tag should be selected after clicking");
        Assert.assertTrue(createNewsPage.isTagSelected(TagButton.EVENTS),
                "Events tag should be selected after clicking");
        Assert.assertTrue(createNewsPage.isTagSelected(TagButton.EDUCATION),
                "Education tag should be selected after clicking");

        // Verify that the "Publish" button is clickable
        Assert.assertTrue(createNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be enabled when all required fields are filled out");

        // Click "Publish" and get the EcoNewsPage
        EcoNewsPage ecoNewsPage = createNewsPage.clickPublishButton();

        //Verify that the news is published with all three selected tags.
    }

    @Test
    public void verifyCantSelectFourTag() {
        // Preconditions: The user is logged into the system.
//        CreateNewsPage createNewsPage = homePage.clickHeaderButton()
//                .successfulLogin(testValueProvider.getUserEmail(), testValueProvider.getUserPassword())


        //  The "Create News" form is open. Fill in the required fields
        CreateNewsPage createNewsPage = homePage
                .gotoEcoNewsPage()
                .clickCreateNewsButton()
                .clickTitleInputTextField()
                .fillTitleInputTextField("test verifyTagSelection")
                .enterTextIntoTextContentField("123456789012345678901")

                // Select three tags
                .clickTagFilterButton(TagButton.NEWS)
                .clickTagFilterButton(TagButton.EVENTS)
                .clickTagFilterButton(TagButton.EDUCATION);

        // Verify all three tags are successfully selected
        Assert.assertTrue(createNewsPage.isTagSelected(TagButton.NEWS),
                "News tag should be selected after clicking");
        Assert.assertTrue(createNewsPage.isTagSelected(TagButton.EVENTS),
                "Events tag should be selected after clicking");
        Assert.assertTrue(createNewsPage.isTagSelected(TagButton.EDUCATION),
                "Education tag should be selected after clicking");

        // Attempt to select fourth tag (should not be allowed)
        createNewsPage.clickTagFilterButton(TagButton.INITIATIVES);

        // Verify fourth tag is unselected
        Assert.assertFalse(createNewsPage.isTagSelected(TagButton.INITIATIVES),
                "Initiatives tag should NOT be selected when trying to select fourth tag");

    }

}









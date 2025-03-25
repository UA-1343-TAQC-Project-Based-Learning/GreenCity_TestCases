package com.greencity.ui;

import com.greencity.ui.component.cards.NewsCardTableViewComponent;
import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.page.econewspage.CreateNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class CheckingTagSelectionTest extends BaseTestRunner {


    @Test
    public void verifyOneTagSelection() {
//         Preconditions: The user is logged into the system.
//        CreateNewsPage createNewsPage = homePage.clickHeaderButton()
//                .successfulLogin(testValueProvider.getUserEmail(), testValueProvider.getUserPassword())


        String newsTitle = "TestNews_" + System.currentTimeMillis();
        String newsContent = "Content_" + System.currentTimeMillis();

// The "Create News" form is open. Fill in the required fields
        CreateNewsPage createNewsPage = homePage
                .gotoEcoNewsPage()
                .clickCreateNewsButton()
                .clickTitleInputTextField()
                .fillTitleInputTextField(newsTitle)
                .enterTextIntoTextContentField(newsContent)
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
        Assert.assertFalse(ecoNewsPage.getNewsCardsContainer().getNewsCardTableViewComponents().isEmpty(),
                "No news cards found after publishing");

        // Get the first news card
        NewsCardTableViewComponent firstNewsCard = ecoNewsPage.getNewsCardsContainer()
                .getNewsCardTableViewComponents().getFirst();

        // Verify the news title matches
        Assert.assertEquals(firstNewsCard.getTitleLabelText(), newsTitle,
                "Published news title doesn't match the created one");

        // Verify that the news is published with the "News" tag
        List<String> publishedTags = firstNewsCard.getFiltersTagText();
        Assert.assertTrue(publishedTags.contains("НОВИНИ") || publishedTags.contains("NEWS"),
                "Published news should contain News tag. Actual tags: " + publishedTags);
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









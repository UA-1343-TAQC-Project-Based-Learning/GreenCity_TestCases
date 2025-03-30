package com.greencity.ui;

import com.greencity.ui.component.cards.NewsCardTableViewComponent;
import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.data.Colors;
import com.greencity.ui.page.econewspage.CreateNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.UUID;
import java.util.Iterator;
import java.util.stream.Stream;

public class CheckingTagSelectionTest extends BaseTestRunner {

    @DataProvider(name = "tagSelectionProvider")
    public Iterator<Object[]> tagSelectionProvider() {
        return Stream.of(
                new Object[]{1, new TagButton[]{TagButton.NEWS}},
                new Object[]{2, new TagButton[]{TagButton.NEWS, TagButton.EVENTS}},
                new Object[]{3, new TagButton[]{TagButton.NEWS, TagButton.EVENTS, TagButton.EDUCATION}}
        ).iterator();
    }

    @Test(dataProvider = "tagSelectionProvider")
    public void verifyTagSelection(int testNumber, TagButton[] tagsToSelect) {
        String newsTitle = "TestNews_" + UUID.randomUUID();
        String newsContent = "Content_" + UUID.randomUUID();

        CreateNewsPage createNewsPage = homePage
                .gotoEcoNewsPage()
                .clickCreateNewsButton()
                .clickTitleInputTextField()
                .fillTitleInputTextField(newsTitle)
                .enterTextIntoTextContentField(newsContent);

        for (TagButton tag : tagsToSelect) {
            createNewsPage.clickTagFilterButton(tag);
        }

        SoftAssert softAssert = new SoftAssert();

        for (TagButton tag : tagsToSelect) {
            softAssert.assertTrue(createNewsPage.isTagSelected(tag),
                    tag + " tag should be selected after clicking");
            softAssert.assertEquals(createNewsPage.getTagButtonColor(tag), Colors.PRIMARY_GREEN,
                    tag + " tag should have green color when selected");
        }

        softAssert.assertTrue(createNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be enabled when all required fields are filled out");

        EcoNewsPage ecoNewsPage = createNewsPage.clickPublishButton();

        softAssert.assertFalse(ecoNewsPage.getNewsCardsContainer().getNewsCardTableViewComponents().isEmpty(),
                "No news cards found after publishing");

        NewsCardTableViewComponent firstNewsCard = ecoNewsPage.getNewsCardsContainer()
                .getNewsCardTableViewComponents().getFirst();

        softAssert.assertEquals(firstNewsCard.getTitleLabelText(), newsTitle,
                "Published news title doesn't match the created one");


        List<String> publishedTags = firstNewsCard.getFiltersTagText();
        for (TagButton tag : tagsToSelect) {
            softAssert.assertTrue(publishedTags.contains(tag.getUkrainianName()),
                    "Published news should contain " + tag + " tag. Actual tags: " + publishedTags);
        }

        softAssert.assertAll();
    }

    @Test
    public void verifyCantSelectFourTag() {
        String newsTitle = "TestNews_" + UUID.randomUUID();
        String newsContent = "Content_" + UUID.randomUUID();

        CreateNewsPage createNewsPage = homePage
                .gotoEcoNewsPage()
                .clickCreateNewsButton()
                .clickTitleInputTextField()
                .fillTitleInputTextField(newsTitle)
                .enterTextIntoTextContentField(newsContent)
                .clickTagFilterButton(TagButton.NEWS)
                .clickTagFilterButton(TagButton.EVENTS)
                .clickTagFilterButton(TagButton.EDUCATION);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(createNewsPage.isTagSelected(TagButton.NEWS),
                "News tag should be selected after clicking");
        softAssert.assertEquals(createNewsPage.getTagButtonColor(TagButton.NEWS), Colors.PRIMARY_GREEN,
                "News tag should have green color when selected");

        softAssert.assertTrue(createNewsPage.isTagSelected(TagButton.EVENTS),
                "Events tag should be selected after clicking");
        softAssert.assertEquals(createNewsPage.getTagButtonColor(TagButton.EVENTS), Colors.PRIMARY_GREEN,
                "Events tag should have green color when selected");

        softAssert.assertTrue(createNewsPage.isTagSelected(TagButton.EDUCATION),
                "Education tag should be selected after clicking");
        softAssert.assertEquals(createNewsPage.getTagButtonColor(TagButton.EDUCATION), Colors.PRIMARY_GREEN,
                "Education tag should have green color when selected");

        createNewsPage.clickTagFilterButton(TagButton.INITIATIVES);

        softAssert.assertFalse(createNewsPage.isTagSelected(TagButton.INITIATIVES),
                "Initiatives tag should NOT be selected when trying to select fourth tag");

        softAssert.assertEquals(createNewsPage.getTagButtonColor(TagButton.INITIATIVES), Colors.PRIMARY_WHITE,
                "Education tag should have green color when selected");

        softAssert.assertAll();
    }
}
package com.greencity.ui;

import com.greencity.ui.component.cards.NewsCardTableViewComponent;
import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.data.Colors;
import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
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
                new Object[]{new TagButton[]{TagButton.NEWS}},
                new Object[]{new TagButton[]{TagButton.NEWS, TagButton.EVENTS}},
                new Object[]{new TagButton[]{TagButton.NEWS, TagButton.EVENTS, TagButton.EDUCATION}}
        ).iterator();
    }

    @Test(dataProvider = "tagSelectionProvider")
    public void verifyTagSelection(TagButton[] tagsToSelect) {
        String newsTitle = "TestNews_" + UUID.randomUUID();
        String newsContent = "Content_" + UUID.randomUUID();

        CreateEditNewsPage createEditNewsPage = homePage
                .gotoEcoNewsPage()
                .clickCreateNewsButton()
                .clickTitleInputTextField()
                .fillTitleInputTextField(newsTitle)
                .enterTextIntoTextContentField(newsContent);

        for (TagButton tag : tagsToSelect) {
            createEditNewsPage.clickTagFilterButton(tag);
        }

        SoftAssert softAssert = new SoftAssert();

        for (TagButton tag : tagsToSelect) {
            softAssert.assertTrue(createEditNewsPage.isTagSelected(tag),
                    tag + " tag should be selected after clicking");
            softAssert.assertEquals(createEditNewsPage.getTagButtonColor(tag), Colors.PRIMARY_GREEN,
                    tag + " tag should have green color when selected");
        }

        softAssert.assertTrue(createEditNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be enabled when all required fields are filled out");

        EcoNewsPage ecoNewsPage = createEditNewsPage.clickPublishButton();

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
        softAssert.assertEquals(createEditNewsPage.getTagButtonColor(TagButton.NEWS), Colors.PRIMARY_GREEN,
                "The tag should have green color when selected");

        softAssert.assertTrue(createEditNewsPage.isTagSelected(TagButton.EVENTS),
                "The tag should be selected after clicking");


        softAssert.assertTrue(createEditNewsPage.isTagSelected(TagButton.EDUCATION),
                "The tag should be selected after clicking");
        softAssert.assertEquals(createEditNewsPage.getTagButtonColor(TagButton.EDUCATION), Colors.PRIMARY_GREEN,
                "The tag should have green color when selected");

        createEditNewsPage.clickTagFilterButton(TagButton.INITIATIVES);

        softAssert.assertFalse(createEditNewsPage.isTagSelected(TagButton.INITIATIVES),
                "The tag should NOT be selected when trying to select fourth tag");

        softAssert.assertEquals(createEditNewsPage.getTagButtonColor(TagButton.INITIATIVES), Colors.PRIMARY_WHITE,
                "The tag should have white color when not selected");

        softAssert.assertAll();
    }
}
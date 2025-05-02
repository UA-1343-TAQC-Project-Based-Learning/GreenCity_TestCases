package com.greencity.ui;

import com.greencity.ui.component.cards.NewsCardTableViewComponent;
import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.data.Colors;
import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import io.qameta.allure.*;
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


    @Description("Verify that the user can select between 1 and 3 tags and news is published with selected tags.")
    @Epic("Create News")
    @Feature("Checking Tag Selection")
    @Issue("15")
    @Owner("Nataliia Hrusha")
    @Test(dataProvider = "tagSelectionProvider")
    public void verifyTagSelection(TagButton[] tagsToSelect) {
        String newsTitle = "TestNews_" + UUID.randomUUID();
        String newsContent = "Content_" + UUID.randomUUID();

        CreateEditNewsPage createEditNewsPage = homePage
                .openEcoNewsPage()
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
            softAssert.assertEquals(createEditNewsPage.getTagButtonColor(tag), Colors.PRIMARY_GREEN.getColor(),
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

}

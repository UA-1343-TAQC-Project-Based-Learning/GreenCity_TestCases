package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.component.cards.NewsCardTableViewComponent;
import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.data.Colors;
import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.page.homepage.HomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class NewsTagSelectionSteps {

    private Hooks hooks;
    private HomePage homePage;
    private EcoNewsPage ecoNewsPage;
    private CreateEditNewsPage createEditNewsPage;
    private List<TagButton> selectedTags;
    private String newsTitle;
    private String newsContent;

    public NewsTagSelectionSteps(Hooks hooks) {
        this.hooks = hooks;
    }

    private TagButton getTagButtonByName(String tagName) {
        try {
            return TagButton.valueOf(tagName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown tag: " + tagName);
        }
    }

    @Given("The user is on Create News page")
    public void userIsOnCreateNewsPage() {
        homePage = new HomePage(hooks.getDriver());
        ecoNewsPage = homePage.gotoEcoNewsPage();
        createEditNewsPage = ecoNewsPage.clickCreateNewsButton();

        newsTitle = "TestNews_" + UUID.randomUUID();
        newsContent = "Content_" + UUID.randomUUID();
    }

    @When("The user fills required fields with valid data")
    public void fillRequiredFieldsWithValidData() {
        createEditNewsPage.clickTitleInputTextField()
                .fillTitleInputTextField(newsTitle)
                .enterTextIntoTextContentField(newsContent);
    }

    @When("The user selects the following tags:")
    public void selectTags(DataTable tagsTable) {
        List<String> tags = tagsTable.asList();
        for (String tag : tags) {
            TagButton tagButton = getTagButtonByName(tag);
            createEditNewsPage.clickTagFilterButton(tagButton);
        }
        this.selectedTags = tags.stream()
                .map(this::getTagButtonByName)
                .collect(Collectors.toList());
    }

    @And("The user clicks {string} button")
    public void clickButton(String buttonName) {
        switch (buttonName) {
            case "Publish":
                ecoNewsPage = createEditNewsPage.clickPublishButton();
                break;
            default:
                Assert.fail("Unknown button: " + buttonName);
        }
    }

    @Then("The news should be published with the selected tags")
    public void verifyPublishedNewsHasCorrectTags() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertFalse(ecoNewsPage.getNewsCardsContainer().getNewsCardTableViewComponents().isEmpty(),
                "No news cards found after publishing");

        NewsCardTableViewComponent firstNewsCard = ecoNewsPage.getNewsCardsContainer()
                .getNewsCardTableViewComponents().getFirst();

        softAssert.assertEquals(firstNewsCard.getTitleLabelText(), newsTitle,
                "Published news title doesn't match the created one");

        List<String> publishedTags = firstNewsCard.getFiltersTagText();
        for (TagButton tag : selectedTags) {
            softAssert.assertTrue(publishedTags.contains(tag.getEnglishName()),
                    "Published news should contain " + tag + " tag. Actual tags: " + publishedTags);
        }

        softAssert.assertAll();
    }

    @Then("The tag {string} should be selected and green")
    public void verifyTagIsSelectedAndGreen(String tagName) {
        TagButton tag = getTagButtonByName(tagName);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(createEditNewsPage.isTagSelected(tag),
                tagName + " tag should be selected");
        softAssert.assertEquals(createEditNewsPage.getTagButtonColor(tag), "#2b6b34",
                tagName + " tag should be green when selected");
        softAssert.assertAll();
    }

    @When("The user tries to select the tag {string}")
    public void tryToSelectFourthTag(String tagName) {
        TagButton tagButton = getTagButtonByName(tagName);
        createEditNewsPage.clickTagFilterButton(tagButton);
    }

    @Then("The tag {string} should not be selected")
    public void verifyTagIsNotSelected(String tagName) {
        TagButton tag = getTagButtonByName(tagName);
        Assert.assertFalse(createEditNewsPage.isTagSelected(tag),
                "Tag " + tagName + " should NOT be selected");
    }

    @Then("The tag {string} should be white")
    public void verifyTagIsWhite(String tagName) {
        TagButton tag = getTagButtonByName(tagName);
        Assert.assertEquals(createEditNewsPage.getTagButtonColor(tag), Colors.PRIMARY_WHITE.getColor(),
                "Tag " + tagName + " should be white when not selected");
    }
}

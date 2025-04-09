package com.greencity.cucumber.steps;

import com.greencity.ui.component.ecoNewsTag.TagButton;
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
import java.util.stream.Collectors;

public class NewsTagSelectionSteps extends BaseStep {

    private HomePage homePage;
    private EcoNewsPage newsPage;
    private CreateEditNewsPage createEditNewsPage;
    private List<TagButton> selectedTags;

    private TagButton getTagButtonByName(String tagName) {
        try {
            return TagButton.valueOf(tagName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown tag: " + tagName);
        }
    }

    @Given("The user is on Create News page")
    public void userIsOnCreateNewsPage() {
        initDriver();
        driver.get(testValueProvider.getBaseUIUrl());
        homePage = new HomePage(driver);
        newsPage = homePage.getHeader().gotoEcoNewsPage();
        newsPage.clickCreateNewsButton();
        createEditNewsPage = new CreateEditNewsPage(driver);
    }

    @And("The user fills required fields with valid data")
    public void fillRequiredFieldsWithValidData() {
        createEditNewsPage.fillTitleInputTextField("Test Title");
        createEditNewsPage.fillContentInput("Valid content with more than 20 characters");
    }

    @When("The user selects the following tags:")
    public void selectTags(DataTable tagsTable) {
        List<String> tags = tagsTable.asList();
        tags.forEach(tag ->
                createEditNewsPage.clickOnlyUnselectedTagFilterButton(getTagButtonByName(tag))
        );
        this.selectedTags = tags.stream()
                .map(this::getTagButtonByName)
                .collect(Collectors.toList());
    }

    @When("The user attempts to select a fourth tag {string}")
    public void attemptToSelectFourthTag(String tagName) {
        createEditNewsPage.clickOnlyUnselectedTagFilterButton(getTagButtonByName(tagName));
    }

    @And("The user clicks {string} button")
    public void clickButton(String buttonName) {
        switch (buttonName) {
            case "Publish":
                driver.findElement(By.xpath("//button[@class='primary-global-button']")).click();
                break;
            default:
                Assert.fail("Unknown button: " + buttonName);
        }
    }

    @Then("The news should be published with the selected tags")
    public void verifyPublishedNewsHasCorrectTags() {
        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);
        var newsCard = ecoNewsPage.getNewsCardsContainer()
                .getNewsCardTableViewComponents()
                .getFirst();

        List<String> actualTags = newsCard.getFiltersTagText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualTags.size(), selectedTags.size(),
                "Number of tags in published news doesn't match");

        selectedTags.forEach(tag ->
                softAssert.assertTrue(actualTags.contains(tag.getUkrainianName()),
                        "Tag " + tag + " not found in published news")
        );

        softAssert.assertAll();
    }

    @Then("The tag {string} should not be selected")
    public void verifyTagNotSelected(String tagName) {
        TagButton tag = getTagButtonByName(tagName);
        Assert.assertFalse(createEditNewsPage.isTagSelected(tag),
                tagName + " tag should not be selectable");
    }

    @Then("Only {int} tags should be selected")
    public void verifyNumberOfSelectedTags(int expectedCount) {
        long selectedCount = selectedTags.stream()
                .filter(tag -> createEditNewsPage.isTagSelected(tag))
                .count();
        Assert.assertEquals(selectedCount, expectedCount,
                "Incorrect number of selected tags");
    }
}

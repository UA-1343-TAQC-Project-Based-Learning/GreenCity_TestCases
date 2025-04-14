package com.greencity.cucumber.steps;

import com.greencity.cucumber.ScenarioContext;
import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.page.econewspage.NewsCardPage;
import com.greencity.ui.page.homepage.HomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class EcoNewsPageAndCardPageStepsDefinition {
    private Hooks hooks;
    private EcoNewsPage ecoNewsPage;
    private NewsCardPage newsCardPage;
    private CreateEditNewsPage createEditNewsPage;
    private final ScenarioContext scenarioContext = new ScenarioContext();

    public EcoNewsPageAndCardPageStepsDefinition(Hooks hooks) {
        this.hooks = hooks;
    }

    @When("the user navigates to the Eco News page")
    public void theUserNavigatesToTheEcoNewsPage() {
        ecoNewsPage = new HomePage(hooks.getDriver()).getHeader().gotoEcoNewsPage();
    }

    @When("store date of creation card as {string}")
    public void storeDateOfCreationCard(String storedValue) {
        LocalDate dateOfCreation = ecoNewsPage.getDataLabelFormating(Locale.ENGLISH);
        scenarioContext.set(storedValue, dateOfCreation);
    }

    @When("store first card title as {string}")
    public void storeFirstCardTitle(String storedValue) {
        String title = ecoNewsPage.getFirstCardTitle();
        scenarioContext.set(storedValue, title);
    }

    @When("store date in the News Card Page as {string}")
    public void storeDateInTheNewsCardPage(String cardDate){
        LocalDate storedCardDate = newsCardPage.getDataLabelFormating(Locale.ENGLISH);
        scenarioContext.set(cardDate, storedCardDate);
    }

    @Then("the stored {string} date should be equal to stored {string} date")
    public void compareSoredDates(String date1, String date2) {
        LocalDate storedDate1 = (LocalDate) scenarioContext.get(date1);
        LocalDate storedDate2 = (LocalDate) scenarioContext.get(date2);
        hooks.getSoftAssert().assertEquals(storedDate1, storedDate2, "stored dates should be equal each other");
    }

    @When("generate unique title and store as {string}")
    public void generateUniqueTitle(String title) {
        String uniqueTitle = "TestTitle-" + UUID.randomUUID();
        scenarioContext.set(title, uniqueTitle);
    }

    @When("the user types {string} in the Content field")
    public void theUserTypesInTheContentField(String content) {
        createEditNewsPage.fillContentInput(content);
    }

    @When("go to the {string} News Card page")
    public void goToTheNewsCardPage(String title) {
        String cardTitle = (String) scenarioContext.get(title);
        newsCardPage = ecoNewsPage.goToNewsCardPage(cardTitle);
    }

    @When("go to the Edit News Card Page")
    public void goToTheEditNewsCardPage() {
        newsCardPage.clickEditButton();
        createEditNewsPage = new CreateEditNewsPage(hooks.getDriver());
    }

    @When("the user types stored value {string} in the Title field")
    public void theUserTypesInTheTitleField(String title) {
        String uniqueTitle = (String) scenarioContext.get(title);
        createEditNewsPage.fillTitleInputTextField(uniqueTitle);
    }

    @When("store title in the News Card Page as {string}")
    public void storeTitleInTheNewsCardPage(String cardTitle) {
        String changedTitle = newsCardPage.getTitleLabelText();
        scenarioContext.set(cardTitle, changedTitle);
    }

    @Then("the stored {string} title should be equal to stored {string} title")
    public void compareSoredTitles(String uniqueTitle, String changedTitle) {
        String title1 = (String) scenarioContext.get(uniqueTitle);
        String title2 = (String) scenarioContext.get(changedTitle);
        hooks.getSoftAssert().assertEquals(title1, title2, "stored titles should be equal each other");
    }

    @Then("the content field should be updated")
    public void theContentFieldShouldBeUpdated(){
        hooks.getSoftAssert().assertEquals(newsCardPage.getContentLabelText(), "updating a content input", "Content should be updated");
    }

    @Then("the card should contain the following tag filters:")
    public void theCardShouldContainTheFollowingTagFilters(DataTable table) {
        List<String> tagFilters = table.asList();
        for(String tagFilter: tagFilters) {
            switch (tagFilter) {
                case "News":
                    hooks.getSoftAssert().assertTrue(newsCardPage.getFiltersTagText().contains("News"), "News filter should be present");
                    break;
                case "Events":
                    hooks.getSoftAssert().assertTrue(newsCardPage.getFiltersTagText().contains("Events"), "Events filter should be present");
                    break;
                case "Education":
                    hooks.getSoftAssert().assertTrue(newsCardPage.getFiltersTagText().contains("Education"), "Education filter should be present");
                    break;
                case "Initiatives":
                    hooks.getSoftAssert().assertTrue(newsCardPage.getFiltersTagText().contains("Initiatives"), "Initiatives filter should be present");
                    break;
                case "Ads":
                    hooks.getSoftAssert().assertTrue(newsCardPage.getFiltersTagText().contains("Ads"), "Ads filter should be present");
                    break;
                default:
                    hooks.getSoftAssert().fail("Unknown tag filter " + tagFilter);
            }
        }
    }

    @When("the user clicks only unselected tag filter button {string}")
    public void theUserClicksOnlyUnselectedTagFilterButton(String tagButtonName){
        switch (tagButtonName) {
            case "News":
                createEditNewsPage.clickOnlyUnselectedTagFilterButton(TagButton.NEWS);
                break;
            case "Events":
                createEditNewsPage.clickOnlyUnselectedTagFilterButton(TagButton.EVENTS);
                break;
            case "Education":
                createEditNewsPage.clickOnlyUnselectedTagFilterButton(TagButton.EDUCATION);
                break;
            case "Initiatives":
                createEditNewsPage.clickOnlyUnselectedTagFilterButton(TagButton.INITIATIVES);
                break;
            case "Ads":
                createEditNewsPage.clickOnlyUnselectedTagFilterButton(TagButton.ADS);
                break;
            default:
                Assert.fail("Unknown button: " + tagButtonName);
        }
    }

    @When("the user clicks the Publish button")
    public void theUserClicksThePublishButton(){
        createEditNewsPage.clickPublishButton();
        ecoNewsPage = new HomePage(hooks.getDriver()).getHeader().gotoEcoNewsPage();
    }
}

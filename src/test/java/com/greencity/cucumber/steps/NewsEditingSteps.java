package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.data.Colors;
import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.page.econewspage.NewsCardPage;
import com.greencity.ui.page.homepage.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.UUID;

public class NewsEditingSteps {

    private Hooks hooks;
    private HomePage homePage;
    private EcoNewsPage ecoNewsPage;
    private NewsCardPage newsCardPage;
    private CreateEditNewsPage createEditNewsPage;
    private String newsTitle;
    private String newsContent;
    private SoftAssert softAssert;

    public NewsEditingSteps(Hooks hooks) {
        this.hooks = hooks;
        this.softAssert = hooks.getSoftAssert();
    }

    @Given("The logged in user created and navigated to their news post")
    public void theUserHasCreatedANewsPost() {
        newsTitle = "TestNews_" + UUID.randomUUID();
        newsContent = "Content_" + UUID.randomUUID();
        homePage = new HomePage(hooks.getDriver());
        ecoNewsPage = homePage
                .gotoEcoNewsPage()
                .clickCreateNewsButton()
                .clickTitleInputTextField()
                .fillTitleInputTextField(newsTitle)
                .clickTagFilterButton(TagButton.NEWS)
                .enterTextIntoTextContentField(newsContent)
                .clickPublishButton();

        newsCardPage = ecoNewsPage.goToNewsCardPage(newsTitle);
    }

    @Then("The {string} button should be visible")
    public void theButtonShouldBeVisible(String buttonText) {
        softAssert.assertEquals(newsCardPage.getEditButtonText(), "Edit news",
                "Edit button is not visible on the Edit News page.");
    }

    @And("The button text should be {string}")
    public void theButtonTextShouldBe(String expectedText) {
        softAssert.assertEquals(newsCardPage.getEditButtonText(), expectedText,
                "Button text mismatch");
    }

    @When("The user clicks the {string} button")
    public void theUserClicksTheButton(String buttonText) {
        createEditNewsPage = newsCardPage.clickEditButton();
    }

    @Then("The edit form header should be {string}")
    public void theEditFormHeaderShouldBe(String expectedHeader) {
        softAssert.assertEquals(createEditNewsPage.getTitleHeaderText(), expectedHeader,
                "Edit form header mismatch");
    }

    @When("The user opens the edit form for their news")
    public void theUserOpensTheEditFormForTheirNews() {
        createEditNewsPage = newsCardPage.clickEditButton();
    }

    @When("The user clears the title field")
    public void theUserClearsTheTitleField() {
        createEditNewsPage.clearTitleInputTextField();
    }

    @When("The user clicks outside the title field")
    public void theUserClicksOutsideTheTitleField() {
        createEditNewsPage.clickTitleHeaderText();
    }

    @Then("The title field border should be red")
    public void theTitleFieldBorderShouldBeRed() {
        softAssert.assertEquals(createEditNewsPage.getTitleInputFieldBorderColor(),
                Colors.RED.getColor(),
                "Title field border should be red");
    }

    @Then("The {string} button should be disabled")
    public void theButtonShouldBeDisabled(String buttonText) {
        softAssert.assertFalse(createEditNewsPage.isEditButtonEnabled(),
                buttonText + " button should be disabled");
    }

    @When("The user cancels the edit")
    public void theUserCancelsTheEdit() {
        ecoNewsPage = createEditNewsPage.clickExitButton().clickYesCancelButton();
    }

    @Then("The original news title should remain unchanged")
    public void theOriginalNewsTitleShouldRemainUnchanged() {
        softAssert.assertTrue(ecoNewsPage.isExistCardComponentByPartialTitle(newsTitle),
                "Original news title should remain after canceling edit");
    }

    @When("The user edits the news title")
    public void theUserEditsTheNewsTitle() {
        String newTitle = "Edited_" + newsTitle;
        createEditNewsPage.clearTitleInputTextField()
                .fillTitleInputTextField(newTitle);
    }

    @Then("The user should be redirected to Eco News page")
    public void theUserShouldBeRedirectedToEcoNewsPage() {
        softAssert.assertEquals(ecoNewsPage.getHeaderText(), "Eco news",
                "Should be redirected to Eco News page");
    }

    @Then("The original news title should be displayed")
    public void theOriginalNewsTitleShouldBeDisplayed() {
        softAssert.assertTrue(ecoNewsPage.isExistCardComponentByPartialTitle(newsTitle),
                "Original news title should be displayed after canceling edit");
    }

    @And("Clean up test news")
    public void cleanUpTestNews() {
        ecoNewsPage.goToNewsCardPage(newsTitle)
                .clickDeleteButton()
                .clickYesButton();
    }
}

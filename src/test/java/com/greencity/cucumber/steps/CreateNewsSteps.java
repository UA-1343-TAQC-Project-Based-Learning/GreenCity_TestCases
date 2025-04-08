package com.greencity.cucumber.steps;

import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.page.homepage.HomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class CreateNewsSteps  extends BaseStep{

    private CreateEditNewsPage createEditNewsPage;
    private HomePage homePage;
    private EcoNewsPage newsPage;


    @Given("the user is registered and logged into the GreenCity system")
    public void theUserIsRegisteredAndLoggedIntoTheGreenCitySystem() {
        initDriver();
        driver.get(testValueProvider.getBaseUIUrl());
        homePage = new HomePage(driver);
    }

    @When("the user navigates to the GreenCity News page")
    public void theUserNavigatesToTheGreenCityNewsPage() {
        newsPage = new HomePage(driver).getHeader().gotoEcoNewsPage();
    }

    @And("the user clicks the {string} button on the main news page")
    public void theUserClicksTheButtonOnTheMainNewsPage(String arg0) {
        newsPage.clickCreateNewsButton();
        sleep(1);
        createEditNewsPage = new CreateEditNewsPage(driver);
    }

    @Then("the Create News form should be displayed")
    public void theFormShouldBeDisplayed() {
        Assert.assertEquals(createEditNewsPage.getTitleText(), "Title","Title should be present in the 'Create News' page");

    }

    @Then("the form should contain the following fields in order")
    public void theFormShouldContainTheFollowingFieldsInOrder() {
        SoftAssert assertion = new SoftAssert();

        assertion.assertEquals(createEditNewsPage.getTitleText(), "Title","Title should be present in the 'Create News' page");
        assertion.assertTrue(createEditNewsPage.isPresentTitleInputTextField(),"Title input text field should be present in the 'Create News' page");
        assertion.assertTrue(createEditNewsPage.getTitleFieldCharacterCounterText().contains("0/170"), "Character count should be present in the 'Create News' page");
        assertion.assertTrue(createEditNewsPage.getOnlyThreeTagsCanBeAddedText().contains("Only 3 tags can be added"));
        assertion.assertEquals(createEditNewsPage.getListOfAllTagButtonsText(),createEditNewsPage.tagFilters,"All tags should be present in the 'Create News' page");
        assertion.assertTrue(createEditNewsPage.isAllSelectedTagsChangeAppearance(),"All tags should change an appearance after selecting");
        assertion.assertEquals(createEditNewsPage.getImageBrowseLinkText(),"browse","link for uploading an image should be present in the 'Create News' page");
        assertion.assertEquals(createEditNewsPage.getContentText(),"Content");
        assertion.assertTrue(createEditNewsPage.isPresentContentInputTextField(),"Content input text field should be present in the 'Create News' page");
        assertion.assertTrue(createEditNewsPage.getContentCharacterCountText().contains("Must be minimum 20 and maximum 63 206 symbols"));
        assertion.assertEquals(createEditNewsPage.getAuthorLabelText(),testValueProvider.getUserName().toLowerCase(), "userName should be present in the 'Create News' page" );
        assertion.assertTrue(createEditNewsPage.isAuthorLabelNotEditable(), "Username label should not be editable");
        assertion.assertEquals(createEditNewsPage.getDataLabelFormating(Locale.ENGLISH), LocalDate.now(), "current date should be present");
        assertion.assertTrue(createEditNewsPage.isDataLabelNotEditable(),"Date label should not be editable");
        assertion.assertEquals(createEditNewsPage.getExternalSourceInputFieldTitle(),"Source (optional)", "Source (optional) should be present in the 'Create News' page");
        assertion.assertTrue(createEditNewsPage.getExternalSourceInputFieldInfoText().contains("Please add the link of original article/news/post. Link must start with http(s)://"),
                "Source info text should be present in the 'Create News' page");
        assertion.assertEquals(createEditNewsPage.getExternalSourceLinkInputFieldPlaceholderText(),"Link to external source",
                "Placeholder Source Field should be equals 'Link to external source'");

        assertion.assertAll();
    }

    @Then("the form should contain the following buttons:")
    public void theFormShouldContainTheFollowingButtons(DataTable table) {
        List<String> buttons = table.asList();
        for(String button: buttons) {
            switch (button) {
                case "Cancel":
                    Assert.assertTrue(createEditNewsPage.isCancelButtonPresent(),"Cancel button should be present in the 'Create News' page");
                    break;
                case "Preview":
                    Assert.assertTrue(createEditNewsPage.isPreviewButtonPresent(),"Preview button should be present in the 'Create News' page");
                    break;
                case "Publish":
                    Assert.assertTrue(createEditNewsPage.isPublishButtonPresent(),"Publish button should be present in the 'Create News' page");
                    break;
                default:
                    Assert.fail("Unknown button: " + button);
            }
        }
    }

    @Then("the Author field should be pre-filled and non-editable")
    public void theFieldShouldBePreFilledAndNonEditable(String arg0) {
    }

    @Then("the Date field should be pre-filled with the current date and non-editable")
    public void theFieldShouldBePreFilledWithTheCurrentDateAndNonEditable(String arg0) {
    }


}

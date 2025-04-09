package com.greencity.cucumber.steps;

import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.page.homepage.HomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class CreateEditNewsPageStepsDefinition extends BaseStep {

    private HomePage homePage;
    private EcoNewsPage newsPage;
    CreateEditNewsPage createEditNewsPage;

    @Given("The user is on the Home page as a logged-in user")
    public void theUserIsOnTheHomePageAsALoggedInUser() {
        initDriver();
        driver.get(testValueProvider.getBaseUIUrl());
        homePage = new HomePage(driver);
    }

    @When("the user navigates to the GreenCity News page")
    public void theUserNavigatesToTheGreenCityNewsPage() {
        newsPage = new HomePage(driver).getHeader().gotoEcoNewsPage();
    }

    @When("the user navigates to the Create News page")
    public void theUserNavigatesToTheCreateNewsPage() {
        newsPage.clickCreateNewsButton();
        createEditNewsPage = new CreateEditNewsPage(driver);
    }

    @When("the user types {string} in the Title field")
    public void theUserTypesInTheTitleField(String title) {
        createEditNewsPage.fillTitleInputTextField(title);
    }

    @When("the user types {string} in the News Content field")
    public void theUserTypesInTheNewsContentField(String content) {
        createEditNewsPage.fillContentInput(content);
    }

    @When("the user types {string} in the Source link field")
    public void theUserTypesInTheSourceLinkField(String url) {
        createEditNewsPage.fillSourceInput(url);
    }

//    @When("the user selects the {tagButton} tag")
//    public void theUserSelectsTheTag(TagButton tagButton) {
//        createEditNewsPage.clickOnlyUnselectedTagFilterButton(tagButton);
//    }
//
//    @When("the user unselects the {tagButton} tag")
//    public void theUserUnselectsTheTag(TagButton tagButton) {
//        if (createEditNewsPage.isTagSelected(tagButton)) {
//            createEditNewsPage.clickTagFilterButton(tagButton);
//        }
//    }

    @When("the user uploads the image from {string}")
    public void theUserUploadsTheImageFrom(String imagePath) {
        createEditNewsPage.switchToImageUploadComponent().uploadImage(imagePath);
    }

    @When("the user clicks the {string} button on the Create News page")
    public void theUserClicksTheButtonOnTheCreateNewsPage(String buttonName) {
        switch (buttonName) {
            case "ImageSubmitButton":
                driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();
                break;
            case "ImageCancelButton":
                driver.findElement(By.xpath("//button[@class='secondary-global-button s-btn']")).click();
                break;
            case "Publish/EditButton":
                driver.findElement(By.xpath("//button[@class='primary-global-button']")).click();
                break;
            case "CancelButton":
                driver.findElement(By.xpath("//button[@class='tertiary-global-button']")).click();
                break;
            case "PreviewButton":
                driver.findElement(By.xpath("//button[@class='secondary-global-button']")).click();
                break;
            default:
                Assert.fail("Unknown button: " + buttonName);
        }
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
       // assertion.assertTrue(createEditNewsPage.getContentCharacterCountText().contains("Must be minimum 20 and maximum 63 206 symbols"));
        assertion.assertEquals(createEditNewsPage.getAuthorLabelText(),testValueProvider.getUserName().toLowerCase(), "userName should be present in the 'Create News' page" );
        assertion.assertTrue(createEditNewsPage.isAuthorLabelNotEditable(), "Username label should not be editable");
       // assertion.assertEquals(createEditNewsPage.getDataLabelFormating(Locale.ENGLISH), LocalDate.now(), "current date should be present");
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
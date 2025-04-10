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
                createEditNewsPage.switchToImageUploadComponent().clickSubmitButton();
                break;
            case "ImageCancelButton":
                createEditNewsPage.switchToImageUploadComponent().clickCancelButton();
                break;
            case "Publish/EditButton":
                createEditNewsPage.clickPublishButton();
                break;
            case "CancelButton":

                break;
            case "PreviewButton":
                createEditNewsPage.clickPreviewButton();
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
        assertion.assertEquals(createEditNewsPage.getContentHeaderText(),"Content","Content header should be present");
        assertion.assertTrue(createEditNewsPage.isPresentContentInputTextField(),"Content input text field should be present in the 'Create News' page");
        assertion.assertTrue(createEditNewsPage.getContentDescriptionWarning().contains("Must be minimum 20 and maximum 63 206 symbols"),"Content warning should be present");
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

    @Then("all elements should be in correct order")
    public void elementsShouldBePresentInCorrectOrder(){
        SoftAssert assertion = new SoftAssert();

        assertion.assertTrue(createEditNewsPage.isElementsOrderCorrect(createEditNewsPage.getTitleInputTextField(),createEditNewsPage.getFilterTagsRoot()),"Incorrect order between titleInputTextField and filterTagsRoot");
        assertion.assertTrue(createEditNewsPage.isElementsOrderCorrect(createEditNewsPage.getFilterTagsRoot(),createEditNewsPage.getExternalSourceLinkInputField()), "Incorrect order between filterTagsRoot and externalSourceLinkInputField");
        assertion.assertTrue(createEditNewsPage.isElementsOrderCorrect(createEditNewsPage.getImageBlockRoot(),createEditNewsPage.getExternalSourceLinkInputField()),"Incorrect order between imageBlockRoot and externalSourceLinkInputField");
        assertion.assertTrue(createEditNewsPage.isElementsOrderCorrect(createEditNewsPage.getExternalSourceLinkInputField(),createEditNewsPage.getTextAreaRoot()),"Incorrect order between externalSourceLinkInputField and textAreaRoot");
        assertion.assertTrue(createEditNewsPage.isElementsOrderCorrect(createEditNewsPage.getTextAreaRoot(),createEditNewsPage.getDataLabel()),"Incorrect order between dtaLabel and textAreaRoot");
        assertion.assertTrue(createEditNewsPage.areElementsOnSameLine(createEditNewsPage.getDataLabel(), createEditNewsPage.getAuthorLabel()),"Incorrect order between dtaLabel and authorLabel");
        assertion.assertTrue(createEditNewsPage.isElementsOrderCorrect(createEditNewsPage.getAuthorLabel(),createEditNewsPage.getExitButton()),"Incorrect order between exitButton and authorLabel");
        assertion.assertTrue(createEditNewsPage.areElementsOnSameLine(createEditNewsPage.getExitButton(), createEditNewsPage.getPreviewButton()),"Incorrect order between exitButton and previewButton");
        assertion.assertTrue(createEditNewsPage.areElementsOnSameLine(createEditNewsPage.getPublishButton(), createEditNewsPage.getPreviewButton()),"Incorrect order between publishButton and previewButton");

        assertion.assertAll();
    }

}
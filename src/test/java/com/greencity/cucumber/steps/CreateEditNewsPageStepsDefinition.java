package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.component.ImageUploadComponent;
import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.data.Colors;
import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.page.homepage.HomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class CreateEditNewsPageStepsDefinition {

    private Hooks hooks;
    private HomePage homePage;
    private EcoNewsPage ecoNewsPage;
    private CreateEditNewsPage createEditNewsPage;
    private ImageUploadComponent imageUploadComponent;

    public CreateEditNewsPageStepsDefinition(Hooks hooks) {
        this.hooks = hooks;
    }

    @Given("The user is on the Home page as a logged-in user")
    public void theUserIsOnTheHomePageAsALoggedInUser() {
        homePage = new HomePage(hooks.getDriver());
    }

    @When("The user is on the Create News page as a logged-in user")
    public void theUserIsOnCreateNewsPageAsALoggedInUser() {
        createEditNewsPage = new HomePage(hooks.getDriver())
                .getHeader()
                .gotoEcoNewsPage()
                .clickCreateNewsButton();
    }

    @When("the user navigates to the GreenCity News page")
    public void theUserNavigatesToTheGreenCityNewsPage() {
        ecoNewsPage = new HomePage(hooks.getDriver()).getHeader().gotoEcoNewsPage();
    }

    @When("the user navigates to the Create News page")
    public void theUserNavigatesToTheCreateNewsPage() {
        ecoNewsPage.clickCreateNewsButton();
        createEditNewsPage = new CreateEditNewsPage(hooks.getDriver());
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

    @When("the user uploads the image from {string}")
    public void theUserUploadsTheImageFrom(String imagePath) {
        imageUploadComponent = createEditNewsPage.switchToImageUploadComponent();
        imageUploadComponent.uploadImage(hooks.getTestValueProvider().getFilePathByResources(imagePath));
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
                createEditNewsPage.clickExitButton();
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


        hooks.getSoftAssert().assertEquals(createEditNewsPage.getTitleText(), "Title","Title should be present in the 'Create News' page");
        hooks.getSoftAssert().assertTrue(createEditNewsPage.isPresentTitleInputTextField(),"Title input text field should be present in the 'Create News' page");
        hooks.getSoftAssert().assertTrue(createEditNewsPage.getTitleFieldCharacterCounterText().contains("0/170"), "Character count should be present in the 'Create News' page");
        hooks.getSoftAssert().assertTrue(createEditNewsPage.getOnlyThreeTagsCanBeAddedText().contains("Only 3 tags can be added"));
        hooks.getSoftAssert().assertEquals(createEditNewsPage.getListOfAllTagButtonsText(),createEditNewsPage.tagFilters,"All tags should be present in the 'Create News' page");
        hooks.getSoftAssert().assertTrue(createEditNewsPage.isAllSelectedTagsChangeAppearance(),"All tags should change an appearance after selecting");
        hooks.getSoftAssert().assertEquals(createEditNewsPage.getImageBrowseLinkText(),"browse","link for uploading an image should be present in the 'Create News' page");
        hooks.getSoftAssert().assertEquals(createEditNewsPage.getContentHeaderText(),"Content","Content header should be present");
        hooks.getSoftAssert().assertTrue(createEditNewsPage.isPresentContentInputTextField(),"Content input text field should be present in the 'Create News' page");
        hooks.getSoftAssert().assertTrue(createEditNewsPage.getContentDescriptionWarning().contains("Must be minimum 20 and maximum 63 206 symbols"),"Content warning should be present");
        hooks.getSoftAssert().assertEquals(createEditNewsPage.getAuthorLabelText(),hooks.getTestValueProvider().getUserName().toLowerCase(), "userName should be present in the 'Create News' page" );
        hooks.getSoftAssert().assertTrue(createEditNewsPage.isAuthorLabelNotEditable(), "Username label should not be editable");
        hooks.getSoftAssert().assertEquals(createEditNewsPage.getDataLabelFormating(Locale.ENGLISH), LocalDate.now(), "current date should be present");
        hooks.getSoftAssert().assertTrue(createEditNewsPage.isDataLabelNotEditable(),"Date label should not be editable");
        hooks.getSoftAssert().assertEquals(createEditNewsPage.getExternalSourceInputFieldTitle(),"Source (optional)", "Source (optional) should be present in the 'Create News' page");
        hooks.getSoftAssert().assertTrue(createEditNewsPage.getExternalSourceInputFieldInfoText().contains("Please add the link of original article/news/post. Link must start with http(s)://"),
                "Source info text should be present in the 'Create News' page");
        hooks.getSoftAssert().assertEquals(createEditNewsPage.getExternalSourceLinkInputFieldPlaceholderText(),"Link to external source",
                "Placeholder Source Field should be equals 'Link to external source'");
    }

    @Then("the form should contain the following buttons:")
    public void theFormShouldContainTheFollowingButtons(DataTable table) {
        List<String> buttons = table.asList();
        for(String button: buttons) {
            switch (button) {
                case "Cancel":
                    hooks.getSoftAssert().assertTrue(createEditNewsPage.isCancelButtonPresent(),"Cancel button should be present in the 'Create News' page");
                    break;
                case "Preview":
                    hooks.getSoftAssert().assertTrue(createEditNewsPage.isPreviewButtonPresent(),"Preview button should be present in the 'Create News' page");
                    break;
                case "Publish":
                    hooks.getSoftAssert().assertTrue(createEditNewsPage.isPublishButtonPresent(),"Publish button should be present in the 'Create News' page");
                    break;
                default:
                    hooks.getSoftAssert().fail("Unknown button: " + button);
            }
        }
    }

    @Then("the Author field should be pre-filled and non-editable")
    public void theFieldShouldBePreFilledAndNonEditable(String arg0) {

    }

    @Then("the Date field should be pre-filled with the current date and non-editable")
    public void theFieldShouldBePreFilledWithTheCurrentDateAndNonEditable(String arg0) {
    }

    @When("the user leaves the Title field empty")
    public void leaveTheFieldEmpty() {
        createEditNewsPage = new CreateEditNewsPage(hooks.getDriver())
                .clickTitleInputTextField()
                .clickTitleHeaderText();
    }

    @Then("the Title field's border is highlighted in red")
    public void theTitleFieldSBorderIsHighlightedInRed() {
        hooks.getSoftAssert().assertTrue(createEditNewsPage.getTitleInputFieldBorderColor().equals(Colors.RED.getColor()),
                "The border color should be red when the Title field is empty");
    }

    @Then("the Publish button is disabled")
    public void thePublishButtonIsDisabled() {
        hooks.getSoftAssert().assertFalse(createEditNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be disabled when all required fields are not filled out");
    }

    @Then("the character counter shows {string}")
    public void theCharacterCounterShows(String numberOfCharacters) {
        hooks.getSoftAssert().assertTrue(createEditNewsPage.getTitleFieldCharacterCounterText().equals(numberOfCharacters),
                "The number of characters should equal 0 when field is empty");
    }

    @When("the user enters {int} character-long string into the Title field")
    public void theUserEntersCharacterLongStringIntoTheTitleField(int numberOfCharacters) {
        createEditNewsPage.fillTitleInputTextField(titleCharacterProvider(171));
    }

    private String titleCharacterProvider(int numberOfCharacters) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < numberOfCharacters; i++) {
            stringBuilder.append("A");
        }
        return stringBuilder.toString();
    }

    @Then("the text is truncated to {int} characters")
    public void theTextIsTruncatedToCharacters(int numberOfCharacters) {
        hooks.getSoftAssert().assertTrue(createEditNewsPage.getTitleInputTextFieldValue().length() == numberOfCharacters,
                "The text should equal " + numberOfCharacters + " characters.");
    }

    @Then("the counter is highlighted in red when exceeding the limit")
    public void theCounterIsHighlightedInRedWhenExceedingTheLimit() {
        hooks.getSoftAssert().assertTrue(createEditNewsPage.getTitleFieldCharacterCounterWarningTextColor().equals(Colors.ERROR_RED.getColor()),
                "The counter text color should be red when the Title field exceeding the limit");
    }

    @When("the user enters {string} into TitleInputTextField")
    public void theUserEntersInto(String inputText) {
        createEditNewsPage.fillTitleInputTextField(inputText);
    }

    @Then("the Title field's border is grey")
    public void theTitleFieldSBorderIsGrey() {
        hooks.getSoftAssert().assertTrue(createEditNewsPage.getTitleInputFieldBorderColor().equals(Colors.QUINTYNARY_LIGHT_GREY.getColor()),
                "The border color should be grey and " + Colors.QUINTYNARY_LIGHT_GREY);
    }

    @When("the user clicks the {string} tag on the Create News page")
    public void theUserClicksTheTagOnTheCreateNewsPage(String tag) {
        createEditNewsPage.clickTagFilterButton(TagButton.NEWS);
        // switch/case will be added soon
    }

    @Then("the Publish button is enabled")
    public void thePublishButtonIsEnabled() {
        hooks.getSoftAssert().assertTrue(createEditNewsPage.getPublishButton().isEnabled(),
                "The Publish button should be enabled when all required fields are filled out");
    }

    @Then("all elements should be in correct order")
    public void allElementsShouldBeInCorrectOrder(){
        hooks.getSoftAssert().assertTrue(createEditNewsPage.isElementsOrderCorrect(createEditNewsPage.getTitleInputTextField(),createEditNewsPage.getFilterTagsRoot()),"Incorrect order between titleInputTextField and filterTagsRoot");
        hooks.getSoftAssert().assertTrue(createEditNewsPage.isElementsOrderCorrect(createEditNewsPage.getFilterTagsRoot(),createEditNewsPage.getExternalSourceLinkInputField()), "Incorrect order between filterTagsRoot and externalSourceLinkInputField");
        hooks.getSoftAssert().assertTrue(createEditNewsPage.isElementsOrderCorrect(createEditNewsPage.getImageBlockRoot(),createEditNewsPage.getExternalSourceLinkInputField()),"Incorrect order between imageBlockRoot and externalSourceLinkInputField");
        hooks.getSoftAssert().assertTrue(createEditNewsPage.isElementsOrderCorrect(createEditNewsPage.getExternalSourceLinkInputField(),createEditNewsPage.getTextAreaRoot()),"Incorrect order between externalSourceLinkInputField and textAreaRoot");
        hooks.getSoftAssert().assertTrue(createEditNewsPage.isElementsOrderCorrect(createEditNewsPage.getTextAreaRoot(),createEditNewsPage.getDataLabel()),"Incorrect order between dtaLabel and textAreaRoot");
        hooks.getSoftAssert().assertTrue(createEditNewsPage.areElementsOnSameLine(createEditNewsPage.getDataLabel(), createEditNewsPage.getAuthorLabel()),"Incorrect order between dtaLabel and authorLabel");
        hooks.getSoftAssert().assertTrue(createEditNewsPage.isElementsOrderCorrect(createEditNewsPage.getAuthorLabel(),createEditNewsPage.getExitButton()),"Incorrect order between exitButton and authorLabel");
        hooks.getSoftAssert().assertTrue(createEditNewsPage.areElementsOnSameLine(createEditNewsPage.getExitButton(), createEditNewsPage.getPreviewButton()),"Incorrect order between exitButton and previewButton");
        hooks.getSoftAssert().assertTrue(createEditNewsPage.areElementsOnSameLine(createEditNewsPage.getPublishButton(), createEditNewsPage.getPreviewButton()),"Incorrect order between publishButton and previewButton");
    }

    @Then("verify the image is not uploaded")
    public void verifyTheImageIsNotUploaded(){
        hooks.getSoftAssert().assertEquals(imageUploadComponent.getUploadFieldWarningText(),"The image isn't uploaded","'The image isn`t uploaded' warning should appears");
    }

    @Then("verify size validation message should present")
    public void verifySizeValidationMessageShouldPresent(){
        hooks.getSoftAssert().assertEquals(imageUploadComponent.getImageFormatWarningMessageText(),"Upload only PNG or JPG. File size must be less than 10MB","'Upload only PNG or JPG. File size must be less than 10MB' warning should appears");
    }

    @Then("the presentation window displayed on the uploaded image")
    public void thePresentationWindowDisplayedOnTheUploadedImage() {
        hooks.getSoftAssert().assertTrue(imageUploadComponent.getPresentationImageWindow().isDisplayed(),
                "The presentation window should be displayed on the uploaded image");
    }

    @When("the user clicks the Cancel button")
    public void theUserClicksTheCancelButton() {
        imageUploadComponent.clickCancelButton();
    }

    @Then("the image upload warning message: {string} should be displayed")
    public void theImageUploadWarningMessageShouldBeDisplayed(String warningMessage) {
        hooks.getSoftAssert().assertTrue(imageUploadComponent.getImageFormatWarningMessage().isEnabled(),
                "The image upload warning message should be displayed.");
        hooks.getSoftAssert().assertTrue(imageUploadComponent.getUploadFieldWarningText().equals(warningMessage),
                "The image isn't uploaded' message should be displayed");
    }

    @Then("the background color of the image dropzone field should be highlighted in red")
    public void theBackgroundColorOfTheImageDropzoneFieldShouldBeHighlightedInRed() {
        hooks.getSoftAssert().assertTrue(imageUploadComponent.getImageDropzoneFieldColor().equals(Colors.IMAGE_DROPZONE_WARNING_BACKGROUND.getColor()),
                "The background color of the dropzone field should be: " + Colors.IMAGE_DROPZONE_WARNING_BACKGROUND.getColor());
    }
}

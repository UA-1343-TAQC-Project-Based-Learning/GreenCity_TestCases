package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
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

import java.util.List;

public class CreateEditNewsPageStepsDefinition extends Steps {

    private HomePage homePage;
    private EcoNewsPage ecoNewsPage;
    private CreateEditNewsPage createEditNewsPage;

    public CreateEditNewsPageStepsDefinition(Hooks hooks) {
        super(hooks);
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
        createEditNewsPage.switchToImageUploadComponent().uploadImage(imagePath);
    }

    @When("the user clicks the {string} button on the Create News page")
    public void theUserClicksTheButtonOnTheCreateNewsPage(String buttonName) {
        switch (buttonName) {
            case "ImageSubmitButton":
                hooks.getDriver().findElement(By.xpath("//button[normalize-space()='Submit']")).click();
                break;
            case "ImageCancelButton":
                hooks.getDriver().findElement(By.xpath("//button[@class='secondary-global-button s-btn']")).click();
                break;
            case "Publish/EditButton":
                hooks.getDriver().findElement(By.xpath("//button[@class='primary-global-button']")).click();
                break;
            case "CancelButton":
                hooks.getDriver().findElement(By.xpath("//button[@class='tertiary-global-button']")).click();
                break;
            case "PreviewButton":
                hooks.getDriver().findElement(By.xpath("//button[@class='secondary-global-button']")).click();
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
        assertion.assertEquals(createEditNewsPage.getAuthorLabelText(),hooks.getTestValueProvider().getUserName().toLowerCase(), "userName should be present in the 'Create News' page" );
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

}

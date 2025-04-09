package com.greencity.cucumber.steps;

import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.page.homepage.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;

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

    @When("The user navigates to the Create News page")
    public void theUserNavigatesToTheCreateNewsPage() {
        newsPage.clickCreateNewsButton();
        createEditNewsPage = new CreateEditNewsPage(driver);
    }

    @When("The user types {string} in the Title field")
    public void theUserTypesInTheTitleField(String title) {
        createEditNewsPage.fillTitleInputTextField(title);
    }

    @When("The user types {string} in the News Content field")
    public void theUserTypesInTheNewsContentField(String content) {
        createEditNewsPage.fillContentInput(content);
    }

    @When("The user types {string} in the Source link field")
    public void theUserTypesInTheSourceLinkField(String url) {
        createEditNewsPage.fillSourceInput(url);
    }

    @When("The user selects the {tagButton} tag")
    public void theUserSelectsTheTag(TagButton tagButton) {
        createEditNewsPage.clickOnlyUnselectedTagFilterButton(tagButton);
    }

    @When("The user unselects the {tagButton} tag")
    public void theUserUnselectsTheTag(TagButton tagButton) {
        if (createEditNewsPage.isTagSelected(tagButton)) {
            createEditNewsPage.clickTagFilterButton(tagButton);
        }
    }

    @When("The user uploads the image from {string}")
    public void theUserUploadsTheImageFrom(String imagePath) {
        createEditNewsPage.switchToImageUploadComponent().uploadImage(imagePath);
    }

    @When("The user clicks the {string} button on the Create News page")
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


}
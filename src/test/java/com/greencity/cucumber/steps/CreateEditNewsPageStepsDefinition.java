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

    @Given("The CreateEditNewsPage is open for logged user")
    public void theUserIsRegisteredAndLoggedIntoTheGreenCitySystem() {
        initDriver();
        driver.get(testValueProvider.getBaseUIUrl());
        homePage = new HomePage(driver);
    }

    @When("User go to CreateEditNewsPage")
    public void theUserGoToCreateEditNewsPage() {
        newsPage.clickCreateNewsButton();
        createEditNewsPage = new CreateEditNewsPage(driver);
    }

    @When("Type {string} into TitleInputTextField")
    public void fillTitleInputTextField(String title) {
        createEditNewsPage.fillTitleInputTextField(title);
    }

    @When("Type {string} into TextContentField")
    public void fillTextContentField(String content) {
        createEditNewsPage.fillContentInput(content);
    }

    @When("Type {string} into SourceLinkInputField")
    public void fillExternalSourceLinkInputField(String url) {
        createEditNewsPage.fillSourceInput(url);
    }

    @When("Select {TagButton} TagFilterButton")
    public void selectTagFilterButton(TagButton tagButton) {
        createEditNewsPage.clickOnlyUnselectedTagFilterButton(tagButton);
    }

    @When("Unselect {TagButton} TagFilterButton")
    public void unselectTagFilterButton(TagButton tagButton) {
        if (createEditNewsPage.isTagSelected(tagButton)) {
            createEditNewsPage.clickTagFilterButton(tagButton);
        }
    }

    @When("Upload {string} Image")
    public void uploadImage(String imagePath) {
        createEditNewsPage.switchToImageUploadComponent().uploadImage(imagePath);
    }

    @When("Click {button} on the CreateEditNewsPage")
    public void clickButton(String buttonName) {
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

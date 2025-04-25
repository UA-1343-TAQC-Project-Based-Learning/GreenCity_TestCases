package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.page.econewspage.NewsCardPage;
import com.greencity.ui.page.homepage.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CheckingNotVisibilityEditButton {
    private Hooks hooks;
    private EcoNewsPage ecoNewsPage;
    private NewsCardPage newsCardPage;

    public CheckingNotVisibilityEditButton(Hooks hooks) {
        this.hooks = hooks;
    }
    @When("the user navigates to the Eco News page")
    public void theUserNavigatesToTheEcoNewsPage() {
        ecoNewsPage = new HomePage(hooks.getDriver()).getHeader().gotoEcoNewsPage();
    }
    @When("the user opens the news post with title {string}")
    public void theUserOpensTheNewsPostWithTitle(String title) {
        newsCardPage = ecoNewsPage.goToNewsCardPage(title);
    }

    @Then("the user should not see the Edit news button")
    public void theUserShouldNotSeeTheEditNewsButton() {
        boolean isButtonAbsent = newsCardPage.isEditButtonAbsent();
        Assert.assertTrue(isButtonAbsent, "FAIL: Edit button is not visible for not logged in users.");
    }
}

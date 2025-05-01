package com.greencity.ui;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.page.econewspage.NewsCardPage;
import com.greencity.ui.testrunners.TestRunnerForNotUser;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckingNotVisibilityEditButtonTest extends TestRunnerForNotUser {
    @Description(" Edit button not visible to other users ")
    @Owner("Khrystyna Martynova")
    @Severity(SeverityLevel.MINOR)
    @Epic("Edit News")
    @Issue("98")
    @Feature("Restrict editing of news posts by not logged-in users")
    @Test
    public void checkNotVisibilityEditButton(){
        EcoNewsPage ecoNewsPage = homePage.gotoEcoNewsPage();
        NewsCardPage newsCardPage = ecoNewsPage
                .goToNewsCardPage("My Test Eco News");
        Assert.assertTrue(newsCardPage.isEditButtonAbsent(),
                "Edit button should not be present for non-logged-in users.");

    }
}

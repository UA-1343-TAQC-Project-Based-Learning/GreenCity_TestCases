package com.greencity.ui;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.page.econewspage.NewsCardPage;
import com.greencity.ui.testrunners.TestRunnerForNotUser;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckingNotVisibilityEditButtonTest extends TestRunnerForNotUser {

    @Test
    public void checkNotVisibilityEditButton(){
        EcoNewsPage ecoNewsPage = homePage.gotoEcoNewsPage();
        NewsCardPage newsCardPage = ecoNewsPage
                .goToNewsCardPage("My Test Eco News");
        Assert.assertTrue(newsCardPage.isEditButtonAbsent(),
                "Edit button should not be present for non-logged-in users.");

    }
}

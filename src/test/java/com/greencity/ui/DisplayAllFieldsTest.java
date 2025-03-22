package com.greencity.ui;

import com.greencity.ui.page.econewspage.CreateNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DisplayAllFieldsTest extends BaseTestRunner {

    @Test
    public void checkOfDisplayingAllFieldsTest() {
//        CreateNewsPage newsPage = homePage.clickHeaderButton()
//                .successfulLogin(testValueProvider.getUserEmail(), testValueProvider.getUserPassword())
//                .gotoEcoNewsPage()
//                .clickCreateNewsButton();


        CreateNewsPage newsPage = homePage.gotoEcoNewsPage().clickCreateNewsButton();

        Assert.assertEquals(newsPage.getTitleText(),"Title");
        Assert.assertTrue(newsPage.getTitleFieldInfoText().contains("0/170"));
    }
}


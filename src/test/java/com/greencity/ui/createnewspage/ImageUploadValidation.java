package com.greencity.ui.createnewspage;

import com.greencity.ui.page.econewspage.CreateNewsPage;
import com.greencity.ui.page.homepage.HomePage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.annotations.Test;

public class ImageUploadValidation extends BaseTestRunner {

    @Test
    public void checkImageUploadTest() {
        CreateNewsPage createNewsPage = new HomePage(driver).gotoEcoNewsPage().clickCreateNewsButton();
        



    }
}

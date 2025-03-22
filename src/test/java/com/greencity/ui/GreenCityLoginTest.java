package com.greencity.ui;



import com.greencity.ui.testrunners.BaseTestRunner;
import com.greencity.data.TesterUser;
import com.greencity.data.TesterUserRepository;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class GreenCityLoginTest extends BaseTestRunner {
    @DataProvider(name = "provideTesterUsers")
    public Object[][] getData() {
        return new Object[][]{
                {TesterUserRepository.getValidUserSecret()},
        };
    }

    @Test(dataProvider = "provideTesterUsers")
    public void checkLogin(TesterUser testerUser) {
        logger.info("Start checkLogin() with testerUser = " + testerUser);
        //
        loadApplication();
        presentationSleep(); // For Presentation
        //
        greencityGuest.signIn(testerUser);
        presentationSleep(); // For Presentation
        //
        // get Username
        String actualUserName = greencityLogged.getUsername();
        String expectedUserName = testerUser.getUsername();
        presentationSleep(); // For Presentation ONLY
        //
        // Check
        Assert.assertEquals(expectedUserName, actualUserName);
        presentationSleep(); // For Presentation ONLY
        //
        System.out.println("\t\tTest testUi() executed");
    }
}
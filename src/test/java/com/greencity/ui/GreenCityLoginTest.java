package com.greencity.ui;


import com.greencity.data.TesterUser;
import com.greencity.data.TesterUserRepository;

import com.greencity.ui.modal.LoginModal;
import com.greencity.ui.page.homepage.HomePage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class GreenCityLoginTest extends BaseTestRunner {
    LoginModal loginModal;

    @DataProvider(name = "provideTesterUsers")
    public Object[][] getData() {
        return new Object[][]{{TesterUserRepository.getValidUserSecret()},};
    }

    @BeforeMethod
    public void goToLogin() {
        loginModal = homePage.getHeader().clickSignIn();

    }

    @AfterMethod
    public void afterMethod() {
        clearBrowserMemory();
    }

    @Test(dataProvider = "provideTesterUsers")
    public void checkLogin(TesterUser testerUser) {

        loginModal.setEmailInput(testerUser.getEmail());
        loginModal.setPasswordInput(testerUser.getPassword());
        loginModal.clickSignInButton();

        HomePage homePage = new HomePage(driver);


        String actualUserName = homePage.getLoggedHeader().getUserName().getText();
        String expectedUserName = testerUser.getUsername();

        Assert.assertEquals(expectedUserName, actualUserName);
        presentationSleep(); // For Presentation ONLY
    }

}
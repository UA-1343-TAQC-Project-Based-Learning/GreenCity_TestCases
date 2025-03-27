package com.greencity.ui.testrunners;

import com.greencity.data.LoginDto;
import com.greencity.data.TesterUser;
import com.greencity.utils.GreenCityPost;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class TestRunnerWithAdmin extends BaseTestRunner {
    protected LoginDto loginDto;

    @BeforeClass
    public void initloginDto() {
        TesterUser admin = new TesterUser(
                testValueProvider.getAdminEmail(),
                testValueProvider.getUserPassword(),
                testValueProvider.getAPIUrlLoginSecretKey(),
                testValueProvider.getAdminName(),
                testValueProvider.getAPIUrlLogin());
        loginDto = GreenCityPost.login(admin);
    }

    @BeforeMethod
    public void login() {
        setLocalStorage(loginDto);
    }

    @AfterMethod
    public void tearThis(ITestContext testContext) {
        clearBrowserMemory();
    }
}

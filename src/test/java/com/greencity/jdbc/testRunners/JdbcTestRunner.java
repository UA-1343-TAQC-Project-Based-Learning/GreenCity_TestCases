package com.greencity.jdbc.testRunners;

import com.greencity.utils.TestValueProvider;
import com.greencity.utils.jdbc.dao.ManagerDAO;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;


public class JdbcTestRunner {
    protected static TestValueProvider testValueProvider;

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        testValueProvider = new TestValueProvider();
    }

    @AfterClass
    public void cleanup() {
        ManagerDAO.closeAllStatements();
    }

}
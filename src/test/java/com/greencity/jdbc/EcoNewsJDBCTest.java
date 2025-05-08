package com.greencity.jdbc;

import com.greencity.jdbc.testRunners.JdbcTestRunner;
import com.greencity.utils.EcoNewsTestUtils;
import com.greencity.utils.jdbc.entity.EcoNewsEntity;
import com.greencity.utils.jdbc.services.EcoNewsService;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import jdk.jfr.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Description("Verify that JDBC methods works.")
@Epic("CRUD for EcoNews JDBC")
@Owner("Maksym Ahafonov")
public class EcoNewsJDBCTest extends JdbcTestRunner {
    private static final Logger log = LoggerFactory.getLogger(EcoNewsJDBCTest.class);
    private static final String AUTOTEST_PREFIX = "EcoNewsJDBCTest_";
    private static EcoNewsService service;

    private static EcoNewsEntity ecoNewsEntity;

    private SoftAssert softAssert;
    private EcoNewsEntity testNews;

    @BeforeSuite
    public void initServices() {
        service = new EcoNewsService(
                testValueProvider.getJDBCGreenCityURL(),
                testValueProvider.getJDBCGreenCityUsername(),
                testValueProvider.getJDBCGreenCityPassword()
        );

        ecoNewsEntity = EcoNewsTestUtils.createTestNewsEntity(AUTOTEST_PREFIX + System.currentTimeMillis());
    }

    @BeforeMethod
    public void prepareTestData() {
        testNews = service.createNews(ecoNewsEntity);
        softAssert = new SoftAssert();
    }

    @AfterMethod
    public void cleanUpTestData() {
        try {
            if (testNews != null && testNews.getId() != null) {
                service.deleteNewsById(testNews.getId());
            }
        } catch (Exception e) {
            log.error("Cleanup error", e);
        } finally {
            softAssert.assertAll();
        }
    }

    @AfterClass
    public void cleanup() {
        super.cleanup();
        service.deleteAllNewsByTitle(AUTOTEST_PREFIX + "%");
    }


    @Test
    public void testCreateNews() {
        softAssert.assertNotNull(testNews.getId(), "News should have ID after creation");
        softAssert.assertEquals(testNews.getTitle(), ecoNewsEntity.getTitle(), "Titles should match");
    }

    @Test(dependsOnMethods = "testCreateNews")
    public void testReadNews() {
        EcoNewsEntity found = service.findNewsById(testNews.getId())
                .orElse(null);

        softAssert.assertNotNull(found, "Persistent news not found");
        softAssert.assertEquals(found.getTitle(), testNews.getTitle(), "Titles should match");
    }

    @Test
    public void testNonIdempotentCreate() {
        String uniqueTitle = "TestNews_" + System.currentTimeMillis();
        EcoNewsEntity newNewsEntity = EcoNewsTestUtils.createTestNewsEntity(uniqueTitle);

        EcoNewsEntity createdNews = service.createNews(newNewsEntity);
        softAssert.assertNotNull(createdNews.getId(), "News should have ID after creation");

        Optional<EcoNewsEntity> foundNewsOpt = service.findNewsById(createdNews.getId());
        softAssert.assertTrue(foundNewsOpt.isPresent(), "Created news should be found");

        foundNewsOpt.ifPresent(foundNews -> {
            softAssert.assertEquals(createdNews.getId(), foundNews.getId(), "IDs should match");
            softAssert.assertEquals(uniqueTitle, foundNews.getTitle(), "Titles should match");
        });

        softAssert.assertAll();

        try {
            service.deleteNewsById(createdNews.getId());
            service.deleteAllNewsByTitle(uniqueTitle);
        } catch (Exception e) {
            log.error("Cleanup failed: {}", e.getMessage(), e);
        }
    }

    @Test
    public void testDeleteNews() {
        service.deleteNewsById(testNews.getId());

        Optional<EcoNewsEntity> deleted = service.findNewsById(testNews.getId());

        softAssert.assertFalse(deleted.isPresent(), "News should be deleted");
    }


    @Test
    public void testIdempotentDelete() {

        service.deleteNewsById(testNews.getId());
        Optional<EcoNewsEntity> deleted = service.findNewsById(testNews.getId());
        softAssert.assertFalse(deleted.isPresent(), "News should remain deleted");

        service.deleteNewsById(testNews.getId());
    }


    @Test
    public void testFindByTitle() {
        EcoNewsEntity testNewsEntity1 = EcoNewsTestUtils.createTestNewsEntity(AUTOTEST_PREFIX + "1");
        EcoNewsEntity testNewsEntity2 = EcoNewsTestUtils.createTestNewsEntity(AUTOTEST_PREFIX + "2");

        EcoNewsEntity testNews1 = service.createNews(testNewsEntity1);
        EcoNewsEntity testNews2 = service.createNews(testNewsEntity2);

        List<EcoNewsEntity> foundNews = service.findNewsByTitle(AUTOTEST_PREFIX);

        Set<Long> expectedIds = Stream.of(testNews1, testNews2, testNews)
                .map(EcoNewsEntity::getId)
                .collect(Collectors.toSet());

        Set<Long> foundIds = foundNews.stream()
                .map(EcoNewsEntity::getId)
                .collect(Collectors.toSet());

        softAssert.assertTrue(foundIds.containsAll(expectedIds),
                "All created news must be found");

        softAssert.assertTrue(
                foundNews.stream().allMatch(n -> n.getTitle().startsWith(AUTOTEST_PREFIX)),
                "All news found must match the prefix"
        );

        softAssert.assertAll();

        try {
            service.deleteNewsById(testNews1.getId());
            service.deleteNewsById(testNews2.getId());
        } catch (Exception e) {
            log.error("Cleanup failed: {}", e.getMessage(), e);
        }

    }

}
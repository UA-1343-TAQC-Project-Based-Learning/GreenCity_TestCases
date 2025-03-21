package com.greencity.ui;

import com.greencity.ui.component.ecoNewsTag.TagButton;
import com.greencity.ui.page.econewspage.CreateNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.annotations.Test;

public class CheckingTagSelectionTest extends BaseTestRunner {


    //1
    @Test
    public void verifyOneTagSelection() {
        // Preconditions: The user is logged into the system. The "Create News" form is open.
        CreateNewsPage createNewsPage = homePage.clickHeaderButton()
                .successfulLogin(testValueProvider.getUserEmail(), testValueProvider.getUserPassword())
                .gotoEcoNewsPage()
                .clickCreateNewsButton();

        // Fill in the required fields
        createNewsPage.clickTitleInputTextField();
        createNewsPage.fillTitleInputTextField("test verifyTagSelection");
        createNewsPage.fillExternalSourceLinkInputField("123456789012345678901");

        // Select one tag
        createNewsPage.getEcoNewsTagFilterComponent().clickTagButton(TagButton.NEWS);

        // Verify that the "Publish" button is clickable


        // Click "Publish"


        // Verify that the news is published with the "News" tag
    }

    // 2
    @Test
    public void verifyThreeTagSelection() {

        // Preconditions: The user is logged into the system. The "Create News" form is open.

        CreateNewsPage createNewsPage = homePage.clickHeaderButton()
                .successfulLogin(testValueProvider.getUserEmail(), testValueProvider.getUserPassword())
                .gotoEcoNewsPage()
                .clickCreateNewsButton();

        // Fill in the required fields:
        // Title
        createNewsPage.clickTitleInputTextField();
        createNewsPage.fillTitleInputTextField("test verifyTagSelection");
        // Main Text: "Test content with 20 chars"
        createNewsPage.fillExternalSourceLinkInputField("123456789012345678901");

        // Select three tags: "News", "Events", "Education".
        createNewsPage.getEcoNewsTagFilterComponent().clickTagButton(TagButton.NEWS);
        createNewsPage.getEcoNewsTagFilterComponent().clickTagButton(TagButton.EVENTS);
        createNewsPage.getEcoNewsTagFilterComponent().clickTagButton(TagButton.EDUCATION);

        //Verify that that button "Publish" is clickable
        //Click "Publish".

        //Verify that the news is published with all three selected tags.
    }

    //3
    @Test
    public void verifyCantSelectFourTag() {
        // Preconditions: The user is logged into the system. The "Create News" form is open.

        CreateNewsPage createNewsPage = homePage.clickHeaderButton()
                .successfulLogin(testValueProvider.getUserEmail(), testValueProvider.getUserPassword())
                .gotoEcoNewsPage()
                .clickCreateNewsButton();

        // Fill in the required fields:
        // Title
        createNewsPage.clickTitleInputTextField();
        createNewsPage.fillTitleInputTextField("test verifyTagSelection");
        // Main Text: "Test content with 20 chars"
        createNewsPage.fillExternalSourceLinkInputField("123456789012345678901");

        // Select four tags: "News", "Events", "Education", "Initiatives"
        createNewsPage.getEcoNewsTagFilterComponent().clickTagButton(TagButton.NEWS);
        createNewsPage.getEcoNewsTagFilterComponent().clickTagButton(TagButton.EVENTS);
        createNewsPage.getEcoNewsTagFilterComponent().clickTagButton(TagButton.EDUCATION);

        // Attempt to select a fourth tag ("Initiatives").

        createNewsPage.getEcoNewsTagFilterComponent().clickTagButton(TagButton.INITIATIVES);

        //Verify that selecting a fourth tag is blocked.

        //Verify that that button "Publish" is not clickable (disabled)


    }
}









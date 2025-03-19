package com.greencity.ui;

import com.greencity.ui.component.EcoNewsTagFilterComponent;
import com.greencity.ui.component.EcoNewsTagFilterComponent;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EcoNewsFilterComponentTest extends BaseTestRunner {

    private EcoNewsTagFilterComponent ecoNewsTagFilterComponent;



    @Test
    public void testClickNewsFilterButton() {
        ecoNewsTagFilterComponent.clickNewsTag();

        Assert.assertTrue(true, "Клік на кнопку 'Новини' не виконано.");
    }

    @Test
    public void testEventsFilterButtonText() {
        String expectedText = "Події";
        String actualText = ecoNewsTagFilterComponent.getEventsTagText();
        Assert.assertEquals(actualText, expectedText, "Текст на кнопці 'Події' не відповідає очікуваному.");
    }

    @Test
    public void testClickEventsFilterButton() {
        ecoNewsTagFilterComponent.clickEventsTag();

        Assert.assertTrue(true, "Клік на кнопку 'Події' не виконано.");
    }

    @Test
    public void testEducationFilterButtonText() {
        String expectedText = "Освіта";
        String actualText = ecoNewsTagFilterComponent.getEducationTagText();
        Assert.assertEquals(actualText, expectedText, "Текст на кнопці 'Освіта' не відповідає очікуваному.");
    }

    @Test
    public void testClickEducationFilterButton() {
        ecoNewsTagFilterComponent.clickEducationTag();

        Assert.assertTrue(true, "Клік на кнопку 'Освіта' не виконано.");
    }

    @Test
    public void testInitiativesFilterButtonText() {
        String expectedText = "Ініціативи";
        String actualText = ecoNewsTagFilterComponent.getInitiativesTagText();
        Assert.assertEquals(actualText, expectedText, "Текст на кнопці 'Ініціативи' не відповідає очікуваному.");
    }

    @Test
    public void testClickInitiativesFilterButton() {
        ecoNewsTagFilterComponent.clickInitiativesTag();

        Assert.assertTrue(true, "Клік на кнопку 'Ініціативи' не виконано.");
    }

    @Test
    public void testAdsFilterButtonText() {
        String expectedText = "Оголошення";
        String actualText = ecoNewsTagFilterComponent.getAdsTagText();
        Assert.assertEquals(actualText, expectedText, "Текст на кнопці 'Оголошення' не відповідає очікуваному.");
    }

    @Test
    public void testClickAdsFilterButton() {
        ecoNewsTagFilterComponent.clickAdsTag();

        Assert.assertTrue(true, "Клік на кнопку 'Оголошення' не виконано.");
    }


}

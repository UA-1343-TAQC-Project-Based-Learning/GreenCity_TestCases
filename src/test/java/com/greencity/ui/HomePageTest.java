package com.greencity.ui;

import com.greencity.ui.page.EcoNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class HomePageTest extends BaseTestRunner {


    // go to EcoNewsPage via arrow
    @Test
    public void testOpenEcoNewsPageByEventsArrowLink() {
        EcoNewsPage ecoNewsPage = homePage.openEcoNewsPageByEventsArrowLink();
        assertEquals("Еко новини", ecoNewsPage.getHeaderText());
    }

    // go to EcoNewsPage via link
    @Test
    public void testOpenEcoNewsPageByEventsLink() {
        assertEquals("Читати всі новини", homePage.getEventsLinkText());
        EcoNewsPage ecoNewsPage = homePage.openEcoNewsPageByEventsLink();
        assertEquals("Еко новини", ecoNewsPage.getHeaderText());
    }




    // Main-header
    @Test
    public void testHeaderTitle() {
        assertEquals("Новий спосіб виховати в собі корисні звички", homePage.getHeaderTitle());
    }

    @Test
    public void testHeaderParagraph() {
        assertEquals("В Україні кожна людина щорічно використовує більше 500 одноразових пакетів і біля 70 паперових стаканчиків. Достатньо брати з собою еко-сумку і своє горнятко і це допоможе зберегти життя 3-х дельфінів, однієї черепашки і зберегти чистими води океанів для наступного покоління.",
                homePage.getHeaderParagraph());
    }

    @Test
    public void testHeaderButton() {
        assertEquals("Почати формувати звичку!", homePage.getHeaderButtonText());
//        homePage.clickHeaderButton();
        // TODO
    }
    @Test
    public void testGuyImageDisplayed() {
        assertTrue(homePage.isGuyImageDisplayed());
    }



    // Stat-section
    @Test
    public void testStatHeaderText() {
        assertEquals("Нас 5 і сьогодні ми", homePage.getStatHeaderText());
    }

    @Test
    public void testBagImageDisplayed() {
        assertTrue(homePage.isBagImageDisplayed());
    }

    @Test
    public void testCupImageDisplayed() {
        assertTrue(homePage.isCupImageDisplayed());
    }

    // margin-right
    @Test
    public void testStatRightHeaderBagsText() {
        assertEquals("Не взяли 0 пакетів", homePage.getStatRightHeaderBagsText());
    }

    @Test
    public void testStatRightParagraphBagsText() {
        assertEquals("А скільки пакетів ти не взяв сьогодні?", homePage.getStatRightParagraphBagsText());
    }

    @Test
    public void testStatRightSpanBagsText() {
        assertEquals("0", homePage.getStatRightSpanBagsText());
    }

    @Test
    public void testStatRightButton() {
        assertEquals("Почати формувати звичку!", homePage.getStatRightButtonText());
//        homePage.clickStatRightButton();
        // TODO
    }

    @Test
    public void testStatRightIconDisplayed() {
        assertTrue(homePage.isStatRightIconDisplayed());
    }

    @Test
    public void testStatRightLink() {
        assertEquals("тут можна купити еко-сумочки і торбинки", homePage.getStatRightLinkText());
//        homePage.clickStatRightLink();
        // TODO
    }

    //margin-left
    @Test
    public void testStatLeftHeaderCupsText() {
        assertEquals("Не викинули 0 склянок", homePage.getStatLeftHeaderCupsText());
    }

    @Test
    public void testStatLeftParagraphCupsText() {
        assertEquals("А скільки склянок не викинув сьогодні ти?", homePage.getStatLeftParagraphCupsText());
    }

    @Test
    public void testStatLeftSpanCupsText() {
        assertEquals("0", homePage.getStatLeftSpanCupsText());
    }

    @Test
    public void testStatLeftButton() {
        assertEquals("Почати формувати звичку!", homePage.getStatLeftButtonText());
//        homePage.clickStatLeftButton();
        // TODO
    }

    @Test
    public void testStatLeftIconDisplayed() {
        assertTrue(homePage.isStatLeftIconDisplayed());
    }

    @Test
    public void testStatLeftLink() {
        assertEquals("заклади, які роблять знижку на напій в своє горнятко", homePage.getStatLeftLinkText());
//        homePage.clickStatLeftLink();
        // TODO
    }

    // Event-section
    @Test
    public void testEventsHeaderText() {
        assertEquals("Еко новини", homePage.getEventsHeaderText());
    }



    // Subscription-section
    @Test
    public void testSubscriptionHeaderText() {
        assertEquals("Отримувати цікаві новини", homePage.getSubscriptionHeaderText());
    }

    @Test
    public void testSubscriptionParagraphText() {
        assertEquals("Підпишіться на розсилку та завжди будьте в курсі новин та оновлень",
                homePage.getSubscriptionParagraphText());
    }

    @Test
    public void testSubscriptionImgQrCodeDisplayed() {
        assertTrue(homePage.isSubscriptionImgQrCodeDisplayed());
    }

    @Test
    public void testSubscriptionInputEmail() {
        homePage.enterSubscriptionEmail("test@example.com");
//        homePage.clickSubscriptionButton();
        //TODO
    }

    @Test
    public void testSubscriptionErrorText() {
        homePage.enterSubscriptionEmail("123");
        assertEquals("Некоректна електронна пошта", homePage.getSubscriptionErrorText());
    }

    @Test
    public void testSubscriptionButton() {
        assertEquals("Підписатися!", homePage.getSubscriptionButtonText());
//        homePage.clickSubscriptionButton();
        // TODO
    }


}

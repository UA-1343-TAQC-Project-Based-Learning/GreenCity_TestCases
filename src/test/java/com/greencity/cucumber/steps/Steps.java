package com.greencity.cucumber.steps;

import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.page.homepage.HomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class Steps extends BaseStep{

    @When("click the {string} button")
    public void clickBtnByName(String btnName){
        driver.findElement(By.xpath("//span[contains(text(),'" + btnName + "')]")).click();
    }


}

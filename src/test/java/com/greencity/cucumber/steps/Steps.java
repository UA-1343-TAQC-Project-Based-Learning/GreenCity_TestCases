package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

public class Steps {

    protected Hooks hooks;

    public Steps(Hooks hooks) {
        this.hooks = hooks;
    }

    @When("click the {string} button")
    public void clickBtnByName(String btnName){
        hooks.getDriver().findElement(By.xpath("//span[contains(text(),'" + btnName + "')]")).click();
    }

}

package com.greencity.ui.component.header;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DropdownComponent {

    protected final String OPTION_NAME_NOT_FOUND = "OptionName not Found.";
    protected WebDriver driver;
    private List<WebElement> listOptions;
    //


    public DropdownComponent(WebDriver driver, By searchLocator) {
        this.driver = driver;
        initElements(searchLocator);
    }

    private void initElements(By searchLocator) {
        listOptions = driver.findElements(searchLocator);
    }


    // listOptions
    public List<WebElement> getListOptions() {
        return listOptions;
    }

    // Functional

    // listOptions
    public WebElement getDropdownOptionByPartialName(String optionName) {
        WebElement result = null;
        for (WebElement current : getListOptions()) {
            if (current.getText().toLowerCase().contains(optionName.toLowerCase())) {
                result = current;
                break;
            }
        }
        return result;
    }

    public List<String> getListOptionsText() {
        List<String> result = new ArrayList<>();
        for (WebElement current : getListOptions()) {
            result.add(current.getText());
        }
        return result;
    }

    public boolean isExistDropdownOptionByPartialName(String optionName) {
        boolean isFound = false;
        for (String current : getListOptionsText()) {
            if (current.toLowerCase().contains(optionName.toLowerCase())) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    @Step("Click on the Dropdown item: {optionName}")
    public void clickDropdownOptionByPartialName(String optionName) {
        getDropdownOptionByPartialName(optionName).click();
    }

    // Business Logic

}

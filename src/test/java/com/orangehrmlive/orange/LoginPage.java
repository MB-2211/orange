package com.orangehrmlive.orange;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://opensource-demo.orangehrmlive.com/
public final class LoginPage {
    @FindBy(css = "input[name='username']")
    private WebElement username;

    @FindBy(css = "input[name='password']")
    private WebElement password;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void with(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);

        loginButton.click();
    }

    public boolean areElementsPresent() {
        return username.isDisplayed() && password.isDisplayed() && loginButton.isDisplayed();
    }
}

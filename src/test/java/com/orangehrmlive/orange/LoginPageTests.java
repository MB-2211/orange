package com.orangehrmlive.orange;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LoginPageTests {
    private WebDriver driver;
    private LoginPage page;

    private final static String username = "Admin";
    private final static String password = "admin123";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/");

        page = new LoginPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testPage() {
        String url = driver.getCurrentUrl();

        assertNotNull(url);
        assertTrue(url.endsWith("/login"));

        assertTrue(page.areElementsPresent());
    }

    @Test
    public void testLogin() {
        page.with(username, password);

        String newUrl = driver.getCurrentUrl();

        assertNotNull(newUrl);
        assertTrue(newUrl.endsWith("dashboard/index"));
    }

    @Test
    public void testLoginWithInvalidPassword() {
        page.with(username, "invalid" + password);

        String error = driver.findElement(By.className("orangehrm-login-error")).getText();
        assertTrue(error.contains("Invalid credentials"));
    }
}

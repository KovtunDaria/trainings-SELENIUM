package com.mera.lesson1;

import org.junit.Test;
import org.openqa.selenium.By;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class FirstTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void myFirstTest() {
        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        wait.until(driver -> driver.findElement(By.name("btnK"))).submit();
        wait.until(titleIs("webdriver - \u041f\u043e\u0438\u0441\u043a\u0020\u0432 Google"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}

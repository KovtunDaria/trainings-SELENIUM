package com.mera.lesson11.Task19;

import org.checkerframework.checker.units.qual.C;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Task19 {
    private static final String MAIN_PAGE_XPATH = "http://localhost/litecart/";
    private static final String CARDS_XPATH = "//h4[contains(@class, 'name')]";
    private WebDriver webDriver;
    private WebDriverWait wait;


    @Before
    public void start() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @Test
    public void startTests() {
        MainPage mainPage = new MainPage(MAIN_PAGE_XPATH, webDriver, wait);
        mainPage.visitThisPage();
        ProductPage productPage = mainPage.findFirstProductPage(CARDS_XPATH);
        for (int i = 0; i < 3; i++) {
            productPage.visitPage();
            productPage.addProducts();
            mainPage.visitThisPage();
        }
        CartPage cartPage = new CartPage(webDriver, wait);
        cartPage.deleteAddedProducts();
    }

    @After
    public void stop() {
        webDriver.quit();
        webDriver = null;
    }
}

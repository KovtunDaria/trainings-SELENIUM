package com.mera.lesson6;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class Task12 {
    private static final String PRODUCT_NAME = "wwwlwww";
    private static final String PRODUCT_SHORT_DESCRIPTION = "wwlww";
    private static final String PRODUCT_PRICE = "1";

    public WebDriver webDriver;
    public WebDriverWait wait;


    @Before
    public void start() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @Test
    public void startAllTests() {
        adminPanelLogin();
        goToNewProductAddMenu();
        addNewProduct();
        checkAvailability();
    }

    private void adminPanelLogin() {
        webDriver.get("http://localhost/litecart/admin/login.php");
        webDriver.findElement(By.name("username")).sendKeys("admin");
        webDriver.findElement(By.name("password")).sendKeys("admin");
        webDriver.findElement(By.name("login")).submit();
    }

    private void goToNewProductAddMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@data-code, 'catalog')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Add New Product')]"))).click();
    }

    private void addNewProduct() {
        webDriver.findElement(By.xpath("//label[contains(text(), 'Enabled')]")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@name, 'name[en]')]"))).sendKeys(PRODUCT_NAME);

        webDriver.findElement(By.xpath("//a[contains(text(), 'Information')]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@name, 'short_description[en]')]"))).sendKeys(PRODUCT_SHORT_DESCRIPTION);

        webDriver.findElement(By.xpath("//a[contains(text(), 'Prices')]")).click();
        webDriver.findElement(By.xpath("//input[contains(@name, 'purchase_price')]")).sendKeys(PRODUCT_PRICE);

        webDriver.findElement(By.xpath("//button[contains(@name, 'save')]")).click();
    }

    private void checkAvailability() {
        webDriver.findElement(By.xpath(String.format("//tr//td//a[contains(text(), '%s')]", PRODUCT_NAME)));
    }

    @After
    public void stop() {
        webDriver.quit();
        webDriver = null;
    }
}

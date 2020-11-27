package com.mera.lesson7;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Task13 {
    public WebDriver webDriver;
    public WebDriverWait wait;
    private String nextQuantityProduct;
    @Before
    public void start() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @Test
    public void startTests() {
        addProducts();
        addProducts();
        addProducts();
        deleteAddedProducts();
    }

    private void addProducts() {
        webDriver.get("http://localhost/litecart/");

        webDriver.findElement(By.xpath("//h4[contains(@class, 'name')]")).click();
        String quantity = webDriver.findElement(By.xpath("//div[contains(@class, 'badge quantity')]")).getText();
        if (quantity.equals("")) {
            nextQuantityProduct = "1";
        } else {
            nextQuantityProduct = Integer.toString(Integer.parseInt(nextQuantityProduct) + 1);
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@name, 'add_cart_product')]"))).click();
        wait.until(webDriver -> webDriver.findElement(By.xpath("//div[contains(@class, 'badge quantity')]")).getText().equals(nextQuantityProduct));
    }

    private void deleteAddedProducts() {
        webDriver.findElement(By.xpath("//div[contains(@id, 'cart')]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[contains(@class, 'fa-trash')]"))).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//h2[contains(text(), 'Order Summary')]")));
        webDriver.findElement(By.xpath("//a[contains(text(), 'Back')]")).click();
    }

    @After
    public void stop() {
        webDriver.quit();
        webDriver = null;
    }

}

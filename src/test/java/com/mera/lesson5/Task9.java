package com.mera.lesson5;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Task9 {
    public WebDriver chromeDriver;
    public WebDriverWait wait;

    @Before
    public void start() {
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
    }

    @Test
    public void checkSorting() {
        chromeDriver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        chromeDriver.findElement(By.name("username")).sendKeys("admin");
        chromeDriver.findElement(By.name("password")).sendKeys("admin");
        chromeDriver.findElement(By.xpath("//button[contains(@name, 'login')]")).submit();

        List<String> countries = wait.until(chromeDriver -> chromeDriver.findElements(By.xpath("//td/a[not(contains(@title, 'Edit'))]")))
                .stream()
                .map(webElement -> webElement.getText())
                .collect(Collectors.toList());
        Assert.assertNotEquals(0, countries.size());
        List<String> sortedCountries = countries.stream()
                .sorted()
                .collect(Collectors.toList());
        Assert.assertEquals(sortedCountries, countries);
    }

    @After
    public void stop() {
        chromeDriver.quit();
        chromeDriver = null;
    }
}

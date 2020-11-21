package com.mera.lesson3;

import org.junit.Test;
import org.openqa.selenium.By;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Task6 {
    public WebDriver firefoxDriver;
    public WebDriverWait wait;

    @Before
    public void start() {
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Program Files\\Firefox Nightly\\firefox.exe");
        firefoxDriver = new FirefoxDriver(options);
        System.out.println(((HasCapabilities) firefoxDriver).getCapabilities());
        wait = new WebDriverWait(firefoxDriver, Duration.ofSeconds(10));
    }

    @Test
    public void firefoxLogin() {
        firefoxDriver.get("http://localhost/litecart/");
        firefoxDriver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul[2]/li[2]/a")).click();
        firefoxDriver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul[2]/li[2]/ul/li[1]/form/div[1]/div/input")).sendKeys("admin@ru.ru");
        firefoxDriver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul[2]/li[2]/ul/li[1]/form/div[2]/div/input")).sendKeys("admin");
        firefoxDriver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul[2]/li[2]/ul/li[1]/form/div[4]/button")).click();
    }


    @After
    public void stop() {
        firefoxDriver.quit();
        firefoxDriver = null;
    }

}
package com.mera.lesson3;

import org.junit.Test;
import org.openqa.selenium.By;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Task4_1 {
    public WebDriver chromeDriver;
    public WebDriverWait wait;

    @Before
    public void start() {
        chromeDriver = new ChromeDriver();
        wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
    }

    @Test
    public void chromeLogin() {
        chromeDriver.get("http://localhost/litecart/");
        chromeDriver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul[2]/li[2]/a")).click();
        chromeDriver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul[2]/li[2]/ul/li[1]/form/div[1]/div/input")).sendKeys("admin@ru.ru");
        chromeDriver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul[2]/li[2]/ul/li[1]/form/div[2]/div/input")).sendKeys("admin");
        chromeDriver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul[2]/li[2]/ul/li[1]/form/div[4]/button")).click();
    }

    @After
    public void stop() {
        chromeDriver.quit();
        chromeDriver = null;
    }

}
package com.mera.lesson2;

import org.junit.Test;
import org.openqa.selenium.By;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class Task3 {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void login() {
        driver.get("http://localhost/litecart/");
        driver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul[2]/li[2]/a")).click();
        driver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul[2]/li[2]/ul/li[1]/form/div[1]/div/input")).sendKeys("admin@ru.ru");
        driver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul[2]/li[2]/ul/li[1]/form/div[2]/div/input")).sendKeys("admin");
        driver.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul[2]/li[2]/ul/li[1]/form/div[4]/button")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
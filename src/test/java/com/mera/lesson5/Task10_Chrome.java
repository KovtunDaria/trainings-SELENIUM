package com.mera.lesson5;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Task10_Chrome extends Task10 {

    @Override
    protected void initDriver() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
    }


}
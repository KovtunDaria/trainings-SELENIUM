package com.mera.lesson5;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Task10_Firefox extends Task10 {
    @Override
    protected void initDriver() {
        webDriver = new FirefoxDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }
}

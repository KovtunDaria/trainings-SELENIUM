package com.mera.lesson4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task8 {
    public WebDriver chromeDriver;
    public WebDriverWait wait;

    @Before
    public void start() {
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
    }

    @Test
    public void chromeLogin() {
        chromeDriver.get("http://localhost/litecart/");
        checkStickers();
    }

    public void checkStickers() {
        List<WebElement> cards = chromeDriver.findElements(By.xpath("//article[contains(@class, 'product-column')]"));
        for (WebElement element : cards) {
            List<WebElement> stickers = element.findElements(By.xpath("./a/div[contains(@class, 'image-wrapper')]/div[contains(@class, 'sticker')]"));
            Assert.assertEquals(1, stickers.size());
        }
    }

    @After
    public void stop() {
        chromeDriver.quit();
        chromeDriver = null;
    }
}

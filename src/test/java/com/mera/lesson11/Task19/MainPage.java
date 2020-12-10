package com.mera.lesson11.Task19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final String url;
    public WebDriver webDriver;
    public WebDriverWait wait;

    public MainPage(String url, WebDriver webDriver, WebDriverWait wait) {
        this.url = url;
        this.webDriver = webDriver;
        this.wait = wait;
    }

    public void visitThisPage() {
        webDriver.get(url);

    }

    public ProductPage findFirstProductPage(String pageXpath) {
//        return webDriver.findElement(By.xpath("//h4[contains(@class, 'name')]")).click();
        return new ProductPage(webDriver, wait, pageXpath);
    }

}

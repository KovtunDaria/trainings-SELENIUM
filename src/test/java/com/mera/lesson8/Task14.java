package com.mera.lesson8;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Task14 {
    public WebDriver webDriver;
    public WebDriverWait wait;

    private static final String ALAND_ISLAND_XPATH = "//a[contains(text(), 'Aland Islands')]";
    private static final String NUMBER_LINK_XPATH = "//label[contains(text(), 'Number (ISO 3166-1 numeric)')]//a/i";
    private static final String CODE_ALPHA_2_LINK_XPATH = "//label[contains(text(), 'Code (ISO 3166-1 alpha-2')]//a/i";
    private static final String CODE_ALPHA_3_LINK_XPATH = "//label[contains(text(), 'Code (ISO 3166-1 alpha-3')]//a/i";
    private static final String ADDRESS_FORMAT = "//label[contains(text(), 'Address Format')]//a/i";
    private static final String TAX_ID_FORMAT = "//label[contains(text(), 'Tax ID Format')]//a/i";
    private static final String POSTCODE_FORMAT = "//label[contains(text(), 'Postcode Format')]//a/i";
    private static final String LANGUAGE_FORMAT = "//label[contains(text(), 'Language Code')]//a/i";
    private static final String CURRENCY_CODE = "//label[contains(text(), 'Currency Code')]//a/i";
    private static final String PHONE_COUNTRY_CODE = "//label[contains(text(), 'Phone Country Code')]//a/i";

    private static final List<String> links;

    static {
        links = List.of(
                "//label[contains(text(), 'Number (ISO 3166-1 numeric)')]//a/i",
                "//label[contains(text(), 'Code (ISO 3166-1 alpha-2')]//a/i",
                "//label[contains(text(), 'Code (ISO 3166-1 alpha-3')]//a/i",
                "//label[contains(text(), 'Address Format')]//a/i",
                "//label[contains(text(), 'Tax ID Format')]//a/i",
                "//label[contains(text(), 'Postcode Format')]//a/i",
                "//label[contains(text(), 'Language Code')]//a/i",
                "//label[contains(text(), 'Currency Code')]//a/i",
                "//label[contains(text(), 'Phone Country Code')]//a/i"
        );
    }

    @Before
    public void start() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @Test
    public void startTests() {
        adminPanelLogin();
        openAndCloseLinks();
    }

    private void adminPanelLogin() {
        webDriver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.name("username")))).sendKeys("admin");
        webDriver.findElement(By.name("password")).sendKeys("admin");
        webDriver.findElement(By.name("login")).submit();
    }

    private void openAndCloseLinks() {
        String mainWindow = webDriver.getWindowHandle();
        Set<String> oldWindows = webDriver.getWindowHandles();
        webDriver.findElement(By.xpath(ALAND_ISLAND_XPATH)).click();

        links.forEach(link -> {
            webDriver.findElement(By.xpath(link)).click();
            String newWindow = wait.until(thereIsWindowOtherThan(oldWindows));
            webDriver.switchTo().window(newWindow);
            webDriver.close();
            webDriver.switchTo().window(mainWindow);
        });
    }

    @After
    public void stop() {
        webDriver.quit();
        webDriver = null;
    }

    private ExpectedCondition<String> thereIsWindowOtherThan(Set<String> oldWindows) {
        return inputWebDriver -> Optional.ofNullable(inputWebDriver)
                .map(webDriver -> webDriver.getWindowHandles())
                .map(windows -> {
                    windows.removeAll(oldWindows);
                    return windows;
                })
                .filter(windows -> windows.size() > 0)
                .map(windows -> windows.iterator().next())
                .orElse(null);
    }
}

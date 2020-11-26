package com.mera.lesson6;

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

public class Task11 {
    private static final String CREATE_ACCOUNT_PAGE = "http://localhost/litecart/create_account";
    private static final String FIRSTNAME_FIELD_XPATH = "//input[contains(@name, 'firstname')]";
    private static final String LASTNAME_FIELD_XPATH = "//input[contains(@name, 'lastname')]";
    private static final String EMAIL_FIELD_XPATH = "//div[contains(@class, 'form-group col-xs-6 required')]//input[contains(@name, 'email')]";
    private static final String PASSWORD_AND_CONFIRMED_PASSWORD_XPATH = "//div[contains(@class, 'form-group col-xs-6 required')]//input[contains(@name, 'password')]";
    private static final String PRIVACY_POLICY_CHECKBOX_XPATH = "//input[contains(@name, 'terms_agreed')]";
    private static final String CREATE_ACCOUNT_BUTTON_XPATH = "//button[contains(@name, 'create_account')]";
    private static final String ACCOUNT_DROPDOWN_XPATH = "//li[contains(@class, 'account dropdown')]//a[contains(@data-toggle, 'dropdown')]";
    private static final String ACCOUNT_DROPDOWN_LOGOUT_XPATH = "//ul[contains(@class, 'dropdown-menu')]//a[text() = 'Logout']";
    private static final String LOG_IN_EMAIL_XPATH = "//input[contains(@name, 'email')]";
    private static final String LOG_IN_PASSWORD_XPATH = "//input[contains(@name, 'password')]";
    private static final String LOG_IN_SIGN_IN_BUTTON_XPATH = "//button[contains(@name, 'login')]";

    private static final String FIRSTNAME = "aaa";
    private static final String LASTNAME = "aaa";
    private static final String EMAIL = "aaa@aa.ak";
    private static final String PASSWORD = "aaaaakaaa";

    public WebDriver webDriver;
    public WebDriverWait wait;

    @Before
    public void start() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @Test
    public void testCreateAccount() {
        webDriver.get(CREATE_ACCOUNT_PAGE);
        fillFields();
        logOut();
        logIn();
        logOut();
    }

    private void fillFields() {
        webDriver.findElement(By.xpath(FIRSTNAME_FIELD_XPATH)).sendKeys(FIRSTNAME);
        webDriver.findElement(By.xpath(LASTNAME_FIELD_XPATH)).sendKeys(LASTNAME);
        webDriver.findElement(By.xpath(EMAIL_FIELD_XPATH)).sendKeys(EMAIL);
        webDriver.findElements(By.xpath(PASSWORD_AND_CONFIRMED_PASSWORD_XPATH))
                .forEach(webElement -> webElement.sendKeys(PASSWORD));
        webDriver.findElement(By.xpath(PRIVACY_POLICY_CHECKBOX_XPATH)).click();
        webDriver.findElement(By.xpath(CREATE_ACCOUNT_BUTTON_XPATH)).click();
    }

    private void logOut() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ACCOUNT_DROPDOWN_XPATH))).click();
        wait.until(webDriver -> webDriver.findElement(By.xpath(ACCOUNT_DROPDOWN_LOGOUT_XPATH))).click();
    }

    private void logIn() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ACCOUNT_DROPDOWN_XPATH))).click();
        wait.until(webDriver -> webDriver.findElement(By.xpath(LOG_IN_EMAIL_XPATH))).sendKeys(EMAIL);
        wait.until(webDriver -> webDriver.findElement(By.xpath(LOG_IN_PASSWORD_XPATH))).sendKeys(PASSWORD);
        wait.until(webDriver -> webDriver.findElement(By.xpath(LOG_IN_SIGN_IN_BUTTON_XPATH))).click();
    }

    @After
    public void stop() {
        webDriver.quit();
        webDriver = null;
    }

}

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
import java.util.concurrent.TimeUnit;

public class Task7 {
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
        chromeDriver.get("http://localhost/litecart/admin/login.php");
        chromeDriver.findElement(By.name("username")).sendKeys("admin");
        chromeDriver.findElement(By.name("password")).sendKeys("admin");
        chromeDriver.findElement(By.name("login")).submit();
        startAllTests();
    }

    public void startAllTests() {
        appearanceClick();
//        catalogClick();
        countriesClick();
        currenciesClick();
        customersClick();
        geozonesClick();
        languagesClick();
        modulesClick();
        ordersClick();
        pagesClick();
        reportsClick();
        settingsClick();
        slidesClick();
        taxClick();
        translationsClick();
        usersClick();
        vqmodsClick();
    }

    public void appearanceClick() {
        wait.until((chromeDriver -> chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'appearance')]")))).click();
        testTitle("Template");
        wait.until((chromeDriver -> chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'appearance')]//li[contains(@data-code, 'template')]")))).click();
        testTitle("Template");
        wait.until(chromeDriver -> chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'appearance')]//li[contains(@data-code, 'logotype')]"))).click();
        testTitle("Logotype");
    }

    private void testTitle(String expected) {
        WebElement title = wait.until(chromeDriver -> chromeDriver.findElement(By.xpath("//div[contains(@class, 'panel-heading')]")));
        Assert.assertNotNull(title.getText());
        Assert.assertEquals(expected, title.getText());
    }

//    public void catalogClick() {
//        wait.until(chromeDriver -> chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'catalog')]"))).click();
//        testTitle("Catalog");
//        wait.until(chromeDriver -> chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'catalog')]//li[contains(@data-code, 'catalog')]"))).click();
//        testTitle("Catalog");
//        wait.until(chromeDriver -> chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'catalog')]//li[contains(@data-code, 'attribute_groups')]"))).click();
//        testTitle("Attribute Groups");
//        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'catalog')]//li[contains(@data-code, 'manufacturers')]")).click();
//        testTitle("Manufacturers");
//        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'catalog')]//li[contains(@data-code, 'suppliers')]")).click();
//        testTitle("Suppliers");
//        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'catalog')]//li[contains(@data-code, 'delivery_statuses')]")).click();
//        testTitle("Delivery Statuses");
//        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'catalog')]//li[contains(@data-code, 'sold_out_statuses')]")).click();
//        testTitle("Sold Out Statuses");
//        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'catalog')]//li[contains(@data-code, 'quantity_units')]")).click();
//        testTitle("Quantity Units");
//        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'catalog')]//li[contains(@data-code, 'csv')]")).click();
//        testTitle("CSV Import/Export");
//    }

    public void countriesClick() {
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'countries')]")).click();
        testTitle("Countries");
    }

    public void currenciesClick() {
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'currencies')]")).click();
        testTitle("Currencies");
    }

    public void customersClick() {
        wait.until(chromeDriver -> chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'customers')]"))).click();
        testTitle("Customers");
        wait.until((chromeDriver -> chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'customers')]//li[contains(@data-code, 'customers')]"))));
        testTitle("Customers");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'customers')]//li[contains(@data-code, 'csv')]")).click();
        testTitle("CSV Import/Export");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'customers')]//li[contains(@data-code, 'newsletter')]")).click();
        testTitle("Newsletter");
    }

    public void geozonesClick() {
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'geo_zones')]")).click();
        testTitle("Geo Zones");
    }

    public void languagesClick() {
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'languages')]")).click();
        testTitle("Languages");
        wait.until((chromeDriver -> chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'languages')]//li[contains(@data-code, 'languages')]"))));
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'languages')]//li[contains(@data-code, 'languages')]")).click();
        testTitle("Languages");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'languages')]//li[contains(@data-code, 'storage_encoding')]")).click();
        testTitle("Storage Encoding");
    }

    public void modulesClick() {
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'modules')]")).click();
        testTitle("Customer Modules");
        wait.until((chromeDriver -> chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'modules')]//li[contains(@data-code, 'customer')]"))));
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'modules')]//li[contains(@data-code, 'customer')]")).click();
        testTitle("Customer Modules");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'modules')]//li[contains(@data-code, 'shipping')]")).click();
        testTitle("Shipping Modules");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'modules')]//li[contains(@data-code, 'payment')]")).click();
        testTitle("Payment Modules");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'modules')]//li[contains(@data-code, 'order')]")).click();
        testTitle("Order Modules");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'modules')]//li[contains(@data-code, 'order_total')]")).click();
        testTitle("Order Total Modules");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'modules')]//li[contains(@data-code, 'jobs')]")).click();
        testTitle("Job Modules");
    }

    public void ordersClick() {
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'orders')]")).click();
        testTitle("Orders");
        wait.until((chromeDriver -> chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'orders')]//li[contains(@data-code, 'orders')]"))));
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'orders')]//li[contains(@data-code, 'orders')]")).click();
        testTitle("Orders");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'orders')]//li[contains(@data-code, 'order_statuses')]")).click();
        testTitle("Order Statuses");
    }

    public void pagesClick() {
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'pages')]")).click();
        testTitle("Pages");
        wait.until((chromeDriver -> chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'pages')]//li[contains(@data-code, 'pages')]"))));
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'pages')]//li[contains(@data-code, 'pages')]")).click();
        testTitle("Pages");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'pages')]//li[contains(@data-code, 'csv')]")).click();
        testTitle("CSV Import/Export");
    }

    public void reportsClick() {
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'reports')]")).click();
        testTitle("Monthly Sales");
        wait.until((chromeDriver -> chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'reports')]//li[contains(@data-code, 'monthly_sales')]"))));
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'reports')]//li[contains(@data-code, 'monthly_sales')]")).click();
        testTitle("Monthly Sales");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'reports')]//li[contains(@data-code, 'most_sold_products')]")).click();
        testTitle("Most Sold Products");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'reports')]//li[contains(@data-code, 'most_shopping_customers')]")).click();
        testTitle("Most Shopping Customers");
    }

    public void settingsClick() {
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'settings')]")).click();
        testTitle("Settings");
        wait.until((chromeDriver -> chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'settings')]//li[contains(@data-code, 'store_info')]"))));
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'settings')]//li[contains(@data-code, 'store_info')]")).click();
        testTitle("Settings");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'settings')]//li[contains(@data-code, 'defaults')]")).click();
        testTitle("Settings");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'settings')]//li[contains(@data-code, 'email')]")).click();
        testTitle("Settings");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'settings')]//li[contains(@data-code, 'listings')]")).click();
        testTitle("Settings");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'settings')]//li[contains(@data-code, 'customer_details')]")).click();
        testTitle("Settings");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'settings')]//li[contains(@data-code, 'legal')]")).click();
        testTitle("Settings");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'settings')]//li[contains(@data-code, 'images')]")).click();
        testTitle("Settings");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'settings')]//li[contains(@data-code, 'checkout')]")).click();
        testTitle("Settings");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'settings')]//li[contains(@data-code, 'advanced')]")).click();
        testTitle("Settings");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'settings')]//li[contains(@data-code, 'security')]")).click();
        testTitle("Settings");
    }

    public void slidesClick() {
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'slides')]")).click();
        testTitle("Slides");
    }

    public void taxClick() {
        wait.until(chromeDriver -> chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'tax')]"))).click();
        testTitle("Tax Rates");
        wait.until((chromeDriver -> chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'tax')]//li[contains(@data-code, 'tax_rates')]"))));
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'tax')]//li[contains(@data-code, 'tax_rates')]")).click();
        testTitle("Tax Rates");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'tax')]//li[contains(@data-code, 'tax_classes')]")).click();
        testTitle("Tax Classes");
    }

    public void translationsClick() {
        wait.until(chromeDriver -> chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'translations')]"))).click();
        testTitle("Search Translations");
        wait.until((chromeDriver -> chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'translations')]//li[contains(@data-code, 'search')]")))).click();
        testTitle("Search Translations");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'translations')]//li[contains(@data-code, 'scan')]")).click();
        testTitle("Scan Files For Translations");
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'translations')]//li[contains(@data-code, 'csv')]")).click();
        testTitle("CSV Import/Export");
    }

    public void usersClick() {
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'users')]")).click();
        testTitle("Users");
    }

    public void vqmodsClick() {
        chromeDriver.findElement(By.xpath("//ul[contains(@id, 'box-apps-menu')]/li[contains(@data-code, 'vqmods')]")).click();
        testTitle("vQmods");
    }

    @After
    public void stop() {
        chromeDriver.quit();
        chromeDriver = null;
    }

}
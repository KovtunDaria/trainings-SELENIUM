package com.mera.lesson11.Task19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    private static final String PRODUCT_QUANTITY_XPATH = "//div[contains(@class, 'badge quantity')]";
    private static final String ADD_PRODUCT_TO_CART_BUTTON_XPATH = "//button[contains(@name, 'add_cart_product')]";

    private final WebDriver webDriver;
    private final WebDriverWait wait;
    private final String pageXpath;

    private String nextQuantityProduct;

    public ProductPage(WebDriver webDriver, WebDriverWait wait, String pageXpath) {
        this.webDriver = webDriver;
        this.wait = wait;
        this.pageXpath = pageXpath;
    }

    public void visitPage() {
        webDriver.findElement(By.xpath(pageXpath)).click();
    }

    public void addProducts() {
        String quantity = webDriver.findElement(By.xpath(PRODUCT_QUANTITY_XPATH)).getText();
        if (quantity.equals("")) {
            nextQuantityProduct = "1";
        } else {
            nextQuantityProduct = Integer.toString(Integer.parseInt(nextQuantityProduct) + 1);
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ADD_PRODUCT_TO_CART_BUTTON_XPATH))).click();
        wait.until(webDriver -> webDriver.findElement(By.xpath(PRODUCT_QUANTITY_XPATH)).getText().equals(nextQuantityProduct));
    }
}
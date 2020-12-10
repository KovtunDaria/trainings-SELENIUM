package com.mera.lesson11.Task19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    private static final String CART_ICON_XPATH = "//div[contains(@id, 'cart')]";
    private static final String TRASH_BUTTON_XPATH = "//i[contains(@class, 'fa-trash')]";
    private static final String ORDER_SUMMARY_TITLE_XPATH = "//h2[contains(text(), 'Order Summary')]";
    private static final String BACK_BUTTON_XPATH = "//a[contains(text(), 'Back')]";

    private final WebDriver webDriver;
    private final WebDriverWait wait;

    public CartPage(WebDriver webDriver, WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
    }

    public void deleteAddedProducts() {
        webDriver.findElement(By.xpath(CART_ICON_XPATH)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TRASH_BUTTON_XPATH))).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(ORDER_SUMMARY_TITLE_XPATH)));
        wait.until(driver -> driver.findElement(By.xpath(BACK_BUTTON_XPATH))).click();
    }
}

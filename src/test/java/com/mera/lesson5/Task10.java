package com.mera.lesson5;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class Task10 {
    private static final String HOME_PAGE = "http://localhost/litecart";

    private static final String PRODUCT_TITLE_XPATH_TEMPLATE =
            "//section[contains(@id, '%s')]//article[contains(@class, 'product-column')][%d]//a//div[contains(@class, 'info')]//h4[contains(@class, 'name')]";
    private static final String PRICE_XPATH_TEMPLATE =
            "//section[contains(@id, '%s')]//article[contains(@class, 'product-column')]//a[contains(@data-name, '%s')]//span[contains(@class, 'price')]";
    private static final String OLD_PRICE_XPATH_TEMPLATE =
            "//section[contains(@id, '%s')]//a[contains(@data-name, '%s')]//div[contains(@class, 'price-wrapper')]//del[contains(@class, 'regular-price')]";
    private static final String SALE_PRICE_XPATH_TEMPLATE =
            "//section[contains(@id, '%s')]//a[contains(@data-name, '%s')]//div[contains(@class, 'price-wrapper')]//strong[contains(@class, 'campaign-price')]";
    private static final String PRODUCT_ALL_LINKS_XPATH_TEMPLATE =
            "//section[contains(@id, '%s')]//article[contains(@class, 'product-column')]//a";

    private static final Pattern COLOR_VALUE_PATTERN = Pattern.compile("rgba?\\((\\d{1,3}), (\\d{1,3}), (\\d{1,3})(, [\\.\\d]+)?\\)");

    protected WebDriver webDriver;
    protected WebDriverWait wait;

    protected abstract void initDriver();

    @Before
    public void start() {
        initDriver();
        webDriver.get(HOME_PAGE);
    }

    @Test
    public void testAllNames() {
        int campaignProductsCount = wait.until(webDriver -> webDriver.findElements(By.xpath("//section[contains(@id, 'box-campaign-products')]//article[contains(@class, 'product-column')]"))).size();
        for (int i = 1; i <= campaignProductsCount; i++) {
            checkName("box-campaign-products", i);
        }

        int popularProductsCount = wait.until(webDriver ->  webDriver.findElements(By.xpath("//section[contains(@id, 'box-popular-products')]//article[contains(@class, 'product-column')]"))).size();
        for (int i = 1; i <= popularProductsCount; i++) {
            checkName("box-popular-products", i);
        }

        int latestProductsCount = wait.until(webDriver -> webDriver.findElements(By.xpath("//section[contains(@id, 'box-latest-products')]//article[contains(@class, 'product-column')]"))).size();
        for (int i = 1; i <= latestProductsCount; i++) {
            checkName("box-latest-products", i);
        }
    }

    private void checkName(String section, int articleIndex) {
        String productNameXPath = String.format(PRODUCT_TITLE_XPATH_TEMPLATE, section, articleIndex);
        WebElement productElement = wait.until(webDriver -> webDriver.findElement(By.xpath(productNameXPath)));
        String outerProductName = productElement.getText();
        productElement.click();
        String innerProductName = wait.until(webDriver -> webDriver.findElement(By.xpath("//h1[contains(@class, 'title')]"))).getText();
        Assert.assertNotNull(outerProductName);
        Assert.assertNotNull(innerProductName);
        Assert.assertEquals(outerProductName, innerProductName);
        webDriver.get(HOME_PAGE);
    }


    @Test
    public void testAllValuesOfPrices() {
        getProducts("box-campaign-products", this::spanPrice, this::checkSimplePricesValues);
        getProducts("box-campaign-products", Predicate.not(this::spanPrice), this::checkSalePricesValues);
        getProducts("box-popular-products", this::spanPrice, this::checkSimplePricesValues);
        getProducts("box-popular-products", Predicate.not(this::spanPrice), this::checkSalePricesValues);
        getProducts("box-latest-products", this::spanPrice, this::checkSimplePricesValues);
        getProducts("box-latest-products", Predicate.not(this::spanPrice), this::checkSalePricesValues);
    }

    private void getProducts(String section, Predicate<WebElement> productFilter, BiConsumer<String, String> test) {
        List<String> productNames = webDriver.findElements(By.xpath(String.format(PRODUCT_ALL_LINKS_XPATH_TEMPLATE, section)))
                .stream()
                .filter(productFilter)
                .map(webElement -> webElement.getAttribute("data-name"))
                .collect(Collectors.toList());
        productNames.forEach(productName -> test.accept(section, productName));
    }

    private boolean spanPrice(WebElement webElement) {
        boolean result;
        try {
            webElement.findElement(By.tagName("span"));
            result = true;
        } catch (NoSuchElementException exception) {
            result = false;
        }
        return result;
    }

    private void checkSimplePricesValues(String section, String dataName) {
        String priceXPath = String.format(PRICE_XPATH_TEMPLATE, section, dataName);
        WebElement priceElement = wait.until(webDriver -> webDriver.findElement(By.xpath(priceXPath)));
        String outerPrice = priceElement.getText();
        webDriver.findElement(By.xpath(priceXPath)).click();
        String innerPrice = wait.until(webDriver -> webDriver.findElement(By.xpath("//span[contains(@class, 'price')]"))).getText();
        Assert.assertNotNull(outerPrice);
        Assert.assertNotNull(innerPrice);
        Assert.assertEquals(outerPrice, innerPrice);
        webDriver.get(HOME_PAGE);
    }

    private void checkSalePricesValues(String section, String productName) {
        String salePriceXPath = String.format(SALE_PRICE_XPATH_TEMPLATE, section, productName);
        String oldPriceXPath = String.format(OLD_PRICE_XPATH_TEMPLATE, section, productName);
        WebElement salePriceElement = wait.until(webDriver -> webDriver.findElement(By.xpath(salePriceXPath)));
        WebElement oldPriceElement = wait.until(webDriver -> webDriver.findElement(By.xpath(oldPriceXPath)));
        String outerSalePrice = salePriceElement.getText();
        String outerOldPrice = oldPriceElement.getText();
        webDriver.findElement(By.xpath(salePriceXPath)).click();
        String innerSalePrice = wait.until(webDriver -> webDriver.findElement(By.xpath("//strong[contains(@class, 'campaign-price')]"))).getText();
        String innerOldPrice = wait.until(webDriver -> webDriver.findElement(By.xpath("//del[contains(@class, 'regular-price')]"))).getText();
        Assert.assertNotNull(outerSalePrice);
        Assert.assertNotNull(innerSalePrice);
        Assert.assertNotNull(outerOldPrice);
        Assert.assertNotNull(innerOldPrice);
        Assert.assertEquals(outerSalePrice, innerSalePrice);
        Assert.assertNotNull(outerOldPrice, innerOldPrice);
        webDriver.get(HOME_PAGE);
    }

    @Test
    public void testSalePricesAppearance() {
        getProducts("box-campaign-products", Predicate.not(this::spanPrice), this::checkSalePricesColorAndFormatting);
        getProducts("box-popular-products", Predicate.not(this::spanPrice), this::checkSalePricesColorAndFormatting);
        getProducts("box-latest-products", Predicate.not(this::spanPrice), this::checkSalePricesColorAndFormatting);
    }

    private void checkSalePricesColorAndFormatting(String section, String dataName) {
        String salePriceXPath = String.format(SALE_PRICE_XPATH_TEMPLATE, section, dataName);
        WebElement salePriceElement = wait.until(webDriver -> webDriver.findElement(By.xpath(salePriceXPath)));

        String outerSalePriceColor = salePriceElement.getCssValue("color");
        Assert.assertNotNull(outerSalePriceColor);
        checkRedColor(outerSalePriceColor);

        String outerSalePriceFontWeight = salePriceElement.getCssValue("font-weight");
        Assert.assertNotNull(outerSalePriceFontWeight);
        Assert.assertTrue(Integer.parseInt(outerSalePriceFontWeight) >= 600);

        webDriver.findElement(By.xpath(salePriceXPath)).click();
        String innerSalePriceColor = wait.until(webDriver -> webDriver.findElement(By.xpath("//strong[contains(@class, 'campaign-price')]"))).getCssValue("color");
        Assert.assertNotNull(innerSalePriceColor);
        checkRedColor(innerSalePriceColor);

        String innerSalePriceFontWeight = wait.until(webDriver -> webDriver.findElement(By.xpath("//strong[contains(@class, 'campaign-price')]"))).getCssValue("font-weight");
        Assert.assertNotNull(innerSalePriceFontWeight);
        Assert.assertTrue(Integer.parseInt(innerSalePriceFontWeight) >= 600);

        webDriver.get(HOME_PAGE);
    }

    private void checkRedColor(String rgbColor) {
        Matcher matcher = COLOR_VALUE_PATTERN.matcher(rgbColor);
        if (matcher.find()) {
            int red = Integer.parseInt(matcher.group(1));
            int green = Integer.parseInt(matcher.group(2));
            int blue = Integer.parseInt(matcher.group(3));

            Assert.assertNotEquals(0, red);
            Assert.assertEquals(0, green);
            Assert.assertEquals(0, blue);
        }
    }

    @Test
    public void testOldPricesAppearance() {
        getProducts("box-campaign-products", Predicate.not(this::spanPrice), this::checkOldPricesColorAndFormatting);
        getProducts("box-popular-products", Predicate.not(this::spanPrice), this::checkOldPricesColorAndFormatting);
        getProducts("box-latest-products", Predicate.not(this::spanPrice), this::checkOldPricesColorAndFormatting);
    }

    private void checkOldPricesColorAndFormatting(String section, String dataName) {
        String oldPriceXPath = String.format(OLD_PRICE_XPATH_TEMPLATE, section, dataName);
        WebElement oldPriceElement = wait.until(webDriver -> webDriver.findElement(By.xpath(oldPriceXPath)));

        String outerOldPriceColor = oldPriceElement.getCssValue("color");
        Assert.assertNotNull(outerOldPriceColor);
        checkGrayColor(outerOldPriceColor);

        String outerOldPriceTextDecoration = oldPriceElement.getCssValue("text-decoration-line");
        Assert.assertNotNull(outerOldPriceTextDecoration);
        Assert.assertEquals("line-through", outerOldPriceTextDecoration);

        webDriver.findElement(By.xpath(oldPriceXPath)).click();
        String innerOldPriceColor = wait.until(webDriver -> webDriver.findElement(By.xpath("//del[contains(@class, 'regular-price')]"))).getCssValue("color");
        Assert.assertNotNull(innerOldPriceColor);
        checkGrayColor(innerOldPriceColor);

        String innerOldPriceTextDecoration = wait.until(webDriver -> webDriver.findElement(By.xpath("//del[contains(@class, 'regular-price')]"))).getCssValue("text-decoration-line");
        Assert.assertNotNull(innerOldPriceTextDecoration);
        Assert.assertEquals("line-through", innerOldPriceTextDecoration);

        webDriver.get(HOME_PAGE);
    }

    private void checkGrayColor(String rgbColor) {
        Matcher matcher = COLOR_VALUE_PATTERN.matcher(rgbColor);
        if (matcher.find()) {
            int red = Integer.parseInt(matcher.group(1));
            int green = Integer.parseInt(matcher.group(2));
            int blue = Integer.parseInt(matcher.group(3));

            Assert.assertTrue(red == green && green == blue);
        }
    }

    @Test
    public void testSalePricesSize() {
        getProducts("box-campaign-products", Predicate.not(this::spanPrice), this::checkOldPricesColorAndFormatting);
        getProducts("box-popular-products", Predicate.not(this::spanPrice), this::checkOldPricesColorAndFormatting);
        getProducts("box-latest-products", Predicate.not(this::spanPrice), this::checkOldPricesColorAndFormatting);
    }

    private void checkThatSalePricesIsBigger(String section, String dataName) {
        String salePriceXPath = String.format(SALE_PRICE_XPATH_TEMPLATE, section, dataName);
        WebElement salePriceElement = wait.until(webDriver -> webDriver.findElement(By.xpath(salePriceXPath)));

        String oldPriceXPath = String.format(OLD_PRICE_XPATH_TEMPLATE, section, dataName);
        WebElement oldPriceElement = wait.until(webDriver -> webDriver.findElement(By.xpath(oldPriceXPath)));

        String outerSalePriceFontSize = salePriceElement.getCssValue("font-size");
        Assert.assertNotNull(outerSalePriceFontSize);
        String outerOldPriceFontSize = oldPriceElement.getCssValue("font-size");
        Assert.assertNotNull(outerSalePriceFontSize);
        Assert.assertTrue(Integer.parseInt(outerSalePriceFontSize) > Integer.parseInt(outerOldPriceFontSize));


        webDriver.findElement(By.xpath(salePriceXPath)).click();
        String innerSalePriceFontSize = wait.until(webDriver -> webDriver.findElement(By.xpath("//strong[contains(@class, 'campaign-price')]"))).getCssValue("font-size");
        Assert.assertNotNull(innerSalePriceFontSize);
        String innerOldPriceFontSize = wait.until(webDriver -> webDriver.findElement(By.xpath("//del[contains(@class, 'regular-price')]"))).getCssValue("font-size");
        Assert.assertNotNull(innerOldPriceFontSize);
        Assert.assertTrue(Integer.parseInt(innerSalePriceFontSize) > Integer.parseInt(innerOldPriceFontSize));

        webDriver.get(HOME_PAGE);
    }

    @After
    public void stop() {
        webDriver.quit();
        webDriver = null;
    }
}

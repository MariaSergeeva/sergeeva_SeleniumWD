package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class lesson7 extends TestBase {
  @Test
  public void cart(){
    login();

    for (int i = 0; i < 1; i++) {
      driver.get("http://localhost/litecart");
      driver.findElement(By.cssSelector("div#box-most-popular a.link")).click();

      String addButton = "//button[@type='submit'][@name='add_cart_product']";
      String productsInCartCountLocator = "//div[@id = 'cart']//span[@class = 'quantity']";

      wait.until(visibilityOfElementLocated(By.xpath(addButton)));
      wait.until(visibilityOfElementLocated(By.xpath(productsInCartCountLocator)));

      WebElement productsInCartCount = driver.findElement(By.xpath(productsInCartCountLocator));
      String countString = productsInCartCount.getAttribute("textContent");
      int count = Integer.parseInt(countString);
      System.out.println(count);

      driver.findElement(By.xpath(addButton)).click();
      wait.until(not(attributeContains(productsInCartCount, "textContent", countString)));

      int newCount = Integer.parseInt(driver.findElement(By.xpath(productsInCartCountLocator)).getAttribute("textContent"));
      Assert.assertEquals(newCount, count + 1);
      System.out.println(newCount);
    }
    driver.findElement(By.xpath("//div[@id = 'cart']//a[@class = 'link']")).click();
    wait.until(visibilityOfElementLocated(By.xpath("//div[@id = 'box-checkout-cart']")));

    String shortcutLocator = "ul.shortcuts li.shortcut:nth-of-type(1) a";
    String inputNumberLocator = "//input[@type = 'number'][@name = 'quantity']";
    String productsInOrderCountLocator = "div#order_confirmation-wrapper tbody tr:nth-of-type(2) td:nth-of-type(1)";
    String removeLocator = "//button[@type = 'submit'][@name = 'remove_cart_item']";
    //driver.findElement(By.cssSelector(shortcutLocator)).click();

    WebElement input = driver.findElement(By.xpath(inputNumberLocator));
    input.clear();
    new Actions(driver).moveToElement(input)
            .click()
            .sendKeys("1")
            .perform();





//    WebElement productsInOrderCount = driver.findElement(By.cssSelector(productsInOrderCountLocator));
//    String countString = productsInOrderCount.getAttribute("textContent");
//    int count = Integer.parseInt(countString);
//    System.out.println(count);
//
//    driver.findElement(By.xpath(removeLocator)).click();
//    wait.until(not(attributeContains(productsInOrderCount, "textContent", countString)));
//
//    int newCount = Integer.parseInt(driver.findElement(By.xpath(productsInOrderCountLocator)).getAttribute("textContent"));
//    Assert.assertEquals(newCount, count - 1);
//    System.out.println(newCount);

    int typesCount = driver.findElements(By.xpath("//table[@class = 'dataTable rounded-corners']//td[@class = 'item']")).size();
    while(typesCount > 1){
        int productsCount = Integer.parseInt(driver.findElement(By.xpath("//table[@class = 'dataTable rounded-corners']//td[1]")).getAttribute("textContent"));
        while (productsCount > 1){
          
        }
    }

  }
}

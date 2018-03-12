package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.lang.reflect.Array;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class lesson4 extends TestBase {


  @Test
  public void menu(){
    login();
    int pointsCount = driver.findElements(By.xpath("//ul[@id = 'box-apps-menu']/li")).size();

    int i = 1;
    while (i<=pointsCount){
      driver.findElement(By.xpath(String.format("//ul[@id = 'box-apps-menu']/li[%s]", i))).click();
      wait.until(visibilityOfElementLocated(By.cssSelector("td#content > h1")));
      int subPointsCount = driver.findElements(By.xpath(String.format("//ul[@id = 'box-apps-menu']/li[%s]//li", i))).size();
      int j = 1;
      while (j<=subPointsCount){
        driver.findElement(By.xpath(String.format("//ul[@id = 'box-apps-menu']/li[%s]//li[%s]", i, j))).click();
        wait.until(visibilityOfElementLocated(By.cssSelector("td#content > h1")));
        j++;
      }
      i++;
    }
  }

  @Test
  public void stickers() {
    driver.get("http://localhost/litecart");
    String[] sections = {"box-most-popular", "box-campaigns", "box-latest-products"};

    for (int i = 0; i < sections.length; i++) {
      String locator = String.format("div#%s li.product", sections[i]);
      int productscount = driver.findElements(By.cssSelector(locator)).size();
      System.out.println("- товаров в категории " + sections[i] + ": " + productscount);
      int j = 0;
      while (j < productscount) {
        int count = j+1;
        String subLocator = String.format("div#%s li.product:nth-of-type(%s) div.sticker", sections[i], count);
        Assert.assertTrue(isThereOnlyOneSticker(locator));
        System.out.println("- стикеров для товара №" + count + " в категории " + sections[i] + ": " + driver.findElements(By.cssSelector(subLocator)).size());
        j++;

      }
    }
  }

  boolean isThereOnlyOneSticker(String locator) {
    return driver.findElements(By.cssSelector(locator)).size() ==1;
  }


}

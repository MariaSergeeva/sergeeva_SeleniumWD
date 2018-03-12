package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class lesson10 extends TestBase {
  @Test
  public void events(){
    System.out.println(driver.manage().logs().getAvailableLogTypes());
    int browserEvents = 0;
    int performanceEvents = 0;
    System.out.println(browserEvents);
    System.out.println(performanceEvents);

    login();
    driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
    Assert.assertTrue(driver.manage().logs().get("browser").getAll().size()==0);
    browserEvents = browserEvents + driver.manage().logs().get("browser").getAll().size();
    performanceEvents = performanceEvents + driver.manage().logs().get("performance").getAll().size();

    int productsCount = driver.findElements(By.xpath("//form[@name = 'catalog_form']//img//..//a[contains(@href, 'category_id=1')]")).size();
    int i = 0;
    while (i<productsCount){
      int count = i+5;
      String locator = String.format("form[name = catalog_form] tr.row:nth-of-type(%s) a",count);
      driver.findElement(By.cssSelector(locator)).click();
      Assert.assertTrue(driver.manage().logs().get("browser").getAll().size()==0);
      browserEvents = browserEvents + driver.manage().logs().get("browser").getAll().size();
      performanceEvents = performanceEvents + driver.manage().logs().get("performance").getAll().size();

      wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@name = 'cancel']"))));
      driver.findElement(By.xpath("//button[@name = 'cancel']")).click();
      Assert.assertTrue(driver.manage().logs().get("browser").getAll().size()==0);
      browserEvents = browserEvents + driver.manage().logs().get("browser").getAll().size();
      performanceEvents = performanceEvents + driver.manage().logs().get("performance").getAll().size();
      i++;
    }
    System.out.println(browserEvents);
    System.out.println(performanceEvents);
    Assert.assertTrue(browserEvents == 0);
  }
}
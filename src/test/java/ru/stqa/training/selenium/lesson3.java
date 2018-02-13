package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class lesson3 extends TestBase {

  public void login(){
    driver.get("http://localhost/litecart/admin/login.php");
    driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
    driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
    driver.findElement(By.xpath("//button[@name='login']")).click();
  }
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
}

package ru.stqa.training.selenium;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class MyFirstTest extends TestBase{
  @Test
  public void myFirstTest(){
    driver.get("http://www.google.com/");
    driver.findElement(By.name("q")).sendKeys("webdriver");
    driver.findElement(By.xpath("//input[@value='Поиск в Google']")).click();
    wait.until(titleIs("webdriver - Поиск в Google"));
  }

  @Test
  public void myTest(){
    driver.get("https://www.google.ru/maps/");
    driver.findElement(By.xpath("//button[@aria-label='Как добраться']")).click();
    wait.until(visibilityOfElementLocated(By.xpath("//div[@class='widget-directions-waypoints']")));
  }
}
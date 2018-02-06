package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class MyFirstTest {
  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void start(){
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
  }

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

  @After
  public void stop(){
    driver.quit();
    driver = null;
  }
}
package ru.stqa.training.selenium;
import net.lightbody.bmp.core.har.Har;
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
//    proxy.newHar();
    driver.get("https://www.google.ru/maps/");
//    System.out.println(driver.manage().logs().getAvailableLogTypes());
//    driver.manage().logs().get("browser").forEach(l -> System.out.println(l));
//    driver.manage().logs().get("performance").forEach(l -> System.out.println(l));
    driver.findElement(By.xpath("//button[@aria-label='Как добраться']")).click();
    wait.until(visibilityOfElementLocated(By.xpath("//div[@class='widget-directions-waypoints']")));
//    Har har = proxy.endHar();
//    har.getLog().getEntries().forEach(l -> System.out.println(l.getResponse().getStatus() + ":" + l.getRequest().getUrl()));
  }
}
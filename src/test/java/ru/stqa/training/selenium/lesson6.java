package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Instant;

public class lesson6 extends TestBase{
  @Test
  public void registration(){
   driver.get("http://localhost/litecart/en/");
   driver.findElement(By.xpath("//form[@name = 'login_form']//a")).click();
   long random = Instant.now().toEpochMilli();
   String index = String.valueOf(random).substring(8);
   String phoneNumber = String.valueOf(random).substring(3);
   String email = random + "@mail.ru";
   String pass = "password_" + random;

    WebElement phone = driver.findElement(By.xpath("//form[@name = 'customer_form']//input[@name = 'phone']"));
    WebElement password = driver.findElement(By.xpath("//form[@name = 'customer_form']//input[@name = 'password']"));
    WebElement confirmed_password = driver.findElement(By.xpath("//form[@name = 'customer_form']//input[@name = 'confirmed_password']"));

    new Actions(driver).moveToElement(driver.findElement(By.xpath("//form[@name = 'customer_form']//input[@name = 'firstname']")))
            .click()
            .sendKeys("firstname_" + random + Keys.TAB + "lastname_" + random + Keys.TAB
            + "address1_" + random + Keys.TAB + Keys.TAB
            + index + Keys.TAB + "city_" + random)
            .moveToElement(driver.findElement(By.xpath("//form[@name = 'customer_form']//span[contains (@class, 'select2')]")))
            .click()
            .sendKeys("United States" + Keys.ENTER)
            .moveToElement(driver.findElement(By.xpath("//form[@name = 'customer_form']//input[@name = 'email']")))
            .click()
            .sendKeys(email)
            .perform();

    phone.clear();
    new Actions(driver).moveToElement(phone)
            .click()
            .sendKeys(phoneNumber)
            .perform();

    password.clear();
    new Actions(driver).moveToElement(password)
            .click()
            .sendKeys(pass)
            .perform();

    confirmed_password.clear();
    new Actions(driver).moveToElement(confirmed_password)
            .click()
            .sendKeys(pass + Keys.ENTER)
            .perform();

    driver.findElement(By.xpath("//div[@id = 'box-account']//a[contains(@href, 'logout')]")).click();
    new Actions(driver).moveToElement(driver.findElement(By.xpath("//input[@name = 'email']")))
            .click()
            .sendKeys(email + Keys.TAB + pass + Keys.ENTER)
            .perform();
    driver.findElement(By.xpath("//div[@id = 'box-account']//a[contains(@href, 'logout')]")).click();
  }
}

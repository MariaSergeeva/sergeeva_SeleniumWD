package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    WebElement nameField = driver.findElement(By.xpath("//form[@name = 'customer_form']//input[@name = 'firstname']"));
    WebElement countriesList = driver.findElement(By.xpath("//form[@name = 'customer_form']//select[@name = 'country_code']"));
    WebElement zonesList = driver.findElement(By.xpath("//form[@name = 'customer_form']//select[@name = 'zone_code']"));
    WebElement email = driver.findElement(By.xpath("//form[@name = 'customer_form']//input[@name = 'email']"));
    WebElement phone = driver.findElement(By.xpath("//form[@name = 'customer_form']//input[@name = 'phone']"));
    WebElement password = driver.findElement(By.xpath("//form[@name = 'customer_form']//input[@name = 'password']"));
    WebElement confirmed_password = driver.findElement(By.xpath("//form[@name = 'customer_form']//input[@name = 'confirmed_password']"));
    WebElement submit = driver.findElement(By.xpath("//form[@name = 'customer_form']//button[@name = 'create_account']"));
    new Actions(driver).moveToElement(nameField)
            .click()
            .sendKeys("firstname_" + random + Keys.TAB + "lastname_" + random + Keys.TAB
            + "address1_" + random + Keys.TAB + Keys.TAB
            + index + Keys.TAB + "city_" + random)
            .moveToElement(countriesList)
            .click()
            .sendKeys("United States" + Keys.ENTER)
            .moveToElement(zonesList)
            .click()
            .moveByOffset(0, 10)
            .click()
            .moveToElement(email)
            .click()
            .sendKeys(random + "@mail.ru")
            .perform();
    phone.clear();
    new Actions(driver).moveToElement(phone)
            .click()
            .sendKeys(phoneNumber)
            .perform();
    password.clear();
    new Actions(driver).moveToElement(password)
            .click()
            .sendKeys("password_" + random)
            .perform();
    confirmed_password.clear();
    new Actions(driver).moveToElement(confirmed_password)
            .click()
            .sendKeys("password_" + random)
            .perform();

    submit.click();
    // доделать логаут
    // доделать повторную авторизацию
    // доделать повторный логаут

  }
}

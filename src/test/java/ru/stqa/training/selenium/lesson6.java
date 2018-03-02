package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

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

  @Test
  public void newProduct(){

   login();
   driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
   driver.findElement(By.xpath("//td[@id = 'content']//a[contains(@href, 'edit_product')]")).click();
   driver.findElement(By.xpath("//input[@type = 'radio'][@value = '1']")).click();

   long random = Instant.now().toEpochMilli();
   String name = "PunkDuck_" + random;
   File picture = new File("src/test/resources/punkDuck.jpg");
   new Actions(driver)
           .moveToElement(driver.findElement(By.xpath("//input[@name = 'name[en]']")))
           .click()
           .sendKeys(name)
           .moveToElement(driver.findElement(By.xpath("//input[@type = 'checkbox'][@data-name = 'Rubber Ducks']")))
           .click()
           .moveToElement(driver.findElement(By.xpath("//input[@type = 'checkbox'][@name = 'product_groups[]'][@value = '1-1']")))
           .click()
           .perform();
   driver.findElement(By.xpath("//input[@type = 'file']")).sendKeys(picture.getAbsolutePath());

   driver.findElement(By.xpath("//a[@href = '#tab-information']")).click();

   new Actions(driver)
           .moveToElement(driver.findElement(By.xpath("//select[@name = 'manufacturer_id']")))
           .click()
           .sendKeys(Keys.ARROW_DOWN)
           .sendKeys(Keys.ENTER)
           .moveToElement(driver.findElement(By.xpath("//input[@name = 'short_description[en]']")))
           .click()
           .sendKeys("Утка-панк")
           .moveToElement(driver.findElement(By.xpath("//textarea[@name = 'description[en]']")))
           .sendKeys("Утка для любителей панк-рока")
           .perform();

   driver.findElement(By.xpath("//a[@href = '#tab-prices']")).click();
   // driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

   new Actions(driver)
           .moveToElement(driver.findElement(By.xpath("//input[@name = 'gross_prices[USD]']")))
           .click()
           .sendKeys("5" + Keys.DELETE)
           .moveToElement(driver.findElement(By.xpath("//input[@name = 'gross_prices[EUR]']")))
           .click()
           .sendKeys("4" + Keys.DELETE)
           .perform();

   driver.findElement(By.xpath("//button[@type = 'submit'][@name = 'save']")).click();


   int idsCount = driver.findElements(By.xpath("//input[@type='checkbox']")).size();
   int[] ids = new int[idsCount];
   int i = 0;
   while (i<idsCount){
    String locator = String.format("table.dataTable tr:nth-of-type(%s) input[type = checkbox]", i+3);
    ids[i] = Integer.parseInt(driver.findElement(By.cssSelector(locator)).getAttribute("value"));
    i++;
   }
   Arrays.sort(ids);
   int id = ids[idsCount-1];
   System.out.println(id);

   String locatorById = String.format("//a[contains(@href, 'product_id=%s')]", id);
   Assert.assertTrue(driver.findElement(By.xpath(locatorById)).getAttribute("textContent").equals(name));
  }

 boolean isElementPresent(String locator) {
  return driver.findElements(By.xpath(locator)).size() ==1;
 }
}

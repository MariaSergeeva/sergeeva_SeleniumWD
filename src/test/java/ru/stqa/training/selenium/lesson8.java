package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Set;

public class lesson8 extends TestBase {
  @Test
  public void links(){

    login();
    driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
    driver.findElement(By.xpath("//a[@class = 'button'][contains(@href , 'edit_country')]")).click();
    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@type = 'submit'][@name = 'save']"))));
    Assert.assertTrue(driver.findElement(By.xpath("//h1")).getAttribute("innerText").equals(" Add New Country"));

    String mainWindow = driver.getWindowHandle();
    String newWindow = "";
    System.out.println(mainWindow);
    isThereOnlyMainWindow(mainWindow);
    String[] locators = {"iso_code_2", "iso_code_3", "tax_id_format", "postcode_format", "currency_code", "phone_code"};
    String[] titles = {"ISO 3166-1 alpha-2", "ISO 3166-1 alpha-3", "Regular expression", "Regular expression", "List of countries and capitals with currency and language", "List of country calling codes"};

    int i = 0;
    while (i < locators.length) {
      String locator = String.format("//tr//input[@name = '%s']//..//a//i[@class = 'fa fa-external-link']", locators[i]);
      driver.findElement(By.xpath(locator)).click();
      isThereTwoWindows(mainWindow);
      newWindow = getNewWindow(mainWindow);

      driver.switchTo().window(newWindow);
      Assert.assertTrue(driver.findElement(By.xpath("//h1[@id = 'firstHeading']")).getAttribute("textContent").equals(titles[i]));
      returnToMainWindow(mainWindow);
      isThereOnlyMainWindow(mainWindow);
      i++;
    }

    driver.findElement(By.xpath("//tr//textarea[@name = 'address_format']//..//a[@target = '_blank']")).click();
    isThereTwoWindows(mainWindow);
    newWindow = getNewWindow(mainWindow);

    driver.switchTo().window(newWindow);
    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@data-component-id = 'logo'][@id = 'cphTopContent_topHeader1_hlLogo']"))));
    Assert.assertTrue(driver.findElement(By.xpath("//a[@data-component-id = 'logo'][@id = 'cphTopContent_topHeader1_hlLogo']")).getAttribute("origin").equals("https://www.informatica.com"));
    returnToMainWindow(mainWindow);
    isThereOnlyMainWindow(mainWindow);
  }

  public boolean isThereOnlyMainWindow(String mainWindow){
    Set<String> allWindows = driver.getWindowHandles();
    System.out.println(allWindows);
    return (allWindows.size() == 1 && allWindows.contains(mainWindow));
  }

  public boolean isThereTwoWindows(String mainWindow){
    Set<String> allWindows = driver.getWindowHandles();
    System.out.println(allWindows);
    return (allWindows.size() == 2 && allWindows.contains(mainWindow));
  }

  public String getNewWindow(String mainWindow){
    String newWindow = "";
    Set<String> allWindows = driver.getWindowHandles();
    System.out.println(allWindows);
    String[] array = allWindows.toArray(new String[allWindows.size()]);
    int i = 0;
    while (i < array.length){
      if(!array[i].equals(mainWindow)){
        newWindow = array[i];
      }
      i++;
    }
    System.out.println(newWindow);
    return newWindow;
  }

  public void returnToMainWindow(String mainWindow){
    driver.close();
    driver.switchTo().window(mainWindow);
  }
}

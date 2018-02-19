package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import java.util.Arrays;

public class lesson5 extends TestBase {
  @Test
  public void countries() {
    login();
    driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
    int countriesCount = driver.findElements(By.xpath("//table[@class='dataTable']//tr[@class='row']")).size();
    String[] countries = new String[countriesCount];
    int[] zonesCounts = new int[countriesCount];
    int i = 0;
    while (i < countriesCount){
      int count = i+2;
      String locator1 = String.format("table.dataTable tr:nth-of-type(%s) a:not([title = Edit])", count);
      countries[i] = driver.findElement(By.cssSelector(locator1)).getAttribute("textContent");
      String locator2 = String.format("table.dataTable tr:nth-of-type(%s) td:nth-of-type(6)", count);
      zonesCounts[i] = Integer.parseInt(driver.findElement(By.cssSelector(locator2)).getAttribute("textContent"));
      i++;
    }
    String[] countriesByAlphabet = countries;
    Arrays.sort(countriesByAlphabet);
    Assert.assertTrue(arraysCompare(countries, countriesByAlphabet));

    driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
    int countriesWithGeozones = driver.findElements(By.xpath("//form[@name='geo_zones_form']//tr[@class='row']")).size();

    int k = 0;
    while (k<countriesWithGeozones){
      int count1 = k + 2;
      String locator1 = String.format("form[name = geo_zones_form] tr:nth-of-type(%s) a:not([title = Edit])", count1);
      driver.findElement(By.cssSelector(locator1)).click();
      String locator2 = "table.dataTable tr:not([class = header]) a#remove-zone";
      int zonesCount = driver.findElements(By.cssSelector(locator2)).size();
      String[] zones = new String[zonesCount];
      int l = 0;
      while (l<zonesCount){
        int count2 = l+2;
        String locator4 = String.format("table.dataTable tr:nth-of-type(%s) td:nth-of-type(3)", count2);
        zones[l] = driver.findElement(By.cssSelector(locator4)).getAttribute("textContent");
        l++;
      }
      String[] zonesByAlphabet = zones;
      Arrays.sort(zonesByAlphabet);
      Assert.assertTrue(arraysCompare(zones, zonesByAlphabet));
      driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
      k++;
    }





  }
  public boolean arraysCompare(String[] countries, String[] countriesByAlphabet) {
    return Arrays.equals(countries, countriesByAlphabet);
  }


}

package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

  @Test
  public void duck(){
    login();
    driver.get("http://localhost/litecart");
    String locator = "//div[@id = 'box-campaigns']//li[1]//a[@class = 'link'] %s";
    String mainPageName = driver.findElement(By.xpath(String.format(locator, "//div[@class = 'name']"))).getAttribute("textContent");
    String mainPageRegularPriceText = driver.findElement(By.xpath(String.format(locator, "//div[@class = 'price-wrapper']/s[@class = 'regular-price']"))).getAttribute("textContent");
    String mainPageRegularPriceColor = driver.findElement(By.xpath(String.format(locator, "//div[@class = 'price-wrapper']/s[@class = 'regular-price']"))).getCssValue("color");
    String mainPageRegularPriceStyle = driver.findElement(By.xpath(String.format(locator, "//div[@class = 'price-wrapper']/s[@class = 'regular-price']"))).getCssValue("font-size");
    String mainPageCampaignPriceText = driver.findElement(By.xpath(String.format(locator, "//div[@class = 'price-wrapper']/strong[@class = 'campaign-price']"))).getAttribute("textContent");
    String mainPageCampaignPriceColor = driver.findElement(By.xpath(String.format(locator, "//div[@class = 'price-wrapper']/strong[@class = 'campaign-price']"))).getCssValue("color");
    String mainPageCampaignPriceStyle = driver.findElement(By.xpath(String.format(locator, "//div[@class = 'price-wrapper']/strong[@class = 'campaign-price']"))).getCssValue("font-size");

    List mainPageRegularPriceColors = getColorParts(mainPageRegularPriceColor);
    Assert.assertTrue(mainPageRegularPriceColors.get(0).equals(mainPageRegularPriceColors.get(1)));
    Assert.assertTrue(mainPageRegularPriceColors.get(0).equals(mainPageRegularPriceColors.get(2)));

    List mainPageCampaignPriceColors = getColorParts(mainPageCampaignPriceColor);
    Assert.assertFalse(mainPageCampaignPriceColors.get(0).equals("0"));
    Assert.assertTrue(mainPageCampaignPriceColors.get(1).equals("0"));
    Assert.assertTrue(mainPageCampaignPriceColors.get(2).equals("0"));

    // доделать проверки стиля - зачеркнуто, размер


    driver.findElement(By.xpath(String.format(locator, ""))).click();
    String duckPageName = driver.findElement(By.xpath("//div[@id = 'box-product']//h1[@class = 'title']")).getAttribute("textContent");
    String locator2 = "//div[@id = 'box-product']//div[@class = 'information'] %s";
    String duckPageRegularPriceText = driver.findElement(By.xpath(String.format(locator2, "//div[@class = 'price-wrapper']/s[@class = 'regular-price']"))).getAttribute("textContent");
    String duckPageRegularPriceColor = driver.findElement(By.xpath(String.format(locator2, "//div[@class = 'price-wrapper']/s[@class = 'regular-price']"))).getCssValue("color");
    String duckPageRegularPriceStyle = driver.findElement(By.xpath(String.format(locator2, "//div[@class = 'price-wrapper']/s[@class = 'regular-price']"))).getCssValue("font-size");
    String duckPageCampaignPriceText = driver.findElement(By.xpath(String.format(locator2, "//div[@class = 'price-wrapper']/strong[@class = 'campaign-price']"))).getAttribute("textContent");
    String duckPageCampaignPriceColor = driver.findElement(By.xpath(String.format(locator2, "//div[@class = 'price-wrapper']/strong[@class = 'campaign-price']"))).getCssValue("color");
    String duckPageCampaignPriceStyle = driver.findElement(By.xpath(String.format(locator2, "//div[@class = 'price-wrapper']/strong[@class = 'campaign-price']"))).getCssValue("font-size");

    Assert.assertTrue(mainPageName.equals(duckPageName));
    Assert.assertTrue(mainPageCampaignPriceText.equals(duckPageCampaignPriceText));
    Assert.assertTrue(mainPageRegularPriceText.equals(duckPageRegularPriceText));

    List duckPageRegularPriceColors = getColorParts(duckPageRegularPriceColor);
    Assert.assertTrue(duckPageRegularPriceColors.get(0).equals(duckPageRegularPriceColors.get(1)));
    Assert.assertTrue(duckPageRegularPriceColors.get(0).equals(duckPageRegularPriceColors.get(2)));

    List duckPageCampaignPriceColors = getColorParts(duckPageCampaignPriceColor);
    Assert.assertFalse(duckPageCampaignPriceColors.get(0).equals("0"));
    Assert.assertTrue(duckPageCampaignPriceColors.get(1).equals("0"));
    Assert.assertTrue(duckPageCampaignPriceColors.get(2).equals("0"));

    // доделать проверки стиля - зачеркнуто, размер

  }

  public ArrayList getColorParts(String colorString){
    colorString = colorString.replace("rgba(", "");
    colorString = colorString.replace(")", "");
    colorString = colorString.replace(" ", "");
    ArrayList list = new ArrayList();
    for (String color : colorString.split(",")) {
      list.add(color);
    }
    return list;
  }
}

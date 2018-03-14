package pageobjects.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

public class CartPage extends Pages {
  public CartPage(EventFiringWebDriver driver) {
    super(driver);
  }

  public WebElement removeButton(){
    return driver.findElement(By.xpath("//button[@type = 'submit'][@name = 'remove_cart_item']"));
  }

  public WebElement shortcut(){
    return driver.findElement(By.cssSelector("ul.shortcuts li.shortcut:nth-of-type(1) a"));
  }

  public List<WebElement> prodactsTypesInCart() {
    return driver.findElements(By.xpath("//table[@class = 'dataTable rounded-corners']//td[@class = 'item']"));
  }

  public WebElement firstTypeProdactsCount(){
    return driver.findElement(By.cssSelector("div#order_confirmation-wrapper tbody tr:nth-of-type(2) td:nth-of-type(1)"));
  }

  public WebElement emptyCart(){
    return driver.findElement(By.xpath("//div[@id = 'checkout-cart-wrapper']//em"));
  }
}

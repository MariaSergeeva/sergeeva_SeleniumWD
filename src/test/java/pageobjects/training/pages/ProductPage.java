package pageobjects.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ProductPage extends Pages {
  public ProductPage(EventFiringWebDriver driver) {
    super(driver);
  }

  public WebElement addButton(){
    return driver.findElement(By.xpath("//button[@type='submit'][@name='add_cart_product']"));
  }

  public WebElement productsInCartCount(){
    return driver.findElement(By.xpath("//div[@id = 'cart']//span[@class = 'quantity']"));
  }

  public WebElement cart() {
    return driver.findElement(By.xpath("//div[@id = 'cart']//a[@class = 'link']"));
  }
}
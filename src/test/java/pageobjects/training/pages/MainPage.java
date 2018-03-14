package pageobjects.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class MainPage extends Pages {

  public MainPage(EventFiringWebDriver driver) {
    super(driver);
  }

  public MainPage openMainPage(){
    driver.get("http://localhost/litecart");
    return this;
  }
   public WebElement getFirstProduct(){
     return driver.findElement(By.cssSelector("div#box-most-popular a.link"));
  }


}

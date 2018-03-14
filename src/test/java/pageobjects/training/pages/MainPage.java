package pageobjects.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class MainPage extends Pages {

  public MainPage(EventFiringWebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public MainPage openMainPage(){
    driver.get("http://localhost/litecart");
    return this;
  }

  @FindBy(css = "div#box-most-popular a.link")
   public WebElement getFirstProduct;
}

package pageobjects.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ProductPage extends Pages {
  public ProductPage(EventFiringWebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  @FindBy(name = "add_cart_product")
  public WebElement addButton;

  @FindBy(xpath = "//div[@id = 'cart']//span[@class = 'quantity']")
  public WebElement productsInCartCount;

  @FindBy(xpath = "//div[@id = 'cart']//a[@class = 'link']")
  public WebElement cart;
}
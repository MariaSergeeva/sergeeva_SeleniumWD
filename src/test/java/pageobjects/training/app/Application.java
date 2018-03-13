package pageobjects.training.app;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Application {
  public EventFiringWebDriver driver;
  private WebDriverWait wait;

  public Application(){
    driver = new EventFiringWebDriver(new ChromeDriver());
    wait = new WebDriverWait(driver, 10);
  }

  public void quit() {
    driver.quit();
    driver = null;
  }


  public void deleteAllProductsFromCart() {
    String shortcutLocator = "ul.shortcuts li.shortcut:nth-of-type(1) a";

    int typesCount = driver.findElements(By.xpath("//table[@class = 'dataTable rounded-corners']//td[@class = 'item']")).size();
    while (typesCount > 1) {
      driver.findElement(By.cssSelector(shortcutLocator)).click();
      deleteProductFromCart();
      typesCount = driver.findElements(By.xpath("//table[@class = 'dataTable rounded-corners']//td[@class = 'item']")).size();
    }
    if (typesCount == 1) {
      deleteProductFromCart();
    }
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id = 'checkout-cart-wrapper']//em")));
    Assert.assertTrue(driver.findElement(By.xpath("//div[@id = 'checkout-cart-wrapper']//em")).getAttribute("textContent").equals("There are no items in your cart."));
  }

  public void goToCart() {
    driver.findElement(By.xpath("//div[@id = 'cart']//a[@class = 'link']")).click();
    wait.until(visibilityOfElementLocated(By.xpath("//div[@id = 'box-checkout-cart']")));
  }

  public void addProductToCart(int productsCount) {
    for (int i = 0; i < productsCount; i++) {
      driver.get("http://localhost/litecart");
      driver.findElement(By.cssSelector("div#box-most-popular a.link")).click();

      String addButton = "//button[@type='submit'][@name='add_cart_product']";
      String productsInCartCountLocator = "//div[@id = 'cart']//span[@class = 'quantity']";

      wait.until(visibilityOfElementLocated(By.xpath(addButton)));
      wait.until(visibilityOfElementLocated(By.xpath(productsInCartCountLocator)));

      WebElement productsInCartCount = driver.findElement(By.xpath(productsInCartCountLocator));
      String countString = productsInCartCount.getAttribute("textContent");
      int count = Integer.parseInt(countString);
      System.out.println(count);

      driver.findElement(By.xpath(addButton)).click();
      wait.until(not(attributeContains(productsInCartCount, "textContent", countString)));

      int newCount = Integer.parseInt(driver.findElement(By.xpath(productsInCartCountLocator)).getAttribute("textContent"));
      Assert.assertEquals(newCount, count + 1);
      System.out.println(newCount);
    }
  }

  public void deleteProductFromCart() {
    String productsInOrderCountLocator = "div#order_confirmation-wrapper tbody tr:nth-of-type(2) td:nth-of-type(1)";
    WebElement productsInOrderCount = driver.findElement(By.cssSelector(productsInOrderCountLocator));
    int count = Integer.parseInt(productsInOrderCount.getAttribute("textContent"));
    System.out.println(count);

    String removeLocator = "//button[@type = 'submit'][@name = 'remove_cart_item']";
    driver.findElement(By.xpath(removeLocator)).click();
    wait.until(ExpectedConditions.stalenessOf(productsInOrderCount));
    System.out.println(0);
  }
  public void login(){
    driver.get("http://localhost/litecart/admin/login.php");
    driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
    driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
    driver.findElement(By.xpath("//button[@name='login']")).click();
  }
}

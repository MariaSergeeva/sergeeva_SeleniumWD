package pageobjects.training.tests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.DataProviders;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.training.selenium.TestBase;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

//@RunWith(DataProviderRunner.class)
public class lesson11 extends NewTestBase {

  @Test
  //указание, какие данные передавать в тест, при этом сам тест должен принимать на вход параметр
  //@UseDataProvider(value = "newProvider", location = pageobjects.training.model.DataProviders.class)
  public void newCart() {
    login();
    addProductToCart(3);
    goToCart();
    deleteAllProducts();

  }

  public void deleteAllProducts() {
    String shortcutLocator = "ul.shortcuts li.shortcut:nth-of-type(1) a";

    int typesCount = driver.findElements(By.xpath("//table[@class = 'dataTable rounded-corners']//td[@class = 'item']")).size();
    while (typesCount > 1) {
      driver.findElement(By.cssSelector(shortcutLocator)).click();
      deleteProduct();
      typesCount = driver.findElements(By.xpath("//table[@class = 'dataTable rounded-corners']//td[@class = 'item']")).size();
    }
    if (typesCount == 1) {
      deleteProduct();
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

  public void deleteProduct() {
    String productsInOrderCountLocator = "div#order_confirmation-wrapper tbody tr:nth-of-type(2) td:nth-of-type(1)";
    WebElement productsInOrderCount = driver.findElement(By.cssSelector(productsInOrderCountLocator));
    int count = Integer.parseInt(productsInOrderCount.getAttribute("textContent"));
    System.out.println(count);

    String removeLocator = "//button[@type = 'submit'][@name = 'remove_cart_item']";
    driver.findElement(By.xpath(removeLocator)).click();
    wait.until(ExpectedConditions.stalenessOf(productsInOrderCount));
    System.out.println(0);
  }
}

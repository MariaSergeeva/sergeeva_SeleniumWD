package pageobjects.training.app;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.training.model.Account;
import pageobjects.training.pages.*;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;

public class Application {
  public EventFiringWebDriver driver;
  private WebDriverWait wait;
  private LoginPage loginPage;
  private MainPage mainPage;
  private ProductPage productPage;
  private CartPage cartPage;

  public Application(){
    driver = new EventFiringWebDriver(new ChromeDriver());
    wait = new WebDriverWait(driver, 10);
    loginPage = new LoginPage(driver);
    mainPage = new MainPage(driver);
    productPage = new ProductPage(driver);
    cartPage = new CartPage(driver);
  }

  public void quit() {
    driver.quit();
  }

  public void deleteAllProductsFromCart() {

    int typesCount = cartPage.prodactsTypesInCart().size();
    while (typesCount > 1) {
      cartPage.shortcut().click();
      deleteProductFromCart();
      typesCount = cartPage.prodactsTypesInCart().size();
    }
    if (typesCount == 1) {
      deleteProductFromCart();
    }
    wait.until(ExpectedConditions.visibilityOf(cartPage.emptyCart()));
    Assert.assertTrue(cartPage.emptyCart().getAttribute("textContent").equals("There are no items in your cart."));
  }

  public void goToCart() {
    productPage.cart().click();
    wait.until(ExpectedConditions.visibilityOf(cartPage.removeButton()));
  }

  public void addProductToCart(int productsCount) {
    for (int i = 0; i < productsCount; i++) {
      mainPage.openMainPage().getFirstProduct().click();
      wait.until(ExpectedConditions.visibilityOf(productPage.addButton()));
      wait.until(ExpectedConditions.visibilityOf(productPage.productsInCartCount()));

      String countString = productPage.productsInCartCount().getAttribute("textContent");
      int count = Integer.parseInt(countString);
      System.out.println(count);

      productPage.addButton().click();
      wait.until(not(attributeContains(productPage.productsInCartCount(), "textContent", countString)));

      int newCount = Integer.parseInt(productPage.productsInCartCount().getAttribute("textContent"));
      Assert.assertEquals(newCount, count + 1);
      System.out.println(newCount);
    }
  }

  public void deleteProductFromCart() {
    String count = cartPage.firstTypeProdactsCount().getAttribute("textContent");
    System.out.println(count);

    cartPage.removeButton().click();
    wait.until(ExpectedConditions.stalenessOf(cartPage.firstTypeProdactsCount()));
  }
  public void login(){
    Account account = new Account().admin();
    loginPage.openLoginPage().enterUsername(account.getUsername()).enterPassword(account.getPassword()).submitLogin();
  }
}

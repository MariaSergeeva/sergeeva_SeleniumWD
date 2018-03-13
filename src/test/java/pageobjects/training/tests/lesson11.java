package pageobjects.training.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.training.app.Application;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

//@RunWith(DataProviderRunner.class)
public class lesson11 extends NewTestBase {

  @Test
  //указание, какие данные передавать в тест, при этом сам тест должен принимать на вход параметр
  //@UseDataProvider(value = "newProvider", location = pageobjects.training.model.DataProviders.class)
  public void newCart() {
    app.login();
    app.addProductToCart(3);
    app.goToCart();
    app.deleteAllProductsFromCart();
  }
}

package pageobjects.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class LoginPage extends Pages {

  public LoginPage(EventFiringWebDriver driver) {
    super(driver);
  }

  public LoginPage openLoginPage() {
    driver.get("http://localhost/litecart/admin/login.php");
    return this;
  }

  public LoginPage enterUsername(String Username){
    driver.findElement(By.xpath("//input[@name='username']")).sendKeys(Username);
    return this;
  }
  public LoginPage enterPassword(String Password){
    driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Password);
    return this;
  }
  public void submitLogin(){
    driver.findElement(By.xpath("//button[@name='login']")).click();
  }
}

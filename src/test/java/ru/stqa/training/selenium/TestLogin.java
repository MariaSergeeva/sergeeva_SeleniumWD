package ru.stqa.training.selenium;
import org.junit.Test;
import org.openqa.selenium.By;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class TestLogin extends TestBase{
  @Test
  public void myTestLogin(){
    driver.get("http://localhost/litecart/admin/login.php");
    driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
    driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
    driver.findElement(By.xpath("//button[@name='login']")).click();
    wait.until(visibilityOfElementLocated(By.xpath("//img[@title='My Store']")));
  }
}
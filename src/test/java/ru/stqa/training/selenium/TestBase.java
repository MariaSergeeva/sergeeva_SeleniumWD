package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

  public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
  public WebDriver driver;
  public WebDriverWait wait;

  @Before
  public void start() {
    if (tlDriver.get() != null) {
      driver = tlDriver.get();
      wait = new WebDriverWait(driver, 10);
      return;
    }
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("unexpectedAlertBehaviour", "dismiss");
    driver = new ChromeDriver(caps);
    tlDriver.set(driver);
    wait = new WebDriverWait(driver, 10);

    Runtime.getRuntime().addShutdownHook(
            new Thread(() -> { driver.quit(); driver = null; }));
  }

  public void login(){
    driver.get("http://localhost/litecart/admin/login.php");
    driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
    driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
    driver.findElement(By.xpath("//button[@name='login']")).click();
  }
//    @Before
//  public void start(){
//    //запуск браузера Chrome с дополнительными настройками
//    ChromeOptions options = new ChromeOptions();
//    options.addArguments("start-fullscreen");
//    DesiredCapabilities caps = new DesiredCapabilities();
//    caps.setCapability("unexpectedAlertBehaviour", "dismiss");
//    caps.setCapability(ChromeOptions.CAPABILITY, options);
//    driver = new ChromeDriver(caps);
//    System.out.println(((HasCapabilities) driver).getCapabilities());
//
//    //старая схема запуска Firefox для версий 45 и ниже
//    DesiredCapabilities caps = new DesiredCapabilities();
//    caps.setCapability(FirefoxDriver.MARIONETTE, false);
//    WebDriver driver = new FirefoxDriver(caps);
//
//    //схема для запуска дев.версии Firefox
//    DesiredCapabilities caps = new DesiredCapabilities();
//    driver = new FirefoxDriver(new FirefoxBinary(new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe")),
//            new FirefoxProfile(), caps); //путь к запускаемому файлу нужной версии браузера
//
//
//    //driver = new ChromeDriver();
//    driver = new FirefoxDriver();
//    //driver = new InternetExplorerDriver();
//    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//    wait = new WebDriverWait(driver, 10);
//  }


  @After
  public void stop() {
    //driver.quit();
    //driver = null;
  }
}
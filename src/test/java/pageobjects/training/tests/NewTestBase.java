package pageobjects.training.tests;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import static com.google.common.io.Files.copy;

public class NewTestBase {

  public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
  public EventFiringWebDriver driver;
  public WebDriverWait wait;
  public BrowserMobProxy proxy;

  public static class MyListener extends AbstractWebDriverEventListener {
    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
      System.out.println(by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
      System.out.println(by +  " found");
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
      System.out.println(throwable);
      File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      File screen = new File("screen-" + System.currentTimeMillis() + ".png");
      try {
        copy(tmp, screen);
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println(screen);
      }
    }


  @Before
  public void start() {
    if (tlDriver.get() != null) {
      driver = (EventFiringWebDriver) tlDriver.get();
      wait = new WebDriverWait(driver, 10);
      return;
    }
//    DesiredCapabilities caps = new DesiredCapabilities();
//    caps.setCapability("unexpectedAlertBehaviour", "dismiss");
//    driver = new ChromeDriver(caps);
//    tlDriver.set(driver);

    proxy = new BrowserMobProxyServer();
    proxy.start(0);
    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

    DesiredCapabilities cap = DesiredCapabilities.chrome();

    cap.setCapability(CapabilityType.PROXY, seleniumProxy);

    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
    cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

    driver = new EventFiringWebDriver(new ChromeDriver(cap));
    driver.register(new MyListener());
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
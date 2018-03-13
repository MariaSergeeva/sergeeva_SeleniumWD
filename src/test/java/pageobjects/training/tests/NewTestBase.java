package pageobjects.training.tests;
import org.junit.After;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import pageobjects.training.app.Application;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import static com.google.common.io.Files.copy;

public class NewTestBase {
  public Application app = new Application();

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

    @After
    public void stop(){
    app.quit();
    }
}
package pageobjects.training.model;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class DataProviders {

  @DataProvider
  public static Object[][] newProvider(){
    return new Object[][]{
            {
              //сюда можно добавлять объекты, сконструированные на основе дополнительного класса-конструктора
            }
           };
  }

}

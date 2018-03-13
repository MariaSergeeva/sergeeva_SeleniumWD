package pageobjects.training.model;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class DataProviders {
  @DataProvider
  public static int getProductsCount(){
    int count = (int) (Math.floor(Math.random() * (10 - 1)) + 1);
    return count;
  }

}

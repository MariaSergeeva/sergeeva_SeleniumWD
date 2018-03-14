package pageobjects.training.model;

public class Account {
  private static String username;
  private static String password;

  public Account wihtUsername(String username) {
    this.username = username;
    return this;
  }

  public Account wihtPassword(String password) {
    this.password = password;
    return this;
  }

  public String getUsername(){
    return Account.username;
  }
  public String getPassword(){
    return Account.password;
  }

  public Account(){
  }

  public Account admin(){
    this.username = "admin";
    this.password = "admin";
    return this;
  }

}

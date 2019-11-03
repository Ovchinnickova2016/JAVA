package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

import java.io.UnsupportedEncodingException;

public class LoginHelper extends HelperBase{
  public LoginHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String password) throws UnsupportedEncodingException {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    click(By.cssSelector("input[type='submit']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[type='submit']"));

  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"),password);
    type(By.name("password_confirm"),password);
    click(By.cssSelector("input[value='Update User']"));
  }
}

package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

import java.io.UnsupportedEncodingException;

public class LoginAdminHelper extends HelperBase{
  public LoginAdminHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String password) throws UnsupportedEncodingException, InterruptedException {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    click(By.cssSelector("input[type='submit']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[type='submit']"));
    wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
    click(By.xpath("//a[@href='manage_user_edit_page.php?user_id=2']"));
    Thread.sleep(1000);
    click(By.cssSelector("input[value='—бросить пароль']"));
    Thread.sleep(1000);
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"),password);
    type(By.name("password_confirm"),password);
    click(By.cssSelector("input[value='Update User']"));
  }
}

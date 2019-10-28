package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.UnsupportedEncodingException;

public class RegistrationHelper extends HelperBase {

  public RegistrationHelper(ApplicationManager app) {
   super(app);
  }

  public void start(String username, String email) throws UnsupportedEncodingException {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
   type(By.name("username"), username);
    type(By.name("email"), email);
    String s = "ааааамма";
    String source = "input[value='Зарегистрироваться']";//..getBytes("utf-8").toString();
    byte bytes[] = source.getBytes("windows-1251");
    String source2 = new String(source.getBytes("windows-1251"), "windows-1251");
    System.out.println(s + "   "+source + "         "  + source2);
    //click(By.cssSelector("input[value='Зарегистрироваться']"));
    click(By.cssSelector(source2));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"),password);
    type(By.name("password_confirm"),password);
    click(By.cssSelector("input[value='Update User']"));
  }
}

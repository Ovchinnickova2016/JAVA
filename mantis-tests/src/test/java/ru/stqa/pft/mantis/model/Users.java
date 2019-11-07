package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;
import org.openqa.selenium.By;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Users extends ForwardingSet<UserData> {

  private Set<UserData> delegate;

  public Users(Users users) {
    this.delegate = new HashSet<UserData>(users.delegate);
  }

  public Users() {
    this.delegate = new HashSet<UserData>();
  }
  public Users(Collection<UserData> users) {
    this.delegate = new HashSet<UserData>(users);
  }

  @Override
  protected Set<UserData> delegate() {
    return delegate;
  }
 /* public Users changePassword(){
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    click(By.cssSelector("input[type='submit']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[type='submit']"));
    wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
    String xpath = String.format("//a[@href='manage_user_edit_page.php?user_id=%s']", userId);
    click(By.xpath(xpath));
    // click(By.xpath("//a[@href='manage_user_edit_page.php?user_id=74']"));
    Thread.sleep(1000);
    click(By.cssSelector("input[value='—бросить пароль']"));
    Thread.sleep(1000);
  }
*/
}

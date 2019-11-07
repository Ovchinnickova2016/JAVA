package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

public class UserHelper extends HelperBase {
  public UserHelper(ApplicationManager app) {
    super(app);
  }
  public void selectUserById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id +"']")).click();
  }


  public void delete(UserData user) {
    selectUserById(user.getId());
  }
}

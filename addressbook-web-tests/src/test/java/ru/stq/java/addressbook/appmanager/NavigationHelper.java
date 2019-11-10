package ru.stq.java.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stq.java.addressbook.model.GroupData;

public class NavigationHelper extends HelperBase{

 private WebDriver wd;

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    if (isElementPresent(By.tagName("h1"))
      //&& wd.findElement(By.tagName("h1")).getText().equals("Groups")
     && isElementPresent(By.name("new")))
     {
      return;
    }
    click(By.linkText("groups"));
  }

  public void goToHomePage(){
    if (isElementPresent(By.id("maintable"))){
      return;
    }
    click(By.linkText("home"));
  }

  public void groupNeedfullPage(GroupData group) {
    wd.findElement(By.linkText("group page \""+group.getName()+"\"")).click();
  }

  public void groupCurrentPage(GroupData group) {
    wd.findElement(By.linkText("group page \""+group.getName()+"\"")).click();
  }
}

package ru.stq.java.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stq.java.addressbook.model.ContactsData;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactsCreation() {
    click(By.xpath("//input[@name='submit'][2]"));
  }

  public void fillContactsForm(ContactsData contactsData, boolean creation) {

    type(By.name("firstname"), contactsData.getFirstName());
    type(By.name("lastname"), contactsData.getLastName());
    type(By.name("home"), contactsData.getPhoneNumber());
    type(By.name("email"), contactsData.getEmail());
    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactsData.getGroup());
    }
    else{
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void selectContact() {
    click(By.id("7"));
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void gotoContactPage() {
    click(By.linkText("home"));
  }

  public void submitContactDeletion() {
    wd.switchTo().alert().accept();
  }

  public void returnToContactPage() {
    click(By.linkText("home"));
  }

  public void initContactModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }
}

package ru.stq.java.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stq.java.addressbook.model.ContactsData;

public class ContactHelper extends HelperBase{

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void submitContactsCreation() {
    click(By.xpath("//input[@name='submit'][2]"));
  }

  public void fillContactsForm(ContactsData contactsData) {

    type(By.name("firstname"), contactsData.getFirstName());
    type(By.name("lastname"), contactsData.getLastName());
    type(By.name("home"), contactsData.getPhoneNumber());
    type(By.name("email"), contactsData.getEmail());
  }

  public void selectContact() {
    click(By.id("5"));
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
}

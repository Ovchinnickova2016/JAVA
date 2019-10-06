package ru.stq.java.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stq.java.addressbook.model.ContactsData;
import ru.stq.java.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
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

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void goToContactsPage() {
    click(By.linkText("add new"));
  }

  public void createContact(ContactsData contactsData) {
    goToContactsPage();
    fillContactsForm(contactsData, true);
    submitContactsCreation();
    returnToContactPage();
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactsData> getContactList() {
    List<ContactsData> contacts = new ArrayList<ContactsData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements){
      String name = element.getText();
      String thurname = element.getText();
    //  int id = Integer.parseInt(element.findElements(By.tagName("td")).toString());//getAttribute("value"));
     int id = Integer.parseInt(String.valueOf(element.findElement(By.tagName("input")).getAttribute("value")));
      List<WebElement> list = element.findElements(By.tagName("td"));
      thurname = list.get(1).getText();
      name = list.get(2).getText();
      ContactsData contact = new ContactsData(id, name, thurname, "44423422", "ovchinnickova.anast@gmail.com", "group3");
      contacts.add(contact);
    }
    return contacts;
  }
}

package ru.stq.java.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stq.java.addressbook.model.Contacts;
import ru.stq.java.addressbook.model.ContactsData;

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
    type(By.name("home"), contactsData.getHomePhone());
    type(By.name("mobile"), contactsData.getMobilePhone());
    type(By.name("work"), contactsData.getWorkPhone());
    type(By.name("email"), contactsData.getEmail());
    type(By.name("address"), contactsData.getAddress());
    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactsData.getGroup());
    }
    else{
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id +"']")).click();
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
  public void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']",id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
    //wd.findElement(By.cssSelector("img[alt='Edit']")).click();
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

  public void create(ContactsData contactsData) {
    goToContactsPage();
    fillContactsForm(contactsData, true);
    submitContactsCreation();
    contactCache = null;
    returnToContactPage();
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }
  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache!=null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements){
      int id = Integer.parseInt(String.valueOf(element.findElement(By.tagName("input")).getAttribute("value")));
      List<WebElement> list = element.findElements(By.tagName("td"));
      String lastname = list.get(1).getText();
      String name = list.get(2).getText();
      String address = list.get(3).getText();
      String email = list.get(4).getText();
      String[] phones = list.get(5).getText().split("\n");
      contactCache.add(new ContactsData().withId(id).
        withFirstName(name).withLastName(lastname).withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkHome(phones[2]).withEmail(email).withAddress(address).withGroup("group3"));
    }
    return new Contacts(contactCache);
  }
  public void modify( ContactsData contact) {
   initContactModificationById(contact.getId());
   fillContactsForm(contact, false);
   submitContactModification();
   contactCache = null;
   returnToContactPage();
  }

  public void delete(ContactsData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    submitContactDeletion();
    contactCache = null;
    returnToContactPage();
  }
  public ContactsData infoFromEditForm(ContactsData contact){
    initContactModificationById(contact.getId());
    String name = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new ContactsData().withId(contact.getId()).withFirstName(name).withLastName(lastname)
      .withHomePhone(home).withMobilePhone(mobile).withWorkHome(work).withEmail(email).withAddress(address);
  }


}

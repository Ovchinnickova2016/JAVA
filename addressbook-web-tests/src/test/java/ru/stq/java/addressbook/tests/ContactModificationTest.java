package ru.stq.java.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.ContactsData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.getContactHelper().gotoContactPage();
    if (!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact (new ContactsData("nastya", "Ovchinnickova", "44423422", "ovchinnickova.anast@gmail.com", "group3"));
    }
  }

  @Test
  public void testContactModification (){
    List<ContactsData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    ContactsData contact = new ContactsData(before.get(index).getId(),"nastya", "Ovchinnickova", "44423422", "ovchinnickova.anast@gmail.com", "group3");
    app.getContactHelper().modifyContact(index);
    List<ContactsData> after = app.getContactHelper().getContactList();
    Assert.assertEquals( after.size() ,before.size());
    before.remove(index);
    before.add(contact);
    Comparator<? super ContactsData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}

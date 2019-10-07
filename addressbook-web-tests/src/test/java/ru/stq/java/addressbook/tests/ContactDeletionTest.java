package ru.stq.java.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.ContactsData;

import java.util.List;

public class ContactDeletionTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.getContactHelper().gotoContactPage();
    if (!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact (new ContactsData("nastya", "Ovchinnickova", "44423422", "ovchinnickova.anast@gmail.com", "group3"));
    }
  }

  @Test
  public void testContactDeletion(){
    List<ContactsData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().submitContactDeletion();
    app.getContactHelper().returnToContactPage();
    List<ContactsData> after = app.getContactHelper().getContactList();
    Assert.assertEquals( after.size() ,before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals( before, after);
  }
}

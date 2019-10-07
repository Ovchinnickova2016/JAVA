package ru.stq.java.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.ContactsData;

import java.util.List;

public class ContactDeletionTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.contact().gotoContactPage();
    if (app.contact().list().size() == 0){
      app.contact().create(new ContactsData().withEmail("ovchinnickova.anast@gmail.com").withFirstName("nastya").withLastName("Ovchinnickova").withPhoneNumber("44423422").withGroup("group3"));
    }
  }

  @Test
  public void testContactDeletion(){
    List<ContactsData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    List<ContactsData> after = app.contact().list();
    Assert.assertEquals( after.size() ,before.size() - 1);
    before.remove(index);
    Assert.assertEquals( before, after);
  }
}

package ru.stq.java.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.ContactsData;
import java.util.Set;

public class ContactDeletionTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.contact().gotoContactPage();
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactsData().withEmail("ovchinnickova.anast@gmail.com").withFirstName("nastya").withLastName("Ovchinnickova").withPhoneNumber("44423422").withGroup("group3"));
    }
  }

  @Test
  public void testContactDeletion(){
    Set<ContactsData> before = app.contact().all();
    ContactsData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Set<ContactsData> after = app.contact().all();
    Assert.assertEquals( after.size() ,before.size() - 1);
    before.remove(deletedContact);
    Assert.assertEquals( before, after);
  }
}

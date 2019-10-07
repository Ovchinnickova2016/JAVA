package ru.stq.java.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.ContactsData;
import java.util.Set;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.contact().gotoContactPage();
    if (app.contact().all().size() == 0){
      ContactsData contact = new ContactsData().withEmail("ovchinnickova.anast@gmail.com").withFirstName("nastya").withLastName("Ovchinnickova").withPhoneNumber("44423422").withGroup("group3");
    }
  }

  @Test
  public void testContactModification (){
    Set<ContactsData> before = app.contact().all();
    ContactsData modifiedContact = before.iterator().next();
    ContactsData contact = new ContactsData().withId(modifiedContact.getId()).withEmail("ovchinnickova.anast@gmail.com").withFirstName("nastyaaa").withLastName("Ovchinnickova").withPhoneNumber("44423422").withGroup("group3");
    app.contact().modify(contact);
    Set<ContactsData> after = app.contact().all();
    Assert.assertEquals( after.size() ,before.size());
    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}

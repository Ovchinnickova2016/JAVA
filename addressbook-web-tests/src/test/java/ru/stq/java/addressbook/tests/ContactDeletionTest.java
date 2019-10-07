package ru.stq.java.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.Contacts;
import ru.stq.java.addressbook.model.ContactsData;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
    Contacts before = app.contact().all();
    ContactsData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.contact().all();
    assertEquals( after.size() ,before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}

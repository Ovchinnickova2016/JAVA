package ru.stq.java.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stq.java.addressbook.model.ContactsData;
import java.util.Set;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    Set<ContactsData> before = app.contact().all();
    app.contact().goToContactsPage();
    ContactsData contact = new ContactsData().withEmail("ovchinnickova.anast@gmail.com").withFirstName("nastya").withLastName("Ovchinnickova").withPhoneNumber("44423422").withGroup("group3");
    app.contact().create(contact);
    Set<ContactsData> after = app.contact().all();
    Assert.assertEquals(after.size() ,before.size() +1);
    contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}

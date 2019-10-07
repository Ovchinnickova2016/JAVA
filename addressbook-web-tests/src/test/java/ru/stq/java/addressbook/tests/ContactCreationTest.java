package ru.stq.java.addressbook.tests;

import org.testng.annotations.*;
import ru.stq.java.addressbook.model.Contacts;
import ru.stq.java.addressbook.model.ContactsData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    Contacts before = app.contact().all();
    app.contact().goToContactsPage();
    ContactsData contact = new ContactsData().withEmail("ovchinnickova.anast@gmail.com").withFirstName("nastya").withLastName("Ovchinnickova").withPhoneNumber("44423422").withGroup("group3");
    app.contact().create(contact);
    assertThat(app.contact().count(),equalTo(before.size() +1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadContactCreation() throws Exception {

    Contacts before = app.contact().all();
    app.contact().goToContactsPage();
    ContactsData contact = new ContactsData().withEmail("ovchinnickova.anast@gmail.com'").withFirstName("nastya").withLastName("Ovchinnickova").withPhoneNumber("44423422").withGroup("group3");
    app.contact().create(contact);
    assertThat(app.contact().count(),equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}

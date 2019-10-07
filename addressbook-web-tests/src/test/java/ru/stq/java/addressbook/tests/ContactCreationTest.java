package ru.stq.java.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stq.java.addressbook.model.ContactsData;


import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    List<ContactsData> before = app.contact().list();
    app.contact().goToContactsPage();
   // ContactsData contact = new ContactsData("nastya", "Ovchinnickova", "44423422", "ovchinnickova.anast@gmail.com", "group3");
    ContactsData contact = new ContactsData().withEmail("ovchinnickova.anast@gmail.com").withFirstName("nastya").withLastName("Ovchinnickova").withPhoneNumber("44423422").withGroup("group3");
    app.contact().create(contact);
    List<ContactsData> after = app.contact().list();
    Assert.assertEquals(after.size() ,before.size() +1);
    contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Comparator<? super ContactsData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}

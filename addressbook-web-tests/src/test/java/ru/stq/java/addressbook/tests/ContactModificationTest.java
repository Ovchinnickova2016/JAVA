package ru.stq.java.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.Contacts;
import ru.stq.java.addressbook.model.ContactsData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size()==0){
      app.contact().gotoContactPage();
      app.contact().create(new ContactsData().withEmail("ovchinnickova.anast@gmail.com").withEmail2("ovch@gmail.com").withEmail3("galim@gmail.com").withFirstName("nastya").withLastName("Ovchinnickova").withMobilePhone("44423422").withWorkPhone("231").withHomePhone("3232").withAddress("2 dd 3").withGroup("group3"));
    }
  }

  @Test
  public void testContactModification (){
    Contacts before = app.db().contacts();
    ContactsData modifiedContact = before.iterator().next();
    ContactsData contact = new ContactsData().withId(modifiedContact.getId()).withEmail("anast@gmail.com").withEmail2("ovh@gmail.com").withEmail3("gal@gmail.com").withFirstName("ana").withLastName("Ovickova").withMobilePhone("443422").withWorkPhone("255531").withHomePhone("3050232").withAddress("2 street").withGroup("group43");
    app.contact().gotoContactPage();
    app.contact().modify(contact);
    assertEquals( app.contact().count() ,before.size());
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}

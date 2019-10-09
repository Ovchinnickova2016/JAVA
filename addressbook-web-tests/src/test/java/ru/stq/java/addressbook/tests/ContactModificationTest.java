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
    app.contact().gotoContactPage();
    if (app.contact().all().size() == 0){
      ContactsData contact = new ContactsData().withEmail("ovchinnickova.anast@gmail.com").withEmail2("ovch@gmail.com").withEmail3("galim@gmail.com").withFirstName("nastya").withLastName("Ovchinnickova").withMobilePhone("44423422").withWorkPhone("231").withHomePhone("3232").withAddress("2 dd 3").withGroup("group3");
    }
  }

  @Test
  public void testContactModification (){
    Contacts before = app.contact().all();
    ContactsData modifiedContact = before.iterator().next();
    ContactsData contact = new ContactsData().withId(modifiedContact.getId()).withEmail("ovchinnickova.anast@gmail.com").withEmail2("ovch@gmail.com").withEmail3("galim@gmail.com").withFirstName("nastya").withLastName("Ovchinnickova").withMobilePhone("44423422").withWorkPhone("231").withHomePhone("3232").withAddress("2 dd 3").withGroup("group3");
    app.contact().modify(contact);
    assertEquals( app.contact().count() ,before.size());
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(modifiedContact)));
  }

}

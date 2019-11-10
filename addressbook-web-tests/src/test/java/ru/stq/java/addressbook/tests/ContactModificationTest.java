package ru.stq.java.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.Contacts;
import ru.stq.java.addressbook.model.ContactsData;
import ru.stq.java.addressbook.model.GroupData;
import ru.stq.java.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    Groups groups  = app.db().groups();
    if (groups.size()==0){
      app.goTo().groupPage();
      GroupData group = new GroupData().withName("group3'");
      app.group().create(group);
    }
    if (app.db().contacts().size()==0){
      app.contact().gotoContactPage();
      app.contact().create(new ContactsData().withEmail("ovchinnickova.anast@gmail.com").withEmail2("ovch@gmail.com")
        .withEmail3("galim@gmail.com").withFirstName("nastya").withLastName("Ovchinnickova").withMobilePhone("44423422")
        .withWorkPhone("231").withHomePhone("3232").withAddress("2 dd 3").inGroup(groups.iterator().next()));
    }
  }

  @Test
  public void testContactModification (){
    Groups groups  = app.db().groups();
    Contacts before = app.db().contacts();
    ContactsData modifiedContact = before.iterator().next();
    ContactsData contact = new ContactsData().withId(modifiedContact.getId()).withEmail("anast@gmail.com").withEmail2("ovh@gmail.com")
      .withEmail3("gal@gmail.com").withFirstName("ana").withLastName("Ovickova").withMobilePhone("443422").withWorkPhone("255531")
      .withHomePhone("3050232").withAddress("2 street").inGroup(groups.iterator().next());
    app.contact().gotoContactPage();
    app.contact().modify(contact);
    assertEquals( app.contact().count() ,before.size());
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }

}

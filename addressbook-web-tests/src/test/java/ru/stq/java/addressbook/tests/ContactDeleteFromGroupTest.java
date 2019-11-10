package ru.stq.java.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.Contacts;
import ru.stq.java.addressbook.model.ContactsData;
import ru.stq.java.addressbook.model.GroupData;
import ru.stq.java.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    //check if there are any groups
    if (groups.size()==0){
      app.goTo().groupPage();
      GroupData group = new GroupData().withName("group3'");
      app.group().create(group);
    }
    //check if there are any contacts add to group
    if (contacts.size()==0){
      app.contact().gotoContactPage();
      app.contact().create(new ContactsData().withEmail("ovchinnickova.anast@gmail.com").withEmail2("ovch@gmail.com")
        .withEmail3("galim@gmail.com").withFirstName("nastya").withLastName("Ovchinnickova").withMobilePhone("44423422")
        .withWorkPhone("231").withHomePhone("3232").withAddress("2 dd 3").inGroup(groups.iterator().next()));
    }

  }
  @Test
  public void testContactDeleteFromGroup(){
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    Contacts before = app.db().contacts();
    GroupData group = app.db().getGroupFromDB(NewContact(contacts,groups).getId());
    Contacts contacts_ = group.getContacts();
    ContactsData deletedContact = contacts_.iterator().next();
    app.contact().deleteContactFromGroup(deletedContact,group.getName());
    app.goTo().groupNeedfullPage(group);

    Contacts afterDeletionContacts = app.db().getGroupFromDB(group.getId()).getContacts();
    assertThat(afterDeletionContacts, equalTo(contacts_.without(deletedContact)));

  }
  private GroupData NewContact(Contacts beforeContact, Groups groups) {
    for (ContactsData contact : beforeContact) {
      if (contact.getGroups().size() > 0) {
        Groups groupsWithContacts =  contact.getGroups();
        return groupsWithContacts.iterator().next();
      }
    }
    ContactsData addedContact = beforeContact.iterator().next();
    GroupData group = groups.iterator().next();
    app.goTo().goToHomePage();
    app.contact().addContactToGroup(addedContact, group);
    app.goTo().groupCurrentPage(group);
    return group;
  }
}

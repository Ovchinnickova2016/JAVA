package ru.stq.java.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.*;

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
    Groups allGroups = app.db().groups();
    Contacts allContacts = app.db().contacts();
    GroupContact groupWithContact =groupWithContacts(allContacts,allGroups);
    GroupData group = groupWithContact.getGroup();
    Contacts contacts = group.getContacts();
    ContactsData  deletedContact = groupWithContact.getContact();
    app.contact().deleteContactFromGroup(deletedContact,group.getId());
    Contacts afterDeletionContacts = app.db().getGroupsFromDB(group.getId()).getContacts();
    assertThat(afterDeletionContacts, equalTo(contacts.without(deletedContact)));
  }
  private GroupContact groupWithContacts(Contacts allContacts, Groups allGroups) {
    for (ContactsData contact : allContacts) {
      if (contact.getGroups().size() > 0) {
        GroupData group =  contact.getGroups().iterator().next();
       return new GroupContact().withGroupInContact(group).withContactInGroup(contact);
      }
    }
    GroupData group = allGroups.iterator().next();
    ContactsData contact = allContacts.iterator().next();
    app.goTo().goToHomePage();
    app.contact().addContactToGroup(contact, group);
    app.goTo().groupCurrentPage(group);
    return new GroupContact().withGroupInContact(group).withContactInGroup(contact);
  }
}

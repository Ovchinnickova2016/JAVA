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
    Contacts contactsBefore = app.db().contacts();
    GroupData group = app.db().getGroupsFromDB(groupWithContacts(contactsBefore,groups).getId());
    Contacts contacts = group.getContacts();
    ContactsData deletedContact = contacts.iterator().next();
    app.contact().deleteContactFromGroup(deletedContact,group.getId());
    Contacts afterDeletionContacts = app.db().getGroupsFromDB(group.getId()).getContacts();
    assertThat(afterDeletionContacts, equalTo(contacts.without(deletedContact)));
  }
  private GroupData groupWithContacts(Contacts beforeContacts, Groups groups) {
    for (ContactsData contact : beforeContacts) {
      if (contact.getGroups().size() > 0) {
        Groups groupsWithContacts =  contact.getGroups();
        return groupsWithContacts.iterator().next();
      }
    }
    //если контактов нет, создаю контакт и помещаю его в группу
    int nextId = app.contact().getNextId(beforeContacts);//for to know the next id for a new contact
    app.contact().gotoContactPage();
    app.contact().create(new ContactsData().withEmail("ovchinnickova.anast@gmail.com").withEmail2("ovch@gmail.com")
      .withEmail3("galim@gmail.com").withFirstName("test2_new_add_to_group").withLastName("Ovchinnickova").withMobilePhone("44423422")
      .withWorkPhone("231").withHomePhone("3232").withAddress("2 dd 3").withId(nextId));
    ContactsData newContact = app.db().contacts().getContactById(app.db().contacts(), nextId); //new just created contact
    GroupData group = groups.iterator().next();
    app.goTo().goToHomePage();
    app.contact().addContactToGroup(newContact, group);
    app.goTo().groupCurrentPage(group);
    return group; //то есть в этой группе гарантированно один (только что созданный контакт) и его и удаляем в последствии из этой группы
  }
}

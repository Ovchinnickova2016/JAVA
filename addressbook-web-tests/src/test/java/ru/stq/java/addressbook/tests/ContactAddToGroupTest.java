package ru.stq.java.addressbook.tests;

import com.sun.xml.fastinfoset.sax.Properties;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.Contacts;
import ru.stq.java.addressbook.model.ContactsData;
import ru.stq.java.addressbook.model.GroupData;
import ru.stq.java.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTest extends TestBase {
  private final Properties properties = new Properties();;

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
    //check if there are any contacts to add
   if (contacts.size()==0){
      app.contact().gotoContactPage();
      app.contact().create(new ContactsData().withEmail("ovchinnickova.anast@gmail.com").withEmail2("ovch@gmail.com")
        .withEmail3("galim@gmail.com").withFirstName("nastya").withLastName("Ovchinnickova").withMobilePhone("44423422")
        .withWorkPhone("231").withHomePhone("3232").withAddress("2 dd 3").inGroup(groups.iterator().next()));
    }
  }
    @Test
  public void testContactAddToGroup(){
      Groups groups = app.db().groups();
      GroupData group = groups.iterator().next();
      Contacts before = app.db().contacts();
      ContactsData addedContact = NewContact(before);
      app.contact().goToHomePage();
      app.contact().addContactToGroup(addedContact, group);
      app.contact().goToHomePage();
      Groups afterAdditionContacts = app.db().getContactsFromDB(addedContact.getId()).getGroups();
      assertThat(afterAdditionContacts, equalTo(addedContact.getGroups().withAdded(group)));
    }
  private ContactsData NewContact(Contacts before) {
    for (ContactsData contact: before){
      if (contact.getGroups().size() == 0){
        return contact;
      }
    }
    app.contact().create(new ContactsData().withEmail("ovchinnickova.anast@gmail.com").withEmail2("ovch@gmail.com")
      .withEmail3("galim@gmail.com").withFirstName("nastya").withLastName("Ovchinnickova").withMobilePhone("44423422")
      .withWorkPhone("231").withHomePhone("3232").withAddress("2 dd 3"));
    Contacts newList = app.db().contacts();
    return newList.iterator().next();
  }
}

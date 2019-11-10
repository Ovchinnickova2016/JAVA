package ru.stq.java.addressbook.tests;

import com.sun.xml.fastinfoset.sax.Properties;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.Contacts;
import ru.stq.java.addressbook.model.ContactsData;
import ru.stq.java.addressbook.model.GroupData;
import ru.stq.java.addressbook.model.Groups;

//import java.util.Properties;

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
      Groups group = app.db().groups();
      Contacts contacts = app.db().contacts();
      GroupData group1 = groups.iterator().next();
      Contacts before = app.db().contacts();
      ContactsData addedContact = NewContact(before);
      app.contact().goToHomePage();
      app.contact().addContactToGroup(addedContact, group1);
      app.contact().goToHomePage();
      Assert.assertEquals(addedContact.getGroups().withAdded(group1), equals(app.db().getContactFromDB(addedContact.getId()).getGroups()));
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

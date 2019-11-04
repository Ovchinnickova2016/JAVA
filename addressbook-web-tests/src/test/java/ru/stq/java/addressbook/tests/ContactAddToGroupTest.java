package ru.stq.java.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.Contacts;
import ru.stq.java.addressbook.model.ContactsData;
import ru.stq.java.addressbook.model.GroupData;
import ru.stq.java.addressbook.model.Groups;

import java.util.Properties;

public class ContactAddToGroupTest extends TestBase {
  private final Properties properties = new Properties();;
  Groups groups = app.db().groups();
  @BeforeMethod
  public void ensurePreconditions() {
    //check if there are any groups
    if (groups.size()==0){
      app.goTo().groupPage();
      GroupData group = new GroupData().withName("group3'");
      app.group().create(group);
    }
  }
    @Test
  public void testContactAddToGroup(){
      Contacts before = app.db().contacts();
     // ContactsData addedContact = before.iterator().next();
      app.contact().goToContactsPage();
      //app.contact().addContactToGroup(addedContact);
      app.contact().goToGroupPage();
    }
}

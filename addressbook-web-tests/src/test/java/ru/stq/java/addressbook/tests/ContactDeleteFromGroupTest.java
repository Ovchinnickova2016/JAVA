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
  public void testContactDeleteFromGroup(){
    Contacts before = app.db().contacts();
    ContactsData deletedContact = before.iterator().next();
    app.contact().goToContactsPage();
    app.contact().deleteContactFromGroup(deletedContact, deletedContact.getId());
    app.contact().goToGroupPage();

    //assertThat(after, equalTo(before.without(deletedGroup)));
  }
}

package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stq.java.addressbook.model.ContactsData;
import ru.stq.java.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.getContactHelper().goToContactsPage();
    List<GroupData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact(new ContactsData("Anastasia", "Galimzyanova", "44423422", "ovchinnickova.anast@gmail.com", "group3"));
    List<GroupData> after = app.getContactHelper().getContactList();
    Assert.assertEquals( after.size() ,before.size() + 1);
  }
}

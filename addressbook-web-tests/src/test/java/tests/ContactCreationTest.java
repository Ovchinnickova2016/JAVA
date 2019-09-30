package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stq.java.addressbook.model.ContactsData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.getContactHelper().goToContactsPage();
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().createContact(new ContactsData("Anastasia", "Galimzyanova", "44423422", "ovchinnickova.anast@gmail.com", "group3"));
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals( after ,before + 1);
  }
}

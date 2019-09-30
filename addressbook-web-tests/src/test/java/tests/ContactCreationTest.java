package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stq.java.addressbook.model.ContactsData;


import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.getContactHelper().goToContactsPage();
    List<ContactsData> before = app.getContactHelper().getContactList();
    ContactsData contact = new ContactsData("Anastasia", "Galimzyanova", "44423422", "ovchinnickova.anast@gmail.com", "group3");
    app.getContactHelper().createContact(contact);
    List<ContactsData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size() ,before.size() +1);
    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Comparator<? super ContactsData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}

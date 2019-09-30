package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.ContactsData;
import ru.stq.java.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {
  @Test
  public void testContactModification (){

    app.getContactHelper().gotoContactPage();
    if (!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact (new ContactsData("Anastasia", "Galimzyanova", "44423422", "ovchinnickova.anast@gmail.com", "group3"));
    }
    List<ContactsData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.size() - 1);
    ContactsData contact = new ContactsData(before.get(before.size()-1).getId(),"Anastasia", "Galimzyanova", "44423422", "ovchinnickova.anast@gmail.com", "group3");
    app.getContactHelper().fillContactsForm(new ContactsData("nastya", "Ovchinnickova", "8774354533", "hhhdduu@mail.ru",null),false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactPage();
    List<ContactsData> after = app.getContactHelper().getContactList();
    Assert.assertEquals( after.size() ,before.size());
    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactsData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}

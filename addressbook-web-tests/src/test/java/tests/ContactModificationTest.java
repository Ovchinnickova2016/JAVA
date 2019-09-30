package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.ContactsData;

public class ContactModificationTest extends TestBase {
  @Test
  public void testContactModification (){

    app.getContactHelper().gotoContactPage();
    int before = app.getContactHelper().getContactCount();
    if (!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact (new ContactsData("Anastasia", "Galimzyanova", "44423422", "ovchinnickova.anast@gmail.com", "group3"));
    }
    app.getContactHelper().initContactModification(before - 1);
    app.getContactHelper().fillContactsForm(new ContactsData("nastya", "Ovchinnickova", "8774354533", "hhhdduu@mail.ru",null),false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactPage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals( after , before );
  }
}

package tests;

import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.ContactsData;

public class ContactDeletionTest extends TestBase{
  @Test
  public void testContactDeletion(){
    app.getContactHelper().gotoContactPage();
    if (!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact (new ContactsData("Anastasia", "Galimzyanova", "44423422", "ovchinnickova.anast@gmail.com", "group3"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().submitContactDeletion();
    app.getContactHelper().returnToContactPage();
  }
}

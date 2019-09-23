package tests;

import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.ContactsData;

public class ContactModificationTest extends TestBase {
  @Test
  public void testContactModification (){
    app.getContactHelper().gotoContactPage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactsForm(new ContactsData("nastya", "Ovchinnickova", "8774354533", "hhhdduu@mail.ru",null),false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactPage();
  }
}

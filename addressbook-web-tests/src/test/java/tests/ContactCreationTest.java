package tests;

import org.testng.annotations.*;
import ru.stq.java.addressbook.model.ContactsData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goToContactsPage();
    app.getContactHelper().fillContactsForm(new ContactsData("Anastasia", "Galimzyanova", "44423422", "ovchinnickova.anast@gmail.com"));
    app.getContactHelper().submitContactsCreation();
  }

}

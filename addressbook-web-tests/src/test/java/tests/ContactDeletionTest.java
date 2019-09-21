package tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase{
  @Test
  public void testContactDeletion(){
    app.getContactHelper().gotoContactPage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().submitContactDeletion();
    app.getContactHelper().returnToContactPage();

  }
}

package ru.stq.java.addressbook.tests;

import org.testng.annotations.*;
import ru.stq.java.addressbook.model.Contacts;
import ru.stq.java.addressbook.model.ContactsData;
import ru.stq.java.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups(){
    List<Object[]> list = new ArrayList<Object[]>();
    File photo = new File("src/test/resources/icon.png");
    list.add(new Object[]{new ContactsData().withPhoto(photo).withEmail("ovchinnickova.anast@gmail.com").withEmail2("ovch@gmail.com").withEmail3("galim@gmail.com").withFirstName("nastya").withLastName("Ovchinnickova").withMobilePhone("44423422").withWorkPhone("231").withHomePhone("3232").withAddress("2 dd 3").withGroup("group3")});
    list.add(new Object[]{new ContactsData().withPhoto(photo).withEmail("ovchinnickova.anast@gmail.com").withEmail2("ovch@gmail.com").withEmail3("galim@gmail.com").withFirstName("nastya2").withLastName("Ovchinnickova2").withMobilePhone("44423422").withWorkPhone("231").withHomePhone("3232").withAddress("2 dd 3").withGroup("group3")});
    list.add(new Object[]{new ContactsData().withPhoto(photo).withEmail("ovchinnickova.anast@gmail.com").withEmail2("ovch@gmail.com").withEmail3("galim@gmail.com").withFirstName("nastya3").withLastName("Ovchinnickova3").withMobilePhone("44423422").withWorkPhone("231").withHomePhone("3232").withAddress("2 dd 3").withGroup("group3")});
    return list.iterator();
  }

  @Test(dataProvider = "validGroups")
  public void testContactCreation(ContactsData contact) throws Exception {
    Contacts before = app.contact().all();
    app.contact().goToContactsPage();
    app.contact().create(contact);
    assertThat(app.contact().count(),equalTo(before.size() +1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadContactCreation() throws Exception {

    Contacts before = app.contact().all();
    app.contact().goToContactsPage();
    ContactsData contact = new ContactsData().withEmail("ovchinnickova.anast@gmail.com'").withEmail2("ovch@gmail.com").withEmail3("galim@gmail.com").withFirstName("nastya").withLastName("Ovchinnickova").withMobilePhone("44423422").withWorkPhone("231").withHomePhone("3232").withAddress("2 dd 3").withGroup("group3");
    app.contact().create(contact);
    assertThat(app.contact().count(),equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }
  @Test(enabled = false)
  public void testCurrentDir(){
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/icon.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}

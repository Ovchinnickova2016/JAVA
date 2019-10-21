package ru.stq.java.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.ContactsData;
import ru.stq.java.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {
  Groups groups  = app.db().groups();
  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size()==0){
      app.contact().gotoContactPage();
      app.contact().create(new ContactsData().withEmail("ovchinnickova.anast@gmail.com").withEmail2("ovch@gmail.com")
        .withEmail3("galim@gmail.com").withFirstName("nastya").withLastName("Ovchinnickova").withMobilePhone("44423422")
        .withWorkPhone("231").withHomePhone("3232").withAddress("2 dd 3").inGroup(groups.iterator().next()));
    }
  }

  @Test
  public void testContactPhones(){
    app.goTo().goToHomePage();
    ContactsData contact = app.contact().all().iterator().next();
    ContactsData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAllEmailes(), equalTo(mergeEmailes(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo((contactInfoFromEditForm.getAddress())));
  }

  private String mergePhones(ContactsData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
      .stream().filter((s)->!s.equals(""))
      .map(ContactPhoneTest::cleaned)
      .collect(Collectors.joining("\n"));
  }

  private String mergeEmailes(ContactsData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
      .stream().filter((s)->!s.equals(""))
      .map(ContactPhoneTest::cleanedEmailes)
      .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
  public static String cleanedEmailes(String email){
    return email.replaceAll("\\s", "");
  }
}

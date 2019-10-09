package ru.stq.java.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.ContactsData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.contact().gotoContactPage();
    if (app.contact().all().size() == 0){
      ContactsData contact = new ContactsData().withEmail("ovchinnickova.anast@gmail.com").withEmail2("ovch@gmail.com").withEmail3("galim@gmail.com").withFirstName("nastya").withLastName("Ovchinnickova").withMobilePhone("44423422").withHomePhone("2662222").withWorkPhone("23232").withGroup("group3");
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

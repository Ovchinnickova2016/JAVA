package ru.stq.java.addressbook.model;

public class ContactsData {
  private final String firstName;
  private final String lastName;
  private final String phoneNumber;
  private final String email;

  public ContactsData(String firstName, String LastName, String phoneNumber, String email) {
    this.firstName = firstName;
    lastName = LastName;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getEmail() {
    return email;
  }
}

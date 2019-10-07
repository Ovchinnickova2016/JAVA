package ru.stq.java.addressbook.model;

import java.util.Objects;

public class ContactsData {
  private int id = Integer.MAX_VALUE;;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String email;
  private String group;

  @Override
  public String toString() {
    return "ContactsData{" +
      "id=" + id +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      '}';
  }

  public ContactsData withId(int id) {
    this.id = id;
    return this;
  }
  public ContactsData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactsData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactsData withPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public ContactsData withEmail(String email) {
    this.email = email;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactsData that = (ContactsData) o;
    return id == that.id &&
      Objects.equals(firstName, that.firstName) &&
      Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName);
  }

  public ContactsData withGroup(String group) {
    this.group = group;
    return this;
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

  public String getGroup() {
    return group;
  }

  public int getId() {
    return  id;
  }
}

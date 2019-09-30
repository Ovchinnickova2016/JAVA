package ru.stq.java.addressbook.model;

import java.util.Objects;

public class ContactsData {
  private int id;
  private final String firstName;
  private final String lastName;
  private final String phoneNumber;
  private final String email;
  private String group;

  @Override
  public String toString() {
    return "ContactsData{" +
      "id=" + id +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactsData that = (ContactsData) o;
    return Objects.equals(firstName, that.firstName) &&
      Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }

  public ContactsData(int id, String firstName, String LastName, String phoneNumber, String email, String group) {
    this.id = id;
    this.firstName = firstName;
    lastName = LastName;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.group = group;
  }
  public ContactsData(String firstName, String LastName, String phoneNumber, String email, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstName = firstName;
    lastName = LastName;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.group = group;
  }

  public void setId(int id) {
    this.id = id;
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

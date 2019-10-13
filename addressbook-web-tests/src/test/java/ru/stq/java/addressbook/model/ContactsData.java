package ru.stq.java.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;
@XStreamAlias("contact")
public class ContactsData {
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String email;
  private String email2;
  private String email3;
  private String allEmailes;

  public File getPhoto() {
    return photo;
  }

  public ContactsData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  private String group;
  private String home;
  private String mobile;
  private String work;
  private String address;
  private String allPhones;
  private File photo;

  public String getAllPhones() {
    return allPhones;
  }
  public String getAllEmailes() {
    return allEmailes;
  }

  public ContactsData withAllPhones(String allphones) {
    this.allPhones = allphones;
    return this;
  }
  public ContactsData withAllEmailes(String allEmailes) {
    this.allEmailes = allEmailes;
    return this;
  }

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
  public ContactsData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }
  public ContactsData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }
  public ContactsData withHomePhone(String home) {
    this.home = home;
    return this;
  }
  public ContactsData withMobilePhone(String mobile) {
    this.mobile = mobile;
    return this;
  }
  public ContactsData withAddress(String address) {
    this.address = address;
    return this;
  }
  public ContactsData withWorkPhone(String work) {
    this.work = work;
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
  public String getEmail2() {
    return email2;
  }
  public String getEmail3() {
    return email3;
  }

  public String getGroup() {
    return group;
  }

  public int getId() {
    return  id;
  }

  public String getHomePhone() {
    return home;
  }
  public String getMobilePhone() {
    return mobile;
  }
  public String getWorkPhone() {
    return work;
  }

  public String getAddress() {
    return address;
  }


}

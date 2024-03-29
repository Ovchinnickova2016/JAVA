package ru.stq.java.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity

@Table(name = "addressbook")
@Proxy(lazy =false)
public class ContactsData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;;
  @Expose
  @Column(name = "firstname")
  private String firstName;
  @Expose
  @Column(name = "lastname")
  private String lastName;
  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String home;
  @Transient
  private String phoneNumber;
  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobile;
  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String work;
  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email;
  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String email2;

  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String email3;
  @Expose
  @Transient
  private String allEmailes;

  @Transient
  private String address;
  @Transient
  private String allPhones;
  @Column(name = "photo")
  @Type(type = "text")
  private String photo;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  public File getPhoto() {
    return new File(photo);
  }

  public ContactsData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public String getHome() {
    return home;
  }

  public String getMobile() {
    return mobile;
  }

  public String getWork() {
    return work;
  }

  public Groups getGroups() {
    return new Groups(groups);
  }

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactsData that = (ContactsData) o;
    return id == that.id &&
      Objects.equals(firstName, that.firstName) &&
      Objects.equals(lastName, that.lastName) &&
      Objects.equals(allEmailes, that.allEmailes) &&
      Objects.equals(address, that.address) &&
      Objects.equals(allPhones, that.allPhones);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, allEmailes, address, allPhones);
  }

  public String getEmail3() {
    return email3;
  }


  public int getId() {
    return  id;
  }
  public int getNextId() {
    return  id=id+1;
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

  public ContactsData inGroup(GroupData group){
    groups.add(group);
    return this;
  }
}

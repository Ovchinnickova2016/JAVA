package ru.stq.java.addressbook.model;

public class GroupContact {

    private  ContactsData contact;
    private  GroupData group;

    public GroupContact(ContactsData contact, GroupData group) {
      this.contact = contact;
      this.group = group;
    }

  public GroupContact() {

  }

  public ContactsData getContact() {
      return contact;
    }

    public GroupData getGroup() {
      return group;
    }
  public GroupContact withGroupInContact(GroupData group) {
    this.group = group;
    return this;
  }

  public GroupContact withContactInGroup(ContactsData contact) {
    this.contact = contact;
    return this;
  }

}

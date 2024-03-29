package ru.stq.java.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactsData> {

  private Set<ContactsData> delegate;

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<ContactsData>(contacts.delegate);
  }

  public Contacts() {
    this.delegate = new HashSet<ContactsData>();
  }

  public Contacts(Collection<ContactsData> contacts) {
    this.delegate = new HashSet<ContactsData>(contacts);
  }

  @Override
  protected Set<ContactsData> delegate() {
    return delegate;
  }

  public Contacts withAdded(ContactsData contact) {
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts without(ContactsData contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }

  public ContactsData getContactById(Contacts contacts, int id) {

    for (ContactsData contact : contacts) {
      if (contact.getId() == id) {
        ContactsData contactWithId = contact;
        return contactWithId;
      }
    }
    return null;
  }


}
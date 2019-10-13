package ru.stq.java.addressbook.generators;

import ru.stq.java.addressbook.model.ContactsData;
import ru.stq.java.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    public static void main(String[] args) throws IOException {
      int count = Integer.parseInt(args[0]);
      File file = new File(args[1]);


      List<ContactsData> contacts = generateContacts(count);
      save(contacts,file);
    }

    private static void save(List<ContactsData> contacts, File file) throws IOException {
      System.out.println(new File(".").getAbsolutePath());
      Writer writer = new FileWriter(file);
      for (ContactsData contact :contacts){
        writer.write(String.format("%s;%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getMobilePhone(), contact.getAddress()));
      }
      writer.close();
    }

    private static List<ContactsData> generateContacts(int count) {
      List<ContactsData> contacts = new ArrayList<ContactsData>();
      for (int i=0; i< count; i++){
        contacts.add(new ContactsData().withFirstName(String.format("Anastasia %s",i)).withLastName((String.format("Galimzyanova %s",i))).withEmail((String.format("ovchi@gmail.com %s",i))).withMobilePhone((String.format("89653453633 %s",i))).withAddress((String.format("Noaya Street 2,3 %s",i))));
      }
      return contacts;
    }
  }


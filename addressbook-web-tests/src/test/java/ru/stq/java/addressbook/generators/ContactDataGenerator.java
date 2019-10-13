package ru.stq.java.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stq.java.addressbook.model.ContactsData;
import ru.stq.java.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    }
    catch(ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
    }

  private void run() throws IOException {
    List<ContactsData> contacts = generateContacts(count);
    save(contacts, new File(file));
  }

  private static void save(List<ContactsData> contacts, File file) throws IOException {
      System.out.println(new File(".").getAbsolutePath());
      Writer writer = new FileWriter(file);
      for (ContactsData contact :contacts){
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getMobilePhone(), contact.getAddress(), contact.getHomePhone(), contact.getWorkPhone(), contact.getEmail2(), contact.getEmail3()));
      }
      writer.close();
    }

    private static List<ContactsData> generateContacts(int count) {
      List<ContactsData> contacts = new ArrayList<ContactsData>();
      for (int i=0; i< count; i++){
        contacts.add(new ContactsData().withFirstName(String.format("Anastasia %s",i)).withLastName((String.format("Galimzyanova %s",i))).withEmail((String.format("ovchi@gmail.com %s",i))).withMobilePhone((String.format("89653453633 %s",i)))
          .withAddress((String.format("Noaya Street 2,3 %s",i))).withHomePhone(String.format("3475858 %s",i)).withWorkPhone(String.format("25463433 %s",i)).withEmail2(String.format("ovchi@gmail.com %s",i)).withEmail3(String.format("rrrr@gmail.com %s",i)));
      }
      return contacts;
    }
  }


package ru.stq.java.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stq.java.addressbook.model.Contacts;
import ru.stq.java.addressbook.model.ContactsData;
import ru.stq.java.addressbook.model.GroupData;
import ru.stq.java.addressbook.model.Groups;

import java.util.List;

public class DbHelper {
  private final SessionFactory sessionFactory;
  public DbHelper(){
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
      .configure() // configures settings from hibernate.cfg.xml
      .build();
     sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Groups groups(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery( "from GroupData" ).list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public Contacts contacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactsData> result = session.createQuery("from ContactsData where deprecated = '0000-00-00 00:00:00'").list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }

  public Groups cont_Ingroups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery( "from GroupData" ).list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }
  public GroupData getGroupsFromDB(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    GroupData result = (GroupData) session.createQuery("from GroupData where id=" + id).uniqueResult();
    session.getTransaction().commit();
    session.close();
    return result;
  }

  public ContactsData getContactsFromDB(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    ContactsData result = (ContactsData) session.createQuery("from ContactsData where id=" + id).uniqueResult();
    session.getTransaction().commit();
    session.close();
    return result;
  }
}

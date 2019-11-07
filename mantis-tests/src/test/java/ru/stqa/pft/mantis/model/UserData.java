package ru.stqa.pft.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Entity
@Table(name = "mantis_user_table")
public class UserData {



  // @XStreamAlias("group")

    // @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    ;
    // @Expose
    @Column(name = "username")
    private String name;
  @Column(name = "email")
  private String email;

    @Column(name = "password")
    private String password;



    public String getName() {
      return name;
    }
  public String getEmail() {
    return email;
  }
  public String getPassword() {
    return password;
  }


    public int getId() {
      return id;
    }


}
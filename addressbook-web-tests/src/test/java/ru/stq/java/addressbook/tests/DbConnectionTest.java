package ru.stq.java.addressbook.tests;

import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.GroupData;
import ru.stq.java.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {
  @Test
  public void testDbConnection() throws SQLException {
    Connection conn = null;
    try{
      conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list");
      Groups groups = new Groups();
      while (rs.next()){
      groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
          .withHeader(rs.getString("group_header")).withFooter(rs.getString("group_footer")));
      }
      rs.close();
      st.close();
      conn.close();
      System.out.println(groups);
    }

 catch (SQLException e){
      System.out.println("SQLException: "+e.getMessage());
   System.out.println("SQLState: "+e.getSQLState());
   System.out.println("VendorError: "+e.getErrorCode());
 }
  }
}

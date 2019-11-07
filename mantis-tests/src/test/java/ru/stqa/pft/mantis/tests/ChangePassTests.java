package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePassTests extends TestBase {
  @Test
  public void testChangePassword() throws IOException, MessagingException, InterruptedException {

    Users users = app.db().users();
    UserData selectedUser = users.iterator().next();
    int userId = selectedUser.getId();
    String user = selectedUser.getName();//"user1572881117609";
    String email = selectedUser.getEmail();//String.format("user%s@localhost", user);
    String password = selectedUser.getPassword();//"1234";
  //  User user1 = selectedUser.getId();
   // String user1 =
    String admin = "administrator";
    String adminpass = "root";
    app.loginAdmin().start(admin, adminpass, Integer.toString(userId));
    //app.users().changePassword(selectedUser);
    app.james().changePassword();
    Thread.sleep(20000);
    List<MailMessage> mailMessages = app.james().waitForMail(user, password, 120000);
    String link = findLink(mailMessages, email);
    app.registration().finish(link, user, password);
    app.james().changePasswordFinish();
    assertTrue(app.newSession().login(user, password));
  }

  private String findLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage =  mailMessages.stream().filter((m)->m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
}

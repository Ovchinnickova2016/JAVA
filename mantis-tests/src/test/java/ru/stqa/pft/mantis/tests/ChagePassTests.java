package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChagePassTests extends TestBase {
  @Test
  public void testRegistration() throws IOException, MessagingException, InterruptedException {
    long now = System.currentTimeMillis();
    String email = String.format("user1572806671268@localhost", now);
    String user = "user1572806671268";
    String password = "1234";
    String user1 = String.format("user%s", now);
    String password1 = "password";
    String admin = "administrator";
    String adminpass = "root";
    app.loginAdmin().start(admin, adminpass);
    app.james().changePassword();
    List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
    String link = findLink(mailMessages, email);
    app.registration().finish(link, user, password);
    app.james().changePasswordFinish();
  }

  private String findLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage =  mailMessages.stream().filter((m)->m.to.equals(email)).reduce((first, second) -> second).get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
}

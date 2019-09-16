package tests;

import org.testng.annotations.*;
import ru.stq.java.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {

    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().iniGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("group3", "group3", "group3"));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();

  }


}

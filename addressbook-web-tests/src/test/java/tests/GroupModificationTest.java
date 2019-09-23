package tests;

import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase {
  @Test
  public void testGroupModification(){
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("group3", "group3", "group3"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
  }
}
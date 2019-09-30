package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase {
  @Test
  public void testGroupModification(){

    app.getNavigationHelper().goToGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    if (!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup (new GroupData("group3", null, null));
    }
    app.getGroupHelper().selectGroup(before - 1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("group3", "group3", "group3"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals( after ,before );

  }
}

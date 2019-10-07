package ru.stq.java.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase{

  @Test
  public void testGroupDeletion() throws Exception {

    app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().isThereAGroup()){
        app.getGroupHelper().createGroup (new GroupData("group3", null, null));
    }

    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals( after.size() ,before.size() - 1);
    before.remove(before.size() - 1);
      Assert.assertEquals( before, after);
  }

}

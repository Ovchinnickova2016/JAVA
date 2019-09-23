package tests;

import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.GroupData;
public class GroupDeletionTest extends TestBase{

  @Test
  public void testGroupDeletion() throws Exception {

    app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().isThereAGroup()){
        app.getGroupHelper().createGroup (new GroupData("group3", null, null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }

}

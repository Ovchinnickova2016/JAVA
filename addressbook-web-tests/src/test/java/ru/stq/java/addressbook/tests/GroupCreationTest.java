package ru.stq.java.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stq.java.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData("group3", null, null);
    app.group().create(group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size() ,before.size() +1);
    group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
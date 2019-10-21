package ru.stq.java.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stq.java.addressbook.model.GroupData;
import ru.stq.java.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){

    if (app.db().groups().size()==0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("group3"));
    }
  }

  @Test
  public void testGroupModification(){
    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId())
      .withName("group1").withHeader("group2").withFooter("group3");
    app.goTo().groupPage();
    app.group().modify(group);
    assertEquals( app.group().count() ,before.size());
    Groups after =  app.db().groups();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }

}

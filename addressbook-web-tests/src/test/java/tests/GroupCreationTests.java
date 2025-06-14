package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GroupData("Group name4", "Group header4", "Group footer4"));
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals( groupCount + 1, newGroupCount );
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        app.groups().createGroup(new GroupData());

    }

    @Test
    public void canCreateGroupWithEmptyNameOnly() {
        app.groups().createGroup(new GroupData().withName("some name"));

    }
}

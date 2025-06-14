package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("Group name4", "Group header4", "Group footer4"));
        }
        int groupCount = app.groups().getCount();
        app.groups().removeGroup();
        int newgroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount - 1, newgroupCount );
    }
    @Test
    public void canRemoveAllGroupsAtOnce(){
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("Group name4", "Group header4", "Group footer4"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0,app.groups().getCount());
    }

}


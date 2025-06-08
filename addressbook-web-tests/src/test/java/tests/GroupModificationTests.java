package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationTests extends TestBase{

    @Test
    void canModifyGroup(){
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("Group name4", "Group header4", "Group footer4"));
        }
        app.groups().modifyGroup(new GroupData().withName("modified name"));
    }
}

package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {

        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Ivan", "Ivanov", "", "",
                    "", "", "","Grandmother village", "112", "", "", "", "i_ivanov@mail.ru",
                    "", "", "", "", "", "", "",
                    "", "", ""));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(oldContacts.get(index));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canRemoveAllContacts() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Ivan", "Ivanov", "", "",
                    "", "","", "Grandmother village", "112", "", "", "", "i_ivanov@mail.ru",
                    "", "", "", "", "", "", "",
                    "", "", ""));
        }
        app.contacts().removeAllContacts();
        var contacts = app.hbm().getContactList();
        Assertions.assertEquals(0, contacts.size());
    }

    @Test
    void canRemoveContactFromGroup(){
        var contact = new ContactData()
                .withFirsName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "Group name4", "Group header4", "Group footer4"));
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        oldRelated.sort(compareById);
        app.contacts().createContactInGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        var index = newRelated.size()-1;
        contact = newRelated.get(index);
        app.contacts().removeContactFromGroup(contact, group);
        var actualRelated = new ArrayList<>(newRelated);
        var lastNewRelated = newRelated.get(newRelated.size()-1);
        actualRelated.remove(lastNewRelated);
        actualRelated.sort(compareById);
        Assertions.assertEquals(oldRelated, actualRelated );
    }
}

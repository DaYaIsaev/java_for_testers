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
        if (app.hbm().getContactCount() == 0){
            var contact = new ContactData()
                    .withFirsName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
            app.contacts().createContact(contact);
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "Group name4", "Group header4", "Group footer4"));
        }
        var group = app.hbm().getGroupList().get(0);
        var getCountContactsInGroup = (app.hbm().getContactsInGroup(group)).size();
        if (getCountContactsInGroup == 0) {
            var getContacts = app.hbm().getContactList();
            Comparator<ContactData> compareById = (o1, o2) -> {
                return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
            };
            getContacts.sort(compareById);
            var contact = getContacts.get(getContacts.size()-1);
            app.contacts().addContactInGroup(contact, group);         }
        var oldRelated = app.hbm().getContactsInGroup(group);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        oldRelated.sort(compareById);
        var contact = oldRelated.get(oldRelated.size()-1);
        app.contacts().removeContactFromGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        newRelated.sort(compareById);
        //var actualRelated = new ArrayList<>(newRelated);
        var lastOldRelated = oldRelated.get(oldRelated.size()-1);
        oldRelated.remove(lastOldRelated);
        oldRelated.sort(compareById);
        Assertions.assertEquals(oldRelated, newRelated);
    }
}

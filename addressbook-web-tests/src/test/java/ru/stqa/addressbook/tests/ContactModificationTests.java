package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {

    @Test
    void canModifyContact() {
        if (app.contacts().getContactsCount() == 0) {
            app.contacts().createContact(new ContactData("", "Ivan", "", "Ivanov", "",
                    "", "","", "Grandmother village", "112", "", "", "", "i_ivanov@mail.ru",
                    "", "", "", "", "", "", "",
                    "", "", ""));
        }
        var oldContacts = app.contacts().getContactsList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testContactData = new ContactData().withFirsName("modified name");
        app.contacts().modifyContact(oldContacts.get(index), testContactData);
        var newContacts = app.contacts().getContactsList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testContactData.withId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);


    }
}

package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {

        if (app.contacts().getContactsCount() == 0) {
            app.contacts().createContact(new ContactData("", "Ivan", "", "Ivanov", "",
                    "", "", "Grandmother village", "112", "", "", "", "i_ivanov@mail.ru",
                    "", "", "", "", "", "", "",
                    "", "", ""));
        }
        var oldContacts = app.contacts().getContactsList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.contacts().getContactsList();
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
        if (app.contacts().getContactsCount() == 0) {
            app.contacts().createContact(new ContactData("", "Ivan", "", "Ivanov", "",
                    "", "", "Grandmother village", "112", "", "", "", "i_ivanov@mail.ru",
                    "", "", "", "", "", "", "",
                    "", "", ""));
        }
        app.contacts().removeAllContacts();
        var contacts = app.contacts().getContactsList();
        Assertions.assertEquals(0, contacts.size());
    }
}

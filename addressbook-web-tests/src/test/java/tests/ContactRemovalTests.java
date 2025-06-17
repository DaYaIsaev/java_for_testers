package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase{

    @Test
    public void canRemoveContact() {

        if (app.contacts().getContactsCount() == 0) {
            app.contacts().createContact(new ContactData("","Ivan", "", "Ivanov", "",
                    "", "", "Grandmother village", "112", "", "", "", "i_ivanov@mail.ru",
                    "", "", "", "", "", "", "",
                    "", "", ""));
        }
        app.contacts().removeContact();
    }

    @Test
    public void canRemoveAllContacts() {
        if (app.contacts().getContactsCount() == 0) {
            app.contacts().createContact(new ContactData("","Ivan", "", "Ivanov", "",
                    "", "", "Grandmother village", "112", "", "", "", "i_ivanov@mail.ru",
                    "", "", "", "", "", "", "",
                    "", "", ""));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0,app.contacts().getContactsCount());
    }
}

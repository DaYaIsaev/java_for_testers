package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase{

    @Test
    public void canRemoveContact() {

        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("Ivan", "", "Ivanov", "",
                    "", "", "Grandmother village", "112", "", "", "", "i_ivanov@mail.ru",
                    "", "", "", "", "", "", "",
                    "", "", ""));
        }
        app.contacts().removeContact();
    }
}

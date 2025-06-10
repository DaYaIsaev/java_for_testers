package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase{

    @Test
    public void canCreateContact(){

        app.contacts().createContact(new ContactData("Ivan", "", "Ivanov", "",
                "", "", "Grandmother village", "112", "", "", "", "i_ivanov@mail.ru",
                "", "", "", "", "", "", "",
                "", "", ""));
    }


    @Test
    public void canCreateContactWithNameOnly() {

        app.contacts().createContact(new ContactData().withFirsName("some name"));

    }

}

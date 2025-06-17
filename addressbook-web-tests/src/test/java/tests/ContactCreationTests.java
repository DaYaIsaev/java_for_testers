package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ContactCreationTests extends TestBase{

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (int i = 0; i < 5; i++ ) {
            result.add(new ContactData("",randomString(i * 10), "", randomString(i * 10), "", "", "", randomString(i * 10), randomString(i * 10),"",
            "", randomString(i * 10), "", "", "", "", "", "", "", "", "", "", ""));
        }
        return result;

    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateContact(ContactData contact){
        int contactsCount = app.contacts().getContactsCount();
        app.contacts().createContact(contact);
        int newContactsCount = app.contacts().getContactsCount();
        Assertions.assertEquals(contactsCount + 1, newContactsCount);

    }


    @Test
    public void canCreateContactWithNameOnly() {

        app.contacts().createContact(new ContactData().withFirsName("some name"));

    }

}

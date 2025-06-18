package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (int i = 0; i < 5; i++ ) {
            result.add(new ContactData("",randomString(i * 10), randomString(i * 10),"", "", "", "", randomString(i * 10), randomString(i * 10),"",
            "", "", randomString(i * 10),  "", "", "", "", "", "", "", "", "", ""));
        }
        return result;

    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateContact(ContactData contact){
        var oldContacts = app.contacts().getContactsList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getContactsList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedContactList = new ArrayList<>(oldContacts);
        expectedContactList.add(contact
                .withId(newContacts.get(newContacts.size()-1).id()).withEmail("").withAddress("").withPhoneHome(""));
        expectedContactList.sort(compareById);
        Assertions.assertEquals(expectedContactList, newContacts );

    }


    @Test
    public void canCreateContactWithNameOnly() {

        app.contacts().createContact(new ContactData().withFirsName("some name"));

    }

}

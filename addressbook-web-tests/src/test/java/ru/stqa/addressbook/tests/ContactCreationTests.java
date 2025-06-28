package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//        for (int i = 0; i < 5; i++) {
//            result.add(new ContactData("", CommonFunctions.randomString(i * 10), CommonFunctions.randomString(i * 10), "", "", "","", "", CommonFunctions.randomString(i * 10), CommonFunctions.randomString(i * 10), "",
//                    "", "", CommonFunctions.randomString(i * 10), "", "", "", "", "", "", "", "", "", ""));
//        }
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>(){});
        result.addAll(value);
        return result;
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>();
        result.add(new ContactData("", "name'", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateContact(ContactData contact) {
        var oldContacts = app.contacts().getContactsList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getContactsList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedContactList = new ArrayList<>(oldContacts);
        var lastNewContact = newContacts.get(newContacts.size() - 1);
        var temp = contact.withPhoto(lastNewContact.photo());
        expectedContactList.add(contact
                .withId(lastNewContact.id()).withEmail("").withAddress("").withPhoneHome("").withPhoto(""));
        expectedContactList.sort(compareById);
        Assertions.assertEquals(expectedContactList, newContacts);

    }


    @Test
    public void canCreateContactWithNameOnly() {

        app.contacts().createContact(new ContactData().withFirsName("some name"));
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        var oldContacts = app.contacts().getContactsList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getContactsList();
        Assertions.assertEquals(oldContacts, newContacts);
    }

    @Test
    void canCreatedContactWithFile(){
        var contact = new ContactData()
                .withFirsName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
        app.contacts().createContact(contact);
    }

}

package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTest extends TestBase {

    @Test
    void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            var contact = new ContactData()
                    .withFirsName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withPhoto(CommonFunctions.randomFile("src/test/resources/images"))
                    .withPhoneHome(CommonFunctions.randomNumber(7))
                    .withPhoneWork(CommonFunctions.randomNumber(7));
            app.contacts().createContact(contact);
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact -> Stream.of(contact.phoneHome(), contact.phoneMobile(), contact.phoneWork())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"))));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testEmails() {
        if (app.hbm().getContactCount() == 0) {
            var contact = new ContactData()
                    .withFirsName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withPhoto(CommonFunctions.randomFile("src/test/resources/images"))
                    .withEmail(CommonFunctions.randomString(10))
                    .withEmail2(CommonFunctions.randomString(10))
                    .withPhoneHome(CommonFunctions.randomNumber(7))
                    .withPhoneWork(CommonFunctions.randomNumber(7));
            app.contacts().createContact(contact);
        }
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var expected = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        var emails = app.contacts().getEmails(contact.id());
        Assertions.assertEquals(expected, emails);
    }

    @Test
    void testAddress() {
        if (app.hbm().getContactCount() == 0) {
            var contact = new ContactData()
                    .withFirsName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withPhoto(CommonFunctions.randomFile("src/test/resources/images"))
                    .withAddress(CommonFunctions.randomString(5) + " " + CommonFunctions.randomString(10) + " " + CommonFunctions.randomString(5))
                    .withEmail(CommonFunctions.randomString(10))
                    .withEmail2(CommonFunctions.randomString(10))
                    .withPhoneHome(CommonFunctions.randomNumber(7))
                    .withPhoneWork(CommonFunctions.randomNumber(7));
            app.contacts().createContact(contact);
        }
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var expected = Stream.of(contact.address())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        var address = app.contacts().getAddress(contact.id());
        Assertions.assertEquals(expected, address);
    }
}

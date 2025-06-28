package ru.stqa.addressbook.manager;

import ru.stqa.addressbook.model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static ru.stqa.addressbook.common.CommonFunctions.randomFile;


public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {

        super(manager);
    }

    public void createContact(ContactData contact) {

        openAddContactPage();
        fillContactFormWithoutAttach(contact);
        submitContactCreation();
        returnToContactsPage();
    }

    private void returnToContactsPage() {

        click(By.linkText("home page"));
    }

    private void submitContactCreation() {

        click(By.name("submit"));
    }

    private void fillContactForm(ContactData contacts) {

        type(By.name("firstname"), contacts.firstName());
        type(By.name("lastname"), contacts.lastName());
        attach(By.name("photo"), contacts.photo());
        type(By.name("address"), contacts.address());
        type(By.name("home"), contacts.phoneHome());
        type(By.name("email"), contacts.email());

    }

    private void fillContactFormWithoutAttach(ContactData contacts) {

        type(By.name("firstname"), contacts.firstName());
        type(By.name("lastname"), contacts.lastName());
        type(By.name("address"), contacts.address());
        type(By.name("home"), contacts.phoneHome());
        type(By.name("email"), contacts.email());

    }

    private void openAddContactPage() {

        if (!manager.isElementPresent(By.name("Last name"))) {
            click(By.linkText("add new"));
        }
    }

    public boolean isContactPresent() {

        openContactsPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    private void openContactsPage() {

        if (!manager.isElementPresent(By.name("Last name"))) {
            click(By.linkText("home"));
        }
    }

    public void removeContact(ContactData contact) {
        openContactsPage();
        selectContact(contact);
        removeSelectedContact();
        //returnToContactsPage();
    }

    private void removeSelectedContact() {
        click(By.xpath("//*[@id=\"content\"]/form[2]/div[2]/input"));
    }

    private void selectContact(ContactData contact) {

        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    public int getContactsCount() {
        openContactsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    private void selectAllContacts() {
        var checkBoxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkBox : checkBoxes) {
            checkBox.click();
        }
    }

    public void removeAllContacts() {
        openContactsPage();
        selectAllContacts();
        removeSelectedContact();
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void initContactModification(ContactData contact) {
        //click(By.xpath("//img[@alt='Edit']"));
        click(By.xpath(String.format("//tr[.//input[@id='%s']]/td[@class='center']/a[.//img[@alt='Edit']]", contact.id())));
    }

    public List<ContactData> getContactsList() {
        openContactsPage();
        var contacts = new ArrayList<ContactData>();
        var entrys = manager.driver.findElements(By.name("entry"));
        for (var entry : entrys) {
            var lastName = entry.findElement(By.cssSelector("td:nth-child(2)")).getText();
            var firstName = entry.findElement(By.cssSelector("td:nth-child(3)")).getText();
            var checkbox = entry.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withFirsName(firstName).withLastName(lastName));
        }
        return contacts;
    }


    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openContactsPage();
        selectContact(contact);
        initContactModification(contact);
        fillContactFormWithoutAttach(modifiedContact);
        submitContactModification();
        returnToContactsPage();
    }
}

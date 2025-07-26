package ru.stqa.addressbook.manager;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.addressbook.model.ContactData;
import org.openqa.selenium.By;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.stqa.addressbook.common.CommonFunctions.randomFile;


public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContactWithGroup(ContactData contact) {
        openAddContactPage();
        fillContactForm(contact);
        //fillContactFormWithoutAttach(contact);
        submitContactCreation();
        returnToContactsPage();
    }

    public void createContact(ContactData contact) {
        openAddContactPage();
        fillContactForm(contact);
        //fillContactFormWithoutAttach(contact);
        submitContactCreation();
        returnToContactsPage();;
    }

    public void createContactInGroup(ContactData contact, GroupData group) {
        openAddContactPage();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToContactsPage();
    }

    public void addContactInGroup(ContactData contact, GroupData group) {
        openContactsPage();
        selectContact(contact);
        addToGroup(group);
        submitAddTo();
        //returnToContactsPage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    private void addToGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    private void returnToContactsPage() {
        click(By.linkText("home page"));
    }

    private void submitAddTo(){
        click(By.name("add"));
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void fillContactForm(ContactData contacts) {
        type(By.name("firstname"), contacts.firstName());
        type(By.name("lastname"), contacts.lastName());
        if (!contacts.photo().isBlank()) {
            attach(By.name("photo"), contacts.photo());
        }
        type(By.name("address"), contacts.address());
        type(By.name("home"), contacts.phoneHome());
        type(By.name("work"), contacts.phoneWork());
        type(By.name("email"), contacts.email());
        type(By.name("email2"), contacts.email2());

    }

    private void fillContactFormWithoutAttach(ContactData contacts) {
        fillContactForm(contacts.withPhoto(""));
//        type(By.name("firstname"), contacts.firstName());
//        type(By.name("lastname"), contacts.lastName());
//        type(By.name("address"), contacts.address());
//        type(By.name("home"), contacts.phoneHome());
//        type(By.name("email"), contacts.email());
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
    }

    private void removeSelectedContact() {
        click(By.xpath("//*[@id=\"content\"]/form[2]/div[2]/input"));
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
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

    public void removeContactFromGroup(ContactData contact, GroupData group) {
            openContactsPage();
            selectGroupFilter(group);
            selectContact(contact);
            submitRemoveFromGroup();
            //returnToContactsPage();
        }

    private void submitRemoveFromGroup() {
        click(By.name("remove"));
    }

    private void selectGroupFilter(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    public String getPhones(String id) {
       return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", id))).getText();
    }

    public Map<String, String> getPhones() {
        var result = new HashMap<String, String>();
        List<WebElement> rows =  manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows){
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public String getEmails(String id) {
         WebElement td = manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[5]", id)));
         List<WebElement> rows = td.findElements(By.tagName("a"));
         String emails = "";
         for (WebElement row : rows){
             var email = row.getText();
              emails = emails + email + "\n";
         }
            return emails.substring(0,emails.length()-1);
    }

    public String getAddress(String id) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[4]", id))).getText();
    }
}


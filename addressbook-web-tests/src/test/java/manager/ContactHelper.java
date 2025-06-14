package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {

        super(manager);
    }

    public void createContact(ContactData contact) {

        openAddContactPage();
        //initGroupCreation();
        fillContactForm(contact);
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

    public void removeContact() {

        openContactsPage();
        selectContact();
        removeSelectedContact();
        //returnToContactsPage();
    }

    private void removeSelectedContact() {
        click(By.xpath("//*[@id=\"content\"]/form[2]/div[2]/input"));
    }

    private void selectContact() {

        click(By.name("selected[]"));
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
}

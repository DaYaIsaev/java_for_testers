package model;

public record ContactData(String firstName, String middleName, String lastName, String nickName, String title,
                          String company, String address, String phoneHome, String phoneMobile, String phoneWork,
                          String phoneFax, String email, String email2, String email3, String homePage,
                          String birthdayDay, String birthdayMonth, String birthdayYear, String anniversaryDay,
                          String anniversaryMonth, String anniversaryYear, String group) {


    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "",
                "", "", "");
    }


    public ContactData withFirsName(String firstName) {
        return new ContactData(firstName, this.lastName, this.middleName, this.nickName, this.title,
                this.company, this.address, this.phoneHome, this.phoneMobile, this.phoneWork,
                this.phoneFax, this.email, this.email2, this.email3, this.homePage,
                this.birthdayDay, this.birthdayMonth, this.birthdayYear, this.anniversaryDay,
                this.anniversaryMonth, this.anniversaryYear, this.group);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.firstName, lastName, this.address, this.middleName, this.nickName, this.title,
                this.company, this.phoneHome, this.phoneMobile, this.phoneWork,
                this.phoneFax, this.email, this.email2, this.email3, this.homePage,
                this.birthdayDay, this.birthdayMonth, this.birthdayYear, this.anniversaryDay,
                this.anniversaryMonth, this.anniversaryYear, this.group);
    }

    public ContactData withAddressEmail(String address) {
        return new ContactData(this.firstName, this.lastName, address, this.email, this.middleName, this.nickName, this.title,
                this.company, this.phoneHome, this.phoneMobile, this.phoneWork,
                this.phoneFax, this.email2, this.email3, this.homePage,
                this.birthdayDay, this.birthdayMonth, this.birthdayYear, this.anniversaryDay,
                this.anniversaryMonth, this.anniversaryYear, this.group);
    }
    public ContactData withEmail(String email) {
        return new ContactData(this.firstName, this.lastName, this.address, email, this.middleName, this.nickName, this.title,
                this.company, this.phoneHome, this.phoneMobile, this.phoneWork,
                this.phoneFax, this.email2, this.email3, this.homePage,
                this.birthdayDay, this.birthdayMonth, this.birthdayYear, this.anniversaryDay,
                this.anniversaryMonth, this.anniversaryYear, this.group);
    }
    public ContactData withPhoneHome(String phoneHome) {
        return new ContactData(this.firstName, this.lastName, this.address, this.email, this.middleName, this.nickName, this.title,
                this.company, phoneHome, this.phoneMobile, this.phoneWork,
                this.phoneFax, this.email2, this.email3, this.homePage,
                this.birthdayDay, this.birthdayMonth, this.birthdayYear, this.anniversaryDay,
                this.anniversaryMonth, this.anniversaryYear, this.group);
    }
}

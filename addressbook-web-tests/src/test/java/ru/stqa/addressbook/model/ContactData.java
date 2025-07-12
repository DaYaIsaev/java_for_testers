package ru.stqa.addressbook.model;

public record ContactData(String id, String firstName, String lastName, String middleName, String nickName,
                          String title, String photo,
                          String company, String address, String phoneHome, String phoneMobile, String phoneWork,
                          String phoneFax, String email, String email2, String email3, String homePage,
                          String birthdayDay, String birthdayMonth, String birthdayYear, String anniversaryDay,
                          String anniversaryMonth, String anniversaryYear, String group) {


    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "",
                "", "", "");
    }


    public ContactData withFirsName(String firstName) {
        return new ContactData(this.id, firstName, this.lastName, this.middleName, this.nickName, this.title,
                this.photo, this.company, this.address, this.phoneHome, this.phoneMobile, this.phoneWork,
                this.phoneFax, this.email, this.email2, this.email3, this.homePage,
                this.birthdayDay, this.birthdayMonth, this.birthdayYear, this.anniversaryDay,
                this.anniversaryMonth, this.anniversaryYear, this.group);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, lastName, this.middleName, this.nickName, this.title,
                this.photo, this.company, this.address, this.phoneHome, this.phoneMobile, this.phoneWork,
                this.phoneFax, this.email, this.email2, this.email3, this.homePage,
                this.birthdayDay, this.birthdayMonth, this.birthdayYear, this.anniversaryDay,
                this.anniversaryMonth, this.anniversaryYear, this.group);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, this.nickName, this.title,
                this.photo, this.company, address, this.phoneHome, this.phoneMobile, this.phoneWork,
                this.phoneFax, this.email, this.email2, this.email3, this.homePage,
                this.birthdayDay, this.birthdayMonth, this.birthdayYear, this.anniversaryDay,
                this.anniversaryMonth, this.anniversaryYear, this.group);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, this.nickName, this.title,
                this.photo, this.company, this.address, this.phoneHome, this.phoneMobile, this.phoneWork,
                this.phoneFax, email, this.email2, this.email3, this.homePage,
                this.birthdayDay, this.birthdayMonth, this.birthdayYear, this.anniversaryDay,
                this.anniversaryMonth, this.anniversaryYear, this.group);
    }

    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, this.nickName, this.title,
                this.photo, this.company, this.address, this.phoneHome, this.phoneMobile, this.phoneWork,
                this.phoneFax, this.email, email2, this.email3, this.homePage,
                this.birthdayDay, this.birthdayMonth, this.birthdayYear, this.anniversaryDay,
                this.anniversaryMonth, this.anniversaryYear, this.group);
    }

    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, this.nickName, this.title,
                this.photo, this.company, this.address, this.phoneHome, this.phoneMobile, this.phoneWork,
                this.phoneFax, this.email, this.email2, email3, this.homePage,
                this.birthdayDay, this.birthdayMonth, this.birthdayYear, this.anniversaryDay,
                this.anniversaryMonth, this.anniversaryYear, this.group);
    }

    public ContactData withPhoneHome(String phoneHome) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, this.nickName, this.title,
                this.photo, this.company, this.address, phoneHome, this.phoneMobile, this.phoneWork,
                this.phoneFax, this.email, this.email2, this.email3, this.homePage,
                this.birthdayDay, this.birthdayMonth, this.birthdayYear, this.anniversaryDay,
                this.anniversaryMonth, this.anniversaryYear, this.group);
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.lastName, this.middleName, this.nickName, this.title,
                this.photo, this.company, this.address, this.phoneHome, this.phoneMobile, this.phoneWork,
                this.phoneFax, this.email, this.email2, this.email3, this.homePage,
                this.birthdayDay, this.birthdayMonth, this.birthdayYear, this.anniversaryDay,
                this.anniversaryMonth, this.anniversaryYear, this.group);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(id, this.firstName, this.lastName, this.middleName, this.nickName, this.title,
                photo, this.company, this.address, this.phoneHome, this.phoneMobile, this.phoneWork,
                this.phoneFax, this.email, this.email2, this.email3, this.homePage,
                this.birthdayDay, this.birthdayMonth, this.birthdayYear, this.anniversaryDay,
                this.anniversaryMonth, this.anniversaryYear, this.group);
    }

    public ContactData withPhoneMobile(String phoneMobile) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, this.nickName, this.title,
                this.photo, this.company, this.address, this.phoneHome, phoneMobile, this.phoneWork,
                this.phoneFax, this.email, this.email2, this.email3, this.homePage,
                this.birthdayDay, this.birthdayMonth, this.birthdayYear, this.anniversaryDay,
                this.anniversaryMonth, this.anniversaryYear, this.group);
    }

    public ContactData withPhoneWork(String phoneWork) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, this.nickName, this.title,
                this.photo, this.company, this.address, this.phoneHome, this.phoneMobile, phoneWork,
                this.phoneFax, this.email, this.email2, this.email3, this.homePage,
                this.birthdayDay, this.birthdayMonth, this.birthdayYear, this.anniversaryDay,
                this.anniversaryMonth, this.anniversaryYear, this.group);
    }


}


package ru.stqa.addressbook.manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    @Column(name = "id")
    public int id;

    public String firstname;
    public String middlename;
    public String lastname;
    public String nickname;
    public String company;
    public String title;
    public String address;

    @Column(name = "home")
    public String phoneHome;

    public String mobile;
    public String work;
    public String fax;
    public String email;
    public String email2;
    public String email3;
    public String homepage;


    public ContactRecord() {

    }

    public ContactRecord(int id, String firstname, String middlename, String lastname, String nickname,
                         String company, String title, String address,
                         String phoneHome, String mobile, String work, String fax, String email, String email2, String email3,
                         String homepage) {

        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.company = company;
        this.title = title;
        this.address = address;
        this.phoneHome = phoneHome;
        this.mobile = mobile;
        this.work = work;
        this.fax = fax;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.homepage = homepage;
    }
}


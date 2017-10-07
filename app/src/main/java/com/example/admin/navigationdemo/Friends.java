package com.example.admin.navigationdemo;

/**
 * Created by jarvis on 02-10-2017.
 */

public class Friends
{
    String name;
    String phoneno;

    public Friends(String Name, String Email, String PhoneNo) {
        this.name = Name;
        this.email = Email;
        this.phoneno =PhoneNo;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String email;

}

package com.devlopp.teq.course;

import com.devlopp.teq.address.Address;

public class CourseContact {
    protected String name;
    protected Address address;
    protected String telephoneNumber;
    protected String telephoneExt;
    protected String emailAddress;

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getTelephoneExt() {
        return telephoneExt;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}

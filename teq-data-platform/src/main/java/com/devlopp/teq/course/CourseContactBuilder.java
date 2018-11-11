package com.devlopp.teq.course;

import com.devlopp.teq.address.Address;

public class CourseContactBuilder {
    private CourseContact contact;

    public CourseContactBuilder() {
        contact = new CourseContact();
    }

    public CourseContactBuilder setContactName(String name) {
        contact.name = name;
        return this;
    }

    public CourseContactBuilder setAddress(Address address) {
        contact.address = address;
        return this;
    }

    public CourseContactBuilder setTelephoneNumber(String number) {
        contact.telephoneNumber = number;
        return this;
    }

    public CourseContactBuilder setTelephoneExt(String ext) {
        contact.telephoneExt = ext;
        return this;
    }

    public CourseContactBuilder setEmailAddress(String emailAddress) {
        contact.emailAddress = emailAddress;
        return this;
    }

    public CourseContact create() {
        return contact;
    }
}

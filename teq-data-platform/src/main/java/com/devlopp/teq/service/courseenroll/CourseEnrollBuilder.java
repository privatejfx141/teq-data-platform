package com.devlopp.teq.service.courseenroll;

import com.devlopp.teq.service.ServiceBuilder;

public class CourseEnrollBuilder extends ServiceBuilder implements ICourseEnrollBuilder {

    private CourseEnroll courseEnroll;

    public CourseEnrollBuilder() {
        service = new CourseEnroll();
        setServiceType("N");
        courseEnroll = (CourseEnroll) service;
    }

    @Override
    public ICourseEnrollBuilder setCourseCode(String courseCode) {
        courseEnroll.courseCode = courseCode;
        return this;
    }

    @Override
    public ICourseEnrollBuilder setFirstClassDate(String date) {
        courseEnroll.firstClassDate = date;
        return this;
    }

    @Override
    public CourseEnroll create() {
        return courseEnroll;
    }
}

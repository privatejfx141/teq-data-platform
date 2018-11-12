package com.devlopp.teq.service.courseexit;

import com.devlopp.teq.service.ServiceBuilder;

public class CourseExitBuilder extends ServiceBuilder implements ICourseExitBuilder {

    private CourseExit courseExit;

    public CourseExitBuilder() {
        service = new CourseExit();
        setServiceType("X");
        courseExit = (CourseExit) service;
    }

    @Override
    public ICourseExitBuilder setCourseCode(String courseCode) {
        courseExit.courseCode = courseCode;
        return this;
    }

    @Override
    public ICourseExitBuilder setExitDate(String date) {
        courseExit.exitDate = date;
        return this;
    }

    @Override
    public ICourseExitBuilder setReason(String reason) {
        courseExit.reason = reason;
        return this;
    }

    @Override
    public ICourseExitBuilder setListeningLevel(String level) {
        courseExit.listeningLevel = level;
        return this;
    }

    @Override
    public ICourseExitBuilder setReadingLevel(String level) {
        courseExit.readingLevel = level;
        return this;
    }

    @Override
    public ICourseExitBuilder setSpeakingLevel(String level) {
        courseExit.speakingLevel = level;
        return this;
    }

    @Override
    public ICourseExitBuilder setWritingLevel(String level) {
        courseExit.writingLevel = level;
        return this;
    }

    @Override
    public CourseExit create() {
        return courseExit;
    }

}

package com.devlopp.teq.service.courseexit;

import com.devlopp.teq.service.Service;

public class CourseExit extends Service {

    protected String courseCode;

    protected String exitDate;

    protected String reason;

    protected String listeningLevel;

    protected String readingLevel;

    protected String speakingLevel;

    protected String writingLevel;

    public String getCourseCode() {
        return courseCode;
    }

    public String getExitDate() {
        return exitDate;
    }

    public String getReason() {
        return reason;
    }

    public String getListeningLevel() {
        return listeningLevel;
    }

    public String getReadingLevel() {
        return readingLevel;
    }

    public String getSpeakingLevel() {
        return speakingLevel;
    }

    public String getWritingLevel() {
        return writingLevel;
    }

}

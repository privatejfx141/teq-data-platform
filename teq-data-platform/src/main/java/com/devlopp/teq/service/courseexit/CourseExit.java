package com.devlopp.teq.service.courseexit;

import com.devlopp.teq.service.Service;

public class CourseExit extends Service {

    protected String courseCode;

    protected String exitDate;

    protected String reason;

    protected int listeningLevel;

    protected int readingLevel;

    protected int speakingLevel;

    protected int writingLevel;

    public String getCourseCode() {
        return courseCode;
    }

    public String getExitDate() {
        return exitDate;
    }

    public String getReason() {
        return reason;
    }

    public int getListeningLevel() {
        return listeningLevel;
    }

    public int getReadingLevel() {
        return readingLevel;
    }

    public int getSpeakingLevel() {
        return speakingLevel;
    }

    public int getWritingLevel() {
        return writingLevel;
    }

}

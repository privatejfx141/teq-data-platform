package com.devlopp.teq.service.courseexit;

import com.devlopp.teq.service.IServiceBuilder;

public interface ICourseExitBuilder extends IServiceBuilder {

    public ICourseExitBuilder setCourseCode(String courseCode);

    public ICourseExitBuilder setExitDate(String date);

    public ICourseExitBuilder setReason(String reason);

    public ICourseExitBuilder setListeningLevel(String level);

    public ICourseExitBuilder setReadingLevel(String level);

    public ICourseExitBuilder setSpeakingLevel(String level);

    public ICourseExitBuilder setWritingLevel(String level);

    public CourseExit create();

}

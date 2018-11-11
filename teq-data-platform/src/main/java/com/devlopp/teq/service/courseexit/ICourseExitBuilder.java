package com.devlopp.teq.service.courseexit;

import com.devlopp.teq.service.IServiceBuilder;

public interface ICourseExitBuilder extends IServiceBuilder {

    public ICourseExitBuilder setCourseCode(String courseCode);

    public ICourseExitBuilder setExitDate(String date);

    public ICourseExitBuilder setReason(String reason);

    public ICourseExitBuilder setListeningLevel(int level);

    public ICourseExitBuilder setReadingLevel(int level);

    public ICourseExitBuilder setSpeakingLevel(int level);

    public ICourseExitBuilder setWritingLevel(int level);

    public CourseExit create();

}

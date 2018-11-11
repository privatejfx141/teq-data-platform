package com.devlopp.teq.service.courseenroll;

import com.devlopp.teq.service.IServiceBuilder;

public interface ICourseEnrollBuilder extends IServiceBuilder {

    public ICourseEnrollBuilder setCourseCode(String courseCode);
    
    public ICourseEnrollBuilder setFirstClassDate(String date);
    
    public CourseEnroll create();
    
}

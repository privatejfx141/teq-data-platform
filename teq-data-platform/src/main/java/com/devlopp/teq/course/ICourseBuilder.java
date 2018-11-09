package com.devlopp.teq.course;

import java.util.List;

public interface ICourseBuilder {
    public ICourseBuilder setCourseCode(String courseCode);

    public ICourseBuilder setNotes(String notes);

    public ICourseBuilder setOngoingBasis(boolean ongoingBasis);

    public ICourseBuilder setLanguage(String language);

    public ICourseBuilder setTrainingFormat(String trainingFormat);

    public ICourseBuilder setClassesHeldAt(String classLocation);

    public ICourseBuilder setInpersonInstruct(float inPercentInstruct);

    public ICourseBuilder setOnlineInstruct(float onlineInstruct);

    public ICourseBuilder setNumberOfSpots(int numberOfSpots);

    public ICourseBuilder setNumberOfIRCC(int numberOfIRCC);

    public ICourseBuilder setEnrollmentType(String enrollmentType);

    public ICourseBuilder setStartDate(String startDate);

    public ICourseBuilder setEndDate(String endDate);

    public ICourseBuilder setInstructHours(int instructHours);

    public ICourseBuilder setWeeklyHours(int weeklyHours);

    public ICourseBuilder setNumWeeks(int numWeeks);

    public ICourseBuilder setNumWeeksPerYear(int numWeeksPerYear);

    public ICourseBuilder setDominantFocus(String dominantFocus);
    
    public ICourseBuilder setCourseContact(CourseContact contact);
    
    public ICourseBuilder setSchedules(List<String> schedules);
    
    public ICourseBuilder setSupportServices(List<String> supportServices);
    
    public ICourseBuilder setTargetGroups(List<String> targetGroups);
    
    public Course create();
}

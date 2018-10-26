package com.teq.entities;

public interface ICourseBuilder {
	
    public ICourseBuilder setCourseCode(String CourseCode);
    
    public ICourseBuilder setNotes(String Notes);
    
    public ICourseBuilder setOngoingBasis(boolean OngoingBasis);
    
    public ICourseBuilder setLanguage(String Language);
    
    public ICourseBuilder setTrainingFormat(String TrainingFormat);
    
    public ICourseBuilder setClassesHeldAt(String ClassesHeldAt);
    
    public ICourseBuilder setInpersonInstruct(float Instruct);

    public ICourseBuilder setOnlineInstruct(float OnlineInstruct);
    
    public ICourseBuilder setNumberOfSpots(int NumberOfSpots);

    public ICourseBuilder setNumberOfIRCC(int NumberOfIRCC);
    
    public ICourseBuilder setEnrollmentType(String EnrollmentType);
    
    public ICourseBuilder setStartDate(String StartDate);

    public ICourseBuilder setEndDate(String EndDate);
    
    public ICourseBuilder setInstructHours(int InstructHours);
    
    public ICourseBuilder setWeeklyHours(int WeeklyHours);
    
    public ICourseBuilder setNumWeeks(int NumWeeks);
    
    public ICourseBuilder setNumWeeksPerYear(int NumWeeksPerYear);
    
    public ICourseBuilder setDominantFocus(String DominantFocus);

}

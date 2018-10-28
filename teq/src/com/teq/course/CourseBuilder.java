package com.teq.course;

public class CourseBuilder implements ICourseBuilder {
    private Course course;

    public CourseBuilder() {
        course = new Course();
    }

    @Override
    public ICourseBuilder setCourseCode(String courseCode) {
        course.courseCode = courseCode;
        return this;
    }

    @Override
    public ICourseBuilder setNotes(String notes) {
        course.notes = notes;
        return this;
    }

    @Override
    public ICourseBuilder setOngoingBasis(boolean ongoingBasis) {
        course.ongoingBasis = ongoingBasis;
        return this;
    }

    @Override
    public ICourseBuilder setLanguage(String language) {
        course.language = language;
        return this;
    }

    @Override
    public ICourseBuilder setTrainingFormat(String trainingFormat) {
        course.trainingFormat = trainingFormat;
        return this;
    }

    @Override
    public ICourseBuilder setClassesHeldAt(String classLocation) {
        course.classLocation = classLocation;
        return this;
    }

    @Override
    public ICourseBuilder setInpersonInstruct(float inpersonInstruct) {
        course.inpersonInstruct = inpersonInstruct;
        return this;
    }

    @Override
    public ICourseBuilder setOnlineInstruct(float onlineInstruct) {
        course.onlineInstruct = onlineInstruct;
        return this;
    }

    @Override
    public ICourseBuilder setNumberOfSpots(int numberOfSpots) {
        course.numberOfSpots = numberOfSpots;
        return this;
    }

    @Override
    public ICourseBuilder setNumberOfIRCC(int numberOfIRCC) {
        course.numberOfIRCC = numberOfIRCC;
        return this;
    }

    @Override
    public ICourseBuilder setEnrollmentType(String enrollmentType) {
        course.enrollmentType = enrollmentType;
        return this;
    }

    @Override
    public ICourseBuilder setStartDate(String startDate) {
        course.startDate = startDate;
        return this;
    }

    @Override
    public ICourseBuilder setEndDate(String endDate) {
        course.endDate = endDate;
        return this;
    }

    @Override
    public ICourseBuilder setInstructHours(int instructHours) {
        course.instructHours = instructHours;
        return this;
    }

    @Override
    public ICourseBuilder setWeeklyHours(int weeklyHours) {
        course.weeklyHours = weeklyHours;
        return this;
    }

    @Override
    public ICourseBuilder setNumWeeks(int numWeeks) {
        course.numWeeks = numWeeks;
        return this;
    }

    @Override
    public ICourseBuilder setNumWeeksPerYear(int numWeeksPerYear) {
        course.numWeeksPerYear = numWeeksPerYear;
        return this;
    }

    @Override
    public ICourseBuilder setDominantFocus(String dominantFocus) {
        course.dominantFocus = dominantFocus;
        return this;
    }

    @Override
    public Course create() {
        return course;
    }
}

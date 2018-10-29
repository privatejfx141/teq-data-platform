CREATE TABLE Course (
    course_code         CHAR(16) PRIMARY KEY NOT NULL,
    notes               VARCHAR(32),
    ongoing_basis       BOOLEAN NOT NULL,
    language            CHAR(3),
    training_format     VARCHAR(32) NOT NULL,
    classes_held_at     VARCHAR(32),
    inperson_instruct   DECIMAL(5,2),
    online_instruct     DECIMAL(5,2),
    number_of_spots     INTEGER NOT NULL,
    number_of_ircc      INTEGER NOT NULL,
    enrollment_type     VARCHAR(32) NOT NULL,
    start_date          DATE NOT NULL,
    end_date            DATE NOT NULL,
    instruct_hours      INTEGER NOT NULL,
    hours_per_week      INTEGER NOT NULL,
    weeks               INTEGER,
    weeks_per_year      INTEGER,
    dominant_focus      VARCHAR(32) NOT NULL
);

CREATE TABLE CourseContact (
    course_code         CHAR(16) PRIMARY KEY NOT NULL,
    contact_name        VARCHAR(32) NOT NULL,
    address_id          INTEGER NOT NULL,
    telephone_number    VARCHAR(32) NOT NULL,
    telephone_ext       VARCHAR(32) NOT NULL,
    email_address       VARCHAR(32) NOT NULL,
    FOREIGN KEY(course_code) REFERENCES Course(course_code),
    FOREIGN KEY(address_id) REFERENCES Address(id)
);

CREATE TABLE CourseSchedule (
    course_code         CHAR(16) NOT NULL REFERENCES Course(course_code),
    schedule_id         INTEGER NOT NULL REFERENCES Schedule(id),
    PRIMARY KEY(course_code, schedule_id)
);

CREATE TABLE CourseSupportService (
    course_code         CHAR(16) NOT NULL REFERENCES Course(course_code),
    support_service_id  INTEGER NOT NULL REFERENCES SupportService(id),
    PRIMARY KEY(course_code, support_service_id)  
);

CREATE TABLE CourseTargetGroup (
    course_code         CHAR(16) NOT NULL REFERENCES Course(course_code),
    target_group_id     INTEGER NOT NULL REFERENCES TargetGroup(id),
    PRIMARY KEY(course_code, target_group_id)
);

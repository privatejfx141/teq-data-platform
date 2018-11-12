CREATE TABLE CourseEnroll (
    service_id          INTEGER NOT NULL REFERENCES Service(id),
    course_code         CHAR(16) NOT NULL REFERENCES Course(course_code),
    first_class_date    DATE NOT NULL,
    PRIMARY KEY(service_id, course_code)
);

CREATE TABLE CourseExit (
    service_id          INTEGER NOT NULL REFERENCES Service(id),
    course_code         CHAR(16) NOT NULL REFERENCES Course(course_code),
    exit_date           DATE,
    reason              VARCHAR(32),
    listening_level     VARCHAR(16),
    reading_level       VARCHAR(16),
    speaking_level      VARCHAR(16),
    writing_level       VARCHAR(16),
    PRIMARY KEY(service_id, course_code)
);

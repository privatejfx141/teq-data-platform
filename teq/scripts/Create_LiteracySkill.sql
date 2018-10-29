CREATE TABLE ListeningSkill (
    course_code         CHAR(16) PRIMARY KEY NOT NULL,
    level               INTEGER NOT NULL,
    value               VARCHAR(16) NOT NULL,
    FOREIGN KEY(course_code) REFERENCES Course(course_code)
);

CREATE TABLE ReadingSkill (
    course_code         CHAR(16) PRIMARY KEY NOT NULL,
    level               INTEGER NOT NULL,
    value               VARCHAR(16) NOT NULL,
    FOREIGN KEY(course_code) REFERENCES Course(course_code)
);

CREATE TABLE SpeakingSkill (
    course_code         CHAR(16) PRIMARY KEY NOT NULL,
    level               INTEGER NOT NULL,
    value               VARCHAR(16) NOT NULL,
    FOREIGN KEY(course_code) REFERENCES Course(course_code)
);

CREATE TABLE WritingSkill (
    course_code         CHAR(16) PRIMARY KEY NOT NULL,
    level               INTEGER NOT NULL,
    value               VARCHAR(16) NOT NULL,
    FOREIGN KEY(course_code) REFERENCES Course(course_code)
);

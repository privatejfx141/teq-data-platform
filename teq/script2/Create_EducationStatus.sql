CREATE TABLE EducationStatus (
    EducationStatusID    CHAR(3) PRIMARY KEY NOT NULL,
    description VARCHAR(255) NOT NULL
);

INSERT INTO EducationStatusID
    (EducationStatus, description)
VALUES
	('FTS', 'Full-time student'),
	('PTS', 'Part-time student'),
	('FTT', 'Full-time training (e.g. language training or job-specific training)'),
	('PTT', 'Part-time training (e.g. language training or job-specific training)'),
	('NAS', 'Not a student');
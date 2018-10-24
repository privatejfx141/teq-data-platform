CREATE TABLE CourseFormat (
    CourseFormatID    CHAR(2) PRIMARY KEY NOT NULL,
    description VARCHAR(255) NOT NULL
);

INSERT INTO CourseFormat
    (CourseFormatID, description)
VALUES
	('BO', 'Blended classroom and online training'),
	('CL', 'Classroom'),
	('IT', 'Itinerant teachers'),
	('OO', 'One-on-one tutoring'),
	('DL', 'Other distance learning (e.g. videoconferencing)'),
	('CT', 'Online computer training'),
	('WP', 'Workplace');
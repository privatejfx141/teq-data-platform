CREATE TABLE ExitReason (
    id CHAR(2) PRIMARY KEY NOT NULL,
    description VARCHAR(255) NOT NULL
);

INSERT INTO TrainingStatus
    (id, description)
VALUES
	('FE', 'Found employment'),
	('ES', 'Enrolled in school'),
	('EL', 'Extended leave'),
	('OS', 'Obtained citizenship'),
	('AL', 'Moved to a more appropriate level'),
	('TP', 'Moved to another training program'),
	('UN', 'Moved/unable to contact client'),
	('IL', 'Illness'),
	('PR', 'Parental leave/caring for family members'),
	('PA', 'Poor attendance'),
	('FQ', 'Focus/quality of course did not meet needs'),
	('SF', 'Schedule/format/location of course did not meet needs'),
	('PE', 'Personal reasons'),
	('SS', 'Lack of support services');


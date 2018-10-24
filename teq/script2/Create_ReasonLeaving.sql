CREATE TABLE ReasonLeaving (
    ReasonLeavingID    CHAR(3) PRIMARY KEY NOT NULL,
    description VARCHAR(32) NOT NULL
);

INSERT INTO ReasonLeaving
    (ReasonLeavingID, description)
VALUES
	('NOT', 'Client felt the service was not meeting current needs'),
	('EMP', 'Found employment'),
	('LAC', 'Lack of support services'),
	('CON', 'Moved/unable to contact client'),
	('CIT', 'Obtained citizenship'),
	('MED', 'Medical reason'),
	('PER', 'Personal reason'),
	('CAR', 'Parental leave/caring for family members'),
	('STU', 'Student left school'),
	('SCH', 'To attend school'),
	('ANO', 'To receive another settlement service'),
	('UNK', 'Reason unknown'),
	('MEN', 'Intervention ended by mentor'),
	('PAR', 'Intervention ended by third party/volunteer');
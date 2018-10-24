CREATE TABLE Referred (
    ReferredID    CHAR(3) PRIMARY KEY NOT NULL,
    description VARCHAR(32) NOT NULL
);

INSERT INTO Referred
    (ReferredID, description)
VALUES
	('COM', 'Community centre / library'),
	('EMP', 'Employer / co-worker'),
	('REG', 'Ethnic or religious organization'),
	('FAM', 'Family / friends'),
	('GOV', 'Canadian government agency'),
	('WEB', 'Government publication / brochure / website'),
	('IMM', 'Immigration consultant / lawyer'),
	('INT', 'Internal to my organization'),
	('NGO', 'Non-governmental newspaper / media / publication / brochure / Web site'),
	('COS', 'In-Canada orientation session'),
	('OOS', 'Overseas orientation session (e.g. CIIP)'),
	('SSP', 'Other settlement service provider'),
	('SCH', 'School'),
	('NOT', 'Not referred');
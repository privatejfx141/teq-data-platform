CREATE TABLE TrainingStatus (
    TrainingStatusID    CHAR(2) PRIMARY KEY NOT NULL,
    description VARCHAR(255) NOT NULL
);

INSERT INTO TrainingStatus
    (TrainingStatusID, description)
VALUES
	('CC', 'Completed the course'),
	('HA', 'Has advanced to the next CLB level'),
	('EX', 'Exited the course before completing');
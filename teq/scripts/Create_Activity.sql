CREATE TABLE Activity (
    ActivityID    CHAR(3) PRIMARY KEY NOT NULL,
    description VARCHAR(32) NOT NULL
);

INSERT INTO Activity
    (ActivityID, description)
VALUES
	('COM', 'Community-based group events and activities'),
	('TAR', 'Targeted Matching and Networking');
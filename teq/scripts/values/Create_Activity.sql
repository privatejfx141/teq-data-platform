CREATE TABLE Activity (
    id CHAR(3) PRIMARY KEY NOT NULL,
    description VARCHAR(32) NOT NULL
);

INSERT INTO Activity
    (id, description)
VALUES
	('COM', 'Community-based group events and activities'),
	('TAR', 'Targeted Matching and Networking');

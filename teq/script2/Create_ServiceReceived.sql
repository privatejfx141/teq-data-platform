CREATE TABLE ServiceReceived (
    ServiceReceivedID    CHAR(3) PRIMARY KEY NOT NULL,
    description VARCHAR(255) NOT NULL
);

INSERT INTO ServiceReceived
    (ServiceReceivedID, description)
VALUES
	('COM', 'Community-based group events and activities: Group session (e.g. conversation circles)'),
	('MNO', 'Targeted Matching and Networking: One-on-one session (e.g. mentoring)'),
	('MNGTargeted Matching and Networking: Group session (e.g. conversation circles)'),
	('ONE', 'One-on-one orientation'),
	('FAM', 'Family orientation'),
	('GRO', 'Group orientation');
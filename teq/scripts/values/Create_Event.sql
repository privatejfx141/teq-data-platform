CREATE TABLE Event (
    id CHAR(3) PRIMARY KEY NOT NULL,
    description VARCHAR(255) NOT NULL
);

INSERT INTO Event
    (id, description)
VALUES
    ('CIR', 'Conversation circle'),
	('TAR', 'Targeted matching between newcomer and settled immigrant or long-time Canadian'),
	('NET', 'Networking activity with other newcomers or Canadian citizens'),
	('LEA', 'Youth leadership project'),
	('REG', 'Other regular group activities to address ongoing needs or interests');
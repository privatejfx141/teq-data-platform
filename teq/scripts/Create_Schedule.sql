CREATE TABLE Schedule (
    id INTEGER PRIMARY KEY NOT NULL,
    description VARCHAR(32) NOT NULL
);

INSERT INTO Schedule
    (description)
VALUES
    ('Morning'),
    ('Afternoon'),
    ('Evening'),
    ('Weekend'),
    ('Anytime'),
    ('Online');

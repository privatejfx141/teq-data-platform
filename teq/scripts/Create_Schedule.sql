CREATE TABLE SCHEDULE (
    id INTEGER PRIMARY KEY NOT NULL,
    description TEXT NOT NULL
);

INSERT INTO SCHEDULE
    (description)
VALUES
    ('Morning'),
    ('Afternoon'),
    ('Evening'),
    ('Weekend'),
    ('Anytime'),
    ('Online');

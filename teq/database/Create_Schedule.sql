CREATE TABLE [IF NOT EXISTS] [teqdb].Schedule (
    ScheduleCode CHAR(5) PRIMARY KEY,
    Description VARCHAR(64)
);

INSERT INTO Schedule (ScheduleCode, Description)
VALUES
    ('MORNG', 'Morning'),
    ('AFTNN', 'Afternoon'),
    ('EVENG', 'Evening'),
    ('WKEND', 'Weekend'),
    ('ANYTM', 'Anytime'),
    ('ONLNE', 'Online')

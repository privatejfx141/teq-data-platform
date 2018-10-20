CREATE TABLE [IF NOT EXISTS] [teqdb].ScheduleType (
    ScheduleCode CHAR(5) PRIMARY KEY,
    Description VARCHAR(64)
);

INSERT INTO ScheduleType (ScheduleCode, Description)
VALUES
    ('MORNG', 'Morning'),
    ('AFTNN', 'Afternoon'),
    ('EVENG', 'Evening'),
    ('WKEND', 'Weekend'),
    ('ANYTM', 'Anytime'),
    ('ONLNE', 'Online')

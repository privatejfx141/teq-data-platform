CREATE TABLE [IF NOT EXISTS] [teqdb].AssessmentIncrease (
    AssessmentIncreaseCode CHAR(5) PRIMARY KEY,
    Description VARCHAR(64)
);

INSERT INTO AssessmentIncrease (AssessmentIncreaseCode, Description)
VALUES
    ('LIFCA', 'Life in Canada'),
    ('CMGOV', 'Community and Government Services'),
    ('WRKCA', 'Working in Canada'),
    ('EDUCA', 'Education in Canada'),
    ('SCNET', 'Social networks'),
    ('PFNET', 'Professional networks'),
    ('ACLCS', 'Access to local community services'),
    ('LVLCI', 'Level of community involvement');

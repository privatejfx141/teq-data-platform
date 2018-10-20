CREATE TABLE [IF NOT EXISTS] [teqdb].NonIRCCService (
    NonIRCCServiceCode CHAR(5) PRIMARY KEY,
    Description VARCHAR(64)
);

INSERT INTO NonIRCCService (NonIRCCServiceCode, Description)
VALUES
    ('FCOMN', 'Food/Clothing/Other Material Needs'),
    ('HSACC', 'Housing/Accommodation'),
    ('HMHWB', 'Health/Mental Health/Well Being'),
    ('FINAN', 'Financial'),
    ('FAMSP', 'Family Support'),
    ('LANGU', 'Language (Non-IRCC)'),
    ('EDUSD', 'Education/Skills Development'),
    ('EMPLY', 'Employment-related')
    ('LGIAS', 'Legal Information and Services')
    ('COMSV', 'Community Services');

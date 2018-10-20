CREATE TABLE [IF NOT EXISTS] [teqdb].TargetGroupType (
    TargetGroupCode CHAR(5) PRIMARY KEY,
    Description VARCHAR(64)
);

INSERT INTO TargetGroupType (TargetGroupCode, Description)
VALUES
    ('CHILD', 'Children (0-14 yrs)'),
    ('YOUTH', 'Youth (15-24 yrs)'),
    ('SENOR', 'Senior'),
    ('GENDR', 'Gender-specific'),
    ('REFUG', 'Refugees'),
    ('ETHNC', 'Ethnic/cultural/linguistic group'),
    ('DEAFH', 'Deaf or Hard of Hearing'),
    ('BLIND', 'Blind or Partially Sighted'),
    ('LGBTQ', 'Lesbian, Gay, Bisexual, Transgender, Queer (LGBTQ)'),
    ('FAMIL', 'Families/Parents'),
    ('OTHER', 'Other impairments (physical, mental)'),
    ('REGPF', 'Clients with international training in a regulated profession'),
    ('REGTD', 'Clients with international training in a regulated trade'),
    ('LGMIN', 'Official language minorities');

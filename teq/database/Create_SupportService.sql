CREATE TABLE [IF NOT EXISTS] [teqdb].SupportService (
    SupportServiceCode CHAR(8) PRIMARY KEY,
    Description VARCHAR(64)
);

INSERT INTO SupportService (SupportServiceCode, Description) 
VALUES
    ('CARECHLD', 'Care for Newcomer Children'),
    ('TRNSPORT', 'Transportation'),
    ('PROVDSBL', 'Provisions for Disabilities'),
    ('TRNSLATN', 'Translation'),
    ('INTRPATN', 'Interpretation'),
    ('CRISISCN', 'Crisis Counciling');

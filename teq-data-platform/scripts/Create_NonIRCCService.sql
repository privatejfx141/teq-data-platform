CREATE TABLE NonIRCCService (
    id INTEGER PRIMARY KEY NOT NULL,
    description VARCHAR(32) NOT NULL
);

INSERT INTO NonIRCCService
    (description)
VALUES
    ('Food/Clothing/Other Material Needs'),
    ('Housing/Accommodation'),
    ('Health/Mental Health/Well Being'),
    ('Financial'),
    ('Family Support'),
    ('Language (Non-IRCC)'),
    ('Education/Skills Development'),
    ('Employment-related'),
    ('Legal Information and Services'),
    ('Community Services');

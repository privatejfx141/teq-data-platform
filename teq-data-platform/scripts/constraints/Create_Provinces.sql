CREATE TABLE Province (
    id CHAR(2) PRIMARY KEY NOT NULL,
    name VARCHAR(32) NOT NULL,
    french_name VARCHAR(32) NOT NULL
)

INSERT INTO Province
    (id, name, french_name)
VALUES
    ('AB', 'Alberta', 'Alberta'),
    ('BC', 'British Columbia', 'Colombie-Britannique'),
    ('MB', 'Manitoba', 'Manitoba'),
    ('NB', 'New Brunswick', 'Nouveau-Brunswick'),
    ('NL', 'Newfoundland and Labrador', 'Terre-Neuve-et-Labrador'),
    ('NS', 'Nova Scotia', 'Nouvelle-Écosse'),
    ('NT', 'Northwest Territories', 'Territoires du Nord-Ouest'),
    ('NU', 'Nunavut', 'Nunavut'),
    ('ON', 'Ontario', 'Ontario'),
    ('PE', 'Prince Edward Island', 'Île-du-Prince-Édouard'),
    ('QC', 'Quebec', 'Québec'),
    ('SK', 'Saskatchewan', 'Saskatchewan'),
    ('YT', 'Yukon', 'Yukon');

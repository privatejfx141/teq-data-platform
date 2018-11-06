CREATE TABLE InterventionType (
    code                CHAR(1) PRIMARY KEY NOT NULL,
    table_name          VARCHAR(32) NOT NULL,
    description         VARCHAR(32) NOT NULL
);

INSERT INTO InterventionType
    (code, table_name, description)
VALUES
    ('L', 'LongTermIntervention', 'Long-Term Intervention'),
    ('S', 'ShortTermIntervention', 'Short-Term Intervention');

CREATE TABLE Employment (
    service_id          INTEGER PRIMARY KEY NOT NULL,
    registration        BOOLEAN NOT NULL,
    referral_to         VARCHAR(32),
    referral_date       DATE,
    employment_status   VARCHAR(32),
    education_status    VARCHAR(32),
    occupation_canada   VARCHAR(32),
    occupation_intend   VARCHAR(32),
    intervention_type   CHAR(1),
    time_spent_hours    INTEGER,
    time_spent_minutes  INTEGER,
    FOREIGN KEY(service_id) REFERENCES Service(id),
    FOREIGN KEY(intervention_type) REFERENCES InterventionType(code)
);

CREATE TABLE LongTermIntervention (
    employment_id       INTEGER PRIMARY KEY NOT NULL,
    service_recieved    VARCHAR(32),
    status              VARCHAR(32),
    reason_for_leave    VARCHAR(32),
    start_date          DATE,
    end_date            DATE,
    employer_size       INTEGER,
    placement_was       VARCHAR(32),
    avg_hours_per_week  VARCHAR(32),
    met_mentor_at       VARCHAR(32),
    hours_per_week      INTEGER,
    profession          VARCHAR(32),
    FOREIGN KEY(employment_id) REFERENCES Employment(service_id)
);

CREATE TABLE ShortTermIntervention (
    employment_id       INTEGER NOT NULL,
    service_recieved    VARCHAR(32) NOT NULL,
    date                DATE NOT NULL,
    PRIMARY KEY(employment_id, service_recieved, date),
    FOREIGN KEY(employment_id) REFERENCES Employment(service_id)
);

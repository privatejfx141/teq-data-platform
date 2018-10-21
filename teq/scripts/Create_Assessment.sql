CREATE TABLE Assessment (
    service_id          INTEGER PRIMARY KEY NOT NULL REFERENCES Service(id),
    start_date          DATE NOT NULL,
    language_skill_goal VARCHAR(32),
    other_skill_goal    VARCHAR(32),
    intends_citizenship BOOLEAN NOT NULL,
    req_support_service BOOLEAN NOT NULL,
    plan_complete       BOOLEAN NOT NULL,
    end_date            DATE NOT NULL,
);

CREATE TABLE AssessmentFindEmployment (
    assessment_id       INTEGER PRIMARY KEY NOT NULL,
    time_frame          VARCHAR(32),
    min_one_year        BOOLEAN,
    skill_level         CHAR(1),
    intends_to_obtain   BOOLEAN,
    FOREIGN KEY(assessment_id) REFERENCES Assessment(service_id)
);

CREATE TABLE AssessmentIncrease (
    assessment_id       INTEGER NOT NULL REFERENCES Assessment(service_id),
    increase_id         INTEGER NOT NULL REFERENCES Increase(id),
    referrals           BOOLEAN,
    PRIMARY KEY(assessment_id, increase_id)
);

CREATE TABLE AssessmentNonIRCCService (
    assessment_id       INTEGER NOT NULL REFERENCES Assessment(service_id),
    non_ircc_service_id INTEGER NOT NULL REFERENCES NonIRCCService(id),
    PRIMARY KEY(assessment_id, non_ircc_service_id)
);

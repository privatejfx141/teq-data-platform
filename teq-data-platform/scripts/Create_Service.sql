CREATE TABLE ServiceType (
    id CHAR(1) PRIMARY KEY NOT NULL,
    table_name VARCHAR(32) NOT NULL,
    description VARCHAR(32) NOT NULL
);

INSERT INTO ServiceType
    (id, table_name, description)
VALUES
    ('A', 'Assessment', 'Needs Assessment & Referrals'),
    ('C', 'CommunityConnections', 'Community Connections'),
    ('O', 'Orientation', 'Information & Orientation'),
    ('E', 'Employment', 'Employment'),
    ('N', 'CourseEnrol', 'LT Client Enrol'),
    ('X', 'CourseExit', 'LT Client Exit');

CREATE TABLE Service (
    id                  INTEGER PRIMARY KEY NOT NULL,
    client_id           INTEGER NOT NULL,
    postal_code         CHAR(6),
    language            CHAR(3),
    organization_type   VARCHAR(32),
    referred_by         VARCHAR(32),
    update_reason       VARCHAR(32),
    service_type        CHAR(1),
    FOREIGN KEY(client_id) REFERENCES Client(id),
    FOREIGN KEY(service_type) REFERENCES ServiceType(id)
);

CREATE TABLE ServiceEssentialSkill (
    service_id          INTEGER NOT NULL REFERENCES Service(id),
    essential_skill_id  INTEGER NOT NULL REFERENCES EssentialSkill(id),
    PRIMARY KEY(service_id, essential_skill_id)
);

CREATE TABLE ServiceSupportService (
    service_id          INTEGER NOT NULL REFERENCES Service(id),
    support_service_id  INTEGER NOT NULL REFERENCES SupportService(id),
    PRIMARY KEY(service_id, support_service_id)
);

CREATE TABLE ServiceTargetGroup (
    service_id          INTEGER NOT NULL REFERENCES Service(id),
    target_group_id     INTEGER NOT NULL REFERENCES TargetGroup(id),
    PRIMARY KEY(service_id, target_group_id)
);

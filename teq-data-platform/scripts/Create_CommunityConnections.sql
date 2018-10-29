CREATE TABLE CommunityConnections (
    service_id          INTEGER PRIMARY KEY NOT NULL,
    event_type          VARCHAR(32),
    main_topic          VARCHAR(32) NOT NULL,
    service_recieved    VARCHAR(32) NOT NULL,
    participants        INTEGER,
    volunteers          BOOLEAN,
    status              VARCHAR(32) NOT NULL,
    reason_for_leave    VARCHAR(32),
    start_date          DATE NOT NULL,
    end_date            DATE,
    projected_end_date  DATE,
    length_hours        INTEGER,
    length_minutes      INTEGER,
    FOREIGN KEY(service_id) REFERENCES Service(id)
);

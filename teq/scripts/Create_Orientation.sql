CREATE TABLE Orientation (
    service_id          INTEGER PRIMARY KEY NOT NULL,
    service_recieved    VARCHAR(32) NOT NULL,
    total_length        VARCHAR(32),
    length_hours        INTEGER,
    length_minutes      INTEGER,
    number_of_clients   INTEGER,
    end_date            DATE
);

CREATE TABLE OrientationTopic (
    orientation_id      INTEGER NOT NULL,
    topic_id            INTEGER NOT NULL,
    referrals           BOOLEAN,
    PRIMARY KEY(orientation_id, topic_id),
    FOREIGN KEY(orientation_id) REFERENCES Orientation(service_id),
    FOREIGN KEY(topic_id) REFERENCES Topic(id)
);

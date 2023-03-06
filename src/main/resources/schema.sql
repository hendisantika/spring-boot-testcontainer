CREATE TABLE if NOT EXISTS customers
(
    id
    bigserial
    NOT
    NULL,
    NAME
    VARCHAR
    NOT
    NULL,
    email
    VARCHAR
    NOT
    NULL,
    PRIMARY
    KEY
(
    id
),
    UNIQUE
(
    email
));

CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE if NOT EXISTS consultant
(
    id
    uuid
    NOT
    NULL
    PRIMARY
    KEY
    UNIQUE,
    NAME
    VARCHAR,
    grade
    INT,
    technology
    VARCHAR
);

CREATE TABLE if NOT EXISTS project
(
    id
    uuid
    NOT
    NULL
    PRIMARY
    KEY
    UNIQUE,
    NAME
    VARCHAR,
    start_date
    DATE
);

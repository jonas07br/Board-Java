--liquibase formatted sql
--changeset Jonas:11712

CREATE TABLE boards (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
 --rollback DROP TABLE BOARDS
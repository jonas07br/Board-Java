--liquibase formatted sql
--changeset jonas:202507082126

CREATE TABLE cards (
                       id BIGSERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       description VARCHAR(255) NOT NULL,
                       board_column_id BIGINT NOT NULL,
                       CONSTRAINT boards_columns__cards_fk
                           FOREIGN KEY (board_column_id) REFERENCES boards_columns(id) ON DELETE CASCADE
);

--rollback DROP TABLE blocks
--liquibase formatted sql
--changeset jonas:202507082121

CREATE TABLE boards_columns (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    "order" INT NOT NULL,
    type VARCHAR(7) NOT NULL,
    board_id BIGINT NOT NULL,
    CONSTRAINT boards_boards_columns_fk
        FOREIGN KEY (board_id) REFERENCES boards(id) ON DELETE CASCADE,
    CONSTRAINT id_order_uk
        UNIQUE (board_id, "order")
);
--rollback DROP TABLE boards_columns
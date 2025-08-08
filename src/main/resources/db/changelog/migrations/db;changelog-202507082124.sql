--liquibase formatted sql
--changeset jonas:202507082124
CREATE TABLE blocks (
                        id BIGSERIAL PRIMARY KEY,
                        blocked_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                        block_reason VARCHAR(255) NOT NULL,
                        unblocked_at TIMESTAMP WITH TIME ZONE,
                        unblock_reason VARCHAR(255),
                        card_id BIGINT NOT NULL,
                        CONSTRAINT cards__blocks_fk
                            FOREIGN KEY (card_id) REFERENCES cards(id) ON DELETE CASCADE
);

--rollback DROP TABLE cards
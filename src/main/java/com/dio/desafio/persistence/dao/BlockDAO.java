package com.dio.desafio.persistence.dao;

import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.OffsetDateTime;

import static com.dio.desafio.converter.OffsetDateTimeConverter.toTimestamp;

@AllArgsConstructor
public class BlockDAO {

    private final Connection connection;

    public void block(final String reason, final Long cardId) throws SQLException {
        var sql = "INSERT INTO blocks (blocked_at, block_reason, card_id) VALUES (?, ?, ?);";
        try(var statement = connection.prepareStatement(sql)){
            var index  = 1;
            statement.setTimestamp(index  ++, toTimestamp(OffsetDateTime.now()));
            statement.setString(index  ++, reason);
            statement.setLong(index , cardId);
            statement.executeUpdate();
        }
    }

    public void unblock(final String reason, final Long cardId) throws SQLException{
        var sql = "UPDATE blocks SET unblocked_at = ?, unblock_reason = ? WHERE card_id = ? AND unblock_reason IS NULL;";
        try(var statement = connection.prepareStatement(sql)){
            var index  = 1;
            statement.setTimestamp(index  ++, toTimestamp(OffsetDateTime.now()));
            statement.setString(index  ++, reason);
            statement.setLong(index , cardId);
            statement.executeUpdate();
        }
    }

}

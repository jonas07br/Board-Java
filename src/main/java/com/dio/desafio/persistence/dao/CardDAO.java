package com.dio.desafio.persistence.dao;

import com.dio.desafio.dto.CardDTO;
import com.dio.desafio.persistence.entity.Card;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import static com.dio.desafio.converter.OffsetDateTimeConverter.toOffsetDateTime;
import static java.util.Objects.nonNull;

@AllArgsConstructor
public class CardDAO {
    private Connection connection;

    public Card insert(final Card card) throws SQLException{
        var sqlString = "INSERT INTO cards (title,description,board_column_id) VALUES (?,?,?)";
        try(var statement = connection.prepareStatement(sqlString, Statement.RETURN_GENERATED_KEYS)) {
            var index = 1;
            statement.setString(index++, card.getTitle());
            statement.setString(index++, card.getDescription());
            statement.setLong(index, card.getColumn().getId());
            statement.executeUpdate();
            try(var generatedKeys = statement.getGeneratedKeys()) {
                if(generatedKeys.next()){
                    card.setId(generatedKeys.getLong(1));
                }
            }
        }
        return card;

    }

    public Card updateCardTitleAndDescription(final Card card) throws SQLException{
        var sqlString = "UPDATE cards SET title=?,description=? WHERE id=?";
        try(var statement = connection.prepareStatement(sqlString)) {
            var index = 1;
            statement.setString(index++, card.getTitle());
            statement.setString(index++, card.getDescription());
            statement.setLong(index, card.getId());
        }
        return card;
    }

    public void moveToColumn(final Long columnId, final Long cardId) throws SQLException{
        var sql = "UPDATE cards SET board_column_id = ? WHERE id = ?;";
        try(var statement = connection.prepareStatement(sql)){
            var i = 1;
            statement.setLong(i ++, columnId);
            statement.setLong(i, cardId);
            statement.executeUpdate();
        }
    }
    
    public Optional<CardDTO> findById(final Long id) throws SQLException {
        var sql =
                """
                SELECT c.id,
                       c.title,
                       c.description,
                       b.blocked_at,
                       b.block_reason,
                       c.board_column_id,
                       bc.name,
                       (SELECT COUNT(sub_b.id)
                               FROM BLOCKS sub_b
                              WHERE sub_b.card_id = c.id) blocks_amount
                  FROM CARDS c
                  LEFT JOIN BLOCKS b
                    ON c.id = b.card_id
                   AND b.unblocked_at IS NULL
                 INNER JOIN BOARDS_COLUMNS bc
                    ON bc.id = c.board_column_id
                  WHERE c.id = ?;
                """;
        try(var statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            statement.executeQuery();
            var resultSet = statement.getResultSet();
            if (resultSet.next()){
                var dto = new CardDTO(
                        resultSet.getLong("c.id"),
                        resultSet.getString("c.title"),
                        resultSet.getString("c.description"),
                        nonNull(resultSet.getString("b.block_reason")),
                        toOffsetDateTime(resultSet.getTimestamp("b.blocked_at")),
                        resultSet.getString("b.block_reason"),
                        resultSet.getInt("blocks_amount"),
                        resultSet.getLong("c.board_column_id"),
                        resultSet.getString("bc.name")
                );
                return Optional.of(dto);
            }
        }
        return Optional.empty();
    }
}

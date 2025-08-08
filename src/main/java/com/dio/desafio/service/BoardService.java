package com.dio.desafio.service;

import com.dio.desafio.persistence.dao.BoardDAO;
import com.dio.desafio.persistence.dao.ColumnDAO;
import com.dio.desafio.persistence.entity.Board;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;

@AllArgsConstructor
public class BoardService {
    private final Connection connection;

    public Board insert(final Board entity) throws SQLException {
        var dao = new BoardDAO(connection);
        var boardColumnDAO = new ColumnDAO(connection);
        try{
            dao.insert(entity);
            var columns = entity.getColumns().stream().map(c -> {
                c.setBoard(entity);
                return c;
            }).toList();
            for (var column :  columns){
                boardColumnDAO.insert(column);
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
        return entity;
    }

    public boolean delete(final Long id) throws SQLException {
        var dao = new BoardDAO(connection);
        try{
            if (!dao.exists(id)) {
                return false;
            }
            dao.delete(id);
            connection.commit();
            return true;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }
}

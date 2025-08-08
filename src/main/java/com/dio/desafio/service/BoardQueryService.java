package com.dio.desafio.service;

import com.dio.desafio.dto.BoardDTO;
import com.dio.desafio.persistence.dao.BoardDAO;
import com.dio.desafio.persistence.dao.ColumnDAO;
import com.dio.desafio.persistence.entity.Board;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@AllArgsConstructor
public class BoardQueryService {

    private final Connection connection;

    public Optional<BoardDTO> showBoardDetails(final Long id) throws SQLException {
        var dao = new BoardDAO(connection);
        var boardColumnDAO = new ColumnDAO(connection);
        var optional = dao.findById(id);
        if (optional.isPresent()){
            var entity = optional.get();
            var columns = boardColumnDAO.findByBoardIdWithDetails(entity.getId());
            var dto = new BoardDTO(entity.getId(), entity.getTitle(), columns);
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    public Optional<Board> findById(final Long id) throws SQLException {
        var dao = new BoardDAO(connection);
        var boardColumnDAO = new ColumnDAO(connection);
        var optional = dao.findById(id);
        if (optional.isPresent()){
            var entity = optional.get();
            entity.setColumns(boardColumnDAO.findByBoardId(entity.getId()));
            return Optional.of(entity);
        }
        return Optional.empty();
    }

}

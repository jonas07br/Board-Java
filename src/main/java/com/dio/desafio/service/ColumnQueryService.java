package com.dio.desafio.service;

import com.dio.desafio.persistence.dao.ColumnDAO;
import com.dio.desafio.persistence.entity.Column;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@AllArgsConstructor
public class ColumnQueryService {
    private final Connection connection;

    public Optional<Column> findById(final Long id) throws SQLException {
        var dao = new ColumnDAO(connection);
        return dao.findById(id);
    }

}

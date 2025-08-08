package com.dio.desafio.service;

import com.dio.desafio.dto.CardDTO;
import com.dio.desafio.persistence.dao.CardDAO;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@AllArgsConstructor
public class CardQueryService {
    private final Connection connection;

    public Optional<CardDTO> findById(final Long id) throws SQLException {
        var dao = new CardDAO(connection);
        return dao.findById(id);
    }

}

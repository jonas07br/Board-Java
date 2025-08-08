package com.dio.desafio.dto;

import com.dio.desafio.persistence.Enums.ColumnType;

public record ColumnDTO(Long id,
                        String name,
                        ColumnType type,
                        int cardsAmount) {
}

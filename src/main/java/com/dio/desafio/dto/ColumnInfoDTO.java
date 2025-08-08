package com.dio.desafio.dto;

import com.dio.desafio.persistence.Enums.ColumnType;

public record ColumnInfoDTO (Long id, int order, ColumnType type) {
}

package com.dio.desafio.persistence.entity;

import com.dio.desafio.persistence.Enums.ColumnType;
import lombok.Data;

@Data
public class Column {
    private ColumnType type;
}

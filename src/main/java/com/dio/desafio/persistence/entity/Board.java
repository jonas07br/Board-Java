package com.dio.desafio.persistence.entity;

import com.dio.desafio.persistence.Enums.ColumnType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.lang.reflect.GenericDeclaration;
import java.util.ArrayList;
import java.util.List;

@Data
public class Board {
    private Long id;
    private String title;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Column> columns = new ArrayList<>();

    public Column getColumn(ColumnType type) {
        return columns.stream().filter(
                column -> column.getType().equals(type)
        ).findFirst().orElse(null);
    }

    public Column getCanceledColumn() {
        return columns.stream().filter(column -> column.getType().equals(ColumnType.CANCELLED)).findFirst().orElse(null);
    }

    public Column getToDoColumn() {
        return columns.stream().filter(column -> column.getType().equals(ColumnType.TODO)).findFirst().orElse(null);
    }
}

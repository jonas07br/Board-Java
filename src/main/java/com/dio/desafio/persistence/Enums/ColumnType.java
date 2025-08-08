package com.dio.desafio.persistence.Enums;

import java.util.stream.Stream;

public enum ColumnType {
    TODO,
    DOING,
    FINISHED,
    CANCELLED;
    public static ColumnType getByName(final String name){

        return(Stream.of(ColumnType.values()).filter(
                columnType -> columnType.name().equals(name)
        ).findFirst().orElse(null));
    }
}

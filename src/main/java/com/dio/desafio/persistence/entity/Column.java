package com.dio.desafio.persistence.entity;

import com.dio.desafio.persistence.Enums.ColumnType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
public class Column {

    private Long id;
    private String name;
    private int order;
    private ColumnType type;
    private Board board = new Board();
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Card> cards = new ArrayList<>();
}

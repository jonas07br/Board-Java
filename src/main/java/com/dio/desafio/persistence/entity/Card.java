package com.dio.desafio.persistence.entity;

import com.dio.desafio.persistence.Enums.ColumnType;
import lombok.Data;

@Data
public class Card {
    private int id;
    private String title;
    private String description;
    private Column column;
}

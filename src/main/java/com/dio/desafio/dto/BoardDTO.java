package com.dio.desafio.dto;

import java.util.List;

public record BoardDTO (Long id,
                        String name,
                        List<ColumnDTO> columns) {
}

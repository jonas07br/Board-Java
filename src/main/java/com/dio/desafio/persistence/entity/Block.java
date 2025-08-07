package com.dio.desafio.persistence.entity;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Block {
    private int id;
    private OffsetDateTime blockedAt;
    private String blockReason;
    private OffsetDateTime unblockedAt;
    private String unblockReason;
}

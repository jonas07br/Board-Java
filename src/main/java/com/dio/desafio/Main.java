package com.dio.desafio;

import com.dio.desafio.persistence.MigrationStrategy;

import static com.dio.desafio.persistence.config.ConnectionConfig.getConnection;

public class Main {
    public static void main(String[] args) {
        try(var connection = getConnection()){
            new MigrationStrategy(connection).executeMigration();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
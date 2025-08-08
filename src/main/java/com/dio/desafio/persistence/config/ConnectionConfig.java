package com.dio.desafio.persistence.config;

import static  lombok.AccessLevel.PRIVATE;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor(access = PRIVATE)
public final class ConnectionConfig {
    public static Connection getConnection() throws SQLException {
        var url="jdbc:postgresql://localhost:1212/board";
        var user="postgres";
        var password="postgre";
        var connection = DriverManager.getConnection(url,user,password);
        connection.setAutoCommit(false);
        return connection;
    }
}

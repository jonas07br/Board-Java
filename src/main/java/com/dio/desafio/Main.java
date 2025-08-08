package com.dio.desafio;

import com.dio.desafio.persistence.MigrationStrategy;
import com.dio.desafio.ui.MainMenu;

import static com.dio.desafio.persistence.config.ConnectionConfig.getConnection;

public class Main {
    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        try(var connection = getConnection()){
            new MigrationStrategy(connection).executeMigration();
            menu.execute();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("FALHA AO INICIAR APLICACAO");
        }
    }
}
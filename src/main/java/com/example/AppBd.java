package com.example;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AppBd {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Nao foi possível carregar a biblioteca(classe) para acesso ao banco de dados" + e.getMessage());
        }
        Statement statement = null;
        try(var conn = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "gitpod", "")){
            System.out.println("Conexão com o banco realizada com sucesso");

            statement = conn.createStatement();
            var result = statement.executeQuery("select * from estado");
            while(result.next()){
                System.out.printf("Id %d Nome %s UF %s\n", result.getInt("Id"), result.getString("Nome"), result.getString("UF"));
            }
        } catch (SQLException e) {
            if (statement == null)
            System.err.println("Nâo foi possível conectar ao banco dados" + e.getMessage());
            else System.err.println("Nâo foi possível fazer a consulta ao banco dados" + e.getMessage());
        }
    }
}

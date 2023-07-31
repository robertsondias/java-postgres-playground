package com.example;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AppBd {
    public static void main(String[] args) {

        try {
            Class.forName("org.postgresql.Driver");     
        
        } catch (ClassNotFoundException e) {
            System.err.println("Não foi possível carregar a biblioteca para acesso ao BD" + e.getMessage());            
        }

        Statement statement = null;
        try (var conn = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "gitpod", "")){
            System.out.println("Conexão com o banco de dados com sucesso.");

            statement = conn.createStatement();
            var result = statement.executeQuery("select * from estado");
            while(result.next()){
                System.out.printf("Id: %d Nome: %s UF: %s\n", result.getInt("id"), result.getString("nome"), result.getString("uf"));
            }
        }   
            catch (SQLException e) {
            if (statement == null)
                System.err.println("Não foi possivel conectar ao BD: " + e.getMessage());
            else System.err.println("Não foi possível a consulta ao BD: " + e.getMessage());            
        }
    }
}

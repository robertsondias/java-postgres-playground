package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AppBd {
    private static final String PASSWORD = "";
    private static final String USERNAME = "gitpod";
    private static final String JDBC_URL = "jdbc:postgresql://localhost/postgres";

    public static void main(String[] args) {
        new AppBd();
    }

    public AppBd(){
        try(var conn = getConnection()){
            carregarDriveJDBC();
            listarEstados(conn);
            localizarEstado(conn, "PR");
            listarDadosTabela(conn, "cliente");
        } catch (SQLException e) {
            System.err.println("Nâo foi possível conectar ao banco dados" + e.getMessage());
        }        
    }    

	private void listarDadosTabela(Connection conn, String tabela) {
        var sql = "select * from " + tabela;
       System.out.println(sql);
       try {
		    var statement = conn.createStatement();
            var result = statement.executeQuery(sql);
            while (result.next()){
                int cols = result.getMetaData().getColumnCount();
                for (int i = 1; i <= cols; i++) {
                    System.out.printf("%-25s | ", result.getString(i));
                }
                System.out.println();
            }
	    } catch (SQLException e) {
            System.err.println("Erro na execução da consulta" + e.getMessage() );
        }   
	}

	private void localizarEstado(Connection conn, String uf) {
        try {
            // var sql = "select * from estado where uf = '" + uf + "'"; - SQL Injection
            var sql = "select * from estado where uf = ?";
            var statement = conn.prepareStatement(sql);
            // System.out.println(sql);
            statement.setString(1, uf);
            var result = statement.executeQuery();
            if(result.next()){
                System.out.printf("Id: %d Nome: %s UF: %s\n", result.getInt("id"), result.getString("nome"), result.getString("uf"));
            }
            System.out.println();
        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta SQL." + e.getMessage());
        }
    }

    private void listarEstados(Connection conn) {
        try{
            System.out.println("Conexão com o banco realizada com sucesso");

            var statement = conn.createStatement();
            var result = statement.executeQuery("select * from estado");
            while(result.next()){
                System.out.printf("Id: %d Nome: %s UF: %s\n", result.getInt("Id"), result.getString("Nome"), result.getString("UF"));
            }
        } catch (SQLException e) {
            System.err.println("Nâo foi possível fazer a consulta ao banco dados" + e.getMessage());
        }
    }

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

    }

    private void carregarDriveJDBC() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Nao foi possível carregar a biblioteca(classe) para acesso ao banco de dados" + e.getMessage());
        }
    }
}

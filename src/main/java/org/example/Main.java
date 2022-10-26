package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try(Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","root","root");
            Statement stmn = conn.createStatement()){
            String sql = "CREATE DATABASE VIDEOJUEGOS";
            stmn.execute(sql);
            System.out.println("Base de datos creada satisfactoriamente...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try(Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/videojuegos","root","root");
            Statement stmn = conn.createStatement()){
            String sqlTabla = "CREATE TABLE videojuego(" +
                    "ID SERIAL PRIMARY KEY," +
                    "NOMBRE VARCHAR(100) NOT NULL," +
                    "ESTUDIO VARCHAR(100)," +
                    "ANIO INT NOT NULL)";
            stmn.execute((sqlTabla));

            String sentenciaSql = "INSERT INTO videojuego (Id,nombre,estudio,anio) VALUES (DEFAULT,?,?,?)";
            PreparedStatement sentencia;
            sentencia=conn.prepareStatement(sentenciaSql);
            sentencia.setString(1,"Minecraft");
            sentencia.setString(2,"Mojang");
            sentencia.setInt(3,2010);
            sentencia.executeUpdate();

            sentencia.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
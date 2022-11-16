package org.Ut3;

import java.sql.*;

public class inicializaBD {
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

        try{
            Connection conn = ConnectionPool.getInstance().getConnection();
            Statement stmn = conn.createStatement();
            String sqlTabla = "CREATE TABLE videojuego(" +
                    "ID SERIAL PRIMARY KEY," +
                    "NOMBRE VARCHAR(100) NOT NULL," +
                    "ESTUDIO VARCHAR(100)," +
                    "año INT NOT NULL)";
            stmn.execute((sqlTabla));
            stmn.close();
            String sentenciaSql = "INSERT INTO videojuego (Id,nombre,estudio,año) VALUES (DEFAULT,?,?,?)";
            PreparedStatement sentencia;
            sentencia=conn.prepareStatement(sentenciaSql);
            sentencia.setString(1,"Minecraft");
            sentencia.setString(2,"Mojang");
            sentencia.setInt(3,2010);
            sentencia.executeUpdate();
            sentencia.setString(1,"World of Warcraft");
            sentencia.setString(2,"Blizzard");
            sentencia.setInt(3,2004);
            sentencia.executeUpdate();
            sentencia.setString(1,"Mortal Kombat");
            sentencia.setString(2,"Midway Games");
            sentencia.setInt(3,1992);
            sentencia.executeUpdate();
            sentencia.setString(1,"Mario Bros.");
            sentencia.setString(2,"Nintendo");
            sentencia.setInt(3,1983);
            sentencia.executeUpdate();
            sentencia.setString(1,"The Binding of Isaac");
            sentencia.setString(2,"Edmund McMillen");
            sentencia.setInt(3,2011);
            sentencia.executeUpdate();
            sentencia.setString(1,"Doom");
            sentencia.setString(2,"id Software");
            sentencia.setInt(3,1993);
            sentencia.executeUpdate();
            sentencia.setString(1,"Diablo II");
            sentencia.setString(2,"Blizzard");
            sentencia.setInt(3,2000);
            sentencia.executeUpdate();
            sentencia.setString(1,"Cooking Mama");
            sentencia.setString(2,"Office Create");
            sentencia.setInt(3,2006);
            sentencia.executeUpdate();
            sentencia.setString(1,"Grand Theft Auto 2");
            sentencia.setString(2,"Rockstar Games");
            sentencia.setInt(3,1999);
            sentencia.executeUpdate();
            sentencia.close();
            ConnectionPool.getInstance().closeConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
package org.example;

import java.sql.*;
import java.util.Scanner;

public class invulnerable {
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            Statement stmn = conn.createStatement();
            String sqlTabla = "CREATE TABLE invulnerable(" +
                    "ID SERIAL PRIMARY KEY," +
                    "NOMBRE VARCHAR(100) NOT NULL," +
                    "ESTUDIO VARCHAR(100)," +
                    "año INT NOT NULL)";
            stmn.execute((sqlTabla));
            String sentenciaSql = "INSERT INTO invulnerable(Id,nombre,estudio,año) VALUES (DEFAULT,?,?,?)";
            PreparedStatement sentencia;
            sentencia=conn.prepareStatement(sentenciaSql);
            sentencia.setString(1,"Minecraft");
            sentencia.setString(2,"Mojang");
            sentencia.setInt(3,2010);
            sentencia.executeUpdate();
            sentencia.close();
            PreparedStatement sentenciados;
            String sqlinjection = "SELECT * from invulnerable where nombre = ?";
            sentenciados=conn.prepareStatement(sqlinjection);
            System.out.println("Haz aquí la inyección: introduce 'Minecraft';drop table vulnerable;");
            String nombre = scan.nextLine();
            sentenciados.setString(1,nombre);
            ResultSet rs = sentenciados.executeQuery();
            while(rs.next()){
                System.out.print(rs.getInt(1)+"\t");
                System.out.print(rs.getString(2)+"\t");
                System.out.print(rs.getString(3)+"\t");
                System.out.println(rs.getInt(4));
            }
            rs.close();
            sentenciados.close();
            ConnectionPool.getInstance().closeConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

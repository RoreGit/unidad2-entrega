package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class vulnerable {
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        try {
        Connection conn = ConnectionPool.getInstance().getConnection();
        Statement stmn = conn.createStatement();
        String sqlTabla = "CREATE TABLE vulnerable(" +
                    "ID SERIAL PRIMARY KEY," +
                    "NOMBRE VARCHAR(100) NOT NULL," +
                    "ESTUDIO VARCHAR(100)," +
                    "año INT NOT NULL)";
        stmn.execute((sqlTabla));
        String sentenciaSql = "INSERT INTO vulnerable(Id,nombre,estudio,año) VALUES (DEFAULT,?,?,?)";
        PreparedStatement sentencia;
        sentencia=conn.prepareStatement(sentenciaSql);
        sentencia.setString(1,"Minecraft");
        sentencia.setString(2,"Mojang");
        sentencia.setInt(3,2010);
        sentencia.executeUpdate();
        sentencia.close();
        System.out.println("Haz aquí la inyección: introduce 'Minecraft';drop table vulnerable;");
        String nombraso = scan.nextLine();
        String sqlinjection = "SELECT * from vulnerable where nombre = "+nombraso;
        stmn.execute(sqlinjection);
        stmn.close();
        ConnectionPool.getInstance().closeConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

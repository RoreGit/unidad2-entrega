package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class accionesResultSet {
    private static final String SQL_QUERY = "SELECT * FROM VIDEOJUEGO";
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        try{
            Connection conn = ConnectionPool.getInstance().getConnection();
            Statement stmn = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmn.executeQuery(SQL_QUERY);
            System.out.println("CONTENIDO DE LA TABLA");
            while(rs.next()){
                System.out.print(rs.getInt(1)+"\t");
                System.out.print(rs.getString(2)+"\t");
                System.out.print(rs.getString(3)+"\t");
                System.out.println(rs.getInt(4));
            }
            Thread.sleep(1000);
            System.out.println("AHORA VAMOS A CONSULTAR UNA FILA EN ESPECÍFICO");
            Thread.sleep(1000);
            System.out.println("INTRODUCE LA FILA A CONSULTAR");
            int fila = scan.nextInt();
            rs.absolute(fila);
            System.out.print(rs.getInt(1)+"\t");
            System.out.print(rs.getString(2)+"\t");
            System.out.print(rs.getString(3)+"\t");
            System.out.println(rs.getInt(4));
            Thread.sleep(1000);
            System.out.println();
            System.out.println("AHORA VAMOS A MOSTRAR LA PRIMERA FILA");
            System.out.println();
            Thread.sleep(1000);
            rs.first();
            System.out.print(rs.getInt(1)+"\t");
            System.out.print(rs.getString(2)+"\t");
            System.out.print(rs.getString(3)+"\t");
            System.out.println(rs.getInt(4));
            Thread.sleep(1000);
            System.out.println();
            System.out.println("AHORA VAMOS A MOSTRAR LA ÚLTIMA FILA");
            System.out.println();
            Thread.sleep(1000);
            rs.last();
            System.out.print(rs.getInt(1)+"\t");
            System.out.print(rs.getString(2)+"\t");
            System.out.print(rs.getString(3)+"\t");
            System.out.println(rs.getInt(4));
            Thread.sleep(1000);
            System.out.println();
            System.out.println("AHORA INSERTAMOS UN JUEGO NUEVO");
            System.out.println();
            Thread.sleep(1000);
            rs.moveToInsertRow();
            rs.updateString(2,"Tomb Raider");
            rs.updateString(3,"Eidos Interactive");
            rs.updateInt(4,1996);
            rs.insertRow();
            rs.last();
            System.out.print(rs.getInt(1)+"\t");
            System.out.print(rs.getString(2)+"\t");
            System.out.print(rs.getString(3)+"\t");
            System.out.println(rs.getInt(4));
            Thread.sleep(1000);
            System.out.println();
            System.out.println("AHORA EDITAMOS EL ÚLTIMO JUEGO INTRODUCIDO");
            System.out.println();
            Thread.sleep(1000);
            rs.last();
            rs.updateString(2,"Pokémon");
            rs.updateString(3,"Game Freak");
            rs.updateInt(4,1996);
            rs.updateRow();
            System.out.print(rs.getInt(1)+"\t");
            System.out.print(rs.getString(2)+"\t");
            System.out.print(rs.getString(3)+"\t");
            System.out.println(rs.getInt(4));
            Thread.sleep(1000);
            System.out.println();
            System.out.println("Y FINALMENTE LO BORRAMOS");
            System.out.println();
            Thread.sleep(1000);
            rs.last();
            rs.deleteRow();
            rs.last();
            System.out.print(rs.getInt(1)+"\t");
            System.out.print(rs.getString(2)+"\t");
            System.out.print(rs.getString(3)+"\t");
            System.out.println(rs.getInt(4));
            stmn.close();
            rs.close();
            ConnectionPool.getInstance().closeConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

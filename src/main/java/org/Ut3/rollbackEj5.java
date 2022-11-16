package org.Ut3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class rollbackEj5 {
    public static void main(String[] args) {
        String sqlito = "Insert into videojuego(ID,NOMBRE,ESTUDIO,AÑO)VALUES(DEFAULT,?,?,?)";
        String sqlitro = "update videojuego set nombre=? where nombre=?";
        Connection conn = null;
        PreparedStatement ps = null;
        PreparedStatement psdos = null;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            ps = conn.prepareStatement(sqlito);
            psdos = conn.prepareStatement(sqlitro);
            conn.setAutoCommit(false);
            ps.setString(1,"Vampire Survivors");
            ps.setString(2,"Poncle");
            ps.setInt(3,2021);
            ps.executeUpdate();
            psdos.setString(1,"MataVise");
            psdos.setString(2,"Vampire Survivors");
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally{
            try {
                conn.rollback();
                conn.setAutoCommit(true);
                ps.close();
                psdos.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            ConnectionPool.getInstance().closeConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

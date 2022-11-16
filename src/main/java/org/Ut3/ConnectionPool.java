package org.Ut3;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private final String URL="jdbc:postgresql://localhost/videojuegos";
    private final String USER="root";
    private final String PASS="root";
    private static ConnectionPool dataSource;
    private BasicDataSource basicDataSource=null;

    private ConnectionPool(){
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.postgresql.Driver");
        basicDataSource.setUsername(USER);
        basicDataSource.setPassword(PASS);
        basicDataSource.setUrl(URL);
        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxIdle(20);
        basicDataSource.setMaxWait(10000);
    }
    public static ConnectionPool getInstance(){
        if (dataSource == null){
            dataSource = new ConnectionPool();
            return dataSource;
        } else {
            return dataSource;
        }
    }

    public Connection getConnection(){
        Connection connect = null;
        try{
            connect = this.basicDataSource.getConnection();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return connect;
    }

    public void closeConnection(Connection connection) throws SQLException{
        connection.close();
    }
}

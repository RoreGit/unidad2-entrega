package org.example;

import org.apache.commons.dbcp.BasicDataSource;

public class PoolDB {
    public static void conexion(){
        BasicDataSource bds = new BasicDataSource();
        bds.setUrl("jdbc:postgresql://localhost/videojuegos");
        bds.setUsername("root");
        bds.setPassword("root");
        bds.setMinIdle(5);
        bds.setMaxIdle(10);
        bds.setMaxOpenPreparedStatements(100);
    }
}

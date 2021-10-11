package edu.karamoozi.model.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PoolConnection {
    private DataSource dataSource;
    private static PoolConnection poolConnection;

    static {
        try {
            poolConnection = new PoolConnection();
            Context ctx = new InitialContext();
            poolConnection.dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/myds");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return poolConnection.dataSource.getConnection();
        } catch (SQLException e) {
        e.printStackTrace();
        return null;
          }
    }
}

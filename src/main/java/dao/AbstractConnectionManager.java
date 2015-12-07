package dao;

import java.sql.Connection;

public interface AbstractConnectionManager{
    Connection getConnection();
    void closeConnection();
    void beginTransaction();
    void commit();
    void rollback();
}

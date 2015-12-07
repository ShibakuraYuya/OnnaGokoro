package dao;

import java.sql.Connection;

public interface AbstractConnectionManager{
    static AbstractConnectionManager getInstance();
    Connection getConnection();
    void closeConnection();
    void beginTransaction();
    void commit();
    void rollback();
}

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class OracleConnectionManager implements AbstractConnectionManager{
    private static OracleConnectionManager oracleConnection = null;
    private Connection connection = null;

    private OracleConnectionManager(){}

    public static AbstractConnectionManager getInstance(){
        if(oracleConnection == null){
            synchronized(OracleConnectionManager.class){
                if(oracleConnection == null){
                    oracleConnection = new OracleConnectionManager();
                }
            }
        }
        return oracleConnection;
    }

    public Connection getConnection(){
        if(connection == null){
            try{
                InitialContext init = new InitialContext();
                DataSource source = (DataSource)init.lookup("java:comp/env/jdbc/onnnagokoro");
                connection = source.getConnection();
            }catch(NamingException e){
                e.printStackTrace();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void closeConnection(){
        try{
            if(connection != null){
                connection.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void beginTransaction(){
        if(connection == null){
            getConnection();
        }
        try{
            connection.setAutoCommit(false);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void commit(){
        try{
            connection.commit();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void rollback(){
        try{
            connection.rollback();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}

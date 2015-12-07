package dao;

import factory.Factory;

public class OracleDaoFactory extends Factory{
    public AbstractUsersDao getUsersDao(){
        return new OracleUsersDao();
    }
}

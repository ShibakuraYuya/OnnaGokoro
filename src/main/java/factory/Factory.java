package factory;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import command.AbstractCommand;
import dao.AbstractUsersDao;
import context.RequestContext;

public abstract class Factory{
    // コマンド用Factoryメソッド
   public static AbstractCommand getCommand(RequestContext reqc){
      AbstractCommand command = null;
      Properties prop = new Properties();

      try{
         prop.load(Factory.class.getClassLoader().getResourceAsStream("factory.properties"));
         String name = prop.getProperty(reqc.getCommandPath());
         Class c = Class.forName(name);
         command = (AbstractCommand)c.newInstance();
      }catch(FileNotFoundException e){
         // TODO独自例外に実装
         throw new RuntimeException(e.getMessage(),e);
      }catch(IOException e){
         throw new RuntimeException(e.getMessage(),e);
      }catch(ClassNotFoundException e){
         throw new RuntimeException(e.getMessage(),e);
      }catch(InstantiationException e){
         throw new RuntimeException(e.getMessage(),e);
      }catch(IllegalAccessException e){
         throw new RuntimeException(e.getMessage(),e);
      }
      return command;
   }

   // DAO用Factoryメソッド
   public static Factory getFactory(){
       Factory factory = null;
       Properties prop = new Properties();

       try{
           prop.load(Factory.class.getClassLoader().getResourceAsStream("factory.properties"));
           String name = prop.getProperty("dao");
           Class c = Class.forName(name);
           factory = (Factory)c.newInstance();
       }catch(ClassNotFoundException e){
           e.printStackTrace();
       }catch(FileNotFoundException e){
           e.printStackTrace();
       }catch(IOException e){
           e.printStackTrace();
       }catch(InstantiationException e){
           e.printStackTrace();
       //    throw new RuntimeException(e.getMessage(),e);
       }catch(IllegalAccessException e){
           e.printStackTrace();
       //    throw new RuntimeException(e.getMessage(),e);
       }
       return factory;
   }

   public abstract AbstractUsersDao getUsersDao();

}

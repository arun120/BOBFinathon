package Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Home
 */

public class Dbdetails {


    
    final static private String Driver="com.mysql.jdbc.Driver";
    final static private String UserName="fluffy";
    final static private String Pass="1234";
    private static String Url="jdbc:mysql://fluffy.mrmeow.org:3306/bob";
     private static Connection conn;
    
   static {
        try {  
             Class.forName("com.mysql.jdbc.Driver").newInstance();


            conn = DriverManager.getConnection(Url,UserName,Pass);


        } catch (Exception ex) {
            ex.printStackTrace();
        } 
    }

    public String getDriver() {
        return Driver;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPass() {
        return Pass;
    }

    public String getUrl() {
        return Url;
    }
    
    public Connection getConnection()
    {
        try {
        if(conn==null||conn.isClosed())
            
                conn=DriverManager.getConnection(Url,UserName,Pass);
        } catch (Exception ex) {
            Logger.getLogger(Dbdetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    return conn;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.iiitd.ac.in.quiz;

/**
 *
 * @author Apple
 */
import java.sql.*;
public class DatabaseConnectionFactory {
    public static String dbURL = "jdbc:mysql://localhost:3306/quiz";
    public static String dbUser = "root";
    public static String dbPassword = "ThunderAndSparks8";
    
    public static Connection createConnection()
    {
        Connection connection = null;
        try
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(dbURL,dbUser, dbPassword);
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return connection;
    }
}

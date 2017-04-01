/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skillsiswa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hadis
 */
public class DBConn {
    Connection con; ResultSet rs;PreparedStatement stat;
    
    public void connection() throws ClassNotFoundException, SQLException{
    String url="jdbc:mysql://localhost:3306/";
    String driver="com.mysql.jdbc.Driver";   
    String db="db_skill_siswa";
    String username="root";
    String password="";
    stat =null; 

       Class.forName(driver);
       con=(Connection)DriverManager.getConnection
        (url+db,username,password);              
        System.out.println("Connected");
    }  

    public void retrieve() throws SQLException{
        Statement stmt=con.createStatement();
        String query="select nama from t_siswa";
        rs = stmt.executeQuery(query);

        System.out.println("Data Telah Terambil");
    }
}

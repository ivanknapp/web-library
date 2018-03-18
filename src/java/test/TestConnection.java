/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Иван
 */
public class TestConnection {
    
    public static void main(String[] args) {
            TestConnection tc = new TestConnection();
            tc.check();
    }
    public void check(){
        try{
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/sample");
            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from book");
            while (rs.next()){
                System.out.println(rs.getString(1));
            }
            
            
        }catch(SQLException e){
             Logger.getLogger(TestConnection.class.getName()).log(Level.SEVERE, null, e);
        } catch (NamingException ex) {
            Logger.getLogger(TestConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

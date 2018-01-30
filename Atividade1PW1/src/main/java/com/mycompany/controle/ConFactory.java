
package com.mycompany.controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConFactory {
    
    private String url="jdbc:postgresql://127.0.0.1:5432/AtividadePW1";
    private String user="postgres";
    private String password = "flavio22";
    
    public Connection getConnection() throws SQLException{
        
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }        
}

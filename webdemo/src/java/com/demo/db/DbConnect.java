/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.db;

import com.demo.model.Contacto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author docente
 */
public class DbConnect {
    private static String user="root";
    private static String password="";
    private static String url="jdbc:mysql://localhost:3306/demo";
    private static Connection con=null;
    
    public static void connect() throws ClassNotFoundException, SQLException{
      Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection(url, user, password);
        
       
    }
    
    public static void save(Contacto c) throws ClassNotFoundException, SQLException{
       connect();
        PreparedStatement pst = con.prepareStatement("Insert into "
                + "contactos values(?,?,?,?)");
        pst.setInt(1,c.getId() );
        pst.setString(2, c.getNombres());
        pst.setString(3, c.getApellidos());
        pst.setString(4, c.getCorreo());
        pst.executeUpdate();
        con.close();
        pst.close();
       
    }
    
    public static List<Contacto> list() throws ClassNotFoundException, SQLException{
       connect();
       List<Contacto> lista=new  ArrayList<>();
        PreparedStatement pst = con.prepareStatement("Select * from contactos ");
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            Contacto c=new Contacto(rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4));
            lista.add(c);
        }
        con.close();
        pst.close();
        return lista;
       
    }
    
    
}

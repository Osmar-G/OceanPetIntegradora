/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integradora.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Osmar
 */
public class conexionDB {
     public static final String URL = "jdbc:mysql://localhost:3306/empresa_reciclaje";
            public static final String USER = "root";
            public static final String PASSWORD = "12345678";
            
            public static Connection obtenerConexion() throws SQLException {
                return DriverManager.getConnection(URL, USER, PASSWORD);
                
            }
}

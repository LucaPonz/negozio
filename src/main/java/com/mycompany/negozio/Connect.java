/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.negozio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author luca
 */
public class Connect {
	
	private  final String URL = "jdbc:mysql://localhost/negozio";
	private  final String USER = "root";
	private  final String PASSWORD = "root";
	private  final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	
	
	public Connection createConnection() {
        try {
            //Step 2: Load MySQL Java driver
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            //Step 3: Establish Java MySQL connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }   
     
}
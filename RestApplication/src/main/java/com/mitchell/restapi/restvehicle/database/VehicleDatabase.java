package com.mitchell.restapi.restvehicle.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mitchell.restapi.restvehicle.vehicleapi.Vehicle;

/**
 * @author Sinduja Alugubelly 
 * VehiclaDatabase connects to the database and
 * handles result set.
 *
 */
public class VehicleDatabase {

	public static Connection dbConn;
	public Statement myStmt;
	public ResultSet dbRes;

	public static Connection DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			dbConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehicledb", "root", "1991");
			System.out.println("succesfull");
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return dbConn;
	}

	public ResultSet getresultSet(String query, Connection Conn) {
		String sql = query;
		dbConn = Conn;

		try {
			myStmt = dbConn.createStatement();
			dbRes = myStmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dbRes;

	}

	public int postresultSet(String query, Connection Conn) {
		String sql = query;
		dbConn = Conn;
		int response = 0;
		try {
			myStmt = dbConn.createStatement();
			response = myStmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return response;

	}
}

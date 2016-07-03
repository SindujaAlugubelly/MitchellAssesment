package com.mitchell.restapi.restvehicle.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mitchell.restapi.restvehicle.vehicleapi.Vehicle;

/**
 * @author Sinduja Alugubelly
 * Vehicledbaccess model
 */
public class Vehicledbaccess {

	VehicleDatabase db = new VehicleDatabase();
	ResultSet rs;
	int res;
	
	public ArrayList<Vehicle> getAllVehiclesAccess(Connection conn) {
		//retrieve
		ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
		String sqlQuery = "SELECT * FROM vehicle";
		rs = db.getresultSet(sqlQuery, conn);
		try {
			while (rs.next()) {
				Vehicle vehObj = new Vehicle();
				vehObj.setVehicleId(rs.getInt("id"));
				vehObj.setVehicleYear(rs.getInt("yr"));
				vehObj.setVehicleMake(rs.getString("make"));
				vehObj.setVehicleModel(rs.getString("model"));
				vehicleList.add(vehObj);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicleList;
	}
   
	public Vehicle getVehicleAccess(int vehicleId, Connection conn) {
		//retrieve vehicleid
		String sql = "SELECT * FROM vehicle WHERE id=" + vehicleId;
		rs = db.getresultSet(sql, conn);
		try {
			if (rs.next()) {
				Vehicle vehObj = new Vehicle();
				vehObj.setVehicleId(rs.getInt("id"));
				vehObj.setVehicleYear(rs.getInt("yr"));
				vehObj.setVehicleMake(rs.getString("make"));
				vehObj.setVehicleModel(rs.getString("model"));
				return vehObj;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String postVehicleAccess(Vehicle vehicle, Connection conn) {
		// insert
		System.out.println(vehicle.getVehicleId());
		String sql = "insert into vehicle values (" + vehicle.getVehicleId() + "," + vehicle.getVehicleYear() + ",'"
				+ vehicle.getVehicleMake() + "','" + vehicle.getVehicleModel() + "')";
		res = db.postresultSet(sql, conn);
		return "Post Successfull";

	}

	public String putVehicleAccess(Vehicle vehicle, Connection conn) {
		// update
		String sql = "UPDATE vehicle SET id=" + vehicle.getVehicleId() + ",yr=" + vehicle.getVehicleYear() + ",make='"
				+ vehicle.getVehicleMake() + "',model='" + vehicle.getVehicleModel() + "'where id="
				+ vehicle.getVehicleId();
		res = db.postresultSet(sql, conn);
		return "Put Successfull";

	}

	public String removeVehicleAccess(int vehicleId, Connection conn) {
		// delete
		String sql = "DELETE FROM vehicle WHERE id=" + vehicleId;
		res = db.postresultSet(sql, conn);
		return "Delete Successfull";

	}

}

package com.mitchell.restapi.restvehicle.dataservice;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mitchell.restapi.restvehicle.database.VehicleDatabase;
import com.mitchell.restapi.restvehicle.database.Vehicledbaccess;
import com.mitchell.restapi.restvehicle.vehicleapi.Vehicle;

/**
 * @author Sinduja Alugubelly 
 * VehicleDataService controller
 *
 */
public class VehicleDataService {
	Connection conn;
	Vehicledbaccess access = new Vehicledbaccess();

	public ArrayList<Vehicle> getAllVehicles() {

		ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
		conn = VehicleDatabase.DBConnection();
		vehicleList = access.getAllVehiclesAccess(conn);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicleList;

	}

	public Vehicle getVehicle(int id) {
		
		Vehicle vehicleObj = new Vehicle();
		conn = VehicleDatabase.DBConnection();
		vehicleObj = access.getVehicleAccess(id, conn);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicleObj;
	}

	public String addVehicle(Vehicle vehicle) {
		String status = null;
		conn = VehicleDatabase.DBConnection();
		status = access.postVehicleAccess(vehicle, conn);
		return status;

	}

	public String updateVehicle(Vehicle vehicle) {
		String status = null;
		if (vehicle.getVehicleId() <= 0) {
			status = "Enter positive value for vehicle id and put again";
			return status;
		}
		conn = VehicleDatabase.DBConnection();
		status = access.putVehicleAccess(vehicle, conn);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;

	}

	public String removeVehicle(int id) {
		conn = VehicleDatabase.DBConnection();
		String status = access.removeVehicleAccess(id, conn);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;

	}

}

package com.mitchell.restapi.restvehicle.vehicleapi;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mitchell.restapi.restvehicle.dataservice.VehicleDataService;

/**
 * @author Sinduja Alugubelly
 * VehicleService View
 */

@Path("vehicles")
public class VehicleService {

	VehicleDataService data_service = new VehicleDataService();
	Gson gson = new GsonBuilder().create();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getVehicles() {
		ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
		String vehicles = null;
		vehicleList = data_service.getAllVehicles();
		vehicles = gson.toJson(vehicleList);
		return vehicles;
	}

	@GET
	@Path("/{vehicleId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getVehicleDetails(@PathParam("vehicleId") int vehicleId) {
		Vehicle vehObj = new Vehicle();
		vehObj = data_service.getVehicle(vehicleId);
		String vehicle = gson.toJson(vehObj);
		return vehicle;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void addVehicle(Vehicle vehicle) {
		String msg = data_service.addVehicle(vehicle);
		System.out.println(msg);

	}

	@PUT
	@Path("/{vehicleId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateVehicle(@PathParam("vehicleId") int vehicleId, Vehicle vehicle) {
		vehicle.setVehicleId(vehicleId);
		String msg = data_service.updateVehicle(vehicle);
		System.out.println(msg);

	}

	@DELETE
	@Path("/{vehicleId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteVehicle(@PathParam("vehicleId") int vehicleId) {
		String msg = data_service.removeVehicle(vehicleId);
		System.out.println(msg);
	}

}

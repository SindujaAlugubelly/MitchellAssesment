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
import javax.ws.rs.core.Response;

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
	public Response getVehicles() {
		ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
		String vehicles = null;
		vehicleList = data_service.getAllVehicles();
		if(vehicleList.size()==0) {
	        return Response.status(Response.Status.NOT_FOUND).entity("Entities not found for vehicles ").build();
	    }
		vehicles = gson.toJson(vehicleList);
		return Response.ok(vehicles, MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/{vehicleId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVehicleDetails(@PathParam("vehicleId") int vehicleId) {
		if(vehicleId <= 0) {
	        return Response.serverError().entity("vehicle Id cannot be blank or negative").build();
	    }
		Vehicle vehObj = new Vehicle();
		vehObj = data_service.getVehicle(vehicleId);
	    if(vehObj == null) {
	        return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for vehicleId: " + vehicleId).build();
	    }
	    String vehJson = gson.toJson(vehObj);
	    return Response.ok(vehJson, MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addVehicle(Vehicle vehicle) {
		if(vehicle.getVehicleId()<=0||vehicle.getVehicleYear()<=0||vehicle.getVehicleMake().isEmpty()||vehicle.getVehicleModel().isEmpty()) {
	        return Response.serverError().entity("vehicle Object should be entered").build();
	    }
		String msg = data_service.addVehicle(vehicle);
		return Response.ok(msg, MediaType.APPLICATION_JSON).build();

	}

	@PUT
	@Path("/{vehicleId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateVehicle(@PathParam("vehicleId") int vehicleId, Vehicle vehicle) {
		vehicle.setVehicleId(vehicleId);
		if(vehicle.getVehicleId()<=0||vehicle.getVehicleYear()<=0||vehicle.getVehicleMake().isEmpty()||vehicle.getVehicleModel().isEmpty()) {
	        return Response.serverError().entity("valid vehicle Object and vehicle Id should be entered").build();
	    }
		String msg = data_service.updateVehicle(vehicle);
		return Response.ok(msg, MediaType.APPLICATION_JSON).build();

	}

	@DELETE
	@Path("/{vehicleId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteVehicle(@PathParam("vehicleId") int vehicleId) {
		if(vehicleId <= 0 ) {
	        return Response.serverError().entity("valid vehicle Id should be given").build();
	    } 
		String msg = data_service.removeVehicle(vehicleId);
		return Response.ok(msg, MediaType.APPLICATION_JSON).build();
	}

}

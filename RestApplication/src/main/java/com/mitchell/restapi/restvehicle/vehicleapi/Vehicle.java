package com.mitchell.restapi.restvehicle.vehicleapi;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sinduja Alugubelly
 * @Builder Class for Vehicle
 */
@XmlRootElement
public class Vehicle {

	public int Id;
	public int Year;
	public String Make;
	public String Model;

	public Vehicle() {

	}

	public Vehicle(int id, int year, String make, String model) {
		this.Id = id;
		this.Year = year;
		this.Make = make;
		this.Model = model;
	}

	public void setVehicleId(int id) {
		this.Id = id;
	}

	public int getVehicleId() {
		return Id;

	}

	public void setVehicleYear(int year) {
		this.Year = year;
	}

	public int getVehicleYear() {
		return Year;

	}

	public void setVehicleMake(String make) {	
		this.Make = make;
	}

	public String getVehicleMake() {
		return Make;

	}

	public void setVehicleModel(String model) {
		this.Model = model;
	}

	public String getVehicleModel() {
		return Model;

	}

	@Override
	public String toString() {
		return "Vehicle [Id=" + Id + ", Year=" + Year + ", Make=" + Make + ", Model=" + Model + "]";
	}

}

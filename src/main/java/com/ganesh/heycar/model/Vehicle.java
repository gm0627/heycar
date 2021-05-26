package com.ganesh.heycar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ganesh.heycar.util.HeyCarUtils;

@JsonIgnoreProperties
public class Vehicle {

	String code;
	String make;
	String model;
	int power;
	int year;
	long price;
	String color;
	private String kW;
	long dealerID;

	public Vehicle() {
		super();
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setPower(String power) {
		HeyCarUtils.ifNotEmpty(power, p -> {
			setPower(Integer.parseInt(p));
		});
	}

	public int getYear() {
		return year;
	}

	public void setYear(String year) {
		HeyCarUtils.ifNotEmpty(year, y -> {
			setYear(Integer.parseInt(y));
		});
	}

	public void setYear(int year) {
		this.year = year;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public void setPrice(String price) {
		HeyCarUtils.ifNotEmpty(price, pr -> {
			setPrice(Integer.parseInt(pr));
		});
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public long getDealerID() {
		return dealerID;
	}

	public void setDealerID(long dealerID) {
		this.dealerID = dealerID;
	}

	private String getkW() {
		return kW;
	}


	public void setkW(String kW) {
		this.kW = kW;
		setPower(kW);
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Vehicle code:" + code + " make:" + make + " model:" + model + " power:" + power + " Year:" + year
				+ " color:" + color + " Price:" + price;
	}

}

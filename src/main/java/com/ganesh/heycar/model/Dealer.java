package com.ganesh.heycar.model;



public class Dealer {
	
	long ID;
	
	String Name;

	public Dealer(long iD, String name) {
		
		this.ID = iD;
		this.Name = name;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Dealer ID:"+ID+" Name:"+Name;
	}

}

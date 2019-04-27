package com.wipro.elevator.dto;



public class RequestDTO {
	
	private int sourceFloor;
	private int destinationFloor;
	private int userWeight;
	
   
	public int getSourceFloor() {
		return sourceFloor;
	}


	public void setSourceFloor(int sourceFloor) {
		this.sourceFloor = sourceFloor;
	}


	public int getDestinationFloor() {
		return destinationFloor;
	}


	public void setDestinationFloor(int destinationFloor) {
		this.destinationFloor = destinationFloor;
	}


	public int getUserWeight() {
		return userWeight;
	}


	public void setUserWeight(int userWeight) {
		this.userWeight = userWeight;
	}


	public RequestDTO(int sourceFloor, int destinationFloor, int userWeight) {
		
		this.sourceFloor = sourceFloor;
		this.destinationFloor = destinationFloor;
		this.userWeight = userWeight;
	}


	@Override
	public String toString() {
		return "RequestDTO [sourceFloor=" + sourceFloor + ", destinationFloor=" + destinationFloor + ", userWeight="
				+ userWeight + "]";
	}

	


    
    


}

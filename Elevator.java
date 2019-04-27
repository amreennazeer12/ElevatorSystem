package com.wipro.elevator.entity;

import com.wipro.elevator.dto.RequestDTO;
import com.wipro.elevator.exception.InvalidDirectionException;

public interface Elevator {
	
	//operations
	
	void moveUp(RequestDTO request) throws InvalidDirectionException;
	void moveDown(RequestDTO request) throws InvalidDirectionException;	
	void moveNext(RequestDTO request) throws InvalidDirectionException;
	
	

}

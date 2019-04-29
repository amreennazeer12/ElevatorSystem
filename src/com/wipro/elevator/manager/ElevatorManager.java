package com.wipro.elevator.manager;

import java.util.ArrayList;
import java.util.List;

import com.wipro.elevator.dto.RequestDTO;
import com.wipro.elevator.entity.ElevatorImpl;
import com.wipro.elevator.exception.InvalidDirectionException;

public class ElevatorManager {
	
	public static List<RequestDTO> requestList;
	private ElevatorImpl elevator;
	
	public ElevatorManager(ElevatorImpl elevator) {
					this.elevator=elevator;
					this.requestList = new ArrayList<>();
	}

	public void addPickUp(RequestDTO request){
		
		
		// add all requests from User to List
		requestList.add(request);
		
		
		
	}
	
	 public void execute() {
		                
						try {
							requestList.stream().forEach(req -> this.elevator.moveNext(req));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		                
					
	  }

}

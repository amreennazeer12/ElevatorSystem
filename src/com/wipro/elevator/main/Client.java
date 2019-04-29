
package com.wipro.elevator.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import com.wipro.elevator.dto.RequestDTO;

import com.wipro.elevator.entity.ElevatorImpl;
import com.wipro.elevator.entity.User;
import com.wipro.elevator.manager.ElevatorManager;

/**
 * @author Amreen Nazeer
 */

public class Client {

	public static void main(String[] args) {
		
		/*User user1 = new User(30,0,5);
		User user2 = new User(50,5,0);
		User user3 = new User(40,2,4);
		
		List<User> userList = new ArrayList<User>();
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);*/
		
	
		/*Elevator elevator2  = new Elevator(4,0,5,userList);*/
		
		  ElevatorImpl elevator = ElevatorImpl.getInstance();
		  elevator.setCurrentFloor(0); /* assuming lift is in ground Floor */
		 
		
		  ElevatorManager elevatorManager = new ElevatorManager(elevator);
		  elevatorManager.addPickUp(new RequestDTO(3,10,30)); // first user access the lift at ground floor and want to go to 5th floor
		  elevatorManager.addPickUp(new RequestDTO(4,5,60)); 
		  elevatorManager.addPickUp(new RequestDTO(6,7,60)); 
	      elevatorManager.addPickUp(new RequestDTO(8,9,40));
	    
	      //elevatorManager.addPickUp(new RequestDTO(7,8,50));*/
	      elevatorManager.execute();
		
		
		
		
		

	}

}

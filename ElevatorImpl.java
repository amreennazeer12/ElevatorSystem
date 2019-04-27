package com.wipro.elevator.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;



import com.wipro.elevator.constants.Constants;
import com.wipro.elevator.dto.RequestDTO;
import com.wipro.elevator.exception.InvalidDirectionException;
import com.wipro.elevator.manager.InputValidator;

public class ElevatorImpl implements Elevator {
	
	/*public int currentSource=0;
	int count =0;
	public int destination=0;
	public int currentchangesource=0;
	public int maxdestination=0;
	List<Integer> list=new ArrayList<Integer>();*/
	private int source=getCurrentFloor();
	
	private int currentFloor;
	
	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	

	private static ElevatorImpl elevator;
	
	
	private ElevatorImpl(){
		
	}
	
	public static  ElevatorImpl   getInstance(){
		if(elevator == null){
			elevator = new ElevatorImpl();			
		}
		return elevator;
		
	}

	//@Override
	/*public void moveUp(RequestDTO request) {
		
		
		if(count==0)
		{
			currentFloor=request.getSourceFloor(); //0
			destination=request.getDestinationFloor(); //5
			currentchangesource=request.getSourceFloor(); //0
			maxdestination=request.getDestinationFloor(); //5
			System.out.println("Elevator is starting from "+currentFloor+ "and its destination is set to" +destination);
			count++;
			return;
		}
		if(request.getSourceFloor()>=currentFloor)
		{
			if(request.getDestinationFloor()>maxdestination)
			{
				maxdestination=request.getDestinationFloor();
			}
			currentFloor=request.getSourceFloor();
			
			currentchangesource=request.getSourceFloor();
			if(request.getDestinationFloor()<destination)
			{
				list.add(request.getDestinationFloor());
			}
			else
			{
				destination=request.getDestinationFloor();
			}
			System.out.println("Elevator is stopped at "+currentFloor+ "and its destination is set to" +request.getDestinationFloor());
			currentchangesource=request.getDestinationFloor();
		}
		Collections.sort(list);
		for(Integer i : list){
			System.out.println("Elevator is stopped at "+i+ "and its destination is set to" +destination);
		}
		if(maxdestination==Constants.MAX_FLOOR && currentchangesource==request.getDestinationFloor()){			
			System.out.println("Max destinaion has been reached");
		}
		
		
		

	}*/

	
	@Override
	public void moveDown(RequestDTO request)  {
		// TODO Auto-generated method stub
		if(getCurrentFloor()==Constants.MAX_FLOOR){			
			  try {
				throw new InvalidDirectionException("You cannot move upwards");
			} catch (InvalidDirectionException e) {
				// TODO Auto-generated catch block
				System.err.println("Information given to move only downstairs");
			}
		}
		
		if(request.getSourceFloor() != getCurrentFloor()){
			System.out.println(" Currently Lift is in " + getCurrentFloor() + " Please Wait" );
			try {
				TimeUnit.SECONDS.sleep(3);
				System.out.println("Lift reached your destination , please get in and go down  to your desired floor which is" +request.getDestinationFloor());
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				
			}
		}
		else {
			System.out.println("Lift is in your floor " +request.getSourceFloor()+ " Enter into the elevator");
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		source=request.getSourceFloor();
		System.out.println("Lift starting at " + request.getSourceFloor() + " and reached his destination (down) " +  request.getDestinationFloor() );
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		source= request.getDestinationFloor();
		setCurrentFloor(source);

	}

	

	@Override
	public void moveNext(RequestDTO request)   {
		InputValidator validator = new InputValidator();
		if(validator.isFullyLoaded(request.getUserWeight()))
			{
				System.out.println("Warning: Maximun weight of 100KG attained please unload");
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
		
		// TODO Auto-generated method stub
		/*System.out.println("current floor :" + getCurrentFloor());
		System.out.println(request);*/
		int a=directionCheck(request.getSourceFloor(),request.getDestinationFloor());
		
			if (a == 1) {

				moveUp(request);

			} else if (a == -1) {
				moveDown(request);
			} else {
				System.out.println("source and destination cant be same");
			}
		
		
	}
	
	/* method to check if Lift need to go towards upwards or downwards*/
	
	public int directionCheck (int source ,int destination)
	{
		
		if((destination-source)>0)
		{
			return 1;
		}
		else if((destination-source)<0)
		{
			return -1;
		}
		return 0;
	}

	@Override
	public void moveUp(RequestDTO request)  {
		if(getCurrentFloor()==Constants.MIN_FLOOR){			
			  try {
				throw new InvalidDirectionException("You cannot move downwards");
			} catch (InvalidDirectionException e) {
				// TODO Auto-generated catch block
				System.err.println("Information given to move only upwards");
			}
		}
	
		
		if(request.getSourceFloor() != getCurrentFloor()){
			System.out.println(" Currently Lift is in " + getCurrentFloor() + " Please Wait" );
			try {
				TimeUnit.SECONDS.sleep(3);
				System.out.println("Lift reached your destination  " +request.getSourceFloor()+ " please get in and go to your desired floor which is" +request.getDestinationFloor());
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				
			}
			
		}else {
			System.out.println("Lift is in your floor " +request.getSourceFloor()+ " Enter into the elevator");
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		source=request.getSourceFloor();
		System.out.println("Lift starting at " + request.getSourceFloor() + " and reached his destination (upward) " +  request.getDestinationFloor() );
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		source= request.getDestinationFloor();
		setCurrentFloor(source);
		
	}
	
	

}

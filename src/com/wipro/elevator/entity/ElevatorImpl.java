package com.wipro.elevator.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;



import com.wipro.elevator.constants.Constants;
import com.wipro.elevator.dto.RequestDTO;
import com.wipro.elevator.exception.InvalidDirectionException;
import com.wipro.elevator.manager.ElevatorManager;
import com.wipro.elevator.manager.InputValidator;

public class ElevatorImpl implements Elevator {
	
	public int currentSource=0;
	int count =0;
	int count2 = 0;
	public int destination=0;
	public int currentchangesource=0;
	public int minimumfloor=0;
	public int requestsum=0;
	public int maxdestination=0;
	List<Integer> list=new ArrayList<Integer>();
	List<Integer> downfloorlist=new ArrayList<Integer>();
	List<RequestDTO> downrequestlist = new ArrayList<RequestDTO>();
	private int source=getCurrentFloor();
	public int weight=0;
	
	
	private static int currentFloor;
	
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
	public void moveUp(RequestDTO request) {
		
		
		List<Integer> changedList = new ArrayList<Integer>();
		InputValidator validator = new InputValidator();
		
		//first request 
		/*if(count==0)
		{
			System.out.println(" currently Elevator  is in " +currentFloor);
			
			if(currentFloor== request.getSourceFloor()){
				System.out.println("Elevator has arrived , Please enter");
				
			}
			else {
				System.out.println("elevator is moving to " +request.getSourceFloor()  );
				System.out.println("Elevator has arrived , Please enter");
			}
			currentFloor=request.getSourceFloor(); //0
			destination=request.getDestinationFloor(); //10
			currentchangesource=request.getSourceFloor(); 
			maxdestination=request.getDestinationFloor(); //5
			System.out.println("Elevator is starting from "+currentFloor+ "and its destination is set to" +destination);
			count++;
			return;
		}*/
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
			if(list != null ){
				for(Integer target : list){
					if(target < request.getSourceFloor()){
						
							weight-=destinationWeight(target);
							
						
						
						System.out.println("Elevator is stopped at "+target+ " and passenger has got down and current weight is " + weight);
						currentFloor=target;
						changedList.add(target);
						
					}
				}
				list.removeAll(changedList);
				
				
			}
			currentFloor= request.getSourceFloor();
			System.out.println("Your elevator is on its way to "+currentFloor);	
			weight+=sourceweight(currentFloor);
			if(!validator.isFullyLoaded(weight))
			{
				System.out.println("Elevator is stopped at "+currentFloor+ " and passenger has selected to go to " +request.getDestinationFloor() + "Weight is " + weight);
				currentchangesource=request.getDestinationFloor();
			}
			else
			{
				weight-=sourceweight(currentFloor);
				System.out.println("Over weight");
				
			}
			
		}
		
		if(requestsum==4)
		{
			Collections.sort(list);
			for(Integer i : list){
				weight-=destinationWeight(i);
				//System.out.println("Elevator is stopped at "+target+ " and passenger has got down and current weight is " + weight);
				
				System.out.println("Elevator is stopped at "+i+ " and passenger has got down and cuurent weight is " + weight);
			}
			weight-=destinationWeight(maxdestination);
			System.out.println("Elevator is on its way to drop the last destination at " + maxdestination + "weight is " + weight);
			currentFloor = maxdestination;
			request.setSourceFloor(0);
			moveDown(request);
		}
		
		
		
		if(maxdestination==Constants.MAX_FLOOR && currentchangesource==request.getDestinationFloor()){			
			//System.out.println("Max destinaion has been reached");
		}
		
		
		

	}
	
	public int sourceweight(int floor)
	{
		int temp=0;
		
		for(RequestDTO  target : ElevatorManager.requestList){
			if(target.getSourceFloor()==floor)
			{
				temp= target.getUserWeight();
			}
		}
		return temp;
	}
	public int destinationWeight(int floor)
	{
		int temp=0;
		
		for(RequestDTO  target : ElevatorManager.requestList){
			if(target.getDestinationFloor()==floor)
			{
				temp= target.getUserWeight();
			}
		}
		return temp;
	}
	@Override
	public void moveDown(RequestDTO request) {
		
	}

	
	/*@Override
	public void moveDown(RequestDTO request)
	{
		
		
		if(requestsum!=4)
		{
			System.out.println("is in if statement");
			downrequestlist.add(request);
			downfloorlist.add(request.getDestinationFloor());
		}
		else
		{
			if(request.getSourceFloor()==0)
			{
				System.out.println("is in else  statement");
				for(RequestDTO  target : downrequestlist){
					   
					if(target.getSourceFloor()==currentFloor)
					{
						count++;
						System.out.println("Elevator has arrived , Please enter");
						System.out.println("Elevator is starting from "+currentFloor+ "and its destination is set to" +request.getDestinationFloor());
						minimumfloor = request.getDestinationFloor();
						count++;
						currentFloor = request.getSourceFloor();
						request.setSourceFloor(0);
					}
					
					
				}
				if(count2==0)
				{
					if(!downrequestlist.isEmpty())
					{
						currentFloor=downrequestlist.get(0).getSourceFloor();
						System.out.println("Your elevator is on its way to "+currentFloor);	
						System.out.println("Elevator is stopped at "+currentFloor+ " and passenger has selected to go to " +downrequestlist.get(0).getDestinationFloor());
						
						minimumfloor=downrequestlist.get(0).getDestinationFloor();
					}
					
				}
				
				for(RequestDTO target:downrequestlist)
				{
					if(target.getDestinationFloor()>=minimumfloor)
					{
						downfloorlist.add(request.getDestinationFloor());
					}
					else
					{
						minimumfloor=request.getDestinationFloor();
					}
					
				}
				
				if(requestsum==4)
				{
					Collections.sort(downfloorlist);
					Collections.reverse(downfloorlist);
					for(Integer i : list){
						System.out.println("Elevator is stopped at "+i+ " and passenger has got down");
					}
					System.out.println("Elevator is on its way to drop the last destination at " + minimumfloor);
					currentFloor = minimumfloor;
				}
			}
			
			
		}
		
		
		
	}
	*/
	/*@Override
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
*/
	

	@Override
	public void moveNext(RequestDTO request)    {
		requestsum++;
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

	
	
		
		/*System.out.println("Elevator stopped at  ");*/
		
		//System.out.println("Eleveator reached to destination to floor " + target );
		
		
		
	

	/*@Override
	public void moveUp(RequestDTO request)  {
		// TODO Auto-generated method stub
		
		int currentFloor = 0;
		
		System.out.println("Elevator stopped at" + currentFloor);
		
		openDoor();
		
		System.out.println("Elevator started at source " + request.getSourceFloor() + "and is moving on its way to "  + request.getDestinationFloor());
		
	}*/

	private void openDoor() {
		System.out.println("Lift arrived at source,Please Enter");
		
	}

	//@Override
	/*public void moveUp(RequestDTO request)  {
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
		
	}*/
	
	

}

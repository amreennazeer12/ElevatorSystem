package com.wipro.elevator.main.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.wipro.elevator.entity.ElevatorImpl;
import com.wipro.elevator.manager.InputValidator;

public class ElevatorTest {

	@Test
	public void isFullyLoadedTest() {
		
		InputValidator validator = new InputValidator();
		assertFalse("User Weight is greater than Max Weight",validator.isFullyLoaded(90));

	}
	
	@Test
	public void directionCheckTest(){
		
		ElevatorImpl impl = ElevatorImpl.getInstance();
		assertEquals("Expected direction is not matching with actual",-1, impl.directionCheck(5,3));
	}

}

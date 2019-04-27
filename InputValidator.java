package com.wipro.elevator.manager;

import com.wipro.elevator.constants.Constants;
import com.wipro.elevator.dto.RequestDTO;

public class InputValidator {
	public boolean isFullyLoaded(int userWeight) {
		if(userWeight > Constants.MAX_WEIGHT)
		{
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

}

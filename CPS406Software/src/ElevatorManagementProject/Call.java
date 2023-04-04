package ElevatorManagementProject;

/*
 * Call object holds information to be passed between ElevatorManagement and calling object
 *Floor current: the floor the call was made on
 *Floor Request: The floor the call requests
 *Elevator elevator: The elevator the call will move - null if elevator is to be decided by ElevatorManagemnt
 *int direction: The direction of movement 
 */

public class Call 
{
	Floor current;
	Floor request;
	Elevator elevator;
	//up = 1, down = -1, 0 = no motion
	int direction;
	
	@Override
	public boolean equals(Object otherObject) { 
		Call other = (Call) otherObject;
		return this.request.equals(other.getRequest());
	}
	
	/*
	 * Constructor for call objects
	 */
	public Call() {
		this.request = null;
		this.current = null;
		this.elevator = null;
		this.direction = 0;
	}
	/**
	 * Call's made by inside buttons
	 * @param Floor current - gives call the current floor
	 * @param Floor request - gives call the requested floor
	 * @param Elevator elevator - gives call which elevator is making the call
	*/
	public Call(Floor current, Floor request, Elevator elevator) {
		this.current = current;
		this.request = request;
		this.elevator = elevator;
		// Call will figure out the direction of the call based on the given parameters
		if (current.currentFloor() - request.currentFloor() > 0) {
			this.direction = -1;
		}
		else if (current.currentFloor() - request.currentFloor() < 0) {
			this.direction = 1;
		}
		else {
			this.direction = 0;
		}
		
	}
	/**
	 * Call's made by outside buttons
	 * @param Floor current - gives call the current floor
	 * @param Elevator elevator - gives call the direction of the call
	*/
	public Call(Floor current, int direction) {
		this.current = current;
		this.request = current;
		this.elevator = null;
		this.direction = direction;
	}
	
	// Gets the Request FLoor
	public Floor getRequest() {
		return this.request;
	}
	
	// Gets the Current Floor
	public Floor getCurrent() {
		return this.current;
	}
	
	// Gets the Elevator
	public Elevator getElevator() {
		return this.elevator;
	}
	
	//Gets the Direction
	public int getDirection() {
		return this.direction;
	}

}
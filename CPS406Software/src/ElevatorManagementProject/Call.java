package ElevatorManagementProject;

public class Call //implements Comparable<Call>
{
	Floor current;
	Floor request;
	Elevator elevator;
	//up = 1, down = -1, 0 = no motion
	int direction;
	
	public boolean equals(Object otherObject) { //overwriting equals method
		Call other = (Call) otherObject;
		return this.request.equals(other.getRequest());
	}
	
	public Call() {
		this.request = null;
		this.current = null;
		this.elevator = null;
		this.direction = 0;
	}
	//Call format inside button should create
	public Call(Floor current, Floor request, Elevator elevator) {
		this.current = current;
		this.request = request;
		this.elevator = elevator;
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
	//Call format outside button should create
	public Call(Floor current, int direction) {
		this.current = current;
		this.request = current;
		this.elevator = null;
		this.direction = direction;
	}
	
	public Floor getRequest() {
		return this.request;
	}
	public Floor getCurrent() {
		return this.current;
	}
	public Elevator getElevator() {
		return this.elevator;
	}
	public int getDirection() {
		return this.direction;
	}

}
package ElevatorManagementProject;

public class SpecialModes {
	Floor current;
	Elevator elevator;
	boolean active;
	int floor;
	
	//constructor that just takes the current floor 
	public SpecialModes(Floor current) {
		this.active = false;
		this.current = current;
	}
	//activate special modes
	public void activate() {
		this.active = true;
	}
	
	//deactivate special modes
	public void deactivate() {
		this.active = false; 
	}
	
	//return the currentfloor 
	public Floor getCurrent() {
		return this.current;
	}
	
	//return the elevator in use 
	public Elevator getElevator() {
		return this.elevator;
	}

}

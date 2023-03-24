package ElevatorManagementProject;

public class SpecialModes implements Comparable<SpecialModes>{
	Floor current;
	Elevator elevator;
	boolean active;
	int floor;
	int priority;
	
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
	
	public int getPriority() {
		return this.priority;
	}
	
	
	@Override
	public int compareTo(SpecialModes o) {
		if (this.getPriority() < o.getPriority()) {
			return -1;
		}
		else if (this.getPriority() > o.getPriority()) {
			return 1;
		}
		else {
			return 1;
		}
	}
	
	

}

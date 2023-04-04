package ElevatorManagementProject;

/**
 * 
 * Special Modes object holds information needed for SpecialModeHandler to activate special modes
 * Floor current: holds the floor the call was made on
 * Elevator elevator: holds the elevator the mode will affect
 * boolean active: indicates whether the mode is active
 * int priority:compares SpecialMode priority with each other
 * BuildingSystem building: the building this call will affect
 * 
 */

public class SpecialModes implements Comparable<SpecialModes>{
	Floor current;
	Elevator elevator;
	boolean active;
	int priority;
	private BuildingSystem building;
	
	//constructor that just takes the current floor 
	public SpecialModes(Floor current, BuildingSystem building) {
		this.active = false;
		this.current = current;
		this.building = building;
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
	
	public BuildingSystem getBuilding() {
		return this.building;
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

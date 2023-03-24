package ElevatorManagementProject;

public class FireEmergency extends SpecialModes {
	BuildingSystem fire;
	private static int priority = 1;
	
	//Constructor, takes floor to say which floor the fire
	//emergency was activated on 
	public FireEmergency(Floor current) {
		super(current);
		fire.setFire(true);
		this.active = true;
	}
	
	public int getPriority() {
		return priority;
	}
}

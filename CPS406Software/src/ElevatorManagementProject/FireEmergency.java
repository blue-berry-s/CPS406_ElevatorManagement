package ElevatorManagementProject;

public class FireEmergency extends SpecialModes {
	BuildingSystem fire;
	private static int FIRE = 1;
	private Floor recall;
	
	//Constructor, takes floor to say which floor the fire
	//emergency was activated on 
	public FireEmergency(Floor current, Floor recall, BuildingSystem building) {
		super(current);
		this.active = true;
		super.priority = this.FIRE;
		this.recall = recall;
		this.fire = building;
		fire.setFire(true);
	}
	
	public Floor getRecall() {
		return this.recall;
	}
	public int getPriority() {
		return priority;
	}
}

package ElevatorManagementProject;

public class FireEmergency extends SpecialModes {
	private static int FIRE = 1;
	private Floor recall;
	
	//Constructor, takes floor to say which floor the fire
	//emergency was activated on 
	public FireEmergency(Floor current, Floor recall, BuildingSystem building) {
		super(current, building);
		this.active = true;
		super.priority = this.FIRE;
		this.recall = recall;
	}
	
	public Floor getRecall() {
		return this.recall;
	}
	public int getPriority() {
		return priority;
	}
}

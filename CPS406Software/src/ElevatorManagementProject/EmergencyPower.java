package ElevatorManagementProject;

public class EmergencyPower extends SpecialModes {
	BuildingSystem power; 
	private static int POWER = 0;
	
	//Emergency power constructor, takes floor to say 
	//which floor the emergency power operation was activated on
	public EmergencyPower(Floor current) {
		super(current);
		power.setGenerator(true);
		this.active = true;
		super.priority = this.POWER;
	}
	
	//check if individual elevator has power 
	public boolean getElevatorPower() {
		return elevator.isPower();
	}
	
	//check if building has power 
	public boolean getBuildingPower() {
		return this.power.getPower();
	}
	
	//return generator state 
	public boolean getGenerator() {
		return this.power.getGenerator();
	}
	
	public int getPriority() {
		return priority;
	}

}
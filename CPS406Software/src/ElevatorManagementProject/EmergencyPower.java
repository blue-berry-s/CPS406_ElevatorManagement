package ElevatorManagementProject;

public class EmergencyPower extends SpecialModes {
	BuildingSystem power; 
	private static int POWER = 0;
	private Floor lobby;
	
	//Emergency power constructor, takes floor to say 
	//which floor the emergency power operation was activated on
	public EmergencyPower(Floor current, Floor lobby, BuildingSystem building) {
		super(current, building);
		this.power = building;
		this.active = true;
		super.priority = this.POWER;
		this.lobby = lobby;
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
	
	//returns the lobby floor
	public Floor getLobby() {
		return this.lobby;
	}

}
package ElevatorManagementProject;

public class MedicalEmergency extends SpecialModes{
	
	private static int MEDICAL = 2;
	
	Floor emergencyFloor;//Holds the emergency floor 
	
	//Constructor that takes current floor
	//and the floor to move the elevator to 
	public MedicalEmergency(Floor current, Floor emergencyFloor, BuildingSystem building){
		super(current, building);
		this.emergencyFloor = emergencyFloor; 
		this.active = true; 
		super.priority = this.MEDICAL;
	}
	
	public Floor getEmergencyFloor() {
		return this.emergencyFloor;
	}
	
	public int getPriority() {
		return priority;
	}

}

package ElevatorManagementProject;

public class MedicalEmergency extends SpecialModes{
	
	private static int priority = 2;
	
	Floor emergencyFloor;//Holds the emergency floor 
	
	//Constructor that takes current floor
	//and the floor to move the elevator to 
	public MedicalEmergency(Floor current, Floor emergencyFloor){
		super(current);
		this.emergencyFloor = emergencyFloor; 
		this.active = true; 
		
	}
	
	public Floor getEmergencyFloor() {
		return this.emergencyFloor;
	}
	
	public int getPriority() {
		return priority;
	}

}

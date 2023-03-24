package ElevatorManagementProject;

public class MedicalEmergency extends SpecialModes{
	
	private static int MEDICAL = 2;
	
	Floor emergencyFloor;//Holds the emergency floor 
	Floor request;
	
	//Constructor that takes current floor
	//and the floor to move the elevator to 
	public MedicalEmergency(Floor current, Floor emergencyFloor,Floor request){
		super(current);
		this.emergencyFloor = emergencyFloor; 
		this.active = true; 
		super.priority = this.MEDICAL;
		this.request = request;
	}
	
	public Floor getRequest() {
		return this.request;
	}
	public Floor getEmergencyFloor() {
		return this.emergencyFloor;
	}
	
	public int getPriority() {
		return priority;
	}

}

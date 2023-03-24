package ElevatorManagementProject;

public class BuildingSystem {
	private boolean powerStatus = true;
	private boolean fireStatus = false;
	private boolean backupGenerator = false;
	private ElevatorManagement elevators;
	
	public BuildingSystem(ElevatorManagement manager) {
		this.elevators = manager;		
	}
	
	public boolean getGenerator() {
		return this.backupGenerator;
	}
	
	public boolean getFire() {
		return this.fireStatus;
	}
	
	public boolean getPower() {
		return this.powerStatus;
	}
	
	public void setPower(boolean power) {
		this.powerStatus = power;
	}
	
	public void setGenerator(boolean power) {
		this.backupGenerator = power;
	}
	
	public void setFire(boolean status) {
		this.fireStatus = status;
	}
	
	public void activateFireAlarm() {
		
	}
	
	public void activateBackUp() {
		
	}

}

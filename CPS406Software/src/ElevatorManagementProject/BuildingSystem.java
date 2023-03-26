package ElevatorManagementProject;

public class BuildingSystem {
	private Floor lobby;
	private boolean powerStatus = true;
	private boolean fireStatus = false;
	private boolean backupGenerator = true;
	private ElevatorManagement elevators; //<-- forgot why we needed this 
	private SpecialModeHandler report;
	
	
	public BuildingSystem(ElevatorManagement manager, SpecialModeHandler report, Floor floor) {
		this.elevators = manager;		
		this.report = report;
		this.lobby = floor;
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
		this.fireStatus = true;
		FireEmergency call = new FireEmergency(this.lobby, this.lobby, this);
		this.report.addEmergencyModes(call);
	}
	
	//presumes that if you make this call, the power system is currently FALSE
	public void activateBackUp() {
		EmergencyPower call = new EmergencyPower(this.lobby, this.lobby, this);
		this.report.addEmergencyModes(call);
	}

	public void deactivateFireAlarm() {
		this.fireStatus = true; 
		
	}

}

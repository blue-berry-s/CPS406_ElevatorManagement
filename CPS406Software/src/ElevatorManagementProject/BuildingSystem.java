package ElevatorManagementProject;

public class BuildingSystem {
	private Floor lobby;
	private boolean powerStatus = true;
	private boolean fireStatus = false;
	private boolean backupGenerator = true;
	private SpecialModeHandler report;
	
	/*
	 * Building System object that represents the Building
	 * report: Holds the handler to report any activated mode to 
	 * fireStatus: True if there is a fire, False if there is no fire
	 * powerStatus: True if there is power, False if there is no power
	 * backupGenerator: True if it is working, False if it is not working
	 */
	public BuildingSystem(SpecialModeHandler report, Floor floor) {	
		this.report = report;
		this.lobby = floor;
	}
	
	/**
	    * gets the status of the back up generator
	    * @return	boolean 
	    */
	public boolean getGenerator() {
		return this.backupGenerator;
	}
	
	/**
	    * gets the status of building's fire status
	    * @return	boolean 
	    */
	public boolean getFire() {
		return this.fireStatus;
	}
	/**
	    * gets the status of building's power status
	    * @return	boolean 
	    */
	public boolean getPower() {
		return this.powerStatus;
	}
	/**
	    * This function sets the buildings power status
	    * @param	boolean power					
	    */
	public void setPower(boolean power) {
		this.powerStatus = power;
	}
	/**
	    * This function sets the buildings backup generator status
	    * @param	boolean power					
	    */
	public void setGenerator(boolean power) {
		//maybe do a check here to see if power == false
		// if this is the case, trigger Emergency Power mode
		//@Alexia
		this.backupGenerator = power;
	}
	/**
	    * This function sets the buildings fire status
	    * @param	boolean status	
	    */
	public void setFire(boolean status) {
		this.fireStatus = status;
	}
		/*
	    * This function simulates the activation of a Fire Alarm
	    */
	public void activateFireAlarm() {
		this.fireStatus = true;
		FireEmergency call = new FireEmergency(this.lobby, this.lobby, this);
		this.report.addEmergencyModes(call);
	}
	
	/*
	    * This function simulates the activation of the backup Generators
	    */
	public void activateBackUp() {
		EmergencyPower call = new EmergencyPower(this.lobby, this.lobby, this);
		this.report.addEmergencyModes(call);
	}

	public void deactivateFireAlarm() {
		this.fireStatus = false; 
		
	}

}

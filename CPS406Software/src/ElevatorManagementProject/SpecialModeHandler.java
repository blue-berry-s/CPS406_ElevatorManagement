package ElevatorManagementProject;

/*
 * Special Mode Handler
 * 
 * Handler for special modes invoked by user or building systems or building patrons
 * Functionality:
 * - Receives special mode requests
 * - Checks whether a special mode can be handled
 * - Activate special modes
 * - Deactivate special modes
 * 
 */

import java.util.PriorityQueue;

public class SpecialModeHandler {
	private ElevatorManagement manager; 
	private Elevator elevator;
	private PriorityQueue<SpecialModes> modeQueue;

	public SpecialModeHandler(ElevatorManagement manager) {
		this.modeQueue = new PriorityQueue<SpecialModes>();
		this.manager = manager;
	}
	
	
	//Add a special mode request to special mode queue
	public void addEmergencyModes(SpecialModes mode) {
		this.modeQueue.add(mode);
	}
	
	//Checks whether there are special mode requests
	public boolean isEmpty() {
		return this.modeQueue.size() == 0;
	}
	
	//Deactivates emergency power mode by checking if it is active for all elevators (in manager modes)
	//It then checks if the building has power yet, if not it keeps the mode active 
	//Otherwise it says the mode isnt active 
	/**
	 *@return boolean - returns True if mode was successfully deactivated, False otherwise
	 */
	public void deactivatePowerEmergency() {
		boolean modeCheck = false; 
		SpecialModes mode = null; 
		if (manager.getManagementModes() != null) {
			for(SpecialModes s : manager.getManagementModes()){
				if (s instanceof EmergencyPower) {
					modeCheck = true; 
					mode = s;
					break;
				}
			}
			if (modeCheck == true) {
				if (mode.getBuilding().getPower() == true) {
					for (Elevator e1: manager.getElevators()) {
						e1.setPower(true);
						manager.activateElevator(e1);
						e1.getDoor().setMode(1);
						
					}
					//remove all instances of emergencypower mode from manager list 
					manager.getManagementModes().removeIf(i -> i instanceof EmergencyPower);
					System.out.println("---EMERGENCY POWER MODE HAS BEEN DEACTIVATED---");
				}
				else if (mode.getBuilding().getGenerator() == true) {
					for (Elevator e1: manager.getElevators()) {
						e1.setPower(true);
						manager.activateElevator(e1);
						e1.getDoor().setMode(1);
						
					}
					//remove all instances of emergencypower mode from manager list 
					manager.getManagementModes().removeIf(i -> i instanceof EmergencyPower);
					System.out.print("---EMERGENCY POWER MODE HAS BEEN DEACTIVATED---");
				}
				else {
					System.out.println("---BUILDING ELECTRICAL SYSTEM NOT ACTIVE, UNABLE TO DEACTIVATE MODE---");
				}
			}
			else {
				System.out.println("---EMERGENCY POWER MODE IS NOT CURRENTLY ACTIVE---");
			}
		}
		else {
			System.out.println("---NO CURRENTLY ACTIVE MODES---");
		}
	
		
		
	}
	
	//ALMOST identical to deactivate power mode, checks if mode exists, 
	//resets elevators, deactivates the alarm
	/**
	 *@return boolean - returns True if mode was successfully deactivated, False otherwise
	 */
	public void deactivateFireEmergency() {
		boolean modeCheck = false; 
		SpecialModes mode = null; 
		if (manager.getManagementModes() != null) {
			for(SpecialModes s : manager.getManagementModes()){
				if (s instanceof FireEmergency) {
					modeCheck = true; 
					mode = s;
					break;
				}
			}
			if (modeCheck == true) {
				for (Elevator e1: manager.getElevators()) {
					manager.activateElevator(e1);
					e1.getDoor().setMode(1);
				}
				mode.getBuilding().deactivateFireAlarm();
				manager.getManagementModes().removeIf(f -> f instanceof FireEmergency);
				System.out.println("***EMERGENCY FIRE MODE HAS BEEN DEACTIVATED***");
			}
			else {
				System.out.println("***EMERGENCY FIRE MODE IS NOT CURRENTLY ACTIVE***");
			}
		}
		else {
			System.out.println("---NO CURRENTLY ACTIVE MODES---");
		}
		
			
		
	}
	
	/**
	 *@return boolean - returns True if mode was successfully deactivated, False otherwise
	 */
	public void deactivateMedicalEmergency(Elevator e1) {
		boolean modeCheck = false; 
		SpecialModes mode = null; 
		if (e1.getCurrentlyActiveModes() != null) {
			for(SpecialModes s : e1.getCurrentlyActiveModes()){
				if (s instanceof MedicalEmergency) {
					modeCheck = true; 
					mode = s;
					break;
				}
			}
			if (modeCheck == true) {
				manager.activateElevator(e1);
				e1.getCurrentlyActiveModes().removeIf(i -> i instanceof MedicalEmergency);
				System.out.println("***MEDICAL EMERGENCY MODE HAS BEEN DEACTIVATED***");
			}
			else {
				System.out.println("***MEDICAL EMERGENCY MODE IS NOT CURRENTLY ACTIVE***");
			}
		}
		else {
			System.out.println("---NO CURRENTLY ACTIVE MODES---");
		}
	}
	
	
	//picks an elevator for MEDICAL EMERGENCY MODE
	public Elevator pickElevator(Floor floor) {
		Elevator ele = this.manager.pickElevator(floor);
		return ele;
	}
	
	
	//method to handle all emergency modes at once
	/**
	 *@return boolean - returns True if mode was successfully activated, False otherwise
	 *NOTE ALL special modes that uses the manager.deactivateElevator() function needs to use it first
	 *and add the call to the elevator directly (As using the manager would produce an error)
	 */
	public void handleEmergencyModes() throws InterruptedException {
		//activate backup power 
		SpecialModes check = modeQueue.poll();
		if (check.getPriority() == 0){
			if (check.getBuilding().getPower() == false) {
				if (check.getBuilding().getGenerator()) {
					System.out.println("---BACKUP GENERATOR ACTIVE---");
					System.out.println("---ALL ELEVATORS CURRENTLY ON BACKUP---");
				}
				else {
					System.out.println("---BACKUP GENERATOR INACTIVE---");
					System.out.println("---ALL ELEVATORS RETURNING TO LOBBY--");
					for (Elevator e1: manager.getElevators()) {
						EmergencyPower convert = (EmergencyPower) check;
						//e1.setPower(false);
						manager.deactivateElevator(e1);
						Call recall = new Call(convert.getLobby(), convert.getLobby(), e1);
						e1.addMotion(recall);
						manager.getManagementModes().add(check);
						e1.getDoor().setMode(3);
					}
				}
			}
			else {
				System.out.println("---BUILDING ELECTRICAL SYSTEM STILL ACTIVE---");
			}
			
			
		}
		else if (check.getPriority() == 1) {
			
			//if fire mode, deactivate all elevators and clear all calls
			//force doors to remain open
			for (Elevator e1: manager.getElevators()) {
				manager.getManagementModes().add(check);
				FireEmergency convert = (FireEmergency) check;
				e1.clearMotion();
				Call recall = new Call(convert.getRecall(), convert.getRecall(), e1);
				manager.deactivateElevator(e1);
				e1.addMotion(recall);
				e1.getDoor().setMode(3);
				
					
				System.out.println("***FIRE EMERGENCY MODE ACTIVATED, ELEVATORS MOVING TO EVACUATION FLOOR, PLEASE EVACUATE UPON ARRIVAL***");
				

	
			}
			
		}
		//if medical mode 
		//deactivate the elevator so it ignores regular calls
		//clear all current calls and move the elevator 
		else if (check.getPriority() == 2) {
			MedicalEmergency convert = (MedicalEmergency) check;
			Elevator ele = this.pickElevator(convert.getEmergencyFloor());
			if (ele == null) {
				System.out.println("NO ELEVATORS IN SERVICE");
			}
			else {
				Call medicalCalls = new Call(convert.getEmergencyFloor(), convert.getEmergencyFloor(),ele);
				manager.deactivateElevator(ele);
				manager.addMedicalElevator(ele);
				ele.addMotion(medicalCalls);
				ele.getCurrentlyActiveModes().add(check);
				System.out.println("+++MEDICAL EMERGENCY MODE ACTIVATED, PROCEEDING TO EMERGENCY FLOOR+++");
			}
			
		}
		else {
			System.out.println("NO CURRENT ACTIVE MODES=");
		}
	}
	
	


}

package ElevatorManagementProject;

import java.util.PriorityQueue;

public class SpecialModeHandler {
	private ElevatorManagement manager; 
	private Elevator elevator;
	//private PriorityQueue<Integer> modeQueue;
	private PriorityQueue<SpecialModes> modeQueue;
	//private FireEmergency fire; 
	//private EmergencyPower outage; 
	//private MedicalEmergency medical; 
	private BuildingSystem building; 
	//private Door door; 
	
	public SpecialModeHandler(ElevatorManagement manager) {
		//modeQueue = new PriorityQueue<Integer>();
		//this.building = building;
		this.modeQueue = new PriorityQueue<SpecialModes>();
		this.manager = manager;
	}
	
	
	//Add a mode to the mode queue 
	public void addEmergencyModes(SpecialModes mode) {
		this.modeQueue.add(mode);
	}
	
	//Deactivates emergency power mode by checking if it is active for all elevators (in manager modes)
	//It then checks if the building has power yet, if not it keeps the mode active 
	//Otherwise it says the mode isnt active 
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
						manager.activateElevator(e1);
						e1.getDoor().setMode(1);
					}
					//remove all instances of emergencypower mode from manager list 
					manager.getManagementModes().removeIf(i -> i instanceof EmergencyPower);
					System.out.print("---EMERGENCY POWER MODE HAS BEEN DEACTIVATED---");
				}
				else {
					System.out.print("---BUILDING ELECTRICAL SYSTEM NOT ACTIVE, UNABLE TO DEACTIVATE MODE---");
				}
			}
			else {
				System.out.print("---EMERGENCY POWER MODE IS NOT CURRENTLY ACTIVE---");
			}
		}
		else {
			System.out.print("---NO CURRENTLY ACTIVE MODES---");
		}
	
		
		
	}
	
	//ALMOST identical to deactivate power mode, checks if mode exists, 
	//resets elevators, deactivates the alarm
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
				manager.getManagementModes().removeIf(i -> i instanceof FireEmergency);
				System.out.print("***EMERGENCY FIRE MODE HAS BEEN DEACTIVATED***");
			}
			else {
				System.out.print("***EMERGENCY FIRE MODE IS NOT CURRENTLY ACTIVE***");
			}
		}
		else {
			System.out.print("---NO CURRENTLY ACTIVE MODES---");
		}
		
			
		
	}
	
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
				System.out.print("***MEDICAL EMERGENCY MODE HAS BEEN DEACTIVATED***");
			}
			else {
				System.out.print("***MEDICAL EMERGENCY MODE IS NOT CURRENTLY ACTIVE***");
			}
		}
		else {
			System.out.print("---NO CURRENTLY ACTIVE MODES---");
		}
	}
	
	
	//picks an elevator for MEDICAL EMERGENCY MODE
	public Elevator pickElevator(Floor floor) {
		Elevator ele = this.manager.pickElevator(floor);
		return ele;
	}
	
	
	//method to handle all emergency modes at once 
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
						e1.clearMotion();
						Call recall = new Call(convert.getLobby(), convert.getLobby(), e1);
						e1.addMotion(recall);
						manager.deactivateElevator(e1);
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
				e1.addMotion(recall);
				manager.deactivateElevator(e1);
				e1.getDoor().setMode(3);
					
				System.out.println("***FIRE EMERGENCY MODE ACTIVATED, ELEVATORS MOVING TO EVACUATION FLOOR, PLEASE EVACUATE UPON ARRIVAL***");
				

	
			}
			
		}
		//if medical mode 
		//deactivate the elevator so it ignores regular calls
		//clear all current calls and move the elevator 
		else if (check.getPriority() == 2) {
			MedicalEmergency convert = (MedicalEmergency) check;
			System.out.println("+++Medical Emergency Mode Activated, Proceeding To Emergency Floor");
			Elevator ele = this.pickElevator(convert.getEmergencyFloor());
			if (ele == null) {
				System.out.println("NO ELEVATOR IN SERVICE");
			}
			else {
				Call medicalCalls = new Call(convert.getEmergencyFloor(), convert.getEmergencyFloor(),ele);
				manager.deactivateElevator(ele);
				manager.addMedicalElevator(ele);
				elevator.clearMotion();
				elevator.addMotion(medicalCalls);
				ele.getCurrentlyActiveModes().add(check);
			}
			
		}
		else {
			System.out.println("NO CURRENT ACTIVE MODES=");
		}
	}
	
	


}

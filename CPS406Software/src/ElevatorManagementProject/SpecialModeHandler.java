package ElevatorManagementProject;

import java.util.PriorityQueue;

public class SpecialModeHandler {
	private ElevatorManagement manager; 
	private Elevator elevator;
	private PriorityQueue<Integer> modeQueue;
	private FireEmergency fire; 
	private EmergencyPower outage; 
	private MedicalEmergency medical; 
	private BuildingSystem building; 
	private boolean override = false; 
	//private Door door; 
	
	public SpecialModeHandler() {
		modeQueue = new PriorityQueue<Integer>();
	}
	//Method to activate a power emergency
	public void activatePowerEmergency(Elevator elevator) {
		modeQueue.add(outage.getPriority());
		outage.activate();
		
	}
	
	//Method to deactivate the first power emergency 
	//request in the list and reset the generator + deactive the mode
	public void deactivatePowerEmergency(Elevator elevator, boolean override ) {
		if (modeQueue.contains(0)&& building.getPower() && elevator.isPower()) {
			modeQueue.remove(0);
			building.setGenerator(false);
			outage.deactivate();
				
		}
		//possibly for maintenance purposes 
		else if (modeQueue.contains(0)&& (building.getPower()== false || elevator.isPower() == false)) {
			if (override) {
				modeQueue.remove(0);
				building.setGenerator(false);
				outage.deactivate();
			}
			else {
				System.out.println("Power to Elevator or Building Unavailable");
			}
		
		}
		else if (modeQueue.contains(0) == false) {
			System.out.println("Backup Power Operation Not Active");
		}
		//if it doesnt hit any of these, something is wrong 
		else {
			System.out.println("ERROR!");
		}
	}
	
	//Method to activate a fire emergency 
	public void activateFireEmergency(Elevator elevator) {
		modeQueue.add(fire.getPriority());
		fire.activate();
	}
	
	//Method to deactivate the first fire emergency 
	//request in the list and add the elevator back to active elevators 
	public void deactivateFireEmergency(Elevator elevator) {
		if (modeQueue.contains(1)) {
			modeQueue.remove(1);
			for (Elevator e1: manager.getElevators()) {
				manager.activateElevator(e1);
			}
			fire.deactivate();
		}
	
		else {
			System.out.println("No Fire Emergency Active, ERROR");
		}
	}
	
	
	//Method to activate a medical emergency 
	public void activateMedicalEmergency(Elevator elevator) {
		modeQueue.add(medical.getPriority());
		medical.activate();
			
	}
	
	//Method to deactivate the first medical emergency 
	//request in the list and add the elevator back to active elevators 
	public void deactiveMedicalEmergency(Elevator elevator) {
		if (modeQueue.contains(2)) {
			modeQueue.remove(2);
			manager.activateElevator(elevator);
			medical.deactivate();
		}
		else {
			System.out.println("No Medical Emergency Active, ERROR");
		}
	}
	
	//method to handle all emergency modes at once 
	public void handleEmergencyModes(Floor floor) throws InterruptedException {
		//activate backup power 
		if (modeQueue.peek() == 0){
			if (elevator.isPower() == false && building.getPower() == false)
			building.setGenerator(true);
			System.out.println("---BACKUP GENERATOR ACTIVE---");
			
		}
		else if (modeQueue.peek() == 1) {
			
			//if fire mode, deactivate all elevators and clear all calls
			//force doors to remain open
			for (Elevator e1: manager.getElevators()) {
					e1.clearMotion();
					Call recall = new Call(floor, floor, e1);
					e1.addMotion(recall);
					manager.deactivateElevator(e1);
					
				//Door
				System.out.println("Doors Open");
				System.out.println("***Fire Emergency Mode Enabled, Please Evacuate***");
		
			}
			
		}
		//if medical mode 
		//deactivate the elevator so it ignores regular calls
		//clear all current calls and move the elevator 
		//Need to fix this to take more than just one call
		else if (modeQueue.peek() == 2) {
			System.out.println("+++Medical Emergency Mode Activated, Proceeding To Emergency Floor");
			Call medicalCalls = new Call(medical.getCurrent(), medical.getEmergencyFloor(),elevator);
			manager.deactivateElevator(elevator);
			elevator.clearMotion();
			elevator.addMotion(medicalCalls);
			System.out.println("Doors Open");
			
		}
		else {
			System.out.println("No active modes");
		}
	}
	
	


}


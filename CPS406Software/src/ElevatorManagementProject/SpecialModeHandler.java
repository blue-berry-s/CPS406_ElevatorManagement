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
	//private BuildingSystem building; 
	private boolean override = false; 
	//private Door door; 
	
	public SpecialModeHandler(ElevatorManagement manager) {
		//modeQueue = new PriorityQueue<Integer>();
		//this.building = building;
		this.modeQueue = new PriorityQueue<SpecialModes>();
		this.manager = manager;
	}
	
	
	/*
	//Method to activate a power emergency
	public void activatePowerEmergency() {
		modeQueue.add(outage);
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
	}*/
	
	public void addEmergencyModes(SpecialModes mode) {
		this.modeQueue.add(mode);
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
					FireEmergency convert = (FireEmergency) check;
					e1.clearMotion();
					Call recall = new Call(convert.getRecall(), convert.getRecall(), e1);
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
			}
			
		}
		else {
			System.out.println("No active modes");
		}
	}
	
	


}


package ElevatorManagementProject;

import java.util.Scanner;

/*
 * 
 * MedicalEmergency Example
 * 
 * This Class demonstrates how to activate MedicalEmergency and its functionalities
 * Code follows outlines given in use case scenario
 * 
 * Medical Emergency is given to an elevator
 * the elevator will now only service calls made within the elevator
 * it will not service any external calls
 * 
 * if all elevators are servicing a medical Emergency, the next request is disregarded
 * 
 * Elevators will only take outside calls once medical Emergency is disabled
 * 
 */

public class MedicalEmergency_Example {
	
	private static int time = 0;
	
	public static void elevatorPrint(Elevator e1, Elevator e2) {
		
		System.out.println("TIME: " + time);
		System.out.println("[-- E" +e1.getId()  + "--\t]\t[-- E" +e2.getId()  + "--]");
		System.out.println("|Location: " +  e1.getlocation().currentFloor() + "\t|\t|Location: " +  e2.getlocation().currentFloor()+"\t|");
		System.out.println("|IsMotion: " + e1.getMotion() + "\t|\t|IsMotion: " + e2.getMotion()+"\t|");
		System.out.println("|IsPower: " + e1.isPower() + "\t|\t|IsPower: " + e2.isPower()+"\t|");
		System.out.println("|IsEnabled: " + e1.isEnable() + "|\t|IsEnabled: " + e2.isEnable()+"|");
		System.out.println("|Door: " + e1.getDoorStatus()+ "\t|\t|Door: " + e2.getDoorStatus()+"\t|");
		
	}

public static void main(String[] args) throws InterruptedException {
	
	//Initializing all classes within our system
	//movement 1 = up, 0 = same, -1 = down
	Floor floor1 = new Floor(1);
	Floor floor2 = new Floor(2);
	Floor floor3 = new Floor(3);
	Floor floor4 = new Floor(4);
	Floor floor5 = new Floor(5);
	Floor floor6 = new Floor(6);
	ElevatorManagement manager = new ElevatorManagement();
	Door door1 = new Door();
	Door door2 = new Door();
	Elevator e1 = new Elevator(door1);
	Elevator e2 = new Elevator(door2);
	Call call1 = new Call(floor1, floor6, e1);
	Call call2 = new Call(floor1, floor4, e2);
	SpecialModeHandler handler = new SpecialModeHandler(manager);
	BuildingSystem MedicalCampus = new BuildingSystem(handler, floor1);
	InsideButton e1Button = new InsideButton(manager, e1, handler);
	InsideButton e2Button = new InsideButton(manager, e2, handler);
	OutsideButton floor5Button = new OutsideButton(manager,floor5);
	OutsideButton floor3Button = new OutsideButton(manager,floor3);
	
	//constructor for Medical Emergency Mode calls
	//MedicalEmergency (current floor its called on, request floor, building)
	//current floor is arbitrary
	MedicalEmergency medical1 = new MedicalEmergency(floor5, floor5, MedicalCampus);
	MedicalEmergency medical2 = new MedicalEmergency(floor5, floor3, MedicalCampus);
	MedicalEmergency medical3 = new MedicalEmergency(floor5, floor2, MedicalCampus);
	
	
	
	
	//make sure you add the elevator object to the manager or else elevators will never receive calls
	manager.addElevator(e1);
	manager.addElevator(e2);
	
	//notice how some elevators are set to different location
	e1.setLocation(floor6);
	e2.setLocation(floor1);
	
	elevatorPrint(e1,e2);
	Scanner input = new Scanner(System.in);
	System.out.println("Press any button to see the next time frame, press X to escape: ");
	String in = input.next();
	while (!(in.equals("X"))) {
		time ++;
		elevatorPrint(e1,e2);
		
		if (time == 1) {
			//this is how you add MedicalEmergency to SpecialModeHandler Directly
			handler.addEmergencyModes(medical1);
		}
		else if (time == 3) {
			//despite Elevator 1 already being at Floor5, It should not service this call because it is only servicing internal button calls
			// Elevator 2 is given the call instead
			floor5Button.callElevator(1);
		}
		else if (time == 5) {
			// Elevator 1 will still service internal calls
			e1Button.callFloor(floor2);
		}
		else if (time == 6) {
			// In the middle of Elevator 2's travel, it activates a Emergency Medical Mode
			// its current call is deleted and is made to service this floor instead
			//@ALEXIA despite the mode being unable to be handled - it will still say it is activated
			handler.addEmergencyModes(medical2);
		}
		else if (time == 10) {
			//both elevators are servicing their own Medical Emergency so there is no more elevator that can service these calls
			handler.addEmergencyModes(medical3);
			floor3Button.callElevator(1);
		}
		else if (time == 13) {
			handler.deactivateMedicalEmergency(e1);
		}
		else if (time == 14) {
			//after deactivating medical Emergency mode - the elevator is able to service the mode
			floor3Button.callElevator(1);
		}
		
		
		while (!handler.isEmpty()) {
			handler.handleEmergencyModes();
		}
		while (!manager.callEmpty()) {
			manager.takeCall();
		}
		
		manager.moveElevators();
		in = input.next();
		
	}
	
	}
}

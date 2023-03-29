package ElevatorManagementProject;

import java.util.Scanner;

public class EmergencyPower_Example {
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
	
	//constructor for EmergencyPower Mode calls
	//MedicalEmergency (current floor its called on, recall/lobby floor, building)
	MedicalEmergency medical1 = new MedicalEmergency(floor5, floor5, MedicalCampus);
	EmergencyPower powerOutage1 = new EmergencyPower(floor1, floor1, MedicalCampus);
	EmergencyPower powerOutage2 = new EmergencyPower(floor2, floor1, MedicalCampus);
	
	
	
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
		else if (time == 4) {
			//if building power is still active - do not continue with EmergencyPower
			handler.addEmergencyModes(powerOutage1);
		}
		else if (time == 5) {
			//if building power is off - Emergency power should try to use Backup Generators first
			MedicalCampus.setPower(false);
			handler.addEmergencyModes(powerOutage1);
		}
		else if (time == 6) {
			//shows that elevators are still able to receive calls while on backup generator
			// also shows MedicalEmergency is still active within the elevator as Elevator 2 has to take the call
			floor5Button.callElevator(1);
		}
		else if (time == 11) {
			//PROBLEM: 
			//Suppose to happen: Elevators all move to lobby/recall floor with doors open
			// What actually: Due to the fact elevator power:false, elevators will not move
			// potential solution: if elevator management contains a Emergencypower mode - it will check whether elevators are at floor1 (lobby) 
			// and set the elevator power:false
			//@Alexia
			// Also check notes in BuidlingSystem.java - I made a comment on how if we setGenerator(false) - it should automatically call
			// a PowerEmergency if the current power is also off
			MedicalCampus.setGenerator(false);
			handler.addEmergencyModes(powerOutage2);
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

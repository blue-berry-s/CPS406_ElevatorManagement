package ElevatorManagementProject;

import java.util.Scanner;

/*
 * 
 * FireEmergency Example
 * 
 * This Class demonstrates how to activate Fire Emergency using the Class directly or BuildingSystem object
 * 
 * 
 * 
 */

public class FireEmergency_Example {
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
		EmergencyPower powerOutage1 = new EmergencyPower(floor1, floor1, MedicalCampus);
		EmergencyPower powerOutage2 = new EmergencyPower(floor2, floor1, MedicalCampus);
		
		//Constructor for FireEmergency
		FireEmergency fire1 = new FireEmergency(floor6, floor4, MedicalCampus);
		
		
		
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
				//this is how you add FireEmergency to the handler directly
				handler.addEmergencyModes(fire1);
			}
			else if (time == 4) {
				//Any Calls made during this time should not be handled
				manager.addCall(call1);
				floor3Button.callElevator(-1);
			}
			else if (time == 7) {
				//This is how you deactivate FireEmergency using the handler
				//@ALEXIA - elevator does not move despite calls being made
				handler.deactivateFireEmergency();
				manager.addCall(call1);
				manager.addCall(call2);
			}
			
			else if (time == 10) {
				//This is how you activate FireEmergency using the BuildingSystem object
				MedicalCampus.activateFireAlarm();
			}
			else if (time == 15) {
				//Any calls made during this time should not be accepted
				manager.addCall(call1);
				manager.addCall(call2);
			}
			else if (time == 16) {
				//This is how you deactivate FireEmergency using the BuildingSystem object
				MedicalCampus.deactivateFireAlarm();
				manager.addCall(call1);
				manager.addCall(call2);
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

package ElevatorManagementProject;

/*
 * TEST DOOR MODE FUNCTIONALITY
 * tests that when sets to AUTO OPEN MANUAL CLOSE elevators should not close doors after arriving at location
 * tests that elevator is unable to move due to doors being open
 * 
 * 
 * NOTE:
 * Elevator door mode is default to AUTOMATIC OPEN + CLOSE
 * (Therefore all open function would automatically close after certain period of time)
 */

import java.util.Scanner;

public class Test_Door_Modes_03_26 {
private static int time = 0;
	
	public static void elevatorPrint(Elevator e1, Elevator e2) {
		
		System.out.println("TIME: " + time);
		System.out.println("[-- E" +e1.getId()  + "--\t]\t[-- E" +e2.getId()  + "--]");
		System.out.println("|Location: " +  e1.getlocation().currentFloor() + "\t|\t|Location: " +  e2.getlocation().currentFloor());
		System.out.println("|IsMotion: " + e1.getMotion() + "\t|\t|IsMotion: " + e2.getMotion());
		//System.out.println("|IsPower: " + e1.isPower() + "\t|\t|IsPower: " + e2.isPower());
		System.out.println("|IsEnabled: " + e1.isEnable() + "|\t|IsEnabled: " + e2.isEnable());
		System.out.println("|Door: " + e1.getDoorStatus()+ "\t|\t|Door: " + e2.getDoorStatus());
		
	}

	public static void main(String[] args) throws InterruptedException {
		
		//Creating scanner object 
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
		Call call3 = new Call(floor2,1);
		SpecialModeHandler handler = new SpecialModeHandler(manager);
		BuildingSystem MedicalCampus = new BuildingSystem(handler, floor1);
		
 		
		manager.addElevator(e1);
		manager.addElevator(e2);
		manager.addCall(call1);
		manager.addCall(call2);
		door1.setMode(3);//change door modes to be AUTO OPEN MANUAL CLOSE
		door2.setMode(3);//change door modes to be AUTO OPEN MANUAL CLOSE
		
		elevatorPrint(e1,e2);
		Scanner input = new Scanner(System.in);
		System.out.println("Press any button to see the next time frame, press X to escape: ");
		String in = input.next();
		while (!(in.equals("X"))) {
			time ++;
			elevatorPrint(e1,e2);
			
			if(time == 10) {
				manager.addCall(call3); //Elevator should not be able to service calls since their doors are still open
			}
			
			while (!manager.callEmpty()) {
				manager.takeCall();
			}
			
			manager.moveElevators();
			in = input.next();
			
		}
	}
}

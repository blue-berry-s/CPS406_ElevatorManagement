package ElevatorManagementProject;

import java.util.Scanner;

/*
 * TEST INSIDE BUTTON FUNCTIONALITY
 * Inside Button can open elevator door
 * Inside Button can close elevator doors
 * Inside Buttons can make proper calls
 * 
 * 
 * NOTE:
 * Elevator door mode is default to AUTOMATIC OPEN + CLOSE
 * (Therefore all open function would automatically close after certain period of time)
 */

public class Test_Buttons_03_27 {
	
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
		SpecialModeHandler handler = new SpecialModeHandler(manager);
		BuildingSystem MedicalCampus = new BuildingSystem(handler, floor1);
		InsideButton e1Button = new InsideButton(manager, e1, handler);
		InsideButton e2Button = new InsideButton(manager, e2, handler);
		manager.addElevator(e1);
		manager.addElevator(e2);
		
		elevatorPrint(e1,e2);
		Scanner input = new Scanner(System.in);
		System.out.println("Press any button to see the next time frame, press X to escape: ");
		String in = input.next();
		while (!(in.equals("X"))) {
			time ++;
			elevatorPrint(e1,e2);
			
			if (time == 2) {
				e2Button.openDoorButton(); //successfully opened e2 door
			}
			
			else if (time == 3) {
				e1Button.openDoorButton(); //successfully opened e2 door
			}
			
			else if (time == 4) {
				e1Button.callFloor(floor6); //successfully call e1 to floor 6
				e2.getDoor().setMode(2); //change mode to MANUAL OPEN AND CLOSE
				e2.getDoor().open(e2); //successfully opened e2 door
			}
			
			else if (time == 5) {
				e2Button.closeDoorButton();//successfully closed e2 door
			}
			
			while (!manager.callEmpty()) {
				manager.takeCall();
			}
			
			manager.moveElevators();
			in = input.next();
			
		}
		
	}

}

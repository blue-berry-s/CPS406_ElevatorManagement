package ElevatorManagementProject;

import java.util.Scanner;

/*
 * 
 * Button Testing Class
 * 
 * This Class demonstrates the functionality of the Inside and Outside Button classes and how they would function in
 * a real world scenario. 
 * 
 * 
 */

public class Test_Buttons_03_29 {
	
	private static int time = 0;
	
	public static void elevatorPrint(Elevator e1, Elevator e2) {
			
			System.out.println("TIME: " + time);
			System.out.println("[-- E" + e1.getId()  + "--\t]\t[-- E" +e2.getId()  + "--]");
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
			Floor floor7 = new Floor(7);
			ElevatorManagement manager = new ElevatorManagement();
			Door door1 = new Door();
			Door door2 = new Door();
			Elevator e1 = new Elevator(door1);
			Elevator e2 = new Elevator(door2);
			SpecialModeHandler handler = new SpecialModeHandler(manager);
			BuildingSystem MedicalCampus = new BuildingSystem(handler, floor1);
			InsideButton e1Button = new InsideButton(manager, e1, handler);
			InsideButton e2Button = new InsideButton(manager, e2, handler);
			OutsideButton floor6Button = new OutsideButton(manager,floor6);
			OutsideButton floor2Button = new OutsideButton(manager,floor2);
			manager.addElevator(e1);
			manager.addElevator(e2);
			
			elevatorPrint(e1, e2);
			Scanner input = new Scanner(System.in);
			System.out.println("Press any button to see the next time frame, press X to escape: ");
			String in = input.next();
			while (!(in.equals("X"))) {
				time ++;
				elevatorPrint(e1,e2);
				
				if (time == 1) {
					// Someone on Floor 2 wants to go up
					floor6Button.callElevator(-1);

				}
				else if (e1.getlocation().currentFloor() == 6 && e1.getMotion() == 0) {
					// Since this request will be handled by E1. At Time = 6, it will arrive 
					// to pick up the passenger from Floor 6. The passenger inside wants 
					// to go to Floor 5, holds the door open for someone else and then that
					// person wishes to go to Floor 4
					e1Button.callFloor(floor5);
					e1Button.openDoorButton();
					e1Button.callFloor(floor4);
				}
				else if (time == 15) {
					// E2 will be called here
					floor2Button.callElevator(1);	
				}
				
				else if (time == 16) {
					// E2's passenger enters the elevator and calls for a Fire Emergency.
					// At Time = 20, both elevators are at the ground floor and disabled 
					// for the emergency
					e2Button.setFireAlarm(floor1, MedicalCampus);
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
			
			input.close();
			
		}

}

package ElevatorManagementProject;

import java.util.Scanner;

/*
 * 
 * Movement Example
 * 
 * This Class demonstrates how elevators can be moved with elevator management calls, inside button calls and outside button calls
 * not demonstrated but possible: you can add directly to the elevator motion set
 * 
 * NOTE:
 * Elevators should not move if over weight limit, doors are open, no power or disabled
 * 
 * NOTE:
 * DISABLE ELEVATORS: clear their motion queue but most other status sets (eg: being overweight, no power, door open) will not clear the motion set
 * 
 * 
 */

public class Movement_Example {
	
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
			
			//make sure you add the elevator object to the manager or else elevators will never receive calls
			manager.addElevator(e1);
			manager.addElevator(e2);
			
			elevatorPrint(e1,e2);
			Scanner input = new Scanner(System.in);
			System.out.println("Press any button to see the next time frame, press X to escape: ");
			String in = input.next();
			while (!(in.equals("X"))) {
				time ++;
				elevatorPrint(e1,e2);
				
				if (time == 1) {
					//this is how you add movement calls to the manager directly 
					manager.addCall(call1);
					manager.addCall(call2);
				}
				else if (e1.getlocation().currentFloor() == 6) {
					//this is how you add movement calls using inside buttons (Tied to a specific elevator)
					e1Button.callFloor(floor2);
				}
				else if(e2.getlocation().currentFloor() == 4) {
					//this is how you add movement calls using outside buttons (Tied to a specific Floor)
					e2Button.callFloor(floor5);
				}
				else if(time == 10) {
					floor3Button.callElevator(-1); //note here - due to the fact that elevator 2 is currently IDLE
					floor5Button.callElevator(1); // Elevator 2, will take both calls (3 and 5) even though e1 would be better suited to take floor3 Call (since it will end at floor2)
					// this is due to the fact that - Elevators do not change arrays when they are given a call (therefore Elevator 2 is still considered idle when it takes
					// both floor3 and floor5 button calls
					// don't know if anyone wants to optimize this - most likely use getLowest instead of getNearest given a certain condition etc
				}
				
				//notice how elevators should not move if OVERWEIGHT
				//notice how elevators should not move if DOORS OPEN
				else if(e1.getlocation().currentFloor() == 2 && e2.getlocation().currentFloor() == 5 && time < 14) {
					e1.getDoor().setMode(2);
					e1.getDoor().open(e1);
					
					e2.setWeight(29);
					manager.addCall(call1);
					manager.addCall(call2);
				}
				
				//notice how elevators should not move if DISABLED
				//notice how elevators should not move if no power
				else if (time == 14) {
					manager.deactivateElevator(e1);
					e2.setPower(false);
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

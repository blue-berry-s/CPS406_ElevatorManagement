package ElevatorManagementProject;

import java.util.Scanner;

/*
 * TEST SPECIAL MODE: FIRE ALARM EMERGENCY MEDICAL MODE
 * RESULT:
 * all elevators gets disabled, all elevators recalled to lobby
 * no elevator is able to take the next call made (at time == 16)
 * */
public class Test_Special_03_24 {
private static int time = 0;
	
	public static void elevatorPrint(Elevator e1) {
		
		System.out.println("TIME: " + time);
		System.out.println("-- E" +e1.getId()  + "--");
		System.out.println("Location: " +  e1.getlocation().currentFloor());
		//System.out.println("Weight: " +  e1.getWeight());
		System.out.println("IsMotion: " + e1.getMotion());
		//System.out.println("IsPower: " + e1.isPower());
		System.out.println("IsEnabled: " + e1.isEnable());
		//System.out.println(" ");
		
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
		Elevator e1 = new Elevator();
		Elevator e2 = new Elevator();
		Call call1 = new Call(floor1, floor6, e1);
		Call call2 = new Call(floor1, floor4, e2);
		SpecialModeHandler handler = new SpecialModeHandler(manager);
		BuildingSystem MedicalCampus = new BuildingSystem(handler, floor1);
		FireEmergency fireCall1 = new FireEmergency(floor1,floor1,MedicalCampus);
		
 		
 		
 		
		manager.addElevator(e1);
		manager.addElevator(e2);
		manager.addCall(call1);
		manager.addCall(call2);
		
		elevatorPrint(e1);
		elevatorPrint(e2);
		Scanner input = new Scanner(System.in);
		System.out.println("Press any button to see the next time frame, press X to escape: ");
		String in = input.next();
		while (!(in.equals("X"))) {
			time ++;
			elevatorPrint(e1);
			elevatorPrint(e2);
			
			if (time == 9) {
				handler.addEmergencyModes(fireCall1);
				handler.handleEmergencyModes();
			}
			else if (time == 16) {
				manager.addCall(call2);
			}
			
			while (!manager.callEmpty()) {
				manager.takeCall();
			}
			
			manager.moveElevators();
			in = input.next();
			
			
		}
	}

}

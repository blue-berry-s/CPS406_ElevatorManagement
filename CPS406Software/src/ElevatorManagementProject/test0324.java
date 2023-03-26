package ElevatorManagementProject;

import java.util.Scanner;

public class test0324 {
private static int time = 0;
	
	public static void elevatorPrint(Elevator e1) {
		
		System.out.println("TIME: " + time);
		System.out.println("-- E" +e1.getId()  + "--");
		System.out.println("Location: " +  e1.getlocation().currentFloor());
		System.out.println("Weight: " +  e1.getWeight());
		System.out.println("IsMotion: " + e1.getMotion());
		//System.out.println("IsPower: " + e1.isPower());
		//System.out.println("IsEnabled: " + e1.isEnable());
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
		//calls for test 1
		Call call1 = new Call(floor1, 1);
		Call call2 = new Call(floor1, floor2, e1);
 		Call call3 = new Call(floor2, -1); //
 		Call call4 = new Call(floor2, floor2, e1); 
 		Call call5 = new Call(floor1, 1);
 		//calls for test 2
 		Call call6 = new Call(floor1, 1);
 		Call call7 = new Call(floor1, floor6, e1);
 		Call call8 = new Call(floor4, 1);
 		Call call9 = new Call(floor4, floor5, e1);
 		Call call10= new Call(floor3, 1);
 		//call for test 2.5 (interleaving with test 1 and 2)
 		Call call11 = new Call(floor1, 1);
 		Call call12 = new Call(floor1, floor5, e2);
		 
		/*
		Call call1 = new Call(floor6, -1);
		Call call2 = new Call(floor3, 1);
 		Call call3= new Call(floor2, -1); 
 		Call call4 = new Call(floor4, 1);
		*/
 		
 		Call call13 = new Call(floor1, floor5, e2); 		
 		
		manager.addElevator(e1);
		manager.addElevator(e2);

		//e1.setWeight(19); // Elevator 1 will not move
		e1.setWeight(12); // Elevator 1 will move
		
		elevatorPrint(e1);
		elevatorPrint(e2);
		Scanner input = new Scanner(System.in);
		System.out.println("Press any button to see the next time frame, press X to escape: ");
		String in = input.next();
		while (!(in.equals("X"))) {
			time ++;
			elevatorPrint(e1);
			elevatorPrint(e2);
			if (time == 1) {
				manager.addCall(call1); //request at Floor 1 for elevator
			}
			else if (time == 3) {
				manager.addCall(call2); //Elevator go to floor 2
			}
			else if (time == 6) {
				manager.addCall(call3); //request at Floor 2 for elevator
			}
			else if (time == 7) {
				manager.addCall(call4); //Elevator go to floor 2(same floor)
			}
			else if (time == 10) {
				manager.addCall(call5);//request at Floor 1 for elevator
			}
			else if (time == 13) {
				manager.addCall(call6);//request at Floor 1 for elevator
			}
			else if (time == 14) {
				manager.addCall(call7);//Elevator go to floor 6
				manager.addCall(call11); //request at Floor 1 for elevator (should be sent to elevator 2)
			}
			else if (time == 15) {
				manager.addCall(call8);//request at Floor 4 for elevator
				manager.addCall(call12);
			}
			else if (time == 16) {
				//this call should technically be made ONCE elevator is at floor 4 (the inside button)
				manager.addCall(call9); //Elevator go to floor 5

			}
			else if (time == 17) {
				manager.addCall(call10); //request at Floor 3 for elevator
			}
			
			//else if (time == 25) {
				//manager.activateFireMode(floor1);
			//}
			else if (time == 35) {
				manager.addCall(call1); //request at Floor 1 for elevator
			}
			
			
			while (!manager.callEmpty()) {
				manager.takeCall();
			}
			manager.moveElevators();
			in = input.next();
			
			
		}
	}

}

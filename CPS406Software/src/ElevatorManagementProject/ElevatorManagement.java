package ElevatorManagementProject;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class ElevatorManagement {
	private Queue<Call> callQueue = new LinkedList<Call>();
	//private Queue<SpecialMode> mode_queue = new LinkedList<Special_mode>();
	private ArrayList<Elevator> elevators = new ArrayList<Elevator>();
	private ArrayList<Elevator> idleElevators = new ArrayList<Elevator>();
	private ArrayList<Elevator> upElevators = new ArrayList<Elevator>();
	private ArrayList<Elevator> downElevators = new ArrayList<Elevator>();
	private ArrayList<Elevator> disableElevators = new ArrayList<Elevator>();
	//have to switch to specialMode, Boolean
	private Dictionary<String, Boolean> status = new Hashtable<String,Boolean>();
	
		public ElevatorManagement() {
		}
		
		//add elevator
		public void addElevator(Elevator elevator) {
			this.elevators.add(elevator);
			this.idleElevators.add(elevator);
		}
		
		//add a list of elevators
		public void setElevators(ArrayList<Elevator> elevators) {
			this.elevators = new ArrayList<Elevator>(elevators);
			this.idleElevators = new ArrayList<Elevator>(elevators);
		}
		
		//return list of elevators 
		public ArrayList<Elevator> getElevators() {
			return elevators;
		}
		
		//return call queue
		public Queue<Call> getCalls(){
			return callQueue;
		}
		
		//add a call request
		public void addCall(Call call) {
			callQueue.add(call);
		}
		
		//check if call requests are empty
		public boolean callEmpty() {
			return (this.callQueue.size() == 0);
		}
		
		//Changes Elevator Management Arrays based on the Calls each Elevator is taking
		//NOTE elevator motion may differ than their current service Call direction
		//EG: if an elevator on floor 1 services a down call from floor 6
		// the elevator must move UPWARDS, but it is in the downElevators
		private void setArrays() {
			for (Elevator el: elevators) {
				if (el.getCurrentCall() != null) {
					int motion = el.getCurrentCall().getDirection();
					if (motion == 1) {
						if (this.idleElevators.contains(el)) {
							this.idleElevators.remove(el);
							this.upElevators.add(el);
						}
						else if (this.downElevators.contains(el)) {
							this.downElevators.remove(el);
							this.upElevators.add(el);
						}
					}
					else if (motion == -1) {
						if (this.idleElevators.contains(el)) {
							this.idleElevators.remove(el);
							this.downElevators.add(el);
						}
						else if (this.upElevators.contains(el)) {
							this.upElevators.remove(el);
							this.downElevators.add(el);
						}
					}
					else {
						if (this.upElevators.contains(el)) {
							this.upElevators.remove(el);
							this.idleElevators.add(el);
						}
						else if (this.downElevators.contains(el)) {
							this.downElevators.remove(el);
							this.idleElevators.add(el);
						}					
					}
				}
				
			}
		}
		
		//Algorithm to select the best elevator
		//Also accounts for when no elevators are available 
		//1) the nearest elevator going in the same direction as the request and has the call in its path
		//2) the nearest idle elevator
		private Elevator getNearest(Floor floor, ArrayList<Elevator> list, int direction) {
			Elevator Ele = null;
			int lowest = 0;
			if (list.isEmpty()) {
				System.out.print("No Elevators Available");
				return Ele;
			}
			for (Elevator el: list) {
				int check = floor.currentFloor() - el.getlocation().currentFloor();
				if (Ele == null) {
					Ele = el;
					lowest = check;
				}
				else if (direction == 1) {
					if (check >= 0 && check < lowest) {
						Ele = el;
						lowest = check;
					}
				}
				else if (direction == -1) {
					if (check <= 0 && check > lowest) {
						Ele = el;
						lowest = check;
					}
				}
				else if (direction == 0) {
					if (Math.abs(check) < Math.abs(lowest)) {
						Ele = el;
						lowest = check;
					}
				}
			}
			
			if ((direction == 1 && lowest < 0) || (direction == -1 && lowest > 0)) {
				return null;
			}
			
			return Ele;
		
		}
		
		//3) the elevator with the least amount of items in its Motion queue
		// CHANGE REQUEST: it may be possible for all elevators to be disabled
		// in which case, getLowest() should actually return NULL
		// it currently will return the first elevator in ArrayList elevators
		private Elevator getLowest() {
			Elevator Ele = null;
			int lowest = 0;
			for (Elevator el: this.elevators) {
				int check = el.setSize();
				if ((Ele == null || check < lowest) && !(disableElevators.contains(el))) {
					Ele = el;
					lowest = check;
				}
			}
			return Ele;
			
		}
		
		// Takes calls from callQueue and send it to the correct Elevator
		// OR indicate to user that no elevator can service the current call
		// CHANGE REQUEST:
		// takeCall should have some kind of error system for both Log and UI (will need to contact remey)
		// takeCall should either return or throw an error to indicate if the request was successful  or not
		// I feel like some of the if statments are redundant/repetitive 
		// EG: logically i feel like !(this.upElevators.isEmpty()) is redundant since .getNearest and .getLowest
		// should be able to handle empty lists
		// get.lowest should also have a CHECK since it may return NULL
		public void takeCall() {
			Call request = callQueue.poll();
			if (request.getElevator() == null) {
				//if there are no idle elevators to pick from
				if (this.idleElevators.isEmpty()) {
					//check if any elevator is in service
					//probably change this to throw error instead
					if (idleElevators.isEmpty() && upElevators.isEmpty() && downElevators.isEmpty()) {
						System.out.println("ERROR: NO ELEVATORS IN SERVICE");
					}
					else if (request.getDirection() == 1) {
						Elevator check = this.getNearest(request.current, this.upElevators, 1);
						if (check == null) {
							this.getLowest().addMotion(request);
						}
						else {
							check.addMotion(request);
						}

					}
					else if (request.getDirection() == -1) {
						Elevator check = this.getNearest(request.current, this.downElevators, -1);
						if (check == null) {
							this.getLowest().addMotion(request);
						}
						else {
							check.addMotion(request);
						}
					}
				}
				//if there are idle elevators to pickfrom
				else {
					this.getNearest(request.current, this.idleElevators, 0).addMotion(request);
				}
			}
			else {
				request.getElevator().addMotion(request);
			}
		}
		//moved to specialmodehandler
		/*
		public void activateFireMode(Floor floor) {
			for (Elevator el: elevators){
				el.clearMotion();
				Call recall = new Call(floor, floor, el);
				el.addMotion(recall);
				this.deactivateElevator(el);
			}
		}
		*/

		public Dictionary checkStatus() {
			return this.status;
		}
		
		public void deactivateElevator(Elevator elevator) {
			elevator.setEnable(false);
			if (idleElevators.contains(elevator)) {
				idleElevators.remove(elevator);
			}
			else if (upElevators.contains(elevator)) {
				upElevators.remove(elevator);
			}
			else if (downElevators.contains(elevator)) {
				downElevators.remove(elevator);
			}
			disableElevators.add(elevator);
		}
		
		//add elevator back to idle elevators to be used again 
		public void activateElevator(Elevator elevator) {
			elevator.setEnable(true);
			idleElevators.add(elevator);
		}
		
		
		//Move all the elevators
		// CHANGE REQUEST:
		// there may need to be changes here but im currently not sure whether it should be implemented here or elsewhere
		// 1) Elevators should not move if it is above the current weight
		// 2) Elevator management system should also check with building system and special modes
		// 3) Elevator managment also needs to service special mode classes?
		public void moveElevators() throws InterruptedException {
			for (Elevator el: elevators) {
				if (!(el.motionEmpty()) || el.getMotion() != 0 ){
					el.move();
				}
			setArrays();
			}
		}
		

}

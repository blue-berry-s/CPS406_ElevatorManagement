package ElevatorManagementProject;

/**
 * Elevator Management
 * Manager to handle all incoming movement calls and sends it to the correct elevator based on picking algorithm
 * Functionality:
 * - takes movement requests from buttons
 * - picks the best elevator
 * - sends movement request to elevators
 * - checks whether elevators can move, and moves elevators
 */


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class ElevatorManagement {
	private Queue<Call> callQueue = new LinkedList<Call>();
	private ArrayList<Elevator> elevators = new ArrayList<Elevator>();
	private ArrayList<Elevator> idleElevators = new ArrayList<Elevator>();
	private ArrayList<Elevator> upElevators = new ArrayList<Elevator>();
	private ArrayList<Elevator> downElevators = new ArrayList<Elevator>();
	private ArrayList<Elevator> disableElevators = new ArrayList<Elevator>();
	private ArrayList<Elevator> medicalElevators = new ArrayList<Elevator>();
	private HashSet<SpecialModes> managementModes = new HashSet<SpecialModes>();;
	
	
		//add elevator
		public void addElevator(Elevator elevator) {
			this.elevators.add(elevator);
			this.idleElevators.add(elevator);
		}
		
		//adds a list of elevators
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
		
		public HashSet<SpecialModes> getManagementModes() {
			return managementModes;
		}

		public void setManagementModes(HashSet<SpecialModes> managementModes) {
			this.managementModes = managementModes;
		}
		
		
		 /**
		    * setArrays
		    * helper internal function for elevator management to handle its arrays and keep Elevators in proper arrays
		    */
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
				else {
					if (this.downElevators.contains(el)) {
						this.downElevators.remove(el);
						this.idleElevators.add(el);
					}
					else if (this.upElevators.contains(el)) {
						this.upElevators.remove(el);
						this.idleElevators.add(el);
					}
				}
				
			}
		}
		
		 /**
		    *Helper function for Elevator Management to find the nearest Elevator
		    * @param	floor				The requested floor
		    * @param	ArrayList list		The list to search through
		    * @param	int	direction		The direction of desired travel
		    * @return	Elevator 			Returns the nearest Elevator and null if elevator is empty
		    */
		private Elevator getNearest(Floor floor, ArrayList<Elevator> list, int direction) {
			Elevator Ele = null;
			int lowest = 0;
			if (list.isEmpty()) {
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
		
		 /**
		    *Helper function for Elevator Management to find the Elevator with the least amount of requests
		    * @return	Elevator 			Returns the Elevator who can service the call fastest and null if all elevators are unavailable
		    */
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
		
		 /**
		    *Public elevator to help pick the most optimized elevator
		    *@param		Floor floor		The floor of interest
		    * @return	Elevator 		Returns the most optimized Elevator who can service the call fastest and null if all elevators are unavailable
		    */
		public Elevator pickElevator(Floor floor) {
			Elevator Ele1 = null;
			Elevator Ele2 = null;
			Elevator Ele3 = null;
			Ele1 = this.getNearest(floor, this.idleElevators, 0);
			Ele2 = this.getNearest(floor, this.upElevators, 0);
			Ele3 = this.getNearest(floor, this.downElevators, 0);
			if (Ele1 != null) {
				return Ele1;
			}
			else if (Ele2 != null) {
				return Ele2;
			}
			else {
				return Ele3;
			}
			
		}
		
		/**
		    *Elevator Manager handles a call request and send it to the correct elevator
		    * @return	boolean		returns True if the call was serviced, returns False if it is unable to be serviced
		    */
		public boolean takeCall() {
			Call request = callQueue.poll();
			if (request.getElevator() == null) {
				//if there are no idle elevators to pick from
				if (this.idleElevators.isEmpty()) {
					//check if any elevator is in service
					//probably change this to throw error instead
					if (upElevators.isEmpty() && downElevators.isEmpty()) {
						System.out.println("ERROR: NO ELEVATORS IN SERVICE");
						return false;
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
				//if there are idle elevators
				else {
					this.getNearest(request.current, this.idleElevators, 0).addMotion(request);
				}
			}
			//If the call is an inside call (or the elevator is already set within the Call method)
			else {
				//checks to see the elevator is able to service the call
				if (request.getElevator().isEnable()) {
					request.getElevator().addMotion(request);
				}
				else {
					System.out.println("ERROR:NOT IN SERVICE");
					return false;
				}
				
			}
			return true;
		}
		
		/**
		    *Deactivates elevators and prevent them from taking anymore calls
		    *@param		Elevator	
		    *NOTICE: Elevators can still take calls (and move if not overweight or no power)
		    *Elevator Management will simply not take calls but
		    *Internally, we can add directly to the Elevator's motion set
		    */
		public void deactivateElevator(Elevator elevator) {
			elevator.setEnable(false);
			elevator.clearMotion();
			elevator.setMotion(0);
			if (idleElevators.contains(elevator)) {
				idleElevators.remove(elevator);
			}
			else if (upElevators.contains(elevator)) {
				upElevators.remove(elevator);
			}
			else if (downElevators.contains(elevator)) {
				downElevators.remove(elevator);
			}
			if (!disableElevators.contains(elevator)) {
				disableElevators.add(elevator);
			}
			
		}
		
		//add elevator back to idle elevators to be used again
		public void activateElevator(Elevator elevator) {
			elevator.setEnable(true);
			if (disableElevators.contains(elevator)) {
				disableElevators.remove(elevator);
			}
			if (medicalElevators.contains(elevator)) {
				medicalElevators.remove(elevator);
			}
			idleElevators.add(elevator);
		}
		
		//add elevator to MEDICAL ELEVATOR array 
		// use activateElevator to return it to idleElevators to return it to normal function
		public void addMedicalElevator(Elevator elevator) {
			elevator.setEnable(true);
			this.medicalElevators.add(elevator);
		}
		
		
		/**
		    *Elevator Manager sends movement signals to Elevators that can move
		    * @return	ArrayList<Boolean>	returns an Arraylist with a boolean that indicates whether each elevator was moved or not
		    */
		public ArrayList<Boolean> moveElevators() throws InterruptedException {
			ArrayList<Boolean> elevatorMoved = new ArrayList<Boolean>();
			for (Elevator el: elevators) {
				if ((!el.motionEmpty() || el.getMotion() != 0)) {
					Boolean weight = el.checkWeight(el.getWeight());
					Boolean doors = el.getDoorStatus();
					Boolean power = el.isPower();
					if (!weight && doors && !power) {
						System.out.println("ERROR: E"+el.getId() +":Cannot move due to overweight and Doors Open and no POWER");
						elevatorMoved.add(false);
					}
					else if (!power) {
						System.out.println("ERROR: E"+el.getId() +":Cannot move due to no power");
						elevatorMoved.add(false);
					}
					else if (!weight) {
						System.out.println("ERROR: E"+el.getId() +":Cannot move due to Overweight");
						elevatorMoved.add(false);
					}
					else if (doors) {
						if (el.getDoor().getMode() != 2 && el.getDoor().getMode() != 3) {
							el.getDoor().close(el);
							el.move();
							elevatorMoved.add(true);
						}
						else {
							System.out.println("ERROR: E"+el.getId() +":Cannot move due to Doors Open");
							elevatorMoved.add(false);
						}
					}
					else {
						el.move();
						elevatorMoved.add(true);
					}
				}
			setArrays();
			}
			setArrays();
			return elevatorMoved;
		}
		

}


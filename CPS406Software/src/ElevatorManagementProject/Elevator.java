package ElevatorManagementProject;

/**
 * Elevator
 * holds information representing an Elevator
 * functionality:
 * - When called by Elevator Management, the elevator will move
 * - Open and close Doors at proper floors
 */

import java.util.LinkedHashSet;
import java.util.concurrent.TimeUnit;
import java.util.HashSet;

public class Elevator{
	
	private static int idGenerate = 1;
	
	//Elevator Variables 
	private int weight; //Weight of the elevator in pounds 
	// 1 for up, 0 for no motion, -1 for down
	private int motion;
	private boolean power; //Checks if power is available 
	private boolean enable; //Checks if elevator is enabled 
	private Floor location;
	private LinkedHashSet<Call> motionSet = new LinkedHashSet<Call>(); 
	private HashSet<SpecialModes> activeModes;
	private Call currentCall = null;
	private int id;
	private Door door = null;
	
	//Elevator Constructor 
	public Elevator () {
		weight = 0;
		motion = 0; 
		power = true; 
		enable = true;
		id = idGenerate;
		idGenerate ++;
		this.location = new Floor();
		this.activeModes = new HashSet<SpecialModes>();
	}
	//Elevator Constructor with Door
	public Elevator (Door door) {
		this();
		this.door = door;
	}
	//getDoor: Getter for door 
	public Door getDoor() {
		return this.door; 
	}
	//getID: Getter for ID
	public int getId() {
		return this.id;
	}
	
	//getWeight: Getter for weight 
	public int getWeight() {
		return weight;
	}
	
	//setWeight: Setter for weight 
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	//setMotion: Setter for motion  
	public void setMotion(int motion) {
		this.motion = motion;
	}
	
	//isPower: Getter for power
	public boolean isPower() {
		return power;
	}
	
	//setPower: Setter for power
	public void setPower(boolean power) {
		this.power = power;
	}
	
	//isEnable: Getter for enable
	public boolean isEnable() {
		return enable;
	}
	
	//setEnable: Setter for enable
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	//getMotion: Is the elevator in motion, 1 is up 0 is no motion, -1 is down
	public int getMotion() {
		return this.motion;
	}
	
	//Location method: Returns the location of the elevator 
	public Floor getlocation() {
		return this.location;
	}
	
	//A HELPER TESTER FUNCTION - SHOULD NOT BE USED IN ACTUAL PRACTICE
	public void setLocation(Floor floor) {
		this.location = new Floor(floor.currentFloor());
	}
	
	//Sets the currently active modes set 
	public void setCurrentlyActiveModes(HashSet<SpecialModes> activeModes){
		this.activeModes = activeModes;
	}
	
	//Returns the currently active modes set 
	public HashSet<SpecialModes> getCurrentlyActiveModes(){
		return this.activeModes;
	}
	
	/**
	 * adds a movement request to Elevator
	 * @param Call call - holds information about the movement request
	 * Ensures that the elevator is taking the longest path possible
	*/
	public void addMotion(Call call) {
		/*if the elevator is already taking a call, 
		 * compares to see if the new call is longer than the current path
		 */
		if (this.motion == 1) {
			this.motionSet.add(currentCall);
			this.motionSet.add(call);
			Call highest = maxFloor();
			this.motionSet.removeIf(n -> (n.equals(highest)));
			this.currentCall = highest;
			
		}
		else if (this.motion == -1) {
			this.motionSet.add(currentCall);
			this.motionSet.add(call);
			Call lowest = minFloor();
			this.motionSet.removeIf(n -> (n.equals(lowest)));
			this.currentCall = lowest;
		}
		//if the elevator was idle before this - simply add the call
		else {
			this.motionSet.add(call);
		}
	}
	
	//motionEmpty: Returns if the elevator has calls
	public boolean motionEmpty() {
		return this.motionSet.isEmpty();
	}
	
	//find the highest floor requested
	private Call maxFloor() {
		int count = 1;
		Call max = null;
		for (Call check : this.motionSet) {
			if (count == 1 || check.getRequest().currentFloor()> max.getRequest().currentFloor()) {
				max = check;
			}
			count ++;
		}
		return max;
	}
	//find the lowest floor requested
	private Call minFloor() {
		int count = 1;
		Call min = null;
		for (Call check : this.motionSet) {
			if (count == 1 || check.getRequest().currentFloor() < min.getRequest().currentFloor()) {
				min = check;
			}
			count ++;
		}
		return min;
	}
	//check if the current floor is in motionSet
	private boolean setContains(Floor check) {	
		Call temp = new Call(check,this.motion);
		boolean success = this.motionSet.removeIf(n -> (n.equals(temp)));
		if (success) {
			this.motionSet.add(temp);
		}
		return success;
	}
	
	//setSize: returns the size of the motionSet
	public int setSize() {
		return this.motionSet.size();
	}
	
	//returns motionSet 
	public LinkedHashSet<Call> getMotionSet(){
		return this.motionSet;
	}
	
	//returns the currentCall being serviced
	public Call getCurrentCall() {
		return this.currentCall;
	}
	
	/**
	 * Elevator Moves
	 * @JOSH - InterruptedException should be Caught 
	 * Elevator will move in the direction based on the current request it is servicing
	 * Elevator will also check to see if the current floor was requested
	 * Elevator will open/close doors if the current floor was requested
	*/
	public void move() throws InterruptedException {
		if (this.currentCall == null) {
			this.currentCall = this.maxFloor();
			this.move();
		}
		else {
			//STOP at this floor if the current floor is in motionSet
			if (this.setContains(this.location) && (!this.currentCall.getRequest().equals(this.location))) {
				if (this.door.getMode() != 2) {
					this.motionSet.removeIf(n -> (n.equals(new Call(this.location, this.motion))));
					this.door.open(this);
					}
				}
			//If Elevator not at the callRequest - start moving
			else if (this.currentCall.getRequest().currentFloor() > this.location.currentFloor()) {
				this.motion = 1;
				this.location.incrementFloor();
			}
			else if (this.currentCall.getRequest().currentFloor() < this.location.currentFloor()) {
				this.motion = -1;
				this.location.decrementFloor();
			}
			
			//if the Elevator has reached the currentCall - stop and open and close doors
			else if (this.currentCall.getRequest().equals(this.location)) {
				if (this.door.getMode() != 2) {
					this.motionSet.removeIf(n -> (n.equals(this.currentCall)));
					this.motion = 0;
					this.currentCall = null;
					this.door.open(this);
				}
			}
		}
		
	}
	
	//clearMotion: deletes all request
	public void clearMotion() {
		this.motionSet.clear();
		this.currentCall = null;
	}

	
	
	//CheckWeight: Checks if the weight of the elevator is over the maximum
	public boolean checkWeight(int weight) {
        return weight <= 18;
    }
	
	/**getDoorStatus: 
	 * @return boolean checks whether the door is open
	 */
	public boolean getDoorStatus() {
		return this.door.getDoorStatus();
	}
	
	
	
}


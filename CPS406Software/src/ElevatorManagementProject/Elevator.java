package ElevatorManagementProject;

import java.util.LinkedHashSet;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.HashSet;

public class Elevator{
	
	private static int idGenerate = 1;
	
	//Elevator Variables 
	private int weight; //Weight of the elevator in pounds 
	private int motion; //Checks if the elevator is in motion 
	private boolean power; //Checks if power is available 
	private boolean enable; //Checks if elevator is enabled 
	private Floor location;
	private LinkedHashSet<Call> motionSet = new LinkedHashSet<Call>(); 
	private HashSet<SpecialModes> activeModes;
	private Call currentCall = null;
	private int id;
	private Floor idleFloor;
	
	//Elevator Constructor 
	public Elevator () {
		weight = 0;
		motion = 0; 
		power = true; 
		enable = true;
		id = idGenerate;
		idGenerate ++;
		this.location = new Floor();
		this.activeModes = null;
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
	
	//Sets the currently active modes set 
	public void setCurrentlyActiveModes(HashSet<SpecialModes> activeModes){
		this.activeModes = activeModes;
	}
	
	//Returns the currently active modes set 
	public HashSet<SpecialModes> getCurrentlyActiveModes(){
		return this.activeModes;
	}
	
	//Adds motions
	public void addMotion(Call call) {
		//re-evaluate to make sure the current service call is the longest path possible 
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
	
	//Move elevator in given direction
	// need to:
	// either implemented here or somewhere else
	// elevator can ONLY move if the weight is not surpass the max weight
	// might be good to contact remey and ask if there could be a numpad input user can use in the UI
	public void move() throws InterruptedException {
		if (this.currentCall == null) {
			this.currentCall = this.maxFloor();
			//System.out.println(this.currentCall);
			this.move();
		}
		else {
			//STOP at this floor if the current floor is in motionSet
			if (this.setContains(this.location) && !(this.currentCall.getRequest().equals(this.location))) {
				this.motionSet.removeIf(n -> (n.equals(new Call(this.location, this.motion))));
				System.out.println("Doors Open");
				TimeUnit.SECONDS.sleep(1);
				System.out.println("Doors Closed");
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
				System.out.println("Doors Open");
				this.motionSet.removeIf(n -> (n.equals(this.currentCall)));
				this.motion = 0;
				this.currentCall = null;
				TimeUnit.SECONDS.sleep(1);
				System.out.println("Doors Closed");
			}
		}
		
	}
	
	public void clearMotion() {
		this.motionSet.clear();
		this.currentCall = null;
	}

	
	
	//CheckWeight: Checks if the weight of the elevator is over the maximum
	public boolean checkWeight(int w) {
		boolean isHeavy = false; //Holds whether the elevator is too heavy
		if (w > 5000) {
			isHeavy = true; 
		}	
		return isHeavy;
	}
	
	
	
}


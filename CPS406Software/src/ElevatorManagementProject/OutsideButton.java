package ElevatorManagementProject;

/**
 * OutsideButton
 */

public class OutsideButton {
    private ElevatorManagement manager;
    private Floor floor;
    private int direction; 

    public OutsideButton(Floor floor, int direction) {
        this.floor = floor;
        this.direction = direction;
    }

    //Makes an outside button call 
    public void callElevator(Floor floor, int direction) {
    	Call call = new Call(floor, direction);
        this.manager.addCall(call);
    }
    
    //Returns the outside button floor 
	public Floor getOutsideButtonFloor() {
		return floor;
	}
	//Sets the outside button floor 
	public void setOutsideButtonFloor(Floor floor) {
		this.floor = floor;
	}
	
	//gets the outside button direction
	public int getOutsideButtonDirection() {
		return direction;
	}
	
	//sets the outside button direction 
	public void setOutsideButtonDirection(int direction) {
		this.direction = direction;
	}
}
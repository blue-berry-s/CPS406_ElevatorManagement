package ElevatorManagementProject;

import java.util.concurrent.TimeUnit;

/**
 * InsideButton
 */
//Featuring potential fixes to the openDoors(), closedoors(), callFloor() and setAlarm()
public class InsideButton {
	private ElevatorManagement manager;
    private Elevator elevator;
    private Door door1;
    private SpecialModeHandler handler;
//    private boolean doorOpened;
//    private boolean doorClosed;
//    private boolean alarm;
	private Door door;

    public InsideButton(ElevatorManagement manager, Elevator elevator, SpecialModeHandler handler){//, Door door, SpecialMode mode) {
        this.manager = manager;
    	this.elevator = elevator;
        this.door1 = this.elevator.getDoor();
        this.handler = handler;
//        this.doorOpened = false;
//        this.doorClosed = true;
//        this.alarm = false;
    }

//    public void openDoors() throws InterruptedException {
//        this.doorOpened = true;
//        this.doorClosed = false;
//        this.door1.open(this.elevator);
//    }
//
//    public void closeDoors() {
//        this.doorOpened = false;
//        this.doorClosed = true;
//        this.door1.close(this.elevator);
//    }
    
    /**
    * This function simulates the door opening button in elevators.
    * @param	floor		The requested floor
    * @return				Whether the operation was successful
    * @throws InterruptedException 
    */
    public boolean callFloor(Floor floor) throws InterruptedException {
//    	If the elevator is already at the requested floor, keep the door open
        if (floor == this.elevator.getlocation()) {
            this.door1.open(elevator);
            return false;
        } 
        else {
//        	If the elevator has power, send the destination to elevator manager and close the door
            if (this.elevator.isPower() && this.elevator.isEnable()) {
            	Call call = new Call(this.elevator.getlocation(), floor, this.elevator);
                manager.addCall(call);
            }
            else {
            	return false;
            }
        }
        return true;
    }
    
    /**
    * This function simulates the door opening button in elevators.
    * @param	elevator	The elevator whose door will be opened
    * @return				Whether the operation was successful
    * @throws InterruptedException 
    */
    public boolean openDoorButton() throws InterruptedException {
    	if (this.elevator.getMotion() == 0) {
    		this.elevator.getDoor().open(elevator);
        	return true;
    	} else {
    		return false;
    	}
    	
    }
    
    /**
    * This function simulates the door closing button in elevators.
    * @param	elevator	The elevator whose door will be closed
    * @return				Whether the operation was successful
    * @throws InterruptedException 
    */
    public boolean closeDoorButton() throws InterruptedException {
    	this.elevator.getDoor().close(this.elevator);
    	return true;    	
    }

    /**
    * This function simulates the door closing button in elevators.
    * @param	recall		The floor which the elevator will proceed to
    * @param	building	The current building
    * @return				Whether the operation was successful
    * @throws InterruptedException 
    */
    public boolean setFireAlarm(Floor recall, BuildingSystem building) throws InterruptedException {
        this.handler.addEmergencyModes(new FireEmergency(this.elevator.getlocation(), recall, building));
        this.elevator.getDoor().open(this.elevator);
        return true;
    }

}
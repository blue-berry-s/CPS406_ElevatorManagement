package ElevatorManagementProject;



/**
 * InsideButton
 */
//Featuring potential fixes to the openDoors(), closedoors(), callFloor() and setAlarm()
public class InsideButton {

    private Elevator elevator;
    //private Door door;
    //private SpecialMode mode;
    private boolean doorOpened;
    private boolean doorClosed;
    private boolean alarm;
	private Door door;

    public InsideButton(Elevator elevator, Door door){//, Door door, SpecialMode mode) {
        this.elevator = elevator;
        this.door = door;
        this.doorOpened = false;
        this.doorClosed = true;
        this.alarm = false;
    }

    public void openDoors() throws InterruptedException {
        this.doorOpened = true;
        this.doorClosed = false;
        this.door.open(this.elevator);
    }

    public void closeDoors() {
        this.doorOpened = false;
        this.doorClosed = true;
        this.door.close(this.elevator);
    }

    public void callFloor(int floor) throws InterruptedException {
        if (floor == this.elevator.getlocation().currentFloor()) {
            openDoors();
        } else {
            if (this.elevator.checkWeight(this.elevator.getWeight()) && this.elevator.isPower()) {
            	Call call = new Call(this.elevator.getlocation(), floor);
                this.elevator.addMotion(call);
                closeDoors();
            }
        }
    }

    public void setAlarm() {
        this.alarm = true;
        this.doorOpened = false;
        this.doorClosed = true;
        this.door.close(this.elevator);
    }

}
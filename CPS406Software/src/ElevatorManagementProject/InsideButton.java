package ElevatorManagementProject;



/**
 * InsideButton
 */

public class InsideButton {

    private Elevator elevator;
    //private Door door;
    //private SpecialMode mode;
    private boolean doorOpened;
    private boolean doorClosed;
    private boolean alarm;

    public InsideButton(Elevator elevator){//, Door door, SpecialMode mode) {
        this.elevator = elevator;
        //this.door = door;
        //this.mode = mode;
        this.doorOpened = false;
        this.doorClosed = true;
        this.alarm = false;
    }

    public void openDoors() {
        this.doorOpened = true;
        this.doorClosed = false;

        //this.door.open();
    }

    public void closeDoors() {
        this.doorOpened = false;
        this.doorClosed = true;

        //this.door.close();
    }

    public void callFloor(int floor) {
        if (floor == this.elevator.location()) {
            openDoors();
        } else {
            if (this.elevator.getWeight() && this.elevator.getPower()) {
                this.elevator.setLocation(floor);
                closeDoors();
            }
        }
    }

    public void setAlarm() {
        this.alarm = true;
        this.doorOpened = false;
        this.doorClosed = true;
        this.door.close();
        this.mode.enable();
    }

}
package ElevatorManagementProject;

/**
 * OutsideButton
 */

public class OutsideButton {
    private ElevatorManagement manager;
    private int floor;
    private boolean up;
    private boolean down;

    public OutsideButton(int floor) {
        this.floor = floor;
        this.up = false;
        this.down = false;
    }

    public void callElevator(int floor, boolean up, boolean down) {
        this.manager.add(floor, up, down);
    }
}
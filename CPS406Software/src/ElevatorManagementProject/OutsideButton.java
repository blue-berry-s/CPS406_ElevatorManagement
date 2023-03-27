package ElevatorManagementProject;

/**
 * OutsideButton
 */
public class OutsideButton {
    private ElevatorManagement manager;
    private Floor floor;

    public OutsideButton(ElevatorManagement manager, Floor floor) {
        this.floor = floor;
        this.manager = manager;
    }

    /**
    * This function simulates the door closing button in elevators.
    * @param	floor		The floor which the passenger is calling the elevator from
    * @param	direction	The direction (1 for up, -1 for down)
    * @return				Whether the operation was successful
    */
    public boolean callElevator(Floor floor, int direction) {
       	this.manager.addCall(new Call(floor, direction));
       	return true;
        
    }
}
package ElevatorManagementProject;
import java.util.concurrent.TimeUnit;

/**
 * Door
 * represents the doors of an elevator
 * functionality:
 * - 3 different modes: Automatically open and close doors, Manually open and Manually close doors, Automatically open and Manually close doors
 * - open doors
 * - close doors
 */

public class Door {
	// Door modes
    public static final int MODE_AUTO_CLOSE_OPEN = 1;
    public static final int MODE_MANUAL_CLOSE_OPEN = 2;
    public static final int MODE_AUTO_OPEN_MANUAL_CLOSE = 3;

    private int mode = MODE_AUTO_CLOSE_OPEN;
    private boolean isOpen;

    /*
     * All doors default mode is Automatically open and Automatically close
     */
    public Door() {
        this.isOpen = false;
    }
    
    public Door(int mode) {
        this.isOpen = false;
        this.setMode(mode);
    }

    /**
     * @JOSH - the interruptedException should be caught somewhere
     * This function simulates doors opening
     * @param	Elevator elevator	information on which Elevator is currently opening door
     * @throws InterruptedException 
     */
    public void open(Elevator ele) throws InterruptedException {
        if (mode == MODE_AUTO_CLOSE_OPEN) {
            autoOpenAndClose(ele);
        } else if (mode == MODE_MANUAL_CLOSE_OPEN || mode == MODE_AUTO_OPEN_MANUAL_CLOSE) {
        	isOpen = true;
        	System.out.println("E" + ele.getId() + ": Doors Opened [isOpen:" + this.isOpen + "]" );
            
        }
    }
    
    /**
     * This function simulates doors closing
     * @param    Elevator ele to indicate a specific Elevator object to close doors of
     * @throws InterruptedException 
     */
    public void close(Elevator ele) throws InterruptedException {
        if (!isOpen) {
            System.out.println("E" + ele.getId() + ": Doors are already closed.");
        } else {
            isOpen = false;
            System.out.println("E" + ele.getId() + ": Doors Closed [isOpen:" + this.isOpen + "]" );    
        }
    }

    /**
     * This function performs mode 1 of Doors to autmatically open and close Elevator doors
     * @param    Elevator ele to indicate a specific Elevator object to open and close doors of
     * @throws InterruptedException 
     */
    private void autoOpenAndClose(Elevator ele) throws InterruptedException {
        isOpen = true;
        System.out.println("E" + ele.getId() + ": Doors Opened [isOpen:" + this.isOpen + "]" );
        TimeUnit.SECONDS.sleep(3);
        isOpen = false;
        System.out.println("E" + ele.getId() + ": Doors Closed [isOpen:" + this.isOpen + "]" );
    }

    /**
     * @return boolean Function to check whether the Door is currently Open
     */
    public boolean getDoorStatus() {
        return this.isOpen;
    }
    
    /**
     * @return Int Function to check the mode of the Door
     */
    public int getMode()
    {
        return mode;
    }
    
    /**
     * @param Integer representing the mode
     * @return boolean - Whether the mode was successfully set
     */
    public boolean setMode(int i) {
    	if (i == MODE_AUTO_CLOSE_OPEN) {
    		this.mode = MODE_AUTO_CLOSE_OPEN;
    	}
    	else if (i == MODE_MANUAL_CLOSE_OPEN) {
    		this.mode = MODE_MANUAL_CLOSE_OPEN;
    	}
    	else if (i == MODE_AUTO_OPEN_MANUAL_CLOSE) {
    		this.mode = MODE_AUTO_OPEN_MANUAL_CLOSE;
    	}
    	else {
    		System.out.println("SYSTEM ERROR: MODE "+ i +" IS NOT PROPER MODE");
    		return false;
    	}
    	return true;
    }

}

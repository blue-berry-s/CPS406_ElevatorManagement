package ElevatorManagementProject;
import java.util.Timer;
import java.util.TimerTask;

public class Door {
	// Door modes
    public static final int MODE_AUTO_CLOSE_OPEN = 1;
    public static final int MODE_MANUAL_CLOSE_OPEN = 2;
    public static final int MODE_AUTO_OPEN_MANUAL_CLOSE = 3;

    private int mode;
    private boolean isOpen;
    private Timer timer;

    public Door(int mode) {
        this.mode = mode;
        this.isOpen = false;
    }

    public void open() {
        if (mode == MODE_AUTO_CLOSE_OPEN) {
            autoOpenAndClose();
        } else if (mode == MODE_MANUAL_CLOSE_OPEN || mode == MODE_AUTO_OPEN_MANUAL_CLOSE) {
            isOpen = true;
        }
    }

    public void close() {
        if (mode == MODE_MANUAL_CLOSE_OPEN) {
            isOpen = false;
        } else if (mode == MODE_AUTO_OPEN_MANUAL_CLOSE) {
//            Elevator.sendDoorCloseSignal();
            isOpen = false;
        }
    }

    private void autoOpenAndClose() {
        isOpen = true;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                isOpen = false;
//                Elevator.sendDoorCloseSignal();
                timer.cancel();
                timer = null;
            }
        }, 3000);
    }

    public boolean getDoorStatus() {  // true -> open and false -> close
        return isOpen;
    }
    
    public int getMode()
    {
        return mode;
    }

}

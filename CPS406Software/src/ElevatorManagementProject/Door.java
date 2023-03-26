package ElevatorManagementProject;
//import java.util.Timer;
//import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Door {
	// Door modes
    public static final int MODE_AUTO_CLOSE_OPEN = 1;
    public static final int MODE_MANUAL_CLOSE_OPEN = 2;
    public static final int MODE_AUTO_OPEN_MANUAL_CLOSE = 3;

    private int mode;
    private boolean isOpen;
    //private Timer timer;

    public Door() {
        this.mode = MODE_AUTO_CLOSE_OPEN;
        this.isOpen = false;
    }
    public Door(int mode) {
        this.mode = mode;
        this.isOpen = false;
    }

    public void open(Elevator ele) throws InterruptedException {
        if (mode == MODE_AUTO_CLOSE_OPEN) {
            autoOpenAndClose(ele);
        } else if (mode == MODE_MANUAL_CLOSE_OPEN || mode == MODE_AUTO_OPEN_MANUAL_CLOSE) {
        	isOpen = true;
        	System.out.println("E" + ele.getId() + ": Doors Opened [isOpen:" + this.isOpen + "]" );
            
        }
    }

    public void close(Elevator ele) {
    	isOpen = false;
    	System.out.println("E" + ele.getId() + ": Doors Closed [isOpen:" + this.isOpen + "]" );
    	
    }

    private void autoOpenAndClose(Elevator ele) throws InterruptedException {
    	isOpen = true;
    	System.out.println("E" + ele.getId() + ": Doors Opened [isOpen:" + this.isOpen + "]" );
        TimeUnit.SECONDS.sleep(3);
        /*timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                isOpen = false;
                timer.cancel();
                timer = null;
            }
        }, 3000);*/
        isOpen = false;
        System.out.println("E" + ele.getId() + ": Doors Closed [isOpen:" + this.isOpen + "]" );
    }

    public boolean getDoorStatus() {  // true -> open and false -> close
        return isOpen;
    }
    
    public int getMode()
    {
        return mode;
    }
    
    public void setMode(int i) {
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
    	}
    }

}

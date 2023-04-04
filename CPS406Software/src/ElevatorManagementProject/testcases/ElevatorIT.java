

import java.util.HashSet;
import java.util.LinkedHashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author DELL
 */
public class ElevatorIT {
    
			Floor floor1 = new Floor(1);
			Floor floor2 = new Floor(2);
			Floor floor3 = new Floor(3);
			Floor floor4 = new Floor(4);
			Floor floor5 = new Floor(5);
			Floor floor6 = new Floor(6);
			ElevatorManagement manager = new ElevatorManagement();
			Door door1 = new Door();
			Door door2 = new Door();
			Elevator e1 = new Elevator(door1);
    public ElevatorIT() {
    }
    
    
    /**
     * Test of getWeight method, of class Elevator.
     */
    @Test
    public void testGetWeight() {
        System.out.println("getWeight");
         int expResult = 0;
        int result = e1.getWeight();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of checkWeight method, of class Elevator.
     */
    @Test
    public void testCheckWeight() {
        System.out.println("checkWeight");
        int weight = 6;
        System.out.println(e1.checkWeight(weight));
    }

    
    
}

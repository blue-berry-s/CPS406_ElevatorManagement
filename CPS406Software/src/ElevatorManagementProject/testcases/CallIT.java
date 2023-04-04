/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class CallIT {
    Floor floor1;
    Floor floor2;
    Elevator elevator;
    public CallIT() {
         floor1 = new Floor(1);
        floor2 = new Floor(2);
        elevator = new Elevator();
    }
    
    @BeforeClass
    public static void setUpClass() {
       
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

   
    /**
     * Test of getRequest method, of class Call.
     */
    @Test
    public void testGetRequest() {
        System.out.println("getRequest");
        Call instance = new Call();
        Floor expResult = null;
        Floor result = instance.getRequest();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrent method, of class Call.
     */
    @Test
    public void testGetCurrent() {
        System.out.println("getCurrent");
        Call instance = new Call();
        Floor expResult = null;
        Floor result = instance.getCurrent();
        assertEquals(expResult, result);
   
    }

    /**
     * Test of getElevator method, of class Call.
     */
    @Test
    public void testGetElevator() {
        System.out.println("getElevator");
        Call instance = new Call();
        Elevator expResult = null;
        Elevator result = instance.getElevator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getDirection (up) for method, of class Call.
     */
       
    @Test
    public void testCallUp() {
        Floor current = new Floor(2);
        Floor request = new Floor(5);
        Elevator elevator = new Elevator();
        Call call = new Call(current, request, elevator);
        assertEquals(1, call.getDirection());
    }
/**
     * Test of getDirection (down) for method, of class Call.
     */
       
    @Test
    public void testCallDown() {
        Floor current = new Floor(7);
        Call call = new Call(current, -1);
        assertEquals(-1, call.getDirection());
    }
}

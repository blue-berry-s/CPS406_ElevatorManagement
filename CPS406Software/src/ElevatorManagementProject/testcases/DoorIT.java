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
public class DoorIT {
    
    public DoorIT() {
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
     * Test of open method, of class Door.
     */
    @Test
    public void testOpen() throws Exception {
        System.out.println("open");
        Elevator ele = new  Elevator(new Door(1));
        Door instance = new Door(3);
        instance.open(ele);
        assertTrue(instance.getDoorStatus());
       }

    /**
     * Test of close method, of class Door.
     */
    @Test
    public void testClose() {
        System.out.println("close");
        Elevator ele = new Elevator(new Door());
        Door instance = new Door();
        instance.close(ele);
        
        assertFalse(instance.getDoorStatus());//check door is closed or not
    }

    /**
     * Test of getDoorStatus method, of class Door.
     */
    @Test
    public void testGetDoorStatus() throws InterruptedException {
        System.out.println("getDoorStatus");
        Door instance = new Door();
        boolean expResult = false;
        boolean result = instance.getDoorStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getMode method, of class Door.
     */
    @Test
    public void testGetMode() {
        System.out.println("getMode");
        Door instance = new Door();
        int expResult = 0;
        int result = instance.getMode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMode method, of class Door.
     */
    @Test
    public void testSetMode() {
        System.out.println("setMode");
        int i = 0;
        Door instance = new Door();
        boolean expResult = false;
        boolean result = instance.setMode(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

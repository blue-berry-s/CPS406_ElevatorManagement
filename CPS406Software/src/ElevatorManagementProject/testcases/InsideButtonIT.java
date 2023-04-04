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
public class InsideButtonIT {
    
    public InsideButtonIT() {
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
     * Test of callFloor method, of class InsideButton.
     */
    @Test
    public void testCallFloor() throws Exception {
        System.out.println("callFloor");
        Floor floor = null;
        InsideButton instance = null;
        boolean expResult = false;
        boolean result = instance.callFloor(floor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of openDoorButton method, of class InsideButton.
     */
    @Test
    public void testOpenDoorButton() throws Exception {
        System.out.println("openDoorButton");
        InsideButton instance = null;
        boolean expResult = false;
        boolean result = instance.openDoorButton();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeDoorButton method, of class InsideButton.
     */
    @Test
    public void testCloseDoorButton() throws Exception {
        System.out.println("closeDoorButton");
        InsideButton instance = null;
        boolean expResult = false;
        boolean result = instance.closeDoorButton();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFireAlarm method, of class InsideButton.
     */
    @Test
    public void testSetFireAlarm() throws Exception {
        System.out.println("setFireAlarm");
        Floor recall = null;
        BuildingSystem building = null;
        InsideButton instance = null;
        boolean expResult = false;
        boolean result = instance.setFireAlarm(recall, building);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import static Movement_Example.elevatorPrint//
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
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
public class ElevatorManagementIT {
    
    public ElevatorManagementIT() {
       // this.callQueue = new LinkedList<>();
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
     * Test of addElevator method, of class ElevatorManagement.
     */
    @Test
    public void testAddElevator() {
        System.out.println("addElevator");
        Elevator elevator = null;
        ElevatorManagement instance = new ElevatorManagement();
        instance.addElevator(elevator);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setElevators method, of class ElevatorManagement.
     */
    @Test
    public void testSetElevators() {
        System.out.println("setElevators");
        ArrayList<Elevator> elevators = null;
        ElevatorManagement instance = new ElevatorManagement();
        instance.setElevators(elevators);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getElevators method, of class ElevatorManagement.
     */
    @Test
    public void testGetElevators() {
        System.out.println("getElevators");
        ElevatorManagement instance = new ElevatorManagement();
        ArrayList<Elevator> expResult = null;
        ArrayList<Elevator> result = instance.getElevators();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCalls method, of class ElevatorManagement.
     */
    @Test
    public void testGetCalls() {
        System.out.println("getCalls");
        ElevatorManagement instance = new ElevatorManagement();
        Queue<Call> expResult = null;
        Queue<Call> result = instance.getCalls();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCall method, of class ElevatorManagement.
     */
    @Test
    public void testAddCall() {
        System.out.println("addCall");
        Call call = null;
        ElevatorManagement instance = new ElevatorManagement();
        instance.addCall(call);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of callEmpty method, of class ElevatorManagement.
     */
    @Test
    public void testCallEmpty() {
        System.out.println("callEmpty");
        ElevatorManagement instance = new ElevatorManagement();
        boolean expResult = false;
        boolean result = instance.callEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getManagementModes method, of class ElevatorManagement.
     */
    @Test
    public void testGetManagementModes() {
        System.out.println("getManagementModes");
        ElevatorManagement instance = new ElevatorManagement();
        HashSet<SpecialModes> expResult = null;
        HashSet<SpecialModes> result = instance.getManagementModes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setManagementModes method, of class ElevatorManagement.
     */
    @Test
    public void testSetManagementModes() {
        System.out.println("setManagementModes");
        HashSet<SpecialModes> managementModes = null;
        ElevatorManagement instance = new ElevatorManagement();
        instance.setManagementModes(managementModes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pickElevator method, of class ElevatorManagement.
     */
    @Test
    public void testPickElevator() {
        System.out.println("pickElevator");
        Floor floor = null;
        ElevatorManagement instance = new ElevatorManagement();
        Elevator expResult = null;
        Elevator result = instance.pickElevator(floor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of takeCall method, of class ElevatorManagement.
     */
    @Test
    public void testTakeCall() {
        System.out.println("takeCall");
        ElevatorManagement instance = new ElevatorManagement();
        boolean expResult = false;
        boolean result = instance.takeCall();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deactivateElevator method, of class ElevatorManagement.
     */
    @Test
    public void testDeactivateElevator() {
        System.out.println("deactivateElevator");
        Elevator elevator = null;
        ElevatorManagement instance = new ElevatorManagement();
        instance.deactivateElevator(elevator);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of activateElevator method, of class ElevatorManagement.
     */
    @Test
    public void testActivateElevator() {
        System.out.println("activateElevator");
        Elevator elevator = null;
        ElevatorManagement instance = new ElevatorManagement();
        instance.activateElevator(elevator);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMedicalElevator method, of class ElevatorManagement.
     */
    @Test
    public void testAddMedicalElevator() {
        System.out.println("addMedicalElevator");
        Elevator elevator = null;
        ElevatorManagement instance = new ElevatorManagement();
        instance.addMedicalElevator(elevator);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moveElevators method, of class ElevatorManagement.
     */
    @Test
    public void testMoveElevators() throws Exception{
        int time = 0;
        System.out.println("moveElevators");
        ElevatorManagement instance = new ElevatorManagement();
     
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
			Elevator e2 = new Elevator(door2);
			
			Call call1 = new Call(floor1, floor3, e1);
			Call call2 = new Call(floor2, floor5, e2);
		
			
			
			
			//make sure you add the elevator object to the manager or else elevators will never receive calls
			manager.addElevator(e1);
			manager.addElevator(e2);
                        manager.addCall(call1);
                        manager.addCall(call2);
                        
        //ArrayList<Boolean> result =manager.moveElevators();
			ArrayList<Boolean> result= new ArrayList<Boolean>();
                        result =manager.moveElevators();
			assertTrue(result.isEmpty());	
				

}

}
    


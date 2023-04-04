/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
public class BuildingSystemIT {
    //Creating scanner object 
		//movement 1 = up, 0 = same, -1 = down
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
		Call call1 = new Call(floor1, floor6, e1);
		Call call2 = new Call(floor1, floor4, e2);
		SpecialModeHandler handler = new SpecialModeHandler(manager);
		BuildingSystem MedicalCampus = new BuildingSystem(handler, floor1);
 		
    public BuildingSystemIT() {
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
     * Test of getGenerator method, of class BuildingSystem.
     */
    @Test
    public void testGetGenerator() {
        System.out.println("getGenerator");
        manager.addElevator(e1);
		manager.addElevator(e2);
		manager.addCall(call1);
		manager.addCall(call2);
		MedicalCampus.setGenerator(true);
                assertTrue(MedicalCampus.getGenerator());
    }

    /**
     * Test of getFire method, of class BuildingSystem.
     */
    @Test
    public void testGetFire() {
        System.out.println("getFire");
         manager.addElevator(e1);
		manager.addElevator(e2);
		manager.addCall(call1);
		manager.addCall(call2);
		MedicalCampus.setFire(true);
                assertTrue(MedicalCampus.getFire());
    }

    /**
     * Test of getPower method, of class BuildingSystem.
     */
    @Test
    public void testGetPower() {
        System.out.println("getPower");
         manager.addElevator(e1);
		manager.addElevator(e2);
		manager.addCall(call1);
		manager.addCall(call2);
		MedicalCampus.setPower(true);
                assertTrue(MedicalCampus.getPower());
    }

    /**
     * Test of setPower method, of class BuildingSystem.
     */
    @Test
    public void testSetPower() {
        System.out.println("setPower");
         manager.addElevator(e1);
		manager.addElevator(e2);
		manager.addCall(call1);
		manager.addCall(call2);
		MedicalCampus.setPower(true);
                assertTrue(MedicalCampus.getPower());
    }

    /**
     * Test of setGenerator method, of class BuildingSystem.
     */
    @Test
    public void testSetGenerator() {
        System.out.println("setGenerator");
        manager.addElevator(e1);
		manager.addElevator(e2);
		manager.addCall(call1);
		manager.addCall(call2);
		MedicalCampus.setGenerator(true);
                assertTrue(MedicalCampus.getGenerator());
    }

    /**
     * Test of setFire method, of class BuildingSystem.
     */
    @Test
    public void testSetFire() {
        System.out.println("setFire");
         manager.addElevator(e1);
		manager.addElevator(e2);
		manager.addCall(call1);
		manager.addCall(call2);
		MedicalCampus.setFire(true);
                assertTrue(MedicalCampus.getFire());
    }

    /**
     * Test of activateFireAlarm method, of class BuildingSystem.
     */
    @Test
    public void testActivateFireAlarm() {
        System.out.println("activateFireAlarm");
      manager.addElevator(e1);
		manager.addElevator(e2);
		manager.addCall(call1);
		manager.addCall(call2);
		MedicalCampus.activateFireAlarm();
                assertTrue(MedicalCampus.getFire());
    }

    /**
     * Test of activateBackUp method, of class BuildingSystem.
     */
    @Test
    public void testActivateBackUp() {
        System.out.println("activateBackUp");
       manager.addElevator(e1);
		manager.addElevator(e2);
		manager.addCall(call1);
		manager.addCall(call2);
		MedicalCampus.activateBackUp();
                assertTrue(MedicalCampus.getPower());
    }

    /**
     * Test of deactivateFireAlarm method, of class BuildingSystem.
     */
    @Test
    public void testDeactivateFireAlarm() {
        System.out.println("deactivateFireAlarm");
        manager.addElevator(e1);
		manager.addElevator(e2);
		manager.addCall(call1);
		manager.addCall(call2);
		MedicalCampus.deactivateFireAlarm();
                assertFalse(MedicalCampus.getFire());
    }
    
}

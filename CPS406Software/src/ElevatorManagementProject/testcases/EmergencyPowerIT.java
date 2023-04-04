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
public class EmergencyPowerIT {
    //Initializing all classes within our system
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
	InsideButton e1Button = new InsideButton(manager, e1, handler);
	InsideButton e2Button = new InsideButton(manager, e2, handler);
	OutsideButton floor5Button = new OutsideButton(manager,floor5);
	OutsideButton floor3Button = new OutsideButton(manager,floor3);
	
	//constructor for Medical Emergency Mode calls
	//MedicalEmergency (current floor its called on, request floor, building)
	//current floor is arbitrary
	
	//constructor for EmergencyPower Mode calls
	//MedicalEmergency (current floor its called on, recall/lobby floor, building)
	MedicalEmergency medical1 = new MedicalEmergency(floor5, floor1, MedicalCampus);
	EmergencyPower powerOutage1 = new EmergencyPower(floor1, floor1, MedicalCampus);
	EmergencyPower powerOutage2 = new EmergencyPower(floor2, floor1, MedicalCampus);
    public EmergencyPowerIT() {
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
     * Test of getElevatorPower method, of class EmergencyPower.
     */
    @Test
    public void testGetElevatorPower() {
        System.out.println("getElevatorPower");
       
	manager.addElevator(e1);
	manager.addElevator(e2);
	
	//notice how some elevators are set to different location
	e1.setLocation(floor6);
	e2.setLocation(floor1);
         assertTrue(powerOutage1.getElevatorPower());
       
    }

    /**
     * Test of getBuildingPower method, of class EmergencyPower.
     */
    @Test
    public void testGetBuildingPower() {
        System.out.println("getBuildingPower");
        manager.addElevator(e1);
	manager.addElevator(e2);
	
	//notice how some elevators are set to different location
	e1.setLocation(floor6);
	e2.setLocation(floor1);
         assertTrue(powerOutage2.getBuildingPower());
    }

    /**
     * Test of getGenerator method, of class EmergencyPower.
     */
    @Test
    public void testGetGenerator() {
        System.out.println("getGenerator");
           manager.addElevator(e1);
	manager.addElevator(e2);
	
	//notice how some elevators are set to different location
	e1.setLocation(floor6);
	e2.setLocation(floor1);
         assertTrue(powerOutage2.getGenerator());
    }

    /**
     * Test of getLobby method, of class EmergencyPower.
     */
    @Test
    public void testGetLobby() {
        System.out.println("getLobby");
            manager.addElevator(e1);
	manager.addElevator(e2);
	
	//notice how some elevators are set to different location
	e1.setLocation(floor6);
	e2.setLocation(floor1);
         assertEquals(floor1,powerOutage2.getLobby());
    }
    
}

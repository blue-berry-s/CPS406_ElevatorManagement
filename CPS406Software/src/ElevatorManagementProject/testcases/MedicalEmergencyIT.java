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
public class MedicalEmergencyIT {
    
    public MedicalEmergencyIT() {
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
     * Test of getEmergencyFloor method, of class MedicalEmergency.
     */
    @Test
    public void testGetEmergencyFloor() {
        System.out.println("getEmergencyFloor");
      
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
	MedicalEmergency medical1 = new MedicalEmergency(floor5, floor5, MedicalCampus);
	MedicalEmergency medical2 = new MedicalEmergency(floor5, floor3, MedicalCampus);
	MedicalEmergency medical3 = new MedicalEmergency(floor5, floor2, MedicalCampus);
        //BuildingSystem building = new BuildingSystem(10);
        Floor currentFloor = new Floor(3);
        Floor emergencyFloor = new Floor(7);
        MedicalEmergency emergency = new MedicalEmergency(currentFloor, emergencyFloor, MedicalCampus);
        assertEquals(emergencyFloor, emergency.getEmergencyFloor());
    }

    /**
     * Test of getPriority method, of class MedicalEmergency.
     */
    @Test
    public void testGetPriority() {
        System.out.println("getPriority");
        
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
	
	
	//constructor for Medical Emergency Mode calls
	//MedicalEmergency (current floor its called on, request floor, building)
	//current floor is arbitrary
	
        Floor currentFloor = new Floor(3);
        Floor emergencyFloor = new Floor(7);
        MedicalEmergency emergency = new MedicalEmergency(currentFloor, emergencyFloor, MedicalCampus);
        assertEquals(2, emergency.getPriority());
    }
    
}

package ElevatorManagementProject;

public class Floor {

	//Floor variables 
	private int floor; // Floor number
	private boolean lobby; //Checks if current floor is the lobby 
	private boolean parking; //Checks if current floor is a parking floor
	
	public boolean equals(Object otherObject) { //overwriting equals method
		Floor other = (Floor) otherObject;
		return this.floor == other.floor;
	}
	
	
	//Floor constructor 
	public Floor () {
		floor = 1;
	}
	public Floor(int floor) {
		this.floor = floor;
	}
	
	public void setLobby() {
		this.lobby = true;
	}
	
	public boolean isLobby() {
		return this.lobby;
	}
	
	public void setParking() {
		this.parking = true;
	}
	
	public boolean isParking() {
		return this.parking;
	}
	
	
	//currentFloor: Returns current floor (technically floor getter)
	public int currentFloor () {
		return floor; 
		
	}
		
	//setFloor: sets the current floor 
	public void setFloor(int floor) {
		
		//Accounts for invalid floors 
		if (floor == 1 || floor == 2) {
			this.floor = floor;
		}
		else {
			System.out.println("Invalid floor");
		}
	}
	
	public void incrementFloor() {
		this.floor ++;
	}
	
	public void decrementFloor() {
		this.floor --;
	}
	
	
	
}

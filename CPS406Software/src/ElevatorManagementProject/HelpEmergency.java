package ElevatorManagementProject;

public class HelpEmergency extends SpecialModes{
	private static int HELP = 3;
	
	public HelpEmergency(Floor current, BuildingSystem building) {
		super(current, building);
		super.active = true; 
		super.priority = this.HELP;
		// TODO Auto-generated constructor stub
	}

}

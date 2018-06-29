package Projeto;

public class UpgradeReset extends Upgrade{

	private int cost = 1;
	private Clicker clicker;
	private int multiplier = 1;
	
	public UpgradeReset() {
		
	}
	public UpgradeReset(Clicker clicker) {
		this.clicker = clicker;
	}
	
	public void costUp() {
	}
	public int getCost() {
		return 0;
	}

	public void setCost(int cost) {
		
	}

}

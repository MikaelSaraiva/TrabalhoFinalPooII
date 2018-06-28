package Projeto;

public class Upgrade {

	private int cost = 1;
	private int period = 1000;
	private int multiplier = 1;
	private Clicker clicker = new Clicker();

	public Upgrade() {

	}

	public Upgrade(Clicker clicker, int cost) {
		this.clicker = clicker;
		this.cost = cost;
	}

	public Upgrade(Clicker clicker, int cost, int period) {
		this(clicker, cost);
		this.period = period;
	}

	public void costUp() {
		cost += cost;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getPeriod() {
		return period;
	}

	public void upgradeClick() {
		multiplier++;
	}

	public int getMulti() {
		return multiplier;
	}

	public void setMulti(int multiplier) {
		this.multiplier = multiplier;
	}
	
}

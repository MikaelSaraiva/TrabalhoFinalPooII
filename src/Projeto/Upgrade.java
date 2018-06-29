package Projeto;

public abstract class Upgrade {

	private int cost = 1;
	private int period = 1000;
	private int multiplier = 1;
	private Clicker clicker = new Clicker();

	public abstract void costUp();
	public abstract int getCost();
	public abstract void setCost(int cost);
}

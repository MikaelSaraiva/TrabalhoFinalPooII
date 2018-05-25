package Projeto;

import javax.swing.JOptionPane;

public class Clicker {
	private int accumulator;

	private final String[] namesLevel = { "UFSC", "USP", "UNAM", "Harvard",
			"MIT", "Global", "Via Lactea", "Universo",
			"University of Toulouse, França", };
	private String level;
	private int upLevel = 0;
	private int resetCoin = 0;
	private float dps;

	public void accumulate(int multiplicador) {
		accumulator += 1 * multiplicador;
	}

	public int getAccumulate() {
		return accumulator;
	}

	public void setAccumulate(int accumulator) {
		this.accumulator = accumulator;
	}

	public void levelUp() {
		upLevel++;
	}

	public void setLevel(int upLevel) {
		this.upLevel = upLevel;
	}

	public String getLevel() {

		level = namesLevel[upLevel];

		return level;
	}

	public void setResetCoin(int resetCoin) {
		this.resetCoin = resetCoin;
	}

	public int getResetCoin() {
		return resetCoin;
	}

	public float getDPS() {
		return dps;
	}

	public void setDPS(float dps) {
		this.dps = dps;
	}

	public String nivel(int accumulator) {

		String labelLevel = getLevel(); // UFSC
		switch (accumulator) {
		case 20000:
			JOptionPane.showMessageDialog(null, "Passou de nivel");
			levelUp();
			labelLevel = "Level: " + getLevel(); // USP
			break;

		case 40000:
			JOptionPane.showMessageDialog(null, "Passou de nivel");
			levelUp();
			labelLevel = "Level: " + getLevel();// UNAM
			break;

		case 60000:
			JOptionPane.showMessageDialog(null, "Passou de nivel");
			levelUp();
			labelLevel = "Level: " + getLevel(); // Harvard
			break;

		case 80000:
			JOptionPane.showMessageDialog(null, "Passou de nivel");
			levelUp();
			labelLevel = "Level: " + getLevel(); // MIT
			break;

		case 100000:
			JOptionPane.showMessageDialog(null, "Passou de nivel");
			levelUp();
			labelLevel = "Level: " + getLevel(); // Global
			break;

		case 120000:
			JOptionPane.showMessageDialog(null, "Passou de nivel");
			levelUp();
			labelLevel = "Level: " + getLevel(); // Via Lactea
			break;

		case 140000:
			JOptionPane.showMessageDialog(null, "Passou de nivel");
			levelUp();
			labelLevel = "Level: " + getLevel(); // Universo
			break;

		case 160000:
			JOptionPane.showMessageDialog(null, "Passou de nivel");
			levelUp();
			labelLevel = "Level: " + getLevel(); // França
			break;

		}

		return labelLevel;
	}

}

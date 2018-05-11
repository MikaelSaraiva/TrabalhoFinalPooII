package Projeto;

import javax.swing.JOptionPane;

public class Clicker {
	private int acumulador;

	private final String[] nivel = { "UFSC", "USP", "UNAM", "Harvard", "MIT", "Global", "Via Lactea", "Universo",
			"University of Toulouse, França", };
	private String level;
	private int upLevel = 0;
	private int resetCoin = 0;

	public void acumular(int multiplicador) {
		acumulador += 1 * multiplicador;
	}

	public int getAcumulador() {
		return acumulador;
	}

	public void setAcumulador(int acumulador) {
		this.acumulador = acumulador;
	}

	public void levelUp() {
		upLevel++;
	}

	public void setLevel(int upLevel) {
		this.upLevel = upLevel;
	}

	public String getLevel() {

		level = nivel[upLevel];

		return level;
	}

	public void setResetCoin(int resetCoin) {
		this.resetCoin = resetCoin;
	}

	public int getResetCoin() {
		return resetCoin;
	}

	public String nivel(int acumulador) {

		String labelLevel = getLevel(); // UFSC
		switch (acumulador) {
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

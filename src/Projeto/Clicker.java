package Projeto;

import javax.swing.JOptionPane;

public class Clicker {
	private int acumulador;

	private final String[] nivel = { "University of Toulouse, França", "Universo", "Via Lactea", "Global", "MIT", "Harvard", "UNAM", "USP",
			"UFSC", };
	private String level;
	private int upLevel = 8;

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
		upLevel--;
	}

	public String getLevel() {

		level = nivel[upLevel];

		return level;
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

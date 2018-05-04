package Projeto;

import java.util.Timer;
import java.util.TimerTask;

public class Clicker {

	private int acumulador;
	private int level;

	public void acumular(int multiplicador) {
		acumulador += 1*multiplicador ;
	}

	public int getAcumulador() {
		return acumulador;
	}

	public void setAcumulador(int acumulador) {
		this.acumulador = acumulador;
	}

	public void levelUp() {
		level++;
	}

	public int getLevel() {
		return level;
	}

}

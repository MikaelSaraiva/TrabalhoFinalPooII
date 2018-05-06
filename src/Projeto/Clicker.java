package Projeto;

public class Clicker {

	private int acumulador;
	private final String[] nivel = { "UFSC", "USP", "UNAM", "Harvard", "MIT", "Global", "Via Lactea", "Universo", "França" };
	private String level;
	private int upLevel = 0;

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
		upLevel++;
	}

	public String getLevel() {
		
		level = nivel[upLevel];
		
		return level;
	}

}

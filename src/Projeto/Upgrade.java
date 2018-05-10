package Projeto;

public class Upgrade {

	private int custo = 1;
	private int periodo = 1000;
	private int multiplicador = 1;
	private Clicker clicker = new Clicker();

	public Upgrade() {

	}

	public Upgrade(Clicker clicker, int custo) {
		this.clicker = clicker;
		this.custo = custo;
	}

	public Upgrade(Clicker clicker, int custo, int periodo) {
		this(clicker, custo);
		this.periodo = periodo;
	}

	public void custo() {
		custo += custo;
	}

	public int getCusto() {
		return custo;
	}

	public void setCusto(int custo) {
		this.custo = custo;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void upgradeClick() {
		multiplicador++;
	}

	public int getMulti() {
		return multiplicador;
	}

	public int multCompras(int vezes) {
		custo = custo * vezes;
		return custo;
	}
}

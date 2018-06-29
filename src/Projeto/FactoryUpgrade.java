package Projeto;

public class FactoryUpgrade {

	public Upgrade[] getUpgrade(String typeUpgrade,int qtdUpgrade, Clicker clicker, int cost, int period) {
		Upgrade[] saida = new Upgrade[qtdUpgrade];
		if (typeUpgrade.equals("autoClick")) {
			for(int i = 0; i < qtdUpgrade; i++) {
			saida[i] = new UpgradeAutoClick(clicker, cost, period);
			}
		}
		if (typeUpgrade.equals("reset")){
			for(int i = 0; i < qtdUpgrade; i++) {
				saida[i] = new UpgradeReset(clicker);
			}
		}
		return saida;
	}
}

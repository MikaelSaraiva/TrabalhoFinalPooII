package Projeto;

public class FactoryUpgrade {

	public Upgrade getUpgrade(String typeUpgrade, Clicker clicker, int cost, int period) {
		Upgrade saida = null;
		if (typeUpgrade.equals("autoClick"))
			saida = new UpgradeAutoClick(clicker, cost, period);
		if (typeUpgrade.equals("reset"))
			saida = new UpgradeReset(clicker);
		return saida;
	}
}

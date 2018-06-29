package Projeto;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class Upgrade {

	private int cost = 1;
	private int period = 1000;
	private int multiplier = 1;
	private Clicker clicker = new Clicker();
	private int altura = 40;
	private int espacamento = altura + 10;
	private float dps = 0;
	private int aux = 0;
	private JButton btnUpgrade = new JButton();

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

	public Upgrade[] upgradeArray(int qtdUpgrade, int diminutiveTime) {
		Upgrade[] upgradeArray = new Upgrade[qtdUpgrade];
		for (int j = 0; j < qtdUpgrade; j++) {
			upgradeArray[j] = new Upgrade(clicker, j, 10000 / diminutiveTime);
		}
		return upgradeArray;
	}

	public JButton buttons(
			int qtdUpgrades, Upgrade[] upgradeArray, int linha, int custoPadrao,
			int TEMPO, TimerGame time, int diminutiveTime
	) {
		JButton[] buttons = new JButton[qtdUpgrades];
		for (aux = 0; aux < qtdUpgrades; aux++) {
			Upgrade upgrade = upgradeArray[aux];
			buttons[aux] = new JButton(String.format("%d articles",
					(custoPadrao * upgrade.getCost())));
			JButton btnUpgrade = buttons[aux];
			btnUpgrade.setBounds(410, espacamento * linha, 100, altura);
			btnUpgrade.setHorizontalAlignment(SwingConstants.RIGHT);
			if (aux == 0)
				System.out.println("Oi");
//				buttons[aux].setVisible(true);
			else
//				buttons[aux].setVisible(true);
			btnUpgrade.addActionListener((e) -> {
				int custo = custoPadrao * upgrade.getCost();
				if (clicker.getAccumulate() >= custo) {
					btnUpgrade.setEnabled(true);
					clicker.setAccumulate(clicker.getAccumulate() - custo);
					long delay = TEMPO * upgrade.getPeriod();
					time.timer(delay, clicker);
					dps += 0.1 * diminutiveTime;
					if (aux == qtdUpgrades - 1) {
						buttons[aux].setEnabled(false);
					} else {
						buttons[aux].setVisible(false);
						buttons[aux + 1].setVisible(true);
					}
				} else
					JOptionPane.showMessageDialog(null, "Not enough articles");
			});
		}

		return btnUpgrade;
	}

}

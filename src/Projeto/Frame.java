package Projeto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class Frame extends JFrame {

	private Clicker clicker;
	private JLabel telaAcumulador;
	private JLabel labelLevel;
	private JLabel telaUpgrade;
	private JLabel upgradeClick;
	private JLabel dpsLabel;
	private JButton botaoAcumulador;
	private JButton multCompras1;
	private JButton multCompras10;
	private JButton multCompras100;
	private fibonacci fibo;
	private Upgrade upgrade;
	private Upgrade[] upgradesClick;
	private final int QTDUPGRADE = 10;
	private int repeticoes;
	private int multicompras;
	private int multiplicador = 1;
	private int j = 0;
	private int i = 0;
	private int k = 0;
	private int l = 0;
	private int m = 0;
	private int n = 0;
	private int o = 0;
	private int p = 0;
	private int q = 0;
	private float dps = 0;
	private int upgradeLevel = 1;
	private int custoPadrao = 1;
	private final int TEMPO = 1;

	public Frame() {
		super("Joguinho muito louco");

		fibo = new fibonacci();
		clicker = new Clicker();
		upgrade = new Upgrade();

		JPanel upgradePanel = new JPanel();
		JPanel clickUpgradePanel = new JPanel();
		JPanel upPanel = new JPanel();
		JPanel botaoPanel = new JPanel();
		JPanel dadosPanel = new JPanel();

		DecimalFormat apsFormat = new DecimalFormat("0.00");

		JScrollPane scrollPane = new JScrollPane(upgradePanel);
		scrollPane.setBackground(Color.RED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		upgradePanel.setLayout(new BoxLayout(upgradePanel, BoxLayout.PAGE_AXIS));
		clickUpgradePanel.setLayout(new BoxLayout(clickUpgradePanel, BoxLayout.PAGE_AXIS));
		upPanel.setLayout(new GridLayout(1, 2));
		botaoPanel.setLayout(new GridLayout(1, 1));
		dadosPanel.setLayout(new BoxLayout(dadosPanel, BoxLayout.PAGE_AXIS));

		upgradePanel.setBackground(Color.BLACK);
		upPanel.setBackground(Color.WHITE);
		clickUpgradePanel.setBackground(Color.BLACK);

		upPanel.add(dadosPanel);
		upPanel.add(botaoPanel);

		setLayout(new GridLayout(2, 1));
		add(upPanel);

		JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		tabs.addTab("Upgrade Auto-Click", scrollPane);
		tabs.addTab("Upgrade Click", clickUpgradePanel);
		add(tabs);

		telaAcumulador = new JLabel("Artigos: " + clicker.getAcumulador());
		telaAcumulador.setAlignmentX(LEFT_ALIGNMENT);
		telaAcumulador.setAlignmentY(TOP_ALIGNMENT);
		dadosPanel.add(telaAcumulador);

		upgradeClick = new JLabel("Upgrade Click: 0");
		upgradeClick.setAlignmentX(LEFT_ALIGNMENT);
		upgradeClick.setAlignmentY(TOP_ALIGNMENT);
		dadosPanel.add(upgradeClick);

		labelLevel = new JLabel("Level: " + clicker.getLevel());
		labelLevel.setAlignmentX(LEFT_ALIGNMENT);
		labelLevel.setAlignmentY(TOP_ALIGNMENT);
		dadosPanel.add(labelLevel);

		telaUpgrade = new JLabel("Upgrade: " + (upgradeLevel - 1));
		telaUpgrade.setAlignmentX(LEFT_ALIGNMENT);
		telaUpgrade.setAlignmentY(TOP_ALIGNMENT);
		dadosPanel.add(telaUpgrade);

		dpsLabel = new JLabel("APS(Artigos per second): " + apsFormat.format(dps));
		dadosPanel.add(dpsLabel);

		botaoAcumulador = new JButton("Clique para pular mais");
		botaoPanel.add(botaoAcumulador);
		botaoAcumulador.setAlignmentX(LEFT_ALIGNMENT);
		botaoAcumulador.setAlignmentY(TOP_ALIGNMENT);
		botaoAcumulador.setBounds(500, 500, 1, 1);
		botaoAcumulador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				clicker.acumular(upgrade.getMulti());
			}
		});

		Upgrade[] upgradesArtigo = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesArtigo[j] = new Upgrade(clicker, j + 1, 10000);
		}

		JButton[] buttonsArtigo = new JButton[QTDUPGRADE];
		for (int i = 0; i < QTDUPGRADE; i++) {
			Upgrade upgrade = upgradesArtigo[i];
			buttonsArtigo[i] = new JButton(
					String.format("Alunos fazendo artigo (%d artigos)", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttonsArtigo[i];
			btnUpgrade.setSize(50, 50);
			btnUpgrade.setVisible(false);
			btnUpgrade.setAlignmentX(LEFT_ALIGNMENT);
			upgradePanel.add(btnUpgrade);
			btnUpgrade.addActionListener((e) -> {
				int custo = custoPadrao * upgrade.getCusto();
				if (clicker.getAcumulador() >= custo) {
					btnUpgrade.setEnabled(true);
					clicker.setAcumulador(clicker.getAcumulador() - custo);
					Timer time = new Timer();
					long delay = TEMPO * upgrade.getPeriodo();
					time.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.acumular(1);
							if (clicker.getAcumulador() > 10000)
								labelLevel.setText("Level: " + clicker.nivel(clicker.getAcumulador()));
						}
					}, delay, delay);
					dps += 0.1;
					telaUpgrade.setText("Upgrade: " + (upgradeLevel++));
					if (this.i == upgradesArtigo.length - 1) {
						buttonsArtigo[this.i].setEnabled(false);
					} else {
						buttonsArtigo[this.i].setVisible(false);
						buttonsArtigo[this.i + 1].setVisible(true);
						this.i++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Você não possui artigos suficiente");
			});
		}
		buttonsArtigo[0].setVisible(true);

		Upgrade[] upgradesGifs = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesGifs[j] = new Upgrade(clicker, j + 1, 1000);
		}

		JButton[] buttonsGifs = new JButton[QTDUPGRADE];
		for (int k = 0; k < QTDUPGRADE; k++) {
			Upgrade upgrade = upgradesGifs[k];
			buttonsGifs[k] = new JButton(
					String.format("Alunos fazendo Gif (%d artigos)", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttonsGifs[k];
			btnUpgrade.setSize(50, 50);
			btnUpgrade.setVisible(false);
			btnUpgrade.setAlignmentX(LEFT_ALIGNMENT);
			upgradePanel.add(btnUpgrade);
			btnUpgrade.addActionListener((e) -> {
				int custo = custoPadrao * upgrade.getCusto();
				if (clicker.getAcumulador() >= custo) {
					btnUpgrade.setEnabled(true);
					clicker.setAcumulador(clicker.getAcumulador() - custo);
					Timer time = new Timer();
					long delay = TEMPO * upgrade.getPeriodo();
					time.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.acumular(1);
							if (clicker.getAcumulador() > 10000)
								labelLevel.setText("Level: " + clicker.nivel(clicker.getAcumulador()));
						}
					}, delay, delay);
					dps += 1;
					telaUpgrade.setText("Upgrade: " + (upgradeLevel++));
					if (this.k == upgradesGifs.length - 1) {
						buttonsGifs[this.k].setEnabled(false);
					} else {
						buttonsGifs[this.k].setVisible(false);
						buttonsGifs[this.k + 1].setVisible(true);
						this.k++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Você não possui artigos suficiente");
			});
		}
		buttonsGifs[0].setVisible(true);

		Upgrade[] upgradesPedia = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesPedia[j] = new Upgrade(clicker, j + 1, 200);
		}

		JButton[] buttonsPedia = new JButton[QTDUPGRADE];
		for (int l = 0; l < QTDUPGRADE; l++) {
			Upgrade upgrade = upgradesPedia[l];
			buttonsPedia[l] = new JButton(
					String.format("Atualizar o ArtigoPédia (%d artigos)", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttonsPedia[l];
			btnUpgrade.setSize(50, 50);
			btnUpgrade.setVisible(false);
			btnUpgrade.setAlignmentX(LEFT_ALIGNMENT);
			upgradePanel.add(btnUpgrade);
			btnUpgrade.addActionListener((e) -> {
				int custo = custoPadrao * upgrade.getCusto();
				if (clicker.getAcumulador() >= custo) {
					btnUpgrade.setEnabled(true);
					clicker.setAcumulador(clicker.getAcumulador() - custo);
					Timer time = new Timer();
					long delay = TEMPO * upgrade.getPeriodo();
					time.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.acumular(1);
							if (clicker.getAcumulador() > 10000)
								labelLevel.setText("Level: " + clicker.nivel(clicker.getAcumulador()));
						}
					}, delay, delay);
					dps += 5;
					telaUpgrade.setText("Upgrade: " + (upgradeLevel++));
					if (this.l == upgradesPedia.length - 1) {
						buttonsPedia[this.l].setEnabled(false);
					} else {
						buttonsPedia[this.l].setVisible(false);
						buttonsPedia[this.l + 1].setVisible(true);
						this.l++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Você não possui artigos suficiente");
			});
		}
		buttonsPedia[0].setVisible(true);

		Upgrade[] upgradesProcessos = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesProcessos[j] = new Upgrade(clicker, j + 1, 100);
		}

		JButton[] buttonsProcessos = new JButton[QTDUPGRADE];
		for (int m = 0; m < QTDUPGRADE; m++) {
			Upgrade upgrade = upgradesProcessos[m];
			buttonsProcessos[m] = new JButton(
					String.format("Dar processos (%d artigos)", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttonsProcessos[m];
			btnUpgrade.setSize(50, 50);
			btnUpgrade.setVisible(false);
			btnUpgrade.setAlignmentX(LEFT_ALIGNMENT);
			upgradePanel.add(btnUpgrade);
			btnUpgrade.addActionListener((e) -> {
				int custo = custoPadrao * upgrade.getCusto();
				if (clicker.getAcumulador() >= custo) {
					btnUpgrade.setEnabled(true);
					clicker.setAcumulador(clicker.getAcumulador() - custo);
					Timer time = new Timer();
					long delay = TEMPO * upgrade.getPeriodo();
					time.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.acumular(1);
							if (clicker.getAcumulador() > 10000)
								labelLevel.setText("Level: " + clicker.nivel(clicker.getAcumulador()));
						}
					}, delay, delay);
					dps += 10;
					telaUpgrade.setText("Upgrade: " + (upgradeLevel++));
					if (this.m == upgradesProcessos.length - 1) {
						buttonsProcessos[this.m].setEnabled(false);
					} else {
						buttonsProcessos[this.m].setVisible(false);
						buttonsProcessos[this.m + 1].setVisible(true);
						this.m++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Você não possui artigos suficiente");
			});
		}
		buttonsProcessos[0].setVisible(true);

		Upgrade[] upgradesSala = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesSala[j] = new Upgrade(clicker, j + 1, 50);
		}

		JButton[] buttonsSala = new JButton[QTDUPGRADE];
		for (int n = 0; n < QTDUPGRADE; n++) {
			Upgrade upgrade = upgradesSala[n];
			buttonsSala[n] = new JButton(
					String.format("Colocar rede na sala (%d artigos)", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttonsSala[n];
			btnUpgrade.setSize(50, 50);
			btnUpgrade.setVisible(false);
			btnUpgrade.setAlignmentX(LEFT_ALIGNMENT);
			upgradePanel.add(btnUpgrade);
			btnUpgrade.addActionListener((e) -> {
				int custo = custoPadrao * upgrade.getCusto();
				if (clicker.getAcumulador() >= custo) {
					btnUpgrade.setEnabled(true);
					clicker.setAcumulador(clicker.getAcumulador() - custo);
					Timer time = new Timer();
					long delay = TEMPO * upgrade.getPeriodo();
					time.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.acumular(1);
							if (clicker.getAcumulador() > 10000)
								labelLevel.setText("Level: " + clicker.nivel(clicker.getAcumulador()));
						}
					}, delay, delay);
					dps += 20;
					telaUpgrade.setText("Upgrade: " + (upgradeLevel++));
					if (this.n == upgradesSala.length - 1) {
						buttonsSala[this.n].setEnabled(false);
					} else {
						buttonsSala[this.n].setVisible(false);
						buttonsSala[this.n + 1].setVisible(true);
						this.n++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Você não possui artigos suficiente");
			});
		}
		buttonsSala[0].setVisible(true);

		Upgrade[] upgradesModem = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesModem[j] = new Upgrade(clicker, j + 1, 33);
		}

		JButton[] buttonsModem = new JButton[QTDUPGRADE];
		for (int o = 0; o < QTDUPGRADE; o++) {
			Upgrade upgrade = upgradesModem[o];
			buttonsModem[o] = new JButton(
					String.format("Inventar o modem (%d artigos)", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttonsModem[o];
			btnUpgrade.setSize(50, 50);
			btnUpgrade.setVisible(false);
			btnUpgrade.setAlignmentX(LEFT_ALIGNMENT);
			upgradePanel.add(btnUpgrade);
			btnUpgrade.addActionListener((e) -> {
				int custo = custoPadrao * upgrade.getCusto();
				if (clicker.getAcumulador() >= custo) {
					btnUpgrade.setEnabled(true);
					clicker.setAcumulador(clicker.getAcumulador() - custo);
					Timer time = new Timer();
					long delay = TEMPO * upgrade.getPeriodo();
					time.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.acumular(1);
							if (clicker.getAcumulador() > 10000)
								labelLevel.setText("Level: " + clicker.nivel(clicker.getAcumulador()));
						}
					}, delay, delay);
					dps += 30;
					telaUpgrade.setText("Upgrade: " + (upgradeLevel++));
					if (this.o == upgradesModem.length - 1) {
						buttonsModem[this.o].setEnabled(false);
					} else {
						buttonsModem[this.o].setVisible(false);
						buttonsModem[this.o + 1].setVisible(true);
						this.o++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Você não possui artigos suficiente");
			});
		}
		buttonsModem[0].setVisible(true);

		Upgrade[] upgradesHistoria = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesHistoria[j] = new Upgrade(clicker, j + 1, 25);
		}

		JButton[] buttonsHistoria = new JButton[QTDUPGRADE];
		for (int p = 0; p < QTDUPGRADE; p++) {
			Upgrade upgrade = upgradesHistoria[p];
			buttonsHistoria[p] = new JButton(
					String.format("Contar historias (%d artigos)", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttonsHistoria[p];
			btnUpgrade.setSize(50, 50);
			btnUpgrade.setVisible(false);
			btnUpgrade.setAlignmentX(LEFT_ALIGNMENT);
			upgradePanel.add(btnUpgrade);
			btnUpgrade.addActionListener((e) -> {
				int custo = custoPadrao * upgrade.getCusto();
				if (clicker.getAcumulador() >= custo) {
					btnUpgrade.setEnabled(true);
					clicker.setAcumulador(clicker.getAcumulador() - custo);
					Timer time = new Timer();
					long delay = TEMPO * upgrade.getPeriodo();
					time.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.acumular(1);
							if (clicker.getAcumulador() > 10000)
								labelLevel.setText("Level: " + clicker.nivel(clicker.getAcumulador()));

						}
					}, delay, delay);
					dps += 40;
					telaUpgrade.setText("Upgrade: " + (upgradeLevel++));
					if (this.p == upgradesHistoria.length - 1) {
						buttonsHistoria[this.p].setEnabled(false);
					} else {
						buttonsHistoria[this.p].setVisible(false);
						buttonsHistoria[this.p + 1].setVisible(true);
						this.p++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Você não possui artigos suficiente");
			});
		}
		buttonsHistoria[0].setVisible(true);

		// pacito a pacito para criar artigo
		// Alunos fazendo artigo <
		// Alunos fazendo gifs <
		// CBW ajeitando o wikipedia <
		// Processos <
		// Colocando rede na sala de redes <
		// modem <
		// Trocar patente do modem por moto
		// trocar faculdade(nivel): UFSC, USP, UNAM, Harvard, EMIT, Global, Via Lactea,
		// Universo.
		// Torturar com historias <

		upgradesClick = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesClick[j] = new Upgrade(clicker, j + 1, 1000);
		}

		JButton[] buttonsClick = new JButton[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			Upgrade upgradeMulti = upgradesClick[j];
			buttonsClick[j] = new JButton(String.format("<html>Aumentar quantidade<br/> de cadeiras (%d processos)<html>",
					(custoPadrao * upgradeMulti.getCusto())));
			JButton btnUpgradeClick = buttonsClick[j];
			btnUpgradeClick.setSize(100, 100);
			btnUpgradeClick.setVisible(false);
			btnUpgradeClick.setAlignmentX(LEFT_ALIGNMENT);
			clickUpgradePanel.add(btnUpgradeClick);
			btnUpgradeClick.addActionListener((e) -> {
				int custo = custoPadrao * upgradeMulti.getCusto();
				if (clicker.getAcumulador() >= custo) {
					btnUpgradeClick.setEnabled(true);
					upgrade.upgradeClick();
					upgradeClick.setText("Upgrade Click: " + (upgrade.getMulti() - 1));
					clicker.setAcumulador(clicker.getAcumulador() - custo);
					telaAcumulador.setText("Artigos: " + clicker.getAcumulador());
					if (this.j == upgradesClick.length - 1) {
						buttonsClick[this.j].setEnabled(false);
					} else {
						buttonsClick[this.j].setVisible(false);
						buttonsClick[this.j + 1].setVisible(true);
						this.j++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Você não possui processos suficiente");
			});
		}
		buttonsClick[0].setVisible(true);

		// Para ficar sempre atualizando o texto...
		Timer time = new Timer();
		time.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				telaAcumulador.setText("Artigos: " + clicker.getAcumulador());
				dpsLabel.setText("APS(Artigos per second): " + apsFormat.format(dps));
				if (clicker.getAcumulador() >= 10000 + (q * 20000)) {
					labelLevel.setText("Level: " + clicker.nivel(clicker.getAcumulador()));
					q++;
				}

			}
		}, 0, TEMPO * 100);

		// multCompras1 = new JButton("1x");
		// multCompras1.setEnabled(false);
		// multCompras1.setSize(10, 10);
		// add(multCompras1);
		// multCompras1.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// custoPadrao = 1;
		// multicompras = 1;
		// multCompras1.setEnabled(false);
		// multCompras10.setEnabled(true);
		// multCompras100.setEnabled(true);
		//
		// }
		// });
		// multCompras10 = new JButton("10x");
		// multCompras10.setSize(10, 10);
		// add(multCompras10);
		// multCompras10.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// custoPadrao = 10;
		// multicompras = 10;
		// multCompras1.setEnabled(true);
		// multCompras10.setEnabled(false);
		// multCompras100.setEnabled(true);
		// }
		// });
		// multCompras100 = new JButton("100x");
		// multCompras100.setSize(10, 10);
		// add(multCompras100);
		// multCompras100.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// custoPadrao = 100;
		// multicompras = 100;
		// multCompras1.setEnabled(true);
		// multCompras10.setEnabled(true);
		// multCompras100.setEnabled(false);
		// }
		// });

		// ButtonGroup multiplicadoresCompra = new ButtonGroup();
		// multiplicadoresCompra.add(multCompras1);
		// multiplicadoresCompra.add(multCompras10);
		// multiplicadoresCompra.add(multCompras100);

	}

	private class fibonacci {

		public int fib(int n) {
			long numb = 0;
			long[] numbAux = new long[n + 3];

			numbAux[0] = 1;
			numbAux[1] = 1;

			for (int i = 2; i < n + 3; i++) {
				numbAux[i] = numbAux[i - 1] + numbAux[i - 2];
			}
			numb = numbAux[n];

			return (int) numb;
		}

	}

}

package Projeto;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class Frame extends JFrame {

	private Clicker clicker;
	private JLabel telaAcumulador;
	private JLabel labelLevel;
	private JLabel telaUpgrade;
	private JLabel upgradeClick;
	private JButton botaoAcumulador;
	private JButton multCompras1;
	private JButton multCompras10;
	private JButton multCompras100;
	private fibonacci fibo;
	private Upgrade[] upgrades;
	private Upgrade upgrade;
	private final int QTDUPGRADE = 10;
	private int repeticoes;
	private int multicompras;
	private int multiplicador = 1;
	private int j = 0;
	private int i = 0;
	private int cont = 1;
	private int custoPadrao = 1;
	private final int TEMPO = 1;

	public Frame() {
		super("Joguinho muito louco");

		fibo = new fibonacci();
		clicker = new Clicker();

		JPanel upgradePanel = new JPanel();
		JPanel clickUpgradePanel = new JPanel();
		JPanel upPanel = new JPanel();
		
		upgradePanel.setLayout(new BoxLayout(upgradePanel, BoxLayout.PAGE_AXIS));
		clickUpgradePanel.setLayout(new BoxLayout(clickUpgradePanel, BoxLayout.PAGE_AXIS));
		upPanel.setLayout(new BoxLayout(upPanel, BoxLayout.PAGE_AXIS));
		
		upgradePanel.setBackground(Color.BLACK);
		upPanel.setBackground(Color.WHITE);
		clickUpgradePanel.setBackground(Color.BLACK);

		setLayout(new GridLayout(3, 1));
		add(upPanel);
//		add(downPanel); 

		JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		tabs.addTab("Upgrade Auto-Click", upgradePanel);
		tabs.addTab("Upgrade Click", clickUpgradePanel);
		
		telaAcumulador = new JLabel("Artigos: " + clicker.getAcumulador());
		telaAcumulador.setAlignmentX(LEFT_ALIGNMENT);
		telaAcumulador.setAlignmentY(TOP_ALIGNMENT);
		upPanel.add(telaAcumulador);

		upgradeClick = new JLabel("Upgrade Click: " + (multiplicador - 1));
		upgradeClick.setAlignmentX(LEFT_ALIGNMENT);
		upgradeClick.setAlignmentY(TOP_ALIGNMENT);
		upPanel.add(upgradeClick);

		labelLevel = new JLabel("Level: " + clicker.getLevel());
		labelLevel.setAlignmentX(LEFT_ALIGNMENT);
		labelLevel.setAlignmentY(TOP_ALIGNMENT);
		upPanel.add(labelLevel);

		telaUpgrade = new JLabel("Upgrade: 0");
		telaUpgrade.setAlignmentX(LEFT_ALIGNMENT);
		telaUpgrade.setAlignmentY(TOP_ALIGNMENT);
		upPanel.add(telaUpgrade);

		botaoAcumulador = new JButton("Clique para pular mais");
		add(botaoAcumulador);
		add(tabs);
		botaoAcumulador.setAlignmentX(LEFT_ALIGNMENT);
		botaoAcumulador.setAlignmentY(TOP_ALIGNMENT);
		botaoAcumulador.setBounds(500, 500, 1, 1);
		botaoAcumulador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				clicker.acumular(multiplicador);
			}
		});		

		upgrades = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgrades[j] = new Upgrade(clicker, j + 1, 1000);
		}

		JButton[] buttons = new JButton[QTDUPGRADE];
		for (int i = 0; i < QTDUPGRADE; i++) {
			Upgrade upgrade = upgrades[i];
			buttons[i] = new JButton(String.format("<html>Upgrade<br />(%d processos)<html>", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttons[i];
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
					System.out.println(custo);
					long delay = TEMPO * upgrade.getPeriodo();
					time.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.acumular(1);
						}
					}, delay, delay);
					repeticoes++;
					telaUpgrade.setText("Upgrade: " + (this.i + 1));
					if (this.i == upgrades.length - 1) {
						buttons[this.i].setEnabled(false);
					} else {
						buttons[this.i].setVisible(false);
						buttons[this.i + 1].setVisible(true);
						this.i++;
					}
					if (cont == 10) {
						clicker.levelUp();
						labelLevel.setText("Level: " + clicker.getLevel());
						cont = 0;
						JOptionPane.showMessageDialog(null, "Level Up");
					}
					cont++;
				} else
					JOptionPane.showMessageDialog(null, "Você não possui processos suficiente");
			});
		}
		buttons[0].setVisible(true);

		JButton[] buttons = new JButton[QTDUPGRADE];
		for (int i = 0; i < QTDUPGRADE; i++) {
			Upgrade upgrade = upgrades[i];
			buttons[i] = new JButton(String.format("<html>Upgrade<br />(%d processos)<html>", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttons[i];
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
					System.out.println(custo);
					long delay = TEMPO * upgrade.getPeriodo();
					time.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.acumular(1);
						}
					}, delay, delay);
					repeticoes++;
					telaUpgrade.setText("Upgrade: " + (this.i + 1));
					if (this.i == upgrades.length - 1) {
						buttons[this.i].setEnabled(false);
					} else {
						buttons[this.i].setVisible(false);
						buttons[this.i + 1].setVisible(true);
						this.i++;
					}
					if (cont == 10) {
						clicker.levelUp();
						labelLevel.setText("Level: " + clicker.getLevel());
						cont = 0;
						JOptionPane.showMessageDialog(null, "Level Up");
					}
					cont++;
				} else
					JOptionPane.showMessageDialog(null, "Você não possui processos suficiente");
			});
		}
		buttons[0].setVisible(true);
		
		JButton[] buttons = new JButton[QTDUPGRADE];
		for (int i = 0; i < QTDUPGRADE; i++) {
			Upgrade upgrade = upgrades[i];
			buttons[i] = new JButton(String.format("<html>Upgrade<br />(%d processos)<html>", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttons[i];
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
					System.out.println(custo);
					long delay = TEMPO * upgrade.getPeriodo();
					time.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.acumular(1);
						}
					}, delay, delay);
					repeticoes++;
					telaUpgrade.setText("Upgrade: " + (this.i + 1));
					if (this.i == upgrades.length - 1) {
						buttons[this.i].setEnabled(false);
					} else {
						buttons[this.i].setVisible(false);
						buttons[this.i + 1].setVisible(true);
						this.i++;
					}
					if (cont == 10) {
						clicker.levelUp();
						labelLevel.setText("Level: " + clicker.getLevel());
						cont = 0;
						JOptionPane.showMessageDialog(null, "Level Up");
					}
					cont++;
				} else
					JOptionPane.showMessageDialog(null, "Você não possui processos suficiente");
			});
		}
		buttons[0].setVisible(true);
		
		JButton[] buttons = new JButton[QTDUPGRADE];
		for (int i = 0; i < QTDUPGRADE; i++) {
			Upgrade upgrade = upgrades[i];
			buttons[i] = new JButton(String.format("<html>Upgrade<br />(%d processos)<html>", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttons[i];
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
					System.out.println(custo);
					long delay = TEMPO * upgrade.getPeriodo();
					time.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.acumular(1);
						}
					}, delay, delay);
					repeticoes++;
					telaUpgrade.setText("Upgrade: " + (this.i + 1));
					if (this.i == upgrades.length - 1) {
						buttons[this.i].setEnabled(false);
					} else {
						buttons[this.i].setVisible(false);
						buttons[this.i + 1].setVisible(true);
						this.i++;
					}
					if (cont == 10) {
						clicker.levelUp();
						labelLevel.setText("Level: " + clicker.getLevel());
						cont = 0;
						JOptionPane.showMessageDialog(null, "Level Up");
					}
					cont++;
				} else
					JOptionPane.showMessageDialog(null, "Você não possui processos suficiente");
			});
		}
		buttons[0].setVisible(true);
		
		JButton[] buttons = new JButton[QTDUPGRADE];
		for (int i = 0; i < QTDUPGRADE; i++) {
			Upgrade upgrade = upgrades[i];
			buttons[i] = new JButton(String.format("<html>Upgrade<br />(%d processos)<html>", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttons[i];
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
					System.out.println(custo);
					long delay = TEMPO * upgrade.getPeriodo();
					time.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.acumular(1);
						}
					}, delay, delay);
					repeticoes++;
					telaUpgrade.setText("Upgrade: " + (this.i + 1));
					if (this.i == upgrades.length - 1) {
						buttons[this.i].setEnabled(false);
					} else {
						buttons[this.i].setVisible(false);
						buttons[this.i + 1].setVisible(true);
						this.i++;
					}
					if (cont == 10) {
						clicker.levelUp();
						labelLevel.setText("Level: " + clicker.getLevel());
						cont = 0;
						JOptionPane.showMessageDialog(null, "Level Up");
					}
					cont++;
				} else
					JOptionPane.showMessageDialog(null, "Você não possui processos suficiente");
			});
		}
		buttons[0].setVisible(true);
		
		JButton[] buttons = new JButton[QTDUPGRADE];
		for (int i = 0; i < QTDUPGRADE; i++) {
			Upgrade upgrade = upgrades[i];
			buttons[i] = new JButton(String.format("<html>Upgrade<br />(%d processos)<html>", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttons[i];
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
					System.out.println(custo);
					long delay = TEMPO * upgrade.getPeriodo();
					time.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.acumular(1);
						}
					}, delay, delay);
					repeticoes++;
					telaUpgrade.setText("Upgrade: " + (this.i + 1));
					if (this.i == upgrades.length - 1) {
						buttons[this.i].setEnabled(false);
					} else {
						buttons[this.i].setVisible(false);
						buttons[this.i + 1].setVisible(true);
						this.i++;
					}
					if (cont == 10) {
						clicker.levelUp();
						labelLevel.setText("Level: " + clicker.getLevel());
						cont = 0;
						JOptionPane.showMessageDialog(null, "Level Up");
					}
					cont++;
				} else
					JOptionPane.showMessageDialog(null, "Você não possui processos suficiente");
			});
		}
		buttons[0].setVisible(true);
		
		JButton[] buttons = new JButton[QTDUPGRADE];
		for (int i = 0; i < QTDUPGRADE; i++) {
			Upgrade upgrade = upgrades[i];
			buttons[i] = new JButton(String.format("<html>Upgrade<br />(%d processos)<html>", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttons[i];
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
					System.out.println(custo);
					long delay = TEMPO * upgrade.getPeriodo();
					time.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.acumular(1);
						}
					}, delay, delay);
					repeticoes++;
					telaUpgrade.setText("Upgrade: " + (this.i + 1));
					if (this.i == upgrades.length - 1) {
						buttons[this.i].setEnabled(false);
					} else {
						buttons[this.i].setVisible(false);
						buttons[this.i + 1].setVisible(true);
						this.i++;
					}
					if (cont == 10) {
						clicker.levelUp();
						labelLevel.setText("Level: " + clicker.getLevel());
						cont = 0;
						JOptionPane.showMessageDialog(null, "Level Up");
					}
					cont++;
				} else
					JOptionPane.showMessageDialog(null, "Você não possui processos suficiente");
			});
		}
		buttons[0].setVisible(true);
		
		JButton[] buttons = new JButton[QTDUPGRADE];
		for (int i = 0; i < QTDUPGRADE; i++) {
			Upgrade upgrade = upgrades[i];
			buttons[i] = new JButton(String.format("<html>Upgrade<br />(%d processos)<html>", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttons[i];
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
					System.out.println(custo);
					long delay = TEMPO * upgrade.getPeriodo();
					time.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.acumular(1);
						}
					}, delay, delay);
					repeticoes++;
					telaUpgrade.setText("Upgrade: " + (this.i + 1));
					if (this.i == upgrades.length - 1) {
						buttons[this.i].setEnabled(false);
					} else {
						buttons[this.i].setVisible(false);
						buttons[this.i + 1].setVisible(true);
						this.i++;
					}
					if (cont == 10) {
						clicker.levelUp();
						labelLevel.setText("Level: " + clicker.getLevel());
						cont = 0;
						JOptionPane.showMessageDialog(null, "Level Up");
					}
					cont++;
				} else
					JOptionPane.showMessageDialog(null, "Você não possui processos suficiente");
			});
		}
		buttons[0].setVisible(true);
		
		JButton[] buttons = new JButton[QTDUPGRADE];
		for (int i = 0; i < QTDUPGRADE; i++) {
			Upgrade upgrade = upgrades[i];
			buttons[i] = new JButton(String.format("<html>Upgrade<br />(%d processos)<html>", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttons[i];
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
					System.out.println(custo);
					long delay = TEMPO * upgrade.getPeriodo();
					time.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.acumular(1);
						}
					}, delay, delay);
					repeticoes++;
					telaUpgrade.setText("Upgrade: " + (this.i + 1));
					if (this.i == upgrades.length - 1) {
						buttons[this.i].setEnabled(false);
					} else {
						buttons[this.i].setVisible(false);
						buttons[this.i + 1].setVisible(true);
						this.i++;
					}
					if (cont == 10) {
						clicker.levelUp();
						labelLevel.setText("Level: " + clicker.getLevel());
						cont = 0;
						JOptionPane.showMessageDialog(null, "Level Up");
					}
					cont++;
				} else
					JOptionPane.showMessageDialog(null, "Você não possui processos suficiente");
			});
		}
		buttons[0].setVisible(true);
		
		JButton[] buttonsArtigo = new JButton[QTDUPGRADE];
		for (int i = 0; i < QTDUPGRADE; i++) {
			Upgrade upgrade = upgrades[i];
			buttonsArtigo[i] = new JButton(String.format("<html>Alunos fazendo artigo<br />(%d artigos)<html>", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttons[i];
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
					System.out.println(custo);
					long delay = TEMPO * upgrade.getPeriodo();
					time.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.acumular(1);
						}
					}, delay, delay);
					repeticoes++;
					telaUpgrade.setText("Upgrade: " + (this.i + 1));
					if (this.i == upgrades.length - 1) {
						buttonsArtigo[this.i].setEnabled(false);
					} else {
						buttonsArtigo[this.i].setVisible(false);
						buttonsArtigo[this.i + 1].setVisible(true);
						this.i++;
					}
					if (cont == 10) {
						clicker.levelUp();
						labelLevel.setText("Level: " + clicker.getLevel());
						cont = 0;
						JOptionPane.showMessageDialog(null, "Level Up");
					}
					cont++;
				} else
					JOptionPane.showMessageDialog(null, "Você não possui processos suficiente");
			});
		}
		buttonsArtigo[0].setVisible(true);
		
		JButton[] buttonsGifs = new JButton[QTDUPGRADE];
		for (int i = 0; i < QTDUPGRADE; i++) {
			Upgrade upgrade = upgrades[i];
			buttonsGifs[i] = new JButton(String.format("<html>Upgrade<br />(%d processos)<html>", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttonsGifs[i];
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
					System.out.println(custo);
					long delay = TEMPO * upgrade.getPeriodo();
					time.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.acumular(1);
						}
					}, delay, delay);
					repeticoes++;
					telaUpgrade.setText("Upgrade: " + (this.i + 1));
					if (this.i == upgrades.length - 1) {
						buttonsGifs[this.i].setEnabled(false);
					} else {
						buttonsGifs[this.i].setVisible(false);
						buttonsGifs[this.i + 1].setVisible(true);
						this.i++;
					}
					if (cont == 10) {
						clicker.levelUp();
						labelLevel.setText("Level: " + clicker.getLevel());
						cont = 0;
						JOptionPane.showMessageDialog(null, "Level Up");
					}
					cont++;
				} else
					JOptionPane.showMessageDialog(null, "Você não possui processos suficiente");
			});
		}
		buttonsGifs[0].setVisible(true);
		
		JButton[] buttonsPedia = new JButton[QTDUPGRADE];
		for (int i = 0; i < QTDUPGRADE; i++) {
			Upgrade upgrade = upgrades[i];
			buttonsPedia[i] = new JButton(String.format("<html>Upgrade<br />(%d processos)<html>", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttonsPedia[i];
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
					System.out.println(custo);
					long delay = TEMPO * upgrade.getPeriodo();
					time.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.acumular(1);
						}
					}, delay, delay);
					repeticoes++;
					telaUpgrade.setText("Upgrade: " + (this.i + 1));
					if (this.i == upgrades.length - 1) {
						buttonsPedia[this.i].setEnabled(false);
					} else {
						buttonsPedia[this.i].setVisible(false);
						buttonsPedia[this.i + 1].setVisible(true);
						this.i++;
					}
					if (cont == 10) {
						clicker.levelUp();
						labelLevel.setText("Level: " + clicker.getLevel());
						cont = 0;
						JOptionPane.showMessageDialog(null, "Level Up");
					}
					cont++;
				} else
					JOptionPane.showMessageDialog(null, "Você não possui processos suficiente");
			});
		}
		buttonsPedia[0].setVisible(true);
		
//		Alunos fazendo artigo
//		Alunos fazendo gifs
//		CBW ajeitando o wikipedia
//		Processos
//		Colocando rede na sala de redes
//		modem
//		Trocar patente do modem por moto
//		trocar faculdade(nivel): UFSC, USP, UNAM, Harvard, EMIT, Global, Via Lactea, Universo.
//		Torturar com historias
		
		JButton[] buttonsClick = new JButton[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			Upgrade upgrade = upgrades[j];
			buttonsClick[j] = new JButton(String.format("<html>Upgrade<br /> (%d processos)<html>", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgradeClick = buttonsClick[j];
			btnUpgradeClick.setSize(100, 100);
			btnUpgradeClick.setVisible(false);
			btnUpgradeClick.setAlignmentX(LEFT_ALIGNMENT);
			clickUpgradePanel.add(btnUpgradeClick);
			btnUpgradeClick.addActionListener((e) -> {
				int custo = custoPadrao * upgrade.getCusto();
				if (clicker.getAcumulador() >= custo) {
					btnUpgradeClick.setEnabled(true);
					multiplicador++;
					upgradeClick.setText("Upgrade Click :" + (multiplicador - 1));
					clicker.setAcumulador(clicker.getAcumulador() - custo);
					telaAcumulador.setText("Processos: " + clicker.getAcumulador());
					if (this.j == upgrades.length - 1) {
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
				telaAcumulador.setText("Processos: " + clicker.getAcumulador());
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

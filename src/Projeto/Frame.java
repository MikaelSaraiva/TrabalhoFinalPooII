package Projeto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Clicker clicker;
	private JLabel accumulatorLabel;
	private JLabel levelLabel;
	private JLabel upgradeLabel;
	private JLabel upgradeClick;
	private JLabel dpsLabel;
	private JLabel pacitoLabel;
	private JLabel artigosLabel;
	private JLabel pediaLabel;
	private JLabel gifsLabel;
	private JLabel historiaLabel;
	private JLabel modemLabel;
	private JLabel processosLabel;
	private JLabel salaLabel;
	private JButton buttonAccumulator;
	private fibonacci fibo;
	private Upgrade upgrade;
	private Upgrade[] upgradesClick;
	private int pacito = 0;
	private int artigo = 0;
	private int click = 0;
	private int gifs = 0;
	private int pedia = 0;
	private int processos = 0;
	private int sala = 0;
	private int modem = 0;
	private int historia = 0;
	private int q = 0;
	private int upgradeLevel = 1;
	private int custoPadrao = 1;
	private float dps = 0;
	private final int QTDUPGRADE = 10;
	private final int TEMPO = 1;
	private final int ACHIEVEMENT = 7;
	private final String[] TEXTACHI = { "Trocar modem/moto", "horas jogadas",
			"quantidade de cadeiras puladas",
			"nivel hard(ir pra fraça sem upgrade)", "Sem trapacear",
			"Nada de molesa", "Camiseta Confortavel", };

	public Frame() {
		super("Super Jumper");

		fibo = new fibonacci();
		clicker = new Clicker();
		upgrade = new Upgrade();

		JPanel upgradePanel = new JPanel();
		JPanel clickUpgradePanel = new JPanel();
		JPanel sideUpPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel dataPanel = new JPanel();
		JPanel resetUpgradesPanel = new JPanel();
		JPanel achievementPanel = new JPanel();
		JPanel achievementSideUpPanel = new JPanel();
		JPanel achievementSideDownPanel = new JPanel();
		JButton[] buttonAchievement;
		JMenu menu = new JMenu("File");
		JMenu achievementMenu = new JMenu("Conquistas");
		JMenuBar bar = new JMenuBar();
		JMenuItem exit = new JMenuItem("Sair");
		JMenuItem save = new JMenuItem("Salvar");
		JMenuItem changeUser = new JMenuItem("Mudar Usuario");
		JMenuItem about = new JMenuItem("Sobre o Jogo");
		JMenuItem itemAchievement = new JMenuItem("Conquistas");
		JDialog dialogAchievement = new JDialog(this, "Conquistas", true);

		DecimalFormat apsFormat = new DecimalFormat("0.00");

		menu.add(changeUser);
		menu.add(save);
		menu.add(about);
		menu.add(exit);

		dialogAchievement.add(achievementPanel);

		achievementSideUpPanel.setSize(400, 100);
		achievementSideDownPanel.setSize(400, 300);

		achievementPanel.add(achievementSideUpPanel);
		achievementPanel.add(achievementSideDownPanel);

		achievementSideUpPanel.setBackground(Color.YELLOW);
		achievementSideDownPanel.setBackground(Color.BLUE);

		achievementMenu.add(itemAchievement);

		setJMenuBar(bar);
		bar.add(menu);
		bar.add(achievementMenu);

		itemAchievement.addActionListener((e) -> {
			dialogAchievement.setSize(400, 400);
			dialogAchievement.setLocationRelativeTo(dialogAchievement);
			dialogAchievement.setVisible(true);
		});

		exit.addActionListener((e) -> {
			// TODO Ação do menu "Sair"
			System.exit(0);
		});

		about.addActionListener((e) -> {
			JOptionPane.showMessageDialog(null,
					"Jogo no estilo Clicker baseado na necessidade de pular cadeiras. Desenvolvido por Bóreas e Marki Cast ,\n " +
							"o jogo apresenta nosso personagem principal, César Barrichello Wessig, pulando cadeiras e desenvolvendo artigos \n " +
							"para ajudar a humanidade com seu  conhecimento imensurável. Enquanto mais artigos publicados, mais poderosa fica \n" +
							"sua mente e mais longe o personagem pode chegar.",
					"Sobre o jogo", JOptionPane.PLAIN_MESSAGE);

		});

		JScrollPane upgradeScrollPane = new JScrollPane(upgradePanel);
		JScrollPane resetScrollPane = new JScrollPane(resetUpgradesPanel);

		upgradeScrollPane.setBackground(Color.RED);
		upgradeScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		resetScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		upgradePanel.setLayout(null);
		upgradePanel.setPreferredSize(new Dimension(510, 8 * 50));
		// resetUpgrades.setPreferredSize(new Dimension(510, 8 * 50));
		clickUpgradePanel.setLayout(
				new BoxLayout(clickUpgradePanel, BoxLayout.PAGE_AXIS));
		dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.PAGE_AXIS));
		resetUpgradesPanel.setLayout(null);
		sideUpPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.setLayout(new GridLayout(1, 1));
		achievementPanel.setLayout(new GridLayout(2, 1));
		achievementSideUpPanel.setLayout(new FlowLayout());
		achievementSideDownPanel.setLayout(new GridLayout(3, 2, 5, 5));

		upgradePanel.setBackground(Color.BLACK);
		sideUpPanel.setBackground(Color.WHITE);
		clickUpgradePanel.setBackground(Color.BLACK);
		resetUpgradesPanel.setBackground(Color.BLACK);

		sideUpPanel.add(dataPanel);
		sideUpPanel.add(buttonPanel);

		setLayout(new GridLayout(2, 1));
		add(sideUpPanel);

		JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP,
				JTabbedPane.SCROLL_TAB_LAYOUT);
		tabs.addTab("Upgrade Auto-Click", upgradeScrollPane);
		tabs.addTab("Upgrade Click", clickUpgradePanel);
		tabs.addTab("Upgrade Reset", resetScrollPane);
		add(tabs);

		accumulatorLabel = new JLabel("Artigos: " + clicker.getAccumulate());
		accumulatorLabel.setAlignmentX(LEFT_ALIGNMENT);
		accumulatorLabel.setAlignmentY(TOP_ALIGNMENT);
		dataPanel.add(accumulatorLabel);

		upgradeClick = new JLabel("Upgrade Click: 0");
		upgradeClick.setAlignmentX(LEFT_ALIGNMENT);
		upgradeClick.setAlignmentY(TOP_ALIGNMENT);
		dataPanel.add(upgradeClick);

		levelLabel = new JLabel("Level: " + clicker.getLevel());
		levelLabel.setAlignmentX(LEFT_ALIGNMENT);
		levelLabel.setAlignmentY(TOP_ALIGNMENT);
		dataPanel.add(levelLabel);

		upgradeLabel = new JLabel("Upgrade: " + (upgradeLevel - 1));
		upgradeLabel.setAlignmentX(LEFT_ALIGNMENT);
		upgradeLabel.setAlignmentY(TOP_ALIGNMENT);
		dataPanel.add(upgradeLabel);

		dpsLabel = new JLabel(
				"APS(Artigos per second): " + apsFormat.format(dps));
		dataPanel.add(dpsLabel);

		// TODO Inicio da criação das labels de upgrade de Auto-click

		int altura = 40;
		int largura = 375;
		int espacamento = altura + 10;

		pacitoLabel = new JLabel(
				"<html>Aprendendo a cria artigos: Porque todos sabem que para se fazer um é pacito a pacito<html>");
		pacitoLabel.setIcon(
				new ImageIcon(this.getClass().getResource("branco.jpg")));
		pacitoLabel.setHorizontalTextPosition(JLabel.CENTER);
		pacitoLabel.setBounds(0, 0, largura, altura);
		upgradePanel.add(pacitoLabel);

		artigosLabel = new JLabel(
				"<html>Artigos: Alunos melhorando o seu futuro, mas acabando com sua sanidade mental e paciência<html>");
		artigosLabel.setIcon(
				new ImageIcon(this.getClass().getResource("branco.jpg")));
		artigosLabel.setHorizontalTextPosition(JLabel.CENTER);
		artigosLabel.setBounds(0, espacamento, largura, altura);
		upgradePanel.add(artigosLabel);

		gifsLabel = new JLabel(
				"<html>Gifs: Alunos usando hack e copiando os gifs dos veteranos, mas não fazendo exatamente igual<html>");
		gifsLabel.setIcon(
				new ImageIcon(this.getClass().getResource("branco.jpg")));
		gifsLabel.setHorizontalTextPosition(JLabel.CENTER);
		gifsLabel.setBounds(0, espacamento * 2, largura, altura);
		upgradePanel.add(gifsLabel);

		pediaLabel = new JLabel(
				"<html>ArtigoPédia: Atualizando e verificando se não foi derrubado novamente<html>");
		pediaLabel.setIcon(
				new ImageIcon(this.getClass().getResource("branco.jpg")));
		pediaLabel.setHorizontalTextPosition(JLabel.CENTER);
		pediaLabel.setBounds(0, espacamento * 3, largura, altura);
		upgradePanel.add(pediaLabel);

		processosLabel = new JLabel(
				"<html>Processos: Aprendendo como  e quando processar com o melhor no ramo<html>");
		processosLabel.setIcon(
				new ImageIcon(this.getClass().getResource("branco.jpg")));
		processosLabel.setHorizontalTextPosition(JLabel.CENTER);
		processosLabel.setBounds(0, espacamento * 4, largura, altura);
		upgradePanel.add(processosLabel);

		salaLabel = new JLabel(
				"<html>Alunos instalando rede: Aprendendo intalar rede na sala<html>");
		salaLabel.setIcon(
				new ImageIcon(this.getClass().getResource("branco.jpg")));
		salaLabel.setHorizontalTextPosition(JLabel.CENTER);
		salaLabel.setBounds(0, espacamento * 5, largura, altura);
		upgradePanel.add(salaLabel);

		modemLabel = new JLabel(
				"<html>Modem: Alunos aprendendo mais sobrea criação e seu funcionamento além de seu criador<html>");
		modemLabel.setIcon(
				new ImageIcon(this.getClass().getResource("branco.jpg")));
		modemLabel.setHorizontalTextPosition(JLabel.CENTER);
		modemLabel.setBounds(0, espacamento * 6, largura, altura);
		upgradePanel.add(modemLabel);

		historiaLabel = new JLabel(
				"<html>Alunos ouvindo historia: Surpreendentes e sem contesto e hora, mas o lugar vai ser a sala de aula<html>");
		historiaLabel.setIcon(
				new ImageIcon(this.getClass().getResource("branco.jpg")));
		historiaLabel.setHorizontalTextPosition(JLabel.CENTER);
		historiaLabel.setBounds(0, espacamento * 7, largura, altura);
		upgradePanel.add(historiaLabel);

		// TODO Fim da criação das labels de upgrade de Auto-click

		buttonAccumulator = new JButton("Clique para pular mais");
		buttonPanel.add(buttonAccumulator);
		buttonAccumulator.setAlignmentY(TOP_ALIGNMENT);
		buttonAccumulator.setOpaque(false);
		buttonAccumulator.setContentAreaFilled(false);
		buttonAccumulator.setBorderPainted(false);
		buttonAccumulator.addActionListener((e) -> {
			// TODO Ação botão acumulador
			clicker.accumulate(upgrade.getMulti());
		});

		buttonAchievement = new JButton[ACHIEVEMENT];
		for (int a = 0; a < ACHIEVEMENT; a++) {
			buttonAchievement[a] = new JButton(TEXTACHI[a]);
			JButton achievementButton = buttonAchievement[a];
			achievementButton.setEnabled(false);
			achievementSideDownPanel.add(achievementButton);
		}

		// TODO Inicio da criação dos botão de upgrade de Auto-click

		// TODO Botão Pacito
		Upgrade[] upgradesPacito = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesPacito[j] = new Upgrade(clicker, j + 1, 10000);
		}

		Timer timePacito = new Timer();
		JButton[] buttonsPacito = new JButton[QTDUPGRADE];
		for (int i = 0; i < QTDUPGRADE; i++) {
			Upgrade upgrade = upgradesPacito[i];
			buttonsPacito[i] = new JButton(String.format("%d artigos",
					(custoPadrao * upgrade.getCost())));
			JButton btnUpgrade = buttonsPacito[i];
			btnUpgrade.setBounds(410, 0, 100, altura);
			btnUpgrade.setHorizontalAlignment(SwingConstants.RIGHT);
			btnUpgrade.setVisible(false);
			upgradePanel.add(btnUpgrade);
			btnUpgrade.addActionListener((e) -> {
				int custo = custoPadrao * upgrade.getCost();
				if (clicker.getAccumulate() >= custo) {
					btnUpgrade.setEnabled(true);
					clicker.setAccumulate(clicker.getAccumulate() - custo);
					long delay = TEMPO * upgrade.getPeriod();
					timePacito.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.accumulate(1);
						}
					}, delay, delay);
					dps += 0.1;
					upgradeLabel.setText("Upgrade: " + (upgradeLevel++));
					if (this.pacito == upgradesPacito.length - 1) {
						buttonsPacito[this.pacito].setEnabled(false);
					} else {
						buttonsPacito[this.pacito].setVisible(false);
						buttonsPacito[this.pacito + 1].setVisible(true);
						this.pacito++;
					}
				} else
					JOptionPane.showMessageDialog(null,
							"Você não possui artigos suficiente");
			});
		}
		buttonsPacito[0].setVisible(true);

		// TODO Botão Artigo
		Upgrade[] upgradesArtigo = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesArtigo[j] = new Upgrade(clicker, j + 1, 10000);
		}

		Timer timeArtigo = new Timer();
		JButton[] buttonsArtigo = new JButton[QTDUPGRADE];
		for (int i = 0; i < QTDUPGRADE; i++) {
			Upgrade upgrade = upgradesArtigo[i];
			buttonsArtigo[i] = new JButton(String.format("%d artigos",
					(custoPadrao * upgrade.getCost())));
			JButton btnUpgrade = buttonsArtigo[i];
			btnUpgrade.setBounds(410, espacamento, 100, altura);
			btnUpgrade.setHorizontalAlignment(SwingConstants.RIGHT);
			btnUpgrade.setVisible(false);
			upgradePanel.add(btnUpgrade);
			btnUpgrade.addActionListener((e) -> {
				int custo = custoPadrao * upgrade.getCost();
				if (clicker.getAccumulate() >= custo) {
					btnUpgrade.setEnabled(true);
					clicker.setAccumulate(clicker.getAccumulate() - custo);
					long delay = TEMPO * upgrade.getPeriod();
					timeArtigo.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.accumulate(1);
						}
					}, delay, delay);
					dps += 0.1;
					upgradeLabel.setText("Upgrade: " + (upgradeLevel++));
					if (this.artigo == upgradesArtigo.length - 1) {
						buttonsArtigo[this.artigo].setEnabled(false);
					} else {
						buttonsArtigo[this.artigo].setVisible(false);
						buttonsArtigo[this.artigo + 1].setVisible(true);
						this.artigo++;
					}
				} else
					JOptionPane.showMessageDialog(null,
							"Você não possui artigos suficiente");
			});
		}
		buttonsArtigo[0].setVisible(true);

		// TODO Botão Gifs
		Upgrade[] upgradesGifs = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesGifs[j] = new Upgrade(clicker, j + 1, 1000);
		}

		Timer timeGifs = new Timer();
		JButton[] buttonsGifs = new JButton[QTDUPGRADE];
		for (int k = 0; k < QTDUPGRADE; k++) {
			Upgrade upgrade = upgradesGifs[k];
			buttonsGifs[k] = new JButton(String.format("%d artigos",
					(custoPadrao * upgrade.getCost())));
			JButton btnUpgrade = buttonsGifs[k];
			btnUpgrade.setBounds(410, espacamento * 2, 100, 40);
			btnUpgrade.setVisible(false);
			btnUpgrade.setAlignmentX(LEFT_ALIGNMENT);
			upgradePanel.add(btnUpgrade);
			btnUpgrade.addActionListener((e) -> {
				int custo = custoPadrao * upgrade.getCost();
				if (clicker.getAccumulate() >= custo) {
					btnUpgrade.setEnabled(true);
					clicker.setAccumulate(clicker.getAccumulate() - custo);
					long delay = TEMPO * upgrade.getPeriod();
					timeGifs.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.accumulate(1);
						}
					}, delay, delay);
					dps += 1;
					upgradeLabel.setText("Upgrade: " + (upgradeLevel++));
					if (this.gifs == upgradesGifs.length - 1) {
						buttonsGifs[this.gifs].setEnabled(false);
					} else {
						buttonsGifs[this.gifs].setVisible(false);
						buttonsGifs[this.gifs + 1].setVisible(true);
						this.gifs++;
					}
				} else
					JOptionPane.showMessageDialog(null,
							"Você não possui artigos suficiente");
			});
		}
		buttonsGifs[0].setVisible(true);

		// TODO Botão ArtigoPédia
		Upgrade[] upgradesPedia = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesPedia[j] = new Upgrade(clicker, j + 1, 200);
		}

		Timer timePedia = new Timer();
		JButton[] buttonsPedia = new JButton[QTDUPGRADE];
		for (int l = 0; l < QTDUPGRADE; l++) {
			Upgrade upgrade = upgradesPedia[l];
			buttonsPedia[l] = new JButton(String.format("%d artigos",
					(custoPadrao * upgrade.getCost())));
			JButton btnUpgrade = buttonsPedia[l];
			btnUpgrade.setBounds(410, espacamento * 3, 100, altura);
			btnUpgrade.setVisible(false);
			btnUpgrade.setAlignmentX(LEFT_ALIGNMENT);
			upgradePanel.add(btnUpgrade);
			btnUpgrade.addActionListener((e) -> {
				int custo = custoPadrao * upgrade.getCost();
				if (clicker.getAccumulate() >= custo) {
					btnUpgrade.setEnabled(true);
					clicker.setAccumulate(clicker.getAccumulate() - custo);
					long delay = TEMPO * upgrade.getPeriod();
					timePedia.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.accumulate(1);
						}
					}, delay, delay);
					dps += 5;
					upgradeLabel.setText("Upgrade: " + (upgradeLevel++));
					if (this.pedia == upgradesPedia.length - 1) {
						buttonsPedia[this.pedia].setEnabled(false);
					} else {
						buttonsPedia[this.pedia].setVisible(false);
						buttonsPedia[this.pedia + 1].setVisible(true);
						this.pedia++;
					}
				} else
					JOptionPane.showMessageDialog(null,
							"Você não possui artigos suficiente");
			});
		}
		buttonsPedia[0].setVisible(true);

		// TODO Botão Processos
		Upgrade[] upgradesProcessos = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesProcessos[j] = new Upgrade(clicker, j + 1, 100);
		}

		Timer timeProcessos = new Timer();
		JButton[] buttonsProcessos = new JButton[QTDUPGRADE];
		for (int m = 0; m < QTDUPGRADE; m++) {
			Upgrade upgrade = upgradesProcessos[m];
			buttonsProcessos[m] = new JButton(String.format("%d artigos",
					(custoPadrao * upgrade.getCost())));
			JButton btnUpgrade = buttonsProcessos[m];
			btnUpgrade.setBounds(410, espacamento * 4, 100, altura);
			btnUpgrade.setVisible(false);
			btnUpgrade.setAlignmentX(LEFT_ALIGNMENT);
			upgradePanel.add(btnUpgrade);
			btnUpgrade.addActionListener((e) -> {
				int custo = custoPadrao * upgrade.getCost();
				if (clicker.getAccumulate() >= custo) {
					btnUpgrade.setEnabled(true);
					clicker.setAccumulate(clicker.getAccumulate() - custo);
					long delay = TEMPO * upgrade.getPeriod();
					timeProcessos.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.accumulate(1);
						}
					}, delay, delay);
					dps += 10;
					upgradeLabel.setText("Upgrade: " + (upgradeLevel++));
					if (this.processos == upgradesProcessos.length - 1) {
						buttonsProcessos[this.processos].setEnabled(false);
					} else {
						buttonsProcessos[this.processos].setVisible(false);
						buttonsProcessos[this.processos + 1].setVisible(true);
						this.processos++;
					}
				} else
					JOptionPane.showMessageDialog(null,
							"Você não possui artigos suficiente");
			});
		}
		buttonsProcessos[0].setVisible(true);

		// TODO Botão Cortina na Sala
		Upgrade[] upgradesSala = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesSala[j] = new Upgrade(clicker, j + 1, 50);
		}

		Timer timeSala = new Timer();
		JButton[] buttonsSala = new JButton[QTDUPGRADE];
		for (int n = 0; n < QTDUPGRADE; n++) {
			Upgrade upgrade = upgradesSala[n];
			buttonsSala[n] = new JButton(String.format("%d artigos",
					(custoPadrao * upgrade.getCost())));
			JButton btnUpgrade = buttonsSala[n];
			btnUpgrade.setBounds(410, espacamento * 5, 100, altura);
			btnUpgrade.setVisible(false);
			btnUpgrade.setAlignmentX(LEFT_ALIGNMENT);
			upgradePanel.add(btnUpgrade);
			btnUpgrade.addActionListener((e) -> {
				int custo = custoPadrao * upgrade.getCost();
				if (clicker.getAccumulate() >= custo) {
					btnUpgrade.setEnabled(true);
					clicker.setAccumulate(clicker.getAccumulate() - custo);
					long delay = TEMPO * upgrade.getPeriod();
					timeSala.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.accumulate(1);
						}
					}, delay, delay);
					dps += 20;
					upgradeLabel.setText("Upgrade: " + (upgradeLevel++));
					if (this.sala == upgradesSala.length - 1) {
						buttonsSala[this.sala].setEnabled(false);
					} else {
						buttonsSala[this.sala].setVisible(false);
						buttonsSala[this.sala + 1].setVisible(true);
						this.sala++;
					}
				} else
					JOptionPane.showMessageDialog(null,
							"Você não possui artigos suficiente");
			});
		}
		buttonsSala[0].setVisible(true);

		// TODO Botão Modem
		Upgrade[] upgradesModem = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesModem[j] = new Upgrade(clicker, j + 1, 33);
		}

		Timer timeModem = new Timer();
		JButton[] buttonsModem = new JButton[QTDUPGRADE];
		for (int o = 0; o < QTDUPGRADE; o++) {
			Upgrade upgrade = upgradesModem[o];
			buttonsModem[o] = new JButton(String.format("%d artigos",
					(custoPadrao * upgrade.getCost())));
			JButton btnUpgrade = buttonsModem[o];
			btnUpgrade.setBounds(410, espacamento * 6, 100, altura);
			btnUpgrade.setVisible(false);
			btnUpgrade.setAlignmentX(LEFT_ALIGNMENT);
			upgradePanel.add(btnUpgrade);
			btnUpgrade.addActionListener((e) -> {
				int custo = custoPadrao * upgrade.getCost();
				if (clicker.getAccumulate() >= custo) {
					btnUpgrade.setEnabled(true);
					clicker.setAccumulate(clicker.getAccumulate() - custo);
					long delay = TEMPO * upgrade.getPeriod();
					timeModem.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.accumulate(1);
						}
					}, delay, delay);
					dps += 30;
					upgradeLabel.setText("Upgrade: " + (upgradeLevel++));
					if (this.modem == upgradesModem.length - 1) {
						buttonsModem[this.modem].setEnabled(false);
					} else {
						buttonsModem[this.modem].setVisible(false);
						buttonsModem[this.modem + 1].setVisible(true);
						this.modem++;
					}
				} else
					JOptionPane.showMessageDialog(null,
							"Você não possui artigos suficiente");
			});
		}
		buttonsModem[0].setVisible(true);

		// TODO Botão História
		Upgrade[] upgradesHistoria = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesHistoria[j] = new Upgrade(clicker, j + 1, 25);
		}

		Timer timeHistoria = new Timer();
		JButton[] buttonsHistoria = new JButton[QTDUPGRADE];
		for (int p = 0; p < QTDUPGRADE; p++) {
			Upgrade upgrade = upgradesHistoria[p];
			buttonsHistoria[p] = new JButton(String.format("%d artigos",
					(custoPadrao * upgrade.getCost())));
			JButton btnUpgrade = buttonsHistoria[p];
			btnUpgrade.setBounds(410, espacamento * 7, 100, altura);
			btnUpgrade.setVisible(false);
			btnUpgrade.setAlignmentX(LEFT_ALIGNMENT);
			upgradePanel.add(btnUpgrade);
			btnUpgrade.addActionListener((e) -> {
				int custo = custoPadrao * upgrade.getCost();
				if (clicker.getAccumulate() >= custo) {
					btnUpgrade.setEnabled(true);
					clicker.setAccumulate(clicker.getAccumulate() - custo);
					long delay = TEMPO * upgrade.getPeriod();
					timeHistoria.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {
							clicker.accumulate(1);
						}
					}, delay, delay);
					dps += 40;
					upgradeLabel.setText("Upgrade: " + (upgradeLevel++));
					if (this.historia == upgradesHistoria.length - 1) {
						buttonsHistoria[this.historia].setEnabled(false);
					} else {
						buttonsHistoria[this.historia].setVisible(false);
						buttonsHistoria[this.historia + 1].setVisible(true);
						this.historia++;
					}
				} else
					JOptionPane.showMessageDialog(null,
							"Você não possui artigos suficiente");
			});
		}
		buttonsHistoria[0].setVisible(true);

		// TODO Fim da criação dos botão de upgrade de Auto-click

		upgradesClick = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesClick[j] = new Upgrade(clicker, j + 1, 1000);
		}

		// TODO Botão aumentar quantidade de cadeiras
		JButton[] buttonsClick = new JButton[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			Upgrade upgradeMulti = upgradesClick[j];
			buttonsClick[j] = new JButton(String.format(
					"Aumentar quantidade de cadeiras (%d processos)",
					(custoPadrao * upgradeMulti.getCost())));
			JButton btnUpgradeClick = buttonsClick[j];
			btnUpgradeClick.setSize(100, 100);
			btnUpgradeClick.setVisible(false);
			btnUpgradeClick.setAlignmentX(LEFT_ALIGNMENT);
			clickUpgradePanel.add(btnUpgradeClick);
			btnUpgradeClick.addActionListener((e) -> {
				int custo = custoPadrao * upgradeMulti.getCost();
				if (clicker.getAccumulate() >= custo) {
					btnUpgradeClick.setEnabled(true);
					upgrade.upgradeClick();
					upgradeClick.setText(
							"Upgrade Click: " + (upgrade.getMulti() - 1));
					clicker.setAccumulate(clicker.getAccumulate() - custo);
					accumulatorLabel
							.setText("Artigos: " + clicker.getAccumulate());
					if (this.click == upgradesClick.length - 1) {
						buttonsClick[this.click].setEnabled(false);
					} else {
						buttonsClick[this.click].setVisible(false);
						buttonsClick[this.click + 1].setVisible(true);
						this.click++;
					}
				} else
					JOptionPane.showMessageDialog(null,
							"Você não possui processos suficiente");
			});
		}
		buttonsClick[0].setVisible(true);

		JButton reset = new JButton("Reset");
		reset.setBounds(410, 0, 100, altura);
		reset.setVisible(true);
		resetUpgradesPanel.add(reset);
		reset.addActionListener((e) -> {
			if (clicker.getAccumulate() > 100 ) {
				timeHistoria.cancel();
				timeArtigo.cancel();
				timeGifs.cancel();
				timeModem.cancel();
				timePacito.cancel();
				timePedia.cancel();
				timeProcessos.cancel();
				timeSala.cancel();
				clicker.setAccumulate(0);
				clicker.setLevel(0);
				upgrade.setMulti(1);
				JOptionPane.showMessageDialog(null,"Reset realizado com sucesso");
//				for (JButton btn : buttonsClick) {
//					btn.setEnabled(false);
//					btn.setVisible(false);
//				}
				for (JButton btn : buttonsArtigo) {
					btn.setEnabled(false);
					btn.setVisible(false);
				}
				for (JButton btn : buttonsGifs) {
					btn.setEnabled(false);
					btn.setVisible(false);
				}
				for (JButton btn : buttonsHistoria) {
					btn.setEnabled(false);
					btn.setVisible(false);
				}
				for (JButton btn : buttonsModem) {
					btn.setEnabled(false);
					btn.setVisible(false);
				}
				for (JButton btn : buttonsPacito) {
					btn.setEnabled(false);
					btn.setVisible(false);
				}
				for (JButton btn : buttonsPedia) {
					btn.setEnabled(false);
					btn.setVisible(false);
				}
				for (JButton btn : buttonsProcessos) {
					btn.setEnabled(false);
					btn.setVisible(false);
				}
				for (JButton btn : buttonsSala) {
					btn.setEnabled(false);
					btn.setVisible(false);
				}
// TODO Separação

//				for (JButton btn : buttonsClick) {
//					btn.setEnabled(true);
//					btn.setVisible(true);
//				}
				for (JButton btn : buttonsArtigo) {
					btn.setEnabled(true);
					btn.setVisible(true);
				}
				for (JButton btn : buttonsGifs) {
					btn.setEnabled(true);
					btn.setVisible(true);
				}
				for (JButton btn : buttonsHistoria) {
					btn.setEnabled(true);
					btn.setVisible(true);
				}
				for (JButton btn : buttonsModem) {
					btn.setEnabled(true);
					btn.setVisible(true);
				}
				for (JButton btn : buttonsPacito) {
					btn.setEnabled(true);
					btn.setVisible(true);
				}
				for (JButton btn : buttonsPedia) {
					btn.setEnabled(true);
					btn.setVisible(true);
				}
				for (JButton btn : buttonsProcessos) {
					btn.setEnabled(true);
					btn.setVisible(true);
				}
				for (JButton btn : buttonsSala) {
					btn.setEnabled(true);
					btn.setVisible(true);
				}
				buttonsClick[0].setEnabled(true);
				buttonsClick[0].setVisible(true);
			} else
				JOptionPane.showMessageDialog(null,
						"Falta um pouco, faça mais artigos para poder resetar");
		});

		// TODO Para ficar sempre atualizando o texto...
		Timer timeUpdater = new Timer();
		timeUpdater.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				accumulatorLabel.setText("Artigos: " + clicker.getAccumulate());
				dpsLabel.setText(
						"APS(Artigos per second): " + apsFormat.format(dps));
				if (clicker.getAccumulate() >= 10000 + (q * 20000)) {
					levelLabel.setText(
							"Level: " + clicker.nivel(clicker.getAccumulate()));
					q++;
				}
				if (clicker.getAccumulate() > 1000) {
					int acc = clicker.getAccumulate();
					accumulatorLabel.setText("Artigos: " +
							(acc / 1000) +
							"," +
							((acc / 100) % 10) +
							"K");
					if (clicker.getAccumulate() > 100000) {
						accumulatorLabel.setText("Artigos: " +
								(acc / 1000) +
								"," +
								((acc / 100) % 10) +
								"KK");

					}
				}

			}
		}, 0, TEMPO * 100);

		// multCompras1 = new JButton("1x");
		// multCompras1.setEnabled(false);
		// multCompras1.setSize(10, 10);
		// dadosPanel.add(multCompras1);
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
		// dadosPanel.add(multCompras10);
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
		// dadosPanel.add(multCompras100);
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
		//
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

package Projeto;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.Icon;
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

	private Clicker clicker;
	private JLabel telaAcumulador;
	private JLabel labelLevel;
	private JLabel telaUpgrade;
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
	private JButton botaoAcumulador;
	private fibonacci fibo;
	private Upgrade upgrade;
	private Upgrade[] upgradesClick;
	private final int QTDUPGRADE = 10;
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
	private float dps = 0;
	private int upgradeLevel = 1;
	private int custoPadrao = 1;
	private final int TEMPO = 1;
	private final int ACHIEVEMENT = 7;
	private final String[] TEXTACHI = { "Trocar modem/moto", "horas jogadas", "quantidade de cadeiras puladas",
			"nivel hard(ir pra fraça sem upgrade)", "Sem trapacear", "Nada de molesa", "Camiseta Confortavel" };

	public Frame() {
		super("Super Jumper");

		fibo = new fibonacci();
		clicker = new Clicker();
		upgrade = new Upgrade();

		JPanel upgradePanel = new JPanel();
		JPanel clickUpgradePanel = new JPanel();
		JPanel upPanel = new JPanel();
		JPanel botaoPanel = new JPanel();
		JPanel dadosPanel = new JPanel();
		JPanel resetUpgrades = new JPanel();
		JPanel panelAchievement = new JPanel();
		JPanel upAchievement = new JPanel();
		JPanel downAchievement = new JPanel();
		JButton[] buttonAchievement;
		JMenu menu = new JMenu("File");
		JMenu achievement = new JMenu("Conquistas");
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

		dialogAchievement.add(panelAchievement);

		upAchievement.setSize(400, 100);
		downAchievement.setSize(400, 300);

		panelAchievement.add(upAchievement);
		panelAchievement.add(downAchievement);

		upAchievement.setBackground(Color.YELLOW);
		downAchievement.setBackground(Color.BLUE);

		achievement.add(itemAchievement);

		setJMenuBar(bar);
		bar.add(menu);
		bar.add(achievement);

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
					"Jogo no estilo Clicker baseado na necessidade de pular cadeiras. Desenvolvido por Bóreas e Marki Cast ,\n "
							+ "o jogo apresenta nosso personagem principal, César Barrichello Wessig, pulando cadeiras e desenvolvendo artigos \n "
							+ "para ajudar a humanidade com seu  conhecimento imensurável. Enquanto mais artigos publicados, mais poderosa fica \n"
							+ "sua mente e mais longe o personagem pode chegar.",
					"Sobre o jogo", JOptionPane.PLAIN_MESSAGE);

		});

		JScrollPane scrollPane = new JScrollPane(upgradePanel);
		JScrollPane resetScrollPane = new JScrollPane(resetUpgrades);

		scrollPane.setBackground(Color.RED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		resetScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		upgradePanel.setLayout(null);
		upgradePanel.setPreferredSize(new Dimension(510, 8 * 50));
//		resetUpgrades.setPreferredSize(new Dimension(510, 8 * 50));
		clickUpgradePanel.setLayout(new BoxLayout(clickUpgradePanel, BoxLayout.PAGE_AXIS));
		dadosPanel.setLayout(new BoxLayout(dadosPanel, BoxLayout.PAGE_AXIS));
		resetUpgrades.setLayout(null);
		upPanel.setLayout(new GridLayout(1, 2));
		botaoPanel.setLayout(new GridLayout(1, 1));
		panelAchievement.setLayout(new GridLayout(2, 1));
		upAchievement.setLayout(new FlowLayout());
		downAchievement.setLayout(new GridLayout(3, 2, 5, 5));

		upgradePanel.setBackground(Color.BLACK);
		upPanel.setBackground(Color.WHITE);
		clickUpgradePanel.setBackground(Color.BLACK);
		resetUpgrades.setBackground(Color.BLACK);

		upPanel.add(dadosPanel);
		upPanel.add(botaoPanel);

		setLayout(new GridLayout(2, 1));
		add(upPanel);

		JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		tabs.addTab("Upgrade Auto-Click", scrollPane);
		tabs.addTab("Upgrade Click", clickUpgradePanel);
		tabs.addTab("Upgrade Reset", resetScrollPane);
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

		// TODO Inicio da criação das labels de upgrade de Auto-click

		int altura = 40;
		int largura = 375;
		int espacamento = altura + 10;

		pacitoLabel = new JLabel(
				"<html>Aprendendo a cria artigos: Porque todos sabem que para se fazer um é pacito a pacito<html>");
		pacitoLabel.setIcon(new ImageIcon(this.getClass().getResource("branco.jpg")));
		pacitoLabel.setHorizontalTextPosition(JLabel.CENTER);
		pacitoLabel.setBounds(0, 0, largura, altura);
		upgradePanel.add(pacitoLabel);

		artigosLabel = new JLabel(
				"<html>Artigos: Alunos melhorando o seu futuro, mas acabando com sua sanidade mental e paciência<html>");
		artigosLabel.setIcon(new ImageIcon(this.getClass().getResource("branco.jpg")));
		artigosLabel.setHorizontalTextPosition(JLabel.CENTER);
		artigosLabel.setBounds(0, espacamento, largura, altura);
		upgradePanel.add(artigosLabel);

		gifsLabel = new JLabel(
				"<html>Gifs: Alunos usando hack e copiando os gifs dos veteranos, mas não fazendo exatamente igual<html>");
		gifsLabel.setIcon(new ImageIcon(this.getClass().getResource("branco.jpg")));
		gifsLabel.setHorizontalTextPosition(JLabel.CENTER);
		gifsLabel.setBounds(0, espacamento * 2, largura, altura);
		upgradePanel.add(gifsLabel);

		pediaLabel = new JLabel("<html>ArtigoPédia: Atualizando e verificando se não foi derrubado novamente<html>");
		pediaLabel.setIcon(new ImageIcon(this.getClass().getResource("branco.jpg")));
		pediaLabel.setHorizontalTextPosition(JLabel.CENTER);
		pediaLabel.setBounds(0, espacamento * 3, largura, altura);
		upgradePanel.add(pediaLabel);

		processosLabel = new JLabel("<html>Processos: Aprendendo como  e quando processar com o melhor no ramo<html>");
		processosLabel.setIcon(new ImageIcon(this.getClass().getResource("branco.jpg")));
		processosLabel.setHorizontalTextPosition(JLabel.CENTER);
		processosLabel.setBounds(0, espacamento * 4, largura, altura);
		upgradePanel.add(processosLabel);

		salaLabel = new JLabel("<html>Alunos instalando rede: Aprendendo intalar rede na sala<html>");
		salaLabel.setIcon(new ImageIcon(this.getClass().getResource("branco.jpg")));
		salaLabel.setHorizontalTextPosition(JLabel.CENTER);
		salaLabel.setBounds(0, espacamento * 5, largura, altura);
		upgradePanel.add(salaLabel);

		modemLabel = new JLabel(
				"<html>Modem: Alunos aprendendo mais sobrea criação e seu funcionamento além de seu criador<html>");
		modemLabel.setIcon(new ImageIcon(this.getClass().getResource("branco.jpg")));
		modemLabel.setHorizontalTextPosition(JLabel.CENTER);
		modemLabel.setBounds(0, espacamento * 6, largura, altura);
		upgradePanel.add(modemLabel);

		historiaLabel = new JLabel(
				"<html>Alunos ouvindo historia: Surpreendentes e sem contesto e hora, mas o lugar vai ser a sala de aula<html>");
		historiaLabel.setIcon(new ImageIcon(this.getClass().getResource("branco.jpg")));
		historiaLabel.setHorizontalTextPosition(JLabel.CENTER);
		historiaLabel.setBounds(0, espacamento * 7, largura, altura);
		upgradePanel.add(historiaLabel);

		// TODO Fim da criação das labels de upgrade de Auto-click

		botaoAcumulador = new JButton("Clique para pular mais");
		botaoPanel.add(botaoAcumulador);
		botaoAcumulador.setAlignmentY(TOP_ALIGNMENT);
		botaoAcumulador.setOpaque(false);
		botaoAcumulador.setContentAreaFilled(false);
		botaoAcumulador.setBorderPainted(false);
		botaoAcumulador.addActionListener((e) -> {
			// TODO Ação botão acumulador
			clicker.acumular(upgrade.getMulti());
		});

		buttonAchievement = new JButton[ACHIEVEMENT];
		for (int a = 0; a < ACHIEVEMENT; a++) {
			buttonAchievement[a] = new JButton(TEXTACHI[a]);
			JButton achievementButton = buttonAchievement[a];
			achievementButton.setEnabled(false);
			downAchievement.add(achievementButton);
		}

		// TODO Fim da criação dos botão de upgrade de Auto-click

		// TODO Botão Pacito
		Upgrade[] upgradesPacito = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesPacito[j] = new Upgrade(clicker, j + 1, 10000);
		}

		JButton[] buttonsPacito = new JButton[QTDUPGRADE];
		for (int i = 0; i < QTDUPGRADE; i++) {
			Upgrade upgrade = upgradesPacito[i];
			buttonsPacito[i] = new JButton(String.format("%d artigos", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttonsPacito[i];
			btnUpgrade.setBounds(410, 0, 100, altura);
			btnUpgrade.setHorizontalAlignment(SwingConstants.RIGHT);
			btnUpgrade.setVisible(false);
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
					if (this.pacito == upgradesPacito.length - 1) {
						buttonsPacito[this.pacito].setEnabled(false);
					} else {
						buttonsPacito[this.pacito].setVisible(false);
						buttonsPacito[this.pacito + 1].setVisible(true);
						this.pacito++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Você não possui artigos suficiente");
			});
		}
		buttonsPacito[0].setVisible(true);

		// TODO Botão Artigo
		Upgrade[] upgradesArtigo = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesArtigo[j] = new Upgrade(clicker, j + 1, 10000);
		}

		JButton[] buttonsArtigo = new JButton[QTDUPGRADE];
		for (int i = 0; i < QTDUPGRADE; i++) {
			Upgrade upgrade = upgradesArtigo[i];
			buttonsArtigo[i] = new JButton(String.format("%d artigos", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttonsArtigo[i];
			btnUpgrade.setBounds(410, espacamento, 100, altura);
			btnUpgrade.setHorizontalAlignment(SwingConstants.RIGHT);
			btnUpgrade.setVisible(false);
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
					if (this.artigo == upgradesArtigo.length - 1) {
						buttonsArtigo[this.artigo].setEnabled(false);
					} else {
						buttonsArtigo[this.artigo].setVisible(false);
						buttonsArtigo[this.artigo + 1].setVisible(true);
						this.artigo++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Você não possui artigos suficiente");
			});
		}
		buttonsArtigo[0].setVisible(true);

		// TODO Botão Gifs
		Upgrade[] upgradesGifs = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesGifs[j] = new Upgrade(clicker, j + 1, 1000);
		}

		JButton[] buttonsGifs = new JButton[QTDUPGRADE];
		for (int k = 0; k < QTDUPGRADE; k++) {
			Upgrade upgrade = upgradesGifs[k];
			buttonsGifs[k] = new JButton(String.format("%d artigos", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttonsGifs[k];
			btnUpgrade.setBounds(410, espacamento * 2, 100, 40);
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
					if (this.gifs == upgradesGifs.length - 1) {
						buttonsGifs[this.gifs].setEnabled(false);
					} else {
						buttonsGifs[this.gifs].setVisible(false);
						buttonsGifs[this.gifs + 1].setVisible(true);
						this.gifs++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Você não possui artigos suficiente");
			});
		}
		buttonsGifs[0].setVisible(true);

		// TODO Botão ArtigoPédia
		Upgrade[] upgradesPedia = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesPedia[j] = new Upgrade(clicker, j + 1, 200);
		}

		JButton[] buttonsPedia = new JButton[QTDUPGRADE];
		for (int l = 0; l < QTDUPGRADE; l++) {
			Upgrade upgrade = upgradesPedia[l];
			buttonsPedia[l] = new JButton(String.format("%d artigos", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttonsPedia[l];
			btnUpgrade.setBounds(410, espacamento * 3, 100, altura);
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
					if (this.pedia == upgradesPedia.length - 1) {
						buttonsPedia[this.pedia].setEnabled(false);
					} else {
						buttonsPedia[this.pedia].setVisible(false);
						buttonsPedia[this.pedia + 1].setVisible(true);
						this.pedia++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Você não possui artigos suficiente");
			});
		}
		buttonsPedia[0].setVisible(true);

		// TODO Botão Processos
		Upgrade[] upgradesProcessos = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesProcessos[j] = new Upgrade(clicker, j + 1, 100);
		}

		JButton[] buttonsProcessos = new JButton[QTDUPGRADE];
		for (int m = 0; m < QTDUPGRADE; m++) {
			Upgrade upgrade = upgradesProcessos[m];
			buttonsProcessos[m] = new JButton(String.format("%d artigos", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttonsProcessos[m];
			btnUpgrade.setBounds(410, espacamento * 4, 100, altura);
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
					if (this.processos == upgradesProcessos.length - 1) {
						buttonsProcessos[this.processos].setEnabled(false);
					} else {
						buttonsProcessos[this.processos].setVisible(false);
						buttonsProcessos[this.processos + 1].setVisible(true);
						this.processos++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Você não possui artigos suficiente");
			});
		}
		buttonsProcessos[0].setVisible(true);

		// TODO Botão Cortina na Sala
		Upgrade[] upgradesSala = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesSala[j] = new Upgrade(clicker, j + 1, 50);
		}

		JButton[] buttonsSala = new JButton[QTDUPGRADE];
		for (int n = 0; n < QTDUPGRADE; n++) {
			Upgrade upgrade = upgradesSala[n];
			buttonsSala[n] = new JButton(String.format("%d artigos", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttonsSala[n];
			btnUpgrade.setBounds(410, espacamento * 5, 100, altura);
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
					if (this.sala == upgradesSala.length - 1) {
						buttonsSala[this.sala].setEnabled(false);
					} else {
						buttonsSala[this.sala].setVisible(false);
						buttonsSala[this.sala + 1].setVisible(true);
						this.sala++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Você não possui artigos suficiente");
			});
		}
		buttonsSala[0].setVisible(true);

		// TODO Botão Modem
		Upgrade[] upgradesModem = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesModem[j] = new Upgrade(clicker, j + 1, 33);
		}

		JButton[] buttonsModem = new JButton[QTDUPGRADE];
		for (int o = 0; o < QTDUPGRADE; o++) {
			Upgrade upgrade = upgradesModem[o];
			buttonsModem[o] = new JButton(String.format("%d artigos", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttonsModem[o];
			btnUpgrade.setBounds(410, espacamento * 6, 100, altura);
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
					if (this.modem == upgradesModem.length - 1) {
						buttonsModem[this.modem].setEnabled(false);
					} else {
						buttonsModem[this.modem].setVisible(false);
						buttonsModem[this.modem + 1].setVisible(true);
						this.modem++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Você não possui artigos suficiente");
			});
		}
		buttonsModem[0].setVisible(true);

		// TODO Botão História
		Upgrade[] upgradesHistoria = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesHistoria[j] = new Upgrade(clicker, j + 1, 25);
		}

		JButton[] buttonsHistoria = new JButton[QTDUPGRADE];
		for (int p = 0; p < QTDUPGRADE; p++) {
			Upgrade upgrade = upgradesHistoria[p];
			buttonsHistoria[p] = new JButton(String.format("%d artigos", (custoPadrao * upgrade.getCusto())));
			JButton btnUpgrade = buttonsHistoria[p];
			btnUpgrade.setBounds(410, espacamento * 7, 100, altura);
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
					if (this.historia == upgradesHistoria.length - 1) {
						buttonsHistoria[this.historia].setEnabled(false);
					} else {
						buttonsHistoria[this.historia].setVisible(false);
						buttonsHistoria[this.historia + 1].setVisible(true);
						this.historia++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Você não possui artigos suficiente");
			});
		}
		buttonsHistoria[0].setVisible(true);

		// TODO Fim da criação dos botão de upgrade de Auto-click

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

		// TODO Botão aumentar quantidade de cadeiras
		JButton[] buttonsClick = new JButton[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			Upgrade upgradeMulti = upgradesClick[j];
			buttonsClick[j] = new JButton(
					String.format("<html>Aumentar quantidade<br/> de cadeiras (%d processos)<html>",
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
					if (this.click == upgradesClick.length - 1) {
						buttonsClick[this.click].setEnabled(false);
					} else {
						buttonsClick[this.click].setVisible(false);
						buttonsClick[this.click + 1].setVisible(true);
						this.click++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Você não possui processos suficiente");
			});
		}
		buttonsClick[0].setVisible(true);
		
		JButton reset = new JButton("Reset");
		reset.setBounds(410, 0, 100, altura);
		reset.setVisible(true);
		resetUpgrades.add(reset);
		reset.addActionListener((e) -> {
			if (clicker.getAcumulador()> 100000000 && clicker.nivel(clicker.getAcumulador()).equals(clicker.nivel(160000))) {
				clicker.setAcumulador(0);
				clicker.setLevel(0);
				JOptionPane.showMessageDialog(null, "Reset realizado com sucesso");
			}
			else 
				JOptionPane.showMessageDialog(null, "Falta um pouco, faça mais artigos para poder resetar");
		}); 
		

		// TODO Para ficar sempre atualizando o texto...
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
				if (clicker.getAcumulador() > 1000) {
					int acc = clicker.getAcumulador();
					telaAcumulador.setText("Artigos: " + (acc / 1000) + "," + ((acc / 100) % 10) + "K");
					if (clicker.getAcumulador() > 100000) {
						telaAcumulador.setText("Artigos: " + (acc / 1000) + "," + ((acc / 100) % 10) + "KK");

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

package Projeto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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

public class Frame extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;
	private Clicker clicker;
	private JLabel accumulatorLabel;
	private JLabel levelLabel;
	private JLabel upgradeLabel;
	private JLabel upgradeClick;
	private JLabel dpsLabel;
	private JLabel clickLabel;
	private JLabel pacitoLabel;
	private JLabel artigosLabel;
	private JLabel pediaLabel;
	private JLabel gifsLabel;
	private JLabel historiaLabel;
	private JLabel modemLabel;
	private JLabel processosLabel;
	private JLabel salaLabel;
	private JLabel coinResetLabel;
	private JButton buttonAccumulator;
	private Upgrade upgrade;
	private int pacito = 0;
	private int artigo = 0;
	private int click = 0;
	private int gifs = 0;
	private int pedia = 0;
	private int processos = 0;
	private int sala = 0;
	private int modem = 0;
	private int historia = 0;
	private int tempo = 0;
	private int q = 0;
	private int upgradeLevel = 1;
	private int custoPadrao = 1;
	private int diminutiveTime = 1;
	private double auxCoinReset = 0.0;
	private float dps = 0;
	private final int QTDUPGRADE = 10;
	private final int TEMPO = 1;
	private final int ACHIEVEMENT = 7;
	private final String[] TEXTACHI = { "Exchanging modem/bike", "hours played",
			"articles writed", "Hardcore", "No Cheating", "No fooling around",
			"Confortable shirt", };
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private TimerGame timeHistoria = new TimerGame();
	private TimerGame timeArtigo = new TimerGame();
	private TimerGame timePacito = new TimerGame();
	private TimerGame timeModem = new TimerGame();
	private TimerGame timeGif = new TimerGame();
	private TimerGame timePedia = new TimerGame();
	private TimerGame timeProcessos = new TimerGame();
	private TimerGame timeSala = new TimerGame();

	public Frame() {
		super("Super Jumper");

		clicker = new Clicker();
		upgrade = new Upgrade();

		ImagePanel upgradePanel = new ImagePanel(
				new ImageIcon(this.getClass().getResource("background.png"))
						.getImage());
		JPanel sideUpPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		ImagePanel dataPanel = new ImagePanel(
				new ImageIcon(this.getClass().getResource("bedroom.png"))
						.getImage());
		ImagePanel resetUpgradePanel = new ImagePanel(
				new ImageIcon(this.getClass().getResource("background.png"))
						.getImage());
		JPanel achievementPanel = new JPanel();
		ImagePanel achievementSideUpPanel = new ImagePanel(
				new ImageIcon(this.getClass().getResource("CTC.png"))
						.getImage());
		ImagePanel achievementSideDownPanel = new ImagePanel(
				new ImageIcon(this.getClass().getResource("achievement.png"))
						.getImage());
		JButton[] buttonAchievement;
		JMenu menu = new JMenu("File");
		JMenu achievementMenu = new JMenu("Achievements");
		JMenuBar bar = new JMenuBar();
		JMenuItem exit = new JMenuItem("Quit");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem changeUser = new JMenuItem("Change User");
		JMenuItem about = new JMenuItem("About");
		JMenuItem itemAchievement = new JMenuItem("Achievements");
		JDialog dialogAchievement = new JDialog(this, "Achievements", true);

		DecimalFormat apsFormat = new DecimalFormat("0.00");

		menu.add(changeUser);
		menu.add(save);
		menu.add(about);
		menu.add(exit);

		dialogAchievement.add(achievementPanel);
		achievementSideUpPanel.setSize(400, 300);
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

		// Quit button exit
		exit.addActionListener((e) -> {
			System.exit(0);
		});

		// About button text
		about.addActionListener((e) -> {
			JOptionPane.showMessageDialog(null,
					"Clicker game about students producing articles. Developed by Bóreas e Marki Cast .\n " +
							"The game shows our main character , CBW, making articles in his computer \n " +
							"to help mankind with his incredible knowledge. With more articles, the stronger he gets \n" +
							"and the farther he goes.",
					"About", JOptionPane.PLAIN_MESSAGE);

		});

		JScrollPane upgradeScrollPane = new JScrollPane(upgradePanel);
		JScrollPane resetScrollPane = new JScrollPane(resetUpgradePanel);
		upgradeScrollPane.setBackground(Color.RED);
		upgradeScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		resetScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		upgradePanel.setLayout(null);
		upgradePanel.setPreferredSize(new Dimension(510, 8 * 50));
		dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.PAGE_AXIS));
		resetUpgradePanel.setLayout(null);
		sideUpPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.setLayout(new GridLayout(1, 1));
		achievementPanel.setLayout(new GridLayout(2, 1));
		achievementSideUpPanel.setLayout(new FlowLayout());
		achievementSideDownPanel.setLayout(new GridLayout(3, 2, 5, 5));

		sideUpPanel.setBackground(Color.WHITE);
		resetUpgradePanel.setBackground(Color.BLACK);

		sideUpPanel.add(dataPanel);
		sideUpPanel.add(buttonPanel);

		setLayout(new GridLayout(2, 1));
		add(sideUpPanel);

		JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP,
				JTabbedPane.SCROLL_TAB_LAYOUT);
		tabs.addTab("Upgrades", upgradeScrollPane);
		tabs.addTab("Reset", resetScrollPane);
		add(tabs);

		accumulatorLabel = new JLabel("Articles: " + clicker.getAccumulate());
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

		coinResetLabel = new JLabel("Coffes: " + clicker.getResetCoin());
		coinResetLabel.setAlignmentX(LEFT_ALIGNMENT);
		coinResetLabel.setAlignmentY(TOP_ALIGNMENT);
		dataPanel.add(coinResetLabel);

		dpsLabel = new JLabel(
				"APS(Articles per second): " + apsFormat.format(dps));
		dataPanel.add(dpsLabel);

		// Creating labels for upgrading the auto-click

		int altura = 40;
		int largura = 375;
		int espacamento = altura + 10;

		clickLabel = new JLabel(
				"<html>Frodo insira aqui textinho<html>");
		clickLabel.setIcon(
				new ImageIcon(this.getClass().getResource("upgrade.png")));
		clickLabel.setHorizontalTextPosition(JLabel.CENTER);
		clickLabel.setBounds(0, 0, largura, altura);
		upgradePanel.add(clickLabel);
		
		pacitoLabel = new JLabel(
				"<html>Learning to write articles: Lots of tutorials to reach ABNT <html>");
		pacitoLabel.setIcon(
				new ImageIcon(this.getClass().getResource("upgrade.png")));
		pacitoLabel.setHorizontalTextPosition(JLabel.CENTER);
		pacitoLabel.setBounds(0, espacamento, largura, altura);
		upgradePanel.add(pacitoLabel);

		artigosLabel = new JLabel(
				"<html>Articles: Students creating the future, but going insane<html>");
		artigosLabel.setIcon(
				new ImageIcon(this.getClass().getResource("upgrade.png")));
		artigosLabel.setHorizontalTextPosition(JLabel.CENTER);
		artigosLabel.setBounds(0, espacamento * 2, largura, altura);
		upgradePanel.add(artigosLabel);

		gifsLabel = new JLabel(
				"<html>Gifs: Students cheating and copying gifs from seniors<html>");
		gifsLabel.setIcon(
				new ImageIcon(this.getClass().getResource("upgrade.png")));
		gifsLabel.setHorizontalTextPosition(JLabel.CENTER);
		gifsLabel.setBounds(0, espacamento * 3, largura, altura);
		upgradePanel.add(gifsLabel);

		pediaLabel = new JLabel(
				"<html>Articlepedia: Updating and verifying it isn't down<html>");
		pediaLabel.setIcon(
				new ImageIcon(this.getClass().getResource("upgrade.png")));
		pediaLabel.setHorizontalTextPosition(JLabel.CENTER);
		pediaLabel.setBounds(0, espacamento * 4, largura, altura);
		upgradePanel.add(pediaLabel);

		processosLabel = new JLabel(
				"<html>Trial: Learning about not letting anyone plagiarize<html>");
		processosLabel.setIcon(
				new ImageIcon(this.getClass().getResource("upgrade.png")));
		processosLabel.setHorizontalTextPosition(JLabel.CENTER);
		processosLabel.setBounds(0, espacamento * 5, largura, altura);
		upgradePanel.add(processosLabel);

		salaLabel = new JLabel(
				"<html>Chilling in the sleeping net: We need a break<html>");
		salaLabel.setIcon(
				new ImageIcon(this.getClass().getResource("upgrade.png")));
		salaLabel.setHorizontalTextPosition(JLabel.CENTER);
		salaLabel.setBounds(0, espacamento * 6, largura, altura);
		upgradePanel.add(salaLabel);

		modemLabel = new JLabel(
				"<html>Modem: Meeting it's properties and it's creator<html>");
		modemLabel.setIcon(
				new ImageIcon(this.getClass().getResource("upgrade.png")));
		modemLabel.setHorizontalTextPosition(JLabel.CENTER);
		modemLabel.setBounds(0, espacamento * 6, largura, altura);
		upgradePanel.add(modemLabel);

		historiaLabel = new JLabel(
				"<html>Story time: Everyone loves knowing how the teacher got stuck in traffic yesterday<html>");
		historiaLabel.setIcon(
				new ImageIcon(this.getClass().getResource("upgrade.png")));
		historiaLabel.setHorizontalTextPosition(JLabel.CENTER);
		historiaLabel.setBounds(0, espacamento * 7, largura, altura);
		upgradePanel.add(historiaLabel);

		// Main Button setup

		Icon normal = new ImageIcon(getClass().getResource("Button.gif"));
		buttonAccumulator = new JButton("Write articles", normal);
		buttonPanel.add(buttonAccumulator);
		buttonAccumulator.setAlignmentY(TOP_ALIGNMENT);
		buttonAccumulator.setOpaque(false);
		buttonAccumulator.setContentAreaFilled(false);
		buttonAccumulator.setBorderPainted(false);
		buttonAccumulator.addActionListener((e) -> {
			clicker.accumulate(upgrade.getMulti());
		});

		// Achievement setup

		buttonAchievement = new JButton[ACHIEVEMENT];
		for (int a = 0; a < ACHIEVEMENT; a++) {
			buttonAchievement[a] = new JButton(TEXTACHI[a]);
			JButton achievementButton = buttonAchievement[a];
			achievementButton.setEnabled(false);
			achievementSideDownPanel.add(achievementButton);
		}

		// Auto-Clickers upgrades setup

		// First upgrade: Creating Articles
		
		upgradePanel.add(upgrade.buttons(QTDUPGRADE, upgrade.upgradeArray(QTDUPGRADE, diminutiveTime), 1, custoPadrao, TEMPO, timePacito, diminutiveTime))  ;
		
//		Upgrade[] upgradesPacito = new Upgrade[QTDUPGRADE];
//		for (int j = 0; j < QTDUPGRADE; j++) {
//			upgradesPacito[j] = new Upgrade(clicker, j + 1,
//					10000 / diminutiveTime);
//		}
//
//		JButton[] buttonsPacito = new JButton[QTDUPGRADE];
//		for (int i = 0; i < QTDUPGRADE; i++) {
//			Upgrade upgrade = upgradesPacito[i];
//			buttonsPacito[i] = new JButton(String.format("%d articles",
//					(custoPadrao * upgrade.getCost())));
//			JButton btnUpgrade = buttonsPacito[i];
//			btnUpgrade.setBounds(410, espacamento * 1, 100, altura);
//			btnUpgrade.setHorizontalAlignment(SwingConstants.RIGHT);
//			btnUpgrade.setVisible(false);
//			upgradePanel.add(btnUpgrade);
//			btnUpgrade.addActionListener((e) -> {
//				int custo = custoPadrao * upgrade.getCost();
//				if (clicker.getAccumulate() >= custo) {
//					btnUpgrade.setEnabled(true);
//					clicker.setAccumulate(clicker.getAccumulate() - custo);
//					long delay = TEMPO * upgrade.getPeriod();
//					timePacito.timer(delay, clicker);
//					dps += 0.1 * diminutiveTime;
//					auxCoinReset += 1.0;
//					upgradeLabel.setText("Upgrade: " + (upgradeLevel++));
//					if (this.pacito == upgradesPacito.length - 1) {
//						buttonsPacito[this.pacito].setEnabled(false);
//					} else {
//						buttonsPacito[this.pacito].setVisible(false);
//						buttonsPacito[this.pacito + 1].setVisible(true);
//						this.pacito++;
//					}
//				} else
//					JOptionPane.showMessageDialog(null, "Not enough articles");
//			});
//		}
//		buttonsPacito[0].setVisible(true);

		// Writing Articles
		Upgrade[] upgradesArtigo = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesArtigo[j] = new Upgrade(clicker, j + 1,
					10000 / diminutiveTime);
		}

		JButton[] buttonsArtigo = new JButton[QTDUPGRADE];
		for (int i = 0; i < QTDUPGRADE; i++) {
			Upgrade upgrade = upgradesArtigo[i];
			buttonsArtigo[i] = new JButton(String.format("%d articles",
					(custoPadrao * upgrade.getCost())));
			JButton btnUpgrade = buttonsArtigo[i];
			btnUpgrade.setBounds(410, espacamento * 2, 100, altura);
			btnUpgrade.setHorizontalAlignment(SwingConstants.RIGHT);
			btnUpgrade.setVisible(false);
			upgradePanel.add(btnUpgrade);
			btnUpgrade.addActionListener((e) -> {
				int custo = custoPadrao * upgrade.getCost();
				if (clicker.getAccumulate() >= custo) {
					btnUpgrade.setEnabled(true);
					clicker.setAccumulate(clicker.getAccumulate() - custo);
					long delay = TEMPO * upgrade.getPeriod();
					timeArtigo.timer(delay, clicker);
					dps += 0.1 * diminutiveTime;
					auxCoinReset += 1.1;
					upgradeLabel.setText("Upgrade: " + (upgradeLevel++));
					if (this.artigo == upgradesArtigo.length - 1) {
						buttonsArtigo[this.artigo].setEnabled(false);
					} else {
						buttonsArtigo[this.artigo].setVisible(false);
						buttonsArtigo[this.artigo + 1].setVisible(true);
						this.artigo++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Not enough articles");
			});
		}
		buttonsArtigo[0].setVisible(true);

		// Gifs
		Upgrade[] upgradesGifs = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesGifs[j] = new Upgrade(clicker, j + 1, 1000);
		}

		JButton[] buttonsGifs = new JButton[QTDUPGRADE];
		for (int k = 0; k < QTDUPGRADE; k++) {
			Upgrade upgrade = upgradesGifs[k];
			buttonsGifs[k] = new JButton(String.format("%d articles",
					(custoPadrao * upgrade.getCost())));
			JButton btnUpgrade = buttonsGifs[k];
			btnUpgrade.setBounds(410, espacamento * 3, 100, 40);
			btnUpgrade.setVisible(false);
			btnUpgrade.setAlignmentX(LEFT_ALIGNMENT);
			upgradePanel.add(btnUpgrade);
			btnUpgrade.addActionListener((e) -> {
				int custo = custoPadrao * upgrade.getCost();
				if (clicker.getAccumulate() >= custo) {
					btnUpgrade.setEnabled(true);
					clicker.setAccumulate(clicker.getAccumulate() - custo);
					long delay = TEMPO * upgrade.getPeriod();
					timeGif.timer(delay, clicker);
					dps += 1;
					auxCoinReset += 1.2;
					upgradeLabel.setText("Upgrade: " + (upgradeLevel++));
					if (this.gifs == upgradesGifs.length - 1) {
						buttonsGifs[this.gifs].setEnabled(false);
					} else {
						buttonsGifs[this.gifs].setVisible(false);
						buttonsGifs[this.gifs + 1].setVisible(true);
						this.gifs++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Not enough articles");
			});
		}
		buttonsGifs[0].setVisible(true);

		// Articlepedia
		Upgrade[] upgradesPedia = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesPedia[j] = new Upgrade(clicker, j + 1, 200);
		}

		JButton[] buttonsPedia = new JButton[QTDUPGRADE];
		for (int l = 0; l < QTDUPGRADE; l++) {
			Upgrade upgrade = upgradesPedia[l];
			buttonsPedia[l] = new JButton(String.format("%d articles",
					(custoPadrao * upgrade.getCost())));
			JButton btnUpgrade = buttonsPedia[l];
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
					timePedia.timer(delay, clicker);
					dps += 5;
					auxCoinReset += 1.3;
					upgradeLabel.setText("Upgrade: " + (upgradeLevel++));
					if (this.pedia == upgradesPedia.length - 1) {
						buttonsPedia[this.pedia].setEnabled(false);
					} else {
						buttonsPedia[this.pedia].setVisible(false);
						buttonsPedia[this.pedia + 1].setVisible(true);
						this.pedia++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Not enough articles");
			});
		}
		buttonsPedia[0].setVisible(true);

		// Trials
		Upgrade[] upgradesProcessos = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesProcessos[j] = new Upgrade(clicker, j + 1, 100);
		}

		JButton[] buttonsProcessos = new JButton[QTDUPGRADE];
		for (int m = 0; m < QTDUPGRADE; m++) {
			Upgrade upgrade = upgradesProcessos[m];
			buttonsProcessos[m] = new JButton(String.format("%d articles",
					(custoPadrao * upgrade.getCost())));
			JButton btnUpgrade = buttonsProcessos[m];
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
					timeProcessos.timer(delay, clicker);
					dps += 10;
					auxCoinReset += 1.4;
					upgradeLabel.setText("Upgrade: " + (upgradeLevel++));
					if (this.processos == upgradesProcessos.length - 1) {
						buttonsProcessos[this.processos].setEnabled(false);
					} else {
						buttonsProcessos[this.processos].setVisible(false);
						buttonsProcessos[this.processos + 1].setVisible(true);
						this.processos++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Not enough articles");
			});
		}
		buttonsProcessos[0].setVisible(true);

		// Sleeping net
		Upgrade[] upgradesSala = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesSala[j] = new Upgrade(clicker, j + 1, 50);
		}

		JButton[] buttonsSala = new JButton[QTDUPGRADE];
		for (int n = 0; n < QTDUPGRADE; n++) {
			Upgrade upgrade = upgradesSala[n];
			buttonsSala[n] = new JButton(String.format("%d articles",
					(custoPadrao * upgrade.getCost())));
			JButton btnUpgrade = buttonsSala[n];
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
					timeSala.timer(delay, clicker);
					dps += 20;
					auxCoinReset += 1.5;
					upgradeLabel.setText("Upgrade: " + (upgradeLevel++));
					if (this.sala == upgradesSala.length - 1) {
						buttonsSala[this.sala].setEnabled(false);
					} else {
						buttonsSala[this.sala].setVisible(false);
						buttonsSala[this.sala + 1].setVisible(true);
						this.sala++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Not enough articles");
			});
		}
		buttonsSala[0].setVisible(true);

		// Modem
		Upgrade[] upgradesModem = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesModem[j] = new Upgrade(clicker, j + 1, 33);
		}

		JButton[] buttonsModem = new JButton[QTDUPGRADE];
		for (int o = 0; o < QTDUPGRADE; o++) {
			Upgrade upgrade = upgradesModem[o];
			buttonsModem[o] = new JButton(String.format("%d articles",
					(custoPadrao * upgrade.getCost())));
			JButton btnUpgrade = buttonsModem[o];
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
					timeModem.timer(delay, clicker);
					dps += 30;
					auxCoinReset += 1.6;
					upgradeLabel.setText("Upgrade: " + (upgradeLevel++));
					if (this.modem == upgradesModem.length - 1) {
						buttonsModem[this.modem].setEnabled(false);
					} else {
						buttonsModem[this.modem].setVisible(false);
						buttonsModem[this.modem + 1].setVisible(true);
						this.modem++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Not enough articles");
			});
		}
		buttonsModem[0].setVisible(true);

		// Story time
		Upgrade[] upgradesHistoria = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesHistoria[j] = new Upgrade(clicker, j + 1, 25);
		}

		JButton[] buttonsHistoria = new JButton[QTDUPGRADE];
		for (int p = 0; p < QTDUPGRADE; p++) {
			Upgrade upgrade = upgradesHistoria[p];
			buttonsHistoria[p] = new JButton(String.format("%d articles",
					(custoPadrao * upgrade.getCost())));
			JButton btnUpgrade = buttonsHistoria[p];
			btnUpgrade.setBounds(410, espacamento * 8, 100, altura);
			btnUpgrade.setVisible(false);
			btnUpgrade.setAlignmentX(LEFT_ALIGNMENT);
			upgradePanel.add(btnUpgrade);
			btnUpgrade.addActionListener((e) -> {
				int custo = custoPadrao * upgrade.getCost();
				if (clicker.getAccumulate() >= custo) {
					btnUpgrade.setEnabled(true);
					clicker.setAccumulate(clicker.getAccumulate() - custo);
					long delay = TEMPO * upgrade.getPeriod();
					timeHistoria.timer(delay, clicker);
					dps += 40;
					auxCoinReset += 1.7;
					upgradeLabel.setText("Upgrade: " + (upgradeLevel++));
					if (this.historia == upgradesHistoria.length - 1) {
						buttonsHistoria[this.historia].setEnabled(false);
					} else {
						buttonsHistoria[this.historia].setVisible(false);
						buttonsHistoria[this.historia + 1].setVisible(true);
						this.historia++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Not enough articles");
			});
		}
		buttonsHistoria[0].setVisible(true);

		// Click upgrade creation

		Upgrade[] upgradesClick = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesClick[j] = new Upgrade(clicker, j + 1, 1000);
		}

		// Click upgrade setup
		JButton[] buttonsClick = new JButton[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			Upgrade upgradeMulti = upgradesClick[j];
			buttonsClick[j] = new JButton(
					String.format("Writing more articles (%d Articles)",
							(custoPadrao * upgradeMulti.getCost())));
			JButton btnUpgradeClick = buttonsClick[j];
			btnUpgradeClick.setBounds(410, 0, 100, altura);
			btnUpgradeClick.setVisible(false);
			btnUpgradeClick.setAlignmentX(LEFT_ALIGNMENT);
			upgradePanel.add(btnUpgradeClick);
			btnUpgradeClick.addActionListener((e) -> {
				int custo = custoPadrao * upgradeMulti.getCost();
				if (clicker.getAccumulate() >= custo) {
					btnUpgradeClick.setEnabled(true);
					upgrade.upgradeClick();
					auxCoinReset += 1.0;
					upgradeClick.setText(
							"Click Upgrade: " + (upgrade.getMulti() - 1));
					clicker.setAccumulate(clicker.getAccumulate() - custo);
					accumulatorLabel
							.setText("Articles: " + clicker.getAccumulate());
					if (this.click == upgradesClick.length - 1) {
						buttonsClick[this.click].setEnabled(false);
					} else {
						buttonsClick[this.click].setVisible(false);
						buttonsClick[this.click + 1].setVisible(true);
						this.click++;
					}
				} else
					JOptionPane.showMessageDialog(null, "Not enough articles");
			});
		}
		buttonsClick[0].setVisible(true);

		// ....................................................................................................
		
		Upgrade[] upgradesTempo = new Upgrade[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			upgradesTempo[j] = new Upgrade(clicker, j + 1);
		}

		JButton[] buttonsTempo = new JButton[QTDUPGRADE];
		for (int j = 0; j < QTDUPGRADE; j++) {
			Upgrade upgradeTempo = upgradesTempo[j];
			buttonsTempo[j] = new JButton(
					String.format("Writing more articles (%d processos)",
							(custoPadrao * upgradeTempo.getCost())));
			JButton btnUpgradeClick = buttonsTempo[j];
			btnUpgradeClick.setVisible(false);
			btnUpgradeClick.setAlignmentX(LEFT_ALIGNMENT);
			btnUpgradeClick.setBounds(410, espacamento, 100, altura);
			resetUpgradePanel.add(btnUpgradeClick);
			btnUpgradeClick.addActionListener((e) -> {
				int custo = custoPadrao * upgradeTempo.getCost();
				if (clicker.getResetCoin() >= custo) {
					clicker.setResetCoin(clicker.getResetCoin() - custo);
					coinResetLabel.setText("Coffes: " + clicker.getResetCoin());
					diminutiveTime += 1;
					btnUpgradeClick.setEnabled(true);
					auxCoinReset += 1.0;
					if (this.tempo == upgradesTempo.length - 1) {
						buttonsTempo[this.tempo].setEnabled(false);
					} else {
						buttonsTempo[this.tempo].setVisible(false);
						buttonsTempo[this.tempo + 1].setVisible(true);
						this.tempo++;
					}
				} else
					JOptionPane.showMessageDialog(null,
							"Didn't drink enough coffe");
			});
		}
		buttonsTempo[0].setVisible(true);

		JButton reset = new JButton("Reset");
		reset.setBounds(410, 0, 100, altura);
		reset.setVisible(true);
		reset.setEnabled(false);
		resetUpgradePanel.add(reset);
		reset.addActionListener((e) -> {
			if (clicker.getAccumulate() > 100) {
				timeHistoria.cancelTimer();
				timeArtigo.cancelTimer();
				timeGif.cancelTimer();
				timeModem.cancelTimer();
				timePacito.cancelTimer();
				timePedia.cancelTimer();
				timeProcessos.cancelTimer();
				timeSala.cancelTimer();
				reset.setEnabled(false);
				clicker.setResetCoin((int) (auxCoinReset * 5) / 100 +
						((clicker.getAccumulate() / 1000) + 1));
				coinResetLabel.setText("Coffes: " + clicker.getResetCoin());
				clicker.setAccumulate(0);
				clicker.setLevel(0);
				upgrade.setMulti(1);
				dps = 0;
				auxCoinReset = 0;
				upgradeLabel.setText("Upgrade: 0");
				JOptionPane.showMessageDialog(null, "Reseted!");
				// for (JButton btn : buttonsClick) {
				// btn.setEnabled(false);
				// btn.setVisible(false);
				// }
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
//				for (JButton btn : buttonsPacito) {
//					btn.setEnabled(false);
//					btn.setVisible(false);
//				}
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

				// for (JButton btn : buttonsClick) {
				// btn.setEnabled(true);
				// btn.setVisible(true);
				// }
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
//				for (JButton btn : buttonsPacito) {
//					btn.setEnabled(true);
//					btn.setVisible(true);
//				}
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
				JOptionPane.showMessageDialog(null, "Not enough articles yet");
		});

		// Text updating
		Timer timeUpdater = new Timer();
		timeUpdater.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				accumulatorLabel
						.setText("Articles: " + clicker.getAccumulate());
				dpsLabel.setText(
						"APS(Artigos per second): " + apsFormat.format(dps));
				if (clicker.getAccumulate() >= 100000)
					reset.setEnabled(true);

				if (clicker.getAccumulate() >= 10000 + (q * 20000)) {
					levelLabel.setText(
							"Level: " + clicker.nivel(clicker.getAccumulate()));
					q++;
				}
				if (clicker.getAccumulate() > 1000) {
					int acc = clicker.getAccumulate();
					accumulatorLabel.setText("Articles: " +
							(acc / 1000) +
							"," +
							((acc / 100) % 10) +
							"K");
					if (clicker.getAccumulate() > 100000) {
						accumulatorLabel.setText("Articles: " +
								(acc / 1000) +
								"," +
								((acc / 100) % 10) +
								"KK");

					}
				}

			}
		}, 0, TEMPO * 100);

	}

}

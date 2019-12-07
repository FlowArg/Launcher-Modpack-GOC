package fr.flowarg.launcher.gui;

import fr.flowarg.launcher.Main;
import fr.flowarg.launcher.downloader.Downloader;
import fr.flowarg.launcher.gui.console.ConsoleFrame.Console;
import fr.litarvan.openauth.AuthenticationException;
import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.util.Saver;
import fr.theshark34.openlauncherlib.util.ramselector.RamSelector;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.animation.Animator;
import fr.theshark34.swinger.colored.SColoredBar;
import fr.theshark34.swinger.event.SwingerEvent;
import fr.theshark34.swinger.event.SwingerEventListener;
import fr.theshark34.swinger.textured.STexturedButton;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.io.File;

@SuppressWarnings("serial")
public class Panel extends JPanel implements SwingerEventListener
{
	private static STexturedButton playButton = new STexturedButton(Swinger.getResource("play.png"));
	private STexturedButton consoleButton = new STexturedButton(Swinger.getResource("console.png"));
	private STexturedButton quitButton = new STexturedButton(Swinger.getResource("quit.png"));
	private STexturedButton hideButton = new STexturedButton(Swinger.getResource("hide.png"));
	
	private static JLabel labelForBar = new JLabel("Clique sur play !", SwingConstants.CENTER);
	private SColoredBar bar = new SColoredBar(Color.RED, Color.GREEN);
	
	private static Saver saver = new Saver(new File(Main.GAME_DIR, "launcher.properties"));
	private static JTextField usernameField = new JTextField(saver.get("username"));
	private static JPasswordField passwordField = new JPasswordField(saver.get("password"));
	
	private static RamSelector ramSelector = new RamSelector(new File(Main.GAME_DIR, "ram.txt"));
	private STexturedButton ramButton = new STexturedButton(Swinger.getResource("ram.png"));
	
	private Font textFontBasic = new Font("SansSerif", Font.BOLD, 30);
	private Font passwordFontBasic = new Font("Calibri", Font.CENTER_BASELINE, 20);
	
	private Image background = Swinger.getResource("background.png");
	
	public Panel()
	{
		int confirm = JOptionPane.showConfirmDialog(this, "Attention, vous devrez lancer auparavant le launcher officiel de Minecraft et une version en 1.12.2 avant de lancer celui-ci, sinon vous allez ne pas pouvoir lancer le jeu. Merci de votre compréhension.", "Attention !!!", JOptionPane.WARNING_MESSAGE);
		if(confirm != JOptionPane.OK_OPTION) Main.exit(0);
		
		this.setLayout(null);
		
		this.quitButton.setBounds((int)(4475 / 2.4), 5);
		this.quitButton.setVisible(true);
		
		this.hideButton.setBounds((int)(4315 / 2.4), 5);
		this.hideButton.setVisible(true);

		this.ramButton.setText("Ram");
		this.ramButton.setBounds(0, 60);
		this.ramButton.setVisible(true);
		
		Panel.usernameField.setBounds((int)(3113 / 2.4), (int)(1466 / 3.2), (int)(1140 / 2.4), (int)(275 / 3.2));
		this.setGood(usernameField);
		Panel.usernameField.setVisible(true);
		
		Panel.passwordField.setBounds((int)(3113 / 2.4), (int)(1978 / 3.2), (int)(1140 / 2.4), (int)(275 / 3.2));
		this.setGood(passwordField);
		Panel.passwordField.setVisible(true);
		
		Panel.playButton.setBounds((int)(3203 / 2.4), (int)(2417 / 3.2));;
		Panel.playButton.setVisible(true);
		
		this.consoleButton.setBounds(0, 0);
		this.consoleButton.setVisible(true);
		
		this.bar.setBounds(0, (int)(3446 / 3.2), (int)(4608 / 2.4), 11);
		this.bar.setVisible(true);
		
		Panel.labelForBar.setBounds(0, (int)(3300 / 3.2), (int)(4598 / 2.4), 48);
		Panel.labelForBar.setFont(textFontBasic);
		Panel.labelForBar.setForeground(Color.YELLOW);
		Panel.labelForBar.setVisible(true);
		
		this.add(Panel.playButton);
		this.add(this.hideButton);
		this.add(this.quitButton);
		this.add(this.consoleButton);
		this.add(Panel.usernameField);
		this.add(Panel.passwordField);
		this.add(this.bar);
		this.add(this.ramButton);
		this.add(Panel.labelForBar);
		
		this.consoleButton.addEventListener(this);
		this.quitButton.addEventListener(this);
		this.hideButton.addEventListener(this);
		this.ramButton.addEventListener(this);
		Panel.playButton.addEventListener(this);
		
		this.setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Swinger.drawFullsizedImage(g, this, background);
	}
	
	private void setGood(JTextField field)
	{
		if(field instanceof JPasswordField) field.setFont(passwordFontBasic);
		else field.setFont(textFontBasic);
		
		field.setForeground(Color.WHITE);
		field.setCaretColor(Color.RED);
		field.setBorder(null);
		field.setOpaque(false);
		field.setHorizontalAlignment(JTextField.CENTER);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onEvent(@NotNull SwingerEvent e)
	{
		if(e.getSource() == Panel.playButton)
		{
			Panel.setFieldsEnabled(false);
			
			if(usernameField.getText().replaceAll(" ", "").length() == 0 || passwordField.getText().length() == 0)
			{
				JOptionPane.showMessageDialog(this, "Veuillez entrer un mot de passe et un e-mail (ou pseudo).", "Erreur", JOptionPane.ERROR_MESSAGE);
				Panel.setFieldsEnabled(true);
				return;
			}
			
			Thread t = new Thread(this::start);
			t.start();
			
			saver.set("username", usernameField.getText());
			
			Panel.playButton.setVisible(false);
		}
		else if(e.getSource() == this.quitButton)
		{
			Animator.fadeOutFrame(Frame.getInstance(), 5, () -> Main.exit(0));
		}
		
		else if(e.getSource() == this.hideButton)
		{
			Frame.getInstance().setState(JFrame.ICONIFIED);
		}
		
		else if(e.getSource() == this.consoleButton)
		{
			Thread t = new Thread(() ->
			{
				Console console = new Console();
				console.init();
			});
			t.start();
		}
		else if(e.getSource() == this.ramButton)
		{
			ramSelector.display();
			ramSelector.setFile(new File(Main.GAME_DIR, "ram.txt"));
			ramSelector.save();
		}
	}
	
	public static void setFieldsEnabled(boolean enabled)
	{
		usernameField.setVisible(enabled);
		passwordField.setVisible(enabled);
		playButton.setVisible(enabled);
	}
	
	public static void setText(String text)
	{
		labelForBar.setText(text);
	}
	public static RamSelector getRamSelector()
	{
		return ramSelector;
	}

	@SuppressWarnings("deprecation")
	private void start()
	{
		int confirm = JOptionPane.showConfirmDialog(Panel.this, "Le launcher va telecharger des fichiers, nous vous conseillons d'être connecté à internet par wifi / cable et d'éviter les réseaux satellites. Cliquez sur ok pour continuer !", "Warning : Telechargement", JOptionPane.WARNING_MESSAGE);

		if (confirm == JOptionPane.OK_OPTION) {
			try {
				Main.auth(usernameField.getText(), passwordField.getText());
			} catch (AuthenticationException e1) {
				JOptionPane.showMessageDialog(Panel.this, "Impossible de se connecter sur les serveurs d'authentification de Mojang, verifiez votre connexion internet, vos identifiants de connexion et de verifier si votre pare-feu ne bloque pas Mojang.", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
				Panel.setFieldsEnabled(true);
				return;
			}
			Downloader.start();
			System.out.println("Launching game...");
			Panel.setText("Launching game...");
			try {
				Main.launch();
			} catch (LaunchException | InterruptedException e1) {
				Main.crashReporter.catchError(e1, "Erreur pendant le lancement du jeu, veuillez essayer de relancer le launcher");
			}
		}
	}
}

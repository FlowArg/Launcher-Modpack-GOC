package fr.flowarg.launcher.gui;

import fr.flowarg.launcher.Main;
import fr.flowarg.launcher.gui.console.Console;
import fr.flowarg.launcher.utils.Logger;
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
public class GPanel extends JPanel implements SwingerEventListener
{
	private static STexturedButton playButton = new STexturedButton(Swinger.getResource("play.png"));
	private static STexturedButton consoleButton = new STexturedButton(Swinger.getResource("console.png"));
	private STexturedButton quitButton = new STexturedButton(Swinger.getResource("quit.png"));
	private STexturedButton hideButton = new STexturedButton(Swinger.getResource("hide.png"));
	
	private static JLabel labelForBar = new JLabel("Clique sur play !", SwingConstants.CENTER);

	private static Saver saver = new Saver(new File(Main.GAME_DIR, "launcher.properties"));
	private static JTextField usernameField = new JTextField(saver.get("username"));
	private static JPasswordField passwordField = new JPasswordField(saver.get("password"));
	private static STexturedButton viewPassword = new STexturedButton(Swinger.getResource("viewPassword.png"));
	
	private static RamSelector ramSelector = new RamSelector(new File(Main.GAME_DIR, "ram.txt"));
	private static STexturedButton ramButton = new STexturedButton(Swinger.getResource("ram.png"));
	
	private Font textFontBasic = new Font("SansSerif", Font.BOLD, 30);
	private Font passwordFontBasic = new Font("Calibri", Font.BOLD, 20);
	
	private Image background = Swinger.getResource("background.png");
	
	public GPanel()
	{
		this.setLayout(null);
		
		this.quitButton.setBounds((int)(4475 / 2.4), 5);
		this.quitButton.setVisible(true);
		
		this.hideButton.setBounds((int)(4315 / 2.4), 5);
		this.hideButton.setVisible(true);

		ramButton.setText("Ram");
		ramButton.setBounds(0, 60);
		ramButton.setVisible(true);
		
		GPanel.usernameField.setBounds((int)(3113 / 2.4), (int)(1466 / 3.2), (int)(1140 / 2.4), (int)(275 / 3.2));
		this.setGood(usernameField);
		GPanel.usernameField.setVisible(true);
		
		GPanel.passwordField.setBounds((int)(3113 / 2.4), (int)(1978 / 3.2), (int)(1140 / 2.4), (int)(275 / 3.2));
		this.setGood(passwordField);
		GPanel.passwordField.setVisible(true);

		viewPassword.setBounds((int)(2983 / 2.4), (int)(2028 / 3.2));
		viewPassword.setVisible(true);
		
		GPanel.playButton.setBounds((int)(3203 / 2.4), (int)(2417 / 3.2));
		GPanel.playButton.setVisible(true);
		
		consoleButton.setBounds(0, 0);
		consoleButton.setVisible(true);

		SColoredBar bar = new SColoredBar(Color.RED, Color.GREEN);
		bar.setBounds(0, (int)(3446 / 3.2), (int)(4608 / 2.4), 11);
		bar.setVisible(true);
		
		GPanel.labelForBar.setBounds(0, (int)(3300 / 3.2), (int)(4598 / 2.4), 48);
		GPanel.labelForBar.setFont(textFontBasic);
		GPanel.labelForBar.setForeground(Color.YELLOW);
		GPanel.labelForBar.setVisible(true);
		
		this.add(GPanel.playButton);
		this.add(this.hideButton);
		this.add(this.quitButton);
		this.add(consoleButton);
		this.add(GPanel.usernameField);
		this.add(GPanel.passwordField);
		this.add(bar);
		this.add(ramButton);
		this.add(GPanel.labelForBar);
		this.add(viewPassword);
		
		consoleButton.addEventListener(this);
		this.quitButton.addEventListener(this);
		this.hideButton.addEventListener(this);
		ramButton.addEventListener(this);
		GPanel.playButton.addEventListener(this);
		viewPassword.addEventListener(this);
		
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
		if(e.getSource() == GPanel.playButton)
		{
			GPanel.setFieldsEnabled(false);
			
			if(usernameField.getText().replaceAll(" ", "").length() == 0 || passwordField.getText().length() == 0)
			{
				JOptionPane.showMessageDialog(this, "Veuillez entrer un mot de passe et un e-mail.", "Erreur", JOptionPane.ERROR_MESSAGE);
				Logger.info("Erreur : Veuillez entrer un mot de passe et un e-mail.");
				GPanel.setFieldsEnabled(true);
				return;
			}
			
			Thread t = new Thread(this::start);
			t.start();
			
			saver.set("username", usernameField.getText());
			
			GPanel.playButton.setVisible(false);
		}
		else if(e.getSource() == this.quitButton)
		{
			Animator.fadeOutFrame(GFrame.getInstance(), 5, () -> Main.exit(0));
		}
		
		else if(e.getSource() == this.hideButton)
		{
			GFrame.getInstance().setState(JFrame.ICONIFIED);
		}
		
		else if(e.getSource() == consoleButton)
		{
			Thread t = new Thread(() ->
			{
				Console console = new Console();
				console.init();
			});
			t.start();
		}
		else if(e.getSource() == ramButton)
		{
			ramSelector.display();
			ramSelector.setFile(new File(Main.GAME_DIR, "ram.txt"));
			ramSelector.save();
		}
		else if(e.getSource() == viewPassword)
		{
			if(passwordField.getEchoChar() != (char)0) passwordField.setEchoChar((char)0);
			else passwordField.setEchoChar('•');
		}
		else passwordField.setEchoChar((char)0);
	}
	
	public static void setFieldsEnabled(boolean enabled)
	{
		usernameField.setVisible(enabled);
		passwordField.setVisible(enabled);
		playButton.setVisible(enabled);
		viewPassword.setVisible(enabled);
		ramButton.setVisible(enabled);
		consoleButton.setVisible(enabled);
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
		int confirm = JOptionPane.showConfirmDialog(GPanel.this, "Le launcher va telecharger des fichiers, nous vous conseillons d'être connecté à internet par wifi / cable et d'éviter les réseaux satellites pour éviter de payer tous frais de forfaits suplémentaires. Cliquez sur ok pour continuer !", "Warning : Telechargement", JOptionPane.OK_CANCEL_OPTION);

		if (confirm == JOptionPane.OK_OPTION) {
			try {
				Main.auth(usernameField.getText(), passwordField.getText());
			} catch (AuthenticationException e) {
				JOptionPane.showMessageDialog(GPanel.this, "Impossible de se connecter sur les serveurs d'authentification de Mojang, verifiez votre connexion internet, vos identifiants de connexion et de verifier si votre pare-feu ne bloque pas Mojang.", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
				Logger.info("Erreur de connexion : Impossible de se connecter sur les serveurs d'authentification de Mojang, verifiez votre connexion internet, vos identifiants de connexion et de verifier si votre pare-feu ne bloque pas Mojang.");
				GPanel.setFieldsEnabled(true);
				return;
			}
			Main.DOWNLOADER.start();
			Logger.info("Launching game...");
			GPanel.setText("Launching game...");
			try {
				Main.launch();
			} catch (LaunchException | InterruptedException e) {
				Main.CRASH_REPORTER.catchError(e, "Erreur pendant le lancement du jeu, veuillez essayer de relancer le launcher");
			}
		}
		else setFieldsEnabled(true);
	}
}

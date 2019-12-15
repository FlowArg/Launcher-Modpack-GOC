package fr.flowarg.launcher.gui;

import fr.flowarg.launcher.Main;
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

	private static Saver saver = new Saver(new File(Main.GAME_DIR, "launcher.properties"));
	private static JTextField usernameField = new JTextField(saver.get("username"));
	private static JPasswordField passwordField = new JPasswordField(saver.get("password"));
	private STexturedButton viewPassword = new STexturedButton(Swinger.getResource("viewPassword.png"));
	
	private static RamSelector ramSelector = new RamSelector(new File(Main.GAME_DIR, "ram.txt"));
	private STexturedButton ramButton = new STexturedButton(Swinger.getResource("ram.png"));
	
	private Font textFontBasic = new Font("SansSerif", Font.BOLD, 30);
	private Font passwordFontBasic = new Font("Calibri", Font.BOLD, 20);
	
	private Image background = Swinger.getResource("background.png");
	
	public Panel()
	{
		int confirm = JOptionPane.showConfirmDialog(this, "Attention, vous devrez lancer auparavant le launcher officiel de Minecraft et une version en 1.12.2 avant de lancer celui-ci, sinon vous n'allez pas pouvoir lancer le jeu. Merci de votre compr�hension.", "Attention !!!", JOptionPane.OK_CANCEL_OPTION);
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

		this.viewPassword.setBounds((int)(2983 / 2.4), (int)(2028 / 3.2));
		this.viewPassword.setVisible(true);
		
		Panel.playButton.setBounds((int)(3203 / 2.4), (int)(2417 / 3.2));
		Panel.playButton.setVisible(true);
		
		this.consoleButton.setBounds(0, 0);
		this.consoleButton.setVisible(true);

		SColoredBar bar = new SColoredBar(Color.RED, Color.GREEN);
		bar.setBounds(0, (int)(3446 / 3.2), (int)(4608 / 2.4), 11);
		bar.setVisible(true);
		
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
		this.add(bar);
		this.add(this.ramButton);
		this.add(Panel.labelForBar);
		this.add(this.viewPassword);
		
		this.consoleButton.addEventListener(this);
		this.quitButton.addEventListener(this);
		this.hideButton.addEventListener(this);
		this.ramButton.addEventListener(this);
		Panel.playButton.addEventListener(this);
		this.viewPassword.addEventListener(this);
		
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
				JOptionPane.showMessageDialog(this, "Veuillez entrer un mot de passe et un e-mail.", "Erreur", JOptionPane.ERROR_MESSAGE);
				System.out.println("Erreur : Veuillez entrer un mot de passe et un e-mail.");
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
		else if(e.getSource() == this.viewPassword)
		{
			if(passwordField.getEchoChar() != (char)0) passwordField.setEchoChar((char)0);
			else passwordField.setEchoChar('*');
		}
		else passwordField.setEchoChar((char)0);
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
		int confirm = JOptionPane.showConfirmDialog(Panel.this, "Le launcher va telecharger des fichiers, nous vous conseillons d'�tre connect� � internet par wifi / cable et d'�viter les r�seaux satellites pour �viter de payer tous frais de forfaits supl�mentaires. Cliquez sur ok pour continuer !", "Warning : Telechargement", JOptionPane.OK_CANCEL_OPTION);

		if (confirm == JOptionPane.OK_OPTION) {
			try {
				Main.auth(usernameField.getText(), passwordField.getText());
			} catch (AuthenticationException e) {
				JOptionPane.showMessageDialog(Panel.this, "Impossible de se connecter sur les serveurs d'authentification de Mojang, verifiez votre connexion internet, vos identifiants de connexion et de verifier si votre pare-feu ne bloque pas Mojang.", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
				System.out.println("Erreur de connexion : Impossible de se connecter sur les serveurs d'authentification de Mojang, verifiez votre connexion internet, vos identifiants de connexion et de verifier si votre pare-feu ne bloque pas Mojang.");
				Panel.setFieldsEnabled(true);
				return;
			}
			System.out.println("Starting " + Main.DOWNLOADER.getName() + " Downloader, wait please...");
			Main.DOWNLOADER.start();
			System.out.println("Launching game...");
			Panel.setText("Launching game...");
			try {
				Main.launch();
			} catch (LaunchException | InterruptedException e) {
				Main.crashReporter.catchError(e, "Erreur pendant le lancement du jeu, veuillez essayer de relancer le launcher");
			}
		}
		else setFieldsEnabled(true);
	}
}
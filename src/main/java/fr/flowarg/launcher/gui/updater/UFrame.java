package fr.flowarg.launcher.gui.updater;

import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.util.WindowMover;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class UFrame extends JFrame
{
	public UFrame()
	{
		super("Updater");
		this.setBackground(Color.BLACK);
		this.setSize(new Dimension(960, 680));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setIconImage(Swinger.getResource("icon.png"));
		WindowMover mover = new WindowMover(this);
		this.addMouseListener(mover);
		this.addMouseMotionListener(mover);
		this.setAlwaysOnTop(false);
		
		this.setContentPane(new UPanel());
	}
}

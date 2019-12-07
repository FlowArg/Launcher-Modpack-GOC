package fr.flowarg.launcher.gui.updater;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.util.WindowMover;

@SuppressWarnings("serial")
public class Frame extends JFrame
{
	private static Frame instance;
	private static Panel panel;
	
	public Frame()
	{
		super("Updater");
		instance = this;
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
		
		this.setContentPane(panel = new Panel());
	}

	public static Frame getInstance()
	{
		return instance;
	}
	
	public static Panel getPanel()
	{
		return panel;
	}
}

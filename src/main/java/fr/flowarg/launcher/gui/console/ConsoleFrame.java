package fr.flowarg.launcher.gui.console;

import javax.swing.*;

@SuppressWarnings("serial")
public class ConsoleFrame extends JFrame
{
	public ConsoleFrame()
	{
		super("Console");
		this.setSize(600, 600);
		this.setResizable(true);
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setVisible(false);
	}
}

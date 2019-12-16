package fr.flowarg.launcher.gui.updater;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class UPanel extends JPanel
{
	private static JLabel label = new JLabel("Checking for updates..., this can takes long time !", SwingConstants.CENTER);

	public UPanel()
	{
		this.setBackground(Color.BLACK);
		Font font = new Font("Calibri", Font.BOLD, 20);
		UPanel.label.setFont(font);
		UPanel.label.setForeground(Color.YELLOW);
		UPanel.label.setVisible(true);
		this.add(label);
		this.setVisible(true);
	}
	
	public static void setText(String text)
	{
		label.setText(text);
	}
}

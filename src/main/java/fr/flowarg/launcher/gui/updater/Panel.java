package fr.flowarg.launcher.gui.updater;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Panel  extends JPanel
{
	private static JLabel label = new JLabel("Checking for updates..., this can takes long time !", SwingConstants.CENTER);

	public Panel()
	{
		this.setBackground(Color.BLACK);
		Font font = new Font("Calibri", Font.BOLD, 20);
		Panel.label.setFont(font);
		Panel.label.setForeground(Color.YELLOW);
		Panel.label.setVisible(true);
		this.add(label);
		this.setVisible(true);
	}
	
	public static void setText(String text)
	{
		label.setText(text);
	}
}

package fr.flowarg.launcher.gui.updater;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Panel  extends JPanel
{
	private static JLabel label = new JLabel("Checking for updates..., this can takes long time !", SwingConstants.CENTER);
	private Font font = new Font("Calibri", Font.CENTER_BASELINE, 20);
	
	public Panel()
	{
		this.setBackground(Color.BLACK);
		Panel.label.setFont(font);
		Panel.label.setForeground(Color.YELLOW);
		Panel.label.setVisible(true);
		this.add(label);
		this.setVisible(true);
	}
	
	public static JLabel getLabel()
	{
		return label;
	}
	
	public static void setText(String text)
	{
		label.setText(text);
	}
}

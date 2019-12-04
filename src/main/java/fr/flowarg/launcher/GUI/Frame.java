package fr.flowarg.launcher.GUI;

import com.sun.awt.AWTUtilities;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.animation.Animator;
import fr.theshark34.swinger.util.WindowMover;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Frame extends JFrame
{
	private static Frame instance;
	
	public Frame(String title)
	{
		super(title);
		instance = this;
		this.setSize(new Dimension(1920, 1080));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setIconImage(Swinger.getResource("icon.png"));
		WindowMover mover = new WindowMover(this);
		this.addMouseListener(mover);
		this.addMouseMotionListener(mover);
		this.setAlwaysOnTop(false);
		AWTUtilities.setWindowOpacity(this, 0.0f);
		
		this.setContentPane(new Panel());
		Animator.fadeInFrame(this, 5);
	}

	public static Frame getInstance()
	{
		return instance;
	}
}

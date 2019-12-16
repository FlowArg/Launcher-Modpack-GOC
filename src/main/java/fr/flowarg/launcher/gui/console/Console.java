package fr.flowarg.launcher.gui.console;

import javax.swing.*;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;

public class Console
{
    JFrame frame = new ConsoleFrame();

    public Console()
    {
        JTextArea textArea = new JTextArea(24, 80);
        ScrollPane pane = new ScrollPane();
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.LIGHT_GRAY);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        textArea.setVisible(true);
        System.setOut(new PrintStream(new OutputStream()
        {
            @Override
            public void write(int b)
            {
                textArea.append(String.valueOf((char) b));
            }
        }));
        pane.add(textArea);
        pane.setWheelScrollingEnabled(true);
        pane.setVisible(true);
        this.frame.add(pane);
    }

    public void init()
    {
        this.frame.pack();
        this.frame.setVisible(true);
    }
}

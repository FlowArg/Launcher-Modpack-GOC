package fr.flowarg.launcher.updater;

import fr.flowarg.launcher.FileUtils;
import fr.flowarg.launcher.Main;
import fr.flowarg.launcher.gui.updater.UFrame;
import fr.flowarg.launcher.gui.updater.UPanel;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

public class Updater
{
    public void start()
    {
    	UFrame frame = new UFrame();
    	frame.setVisible(true);
        System.out.println("Calling https://flowarg.github.io/ for updates... This can takes loooooong time !");
        UPanel.setText("Calling https://flowarg.github.io/ for updates... This can takes loooooong time !");
        String website = "https://flowarg.github.io/minecraft/launcher/Launcher-Modpack.jar";
        try {
            if(!FileUtils.getMD5FromURL(website).equals(FileUtils.getMD5ofFile(FileUtils.getFilePathOfClass(Updater.class))))
            {
                System.out.println("MD5 of the valid file is : " + FileUtils.getMD5FromURL(website) + " and the non-valid MD5 of your launcher is : " + FileUtils.getMD5ofFile(FileUtils.getFilePathOfClass(Updater.class)) + " .");
                UPanel.setText("MD5 of the valid file is : " + FileUtils.getMD5FromURL(website) + " and the non-valid MD5 of your launcher is : " + FileUtils.getMD5ofFile(FileUtils.getFilePathOfClass(Updater.class)) + " .");
                System.out.println("Update found ! Downloading it !");
                UPanel.setText("Update found ! Downloading it !");
                try
                {
                    org.apache.commons.io.FileUtils.copyURLToFile(new URL("https://flowarg.github.io/minecraft/launcher/Launcher-Modpack.jar"), new File(System.getProperty("user.home") + "\\Desktop\\Launcher-Modpack.jar"));
                    System.out.println("Ton launcher est sur le bureau !\nSupprime le launcher actuel et lance celui sur le bureau ! Enjoy.");
                    UPanel.setText("Ton launcher est sur le bureau !\nSupprime le launcher actuel et lance celui sur le bureau ! Enjoy.");
                    JOptionPane.showMessageDialog(frame, "Ton launcher est sur le bureau !\nSupprime le launcher actuel et lance celui sur le bureau ! Enjoy.", "Finish", JOptionPane.INFORMATION_MESSAGE);
                    Main.exit(0);
                }
                catch (IOException e)
                {
                    Main.crashReporter.catchError(e, "Verifiez que le launcher ne se situe pas dans un dossier avec des espaces/accents et que le fichier se nomme bien 'Launcher-Modpack.jar' .");
                }
            }
            else
            {
                frame.setVisible(false);
                System.out.println("No updates found." + " Launcher is running at " + Main.ACTUAL_VERSION + " version.");
            }
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }
}

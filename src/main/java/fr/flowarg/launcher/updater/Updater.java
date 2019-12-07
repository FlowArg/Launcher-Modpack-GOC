package fr.flowarg.launcher.updater;

import fr.flowarg.launcher.FileUtils;
import fr.flowarg.launcher.Main;
import fr.flowarg.launcher.gui.updater.Frame;
import fr.flowarg.launcher.gui.updater.Panel;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.NoSuchAlgorithmException;

public class Updater
{
    public static void start() throws IOException, NoSuchAlgorithmException
    {
    	Frame frame = new Frame();
    	frame.setVisible(true);
        System.out.println("Calling https://flowarg.github.io/ for updates... This can takes loooooong time !");
        Panel.setText("Calling https://flowarg.github.io/ for updates... This can takes loooooong time !");
        String website = "https://flowarg.github.io/minecraft/launcher/Launcher-Modpack.jar";
        if(!FileUtils.getFileSizeBytes(new File(website)).equals(FileUtils.getFileSizeBytes(new File(Updater.class.getProtectionDomain().getCodeSource().getLocation().getFile()))))
        {
            System.out.println("Update found ! Downloading it !");
            Panel.setText("Update found ! Downloading it !");
            try
            {
                download();
                System.out.println("Ton launcher est sur le bureau !\nSupprime le launcher actuel et lance celui sur le bureau ! Enjoy.");
                Panel.setText("Ton launcher est sur le bureau !\nSupprime le launcher actuel et lance celui sur le bureau ! Enjoy.");
                JOptionPane.showMessageDialog(frame, "Ton launcher est sur le bureau !\nSupprime le launcher actuel et lance celui sur le bureau ! Enjoy.", "Finish", JOptionPane.INFORMATION_MESSAGE);
                Main.exit(0);
            }
            catch (IOException e)
            {
                Main.crashReporter.catchError(e, "Vérifiez que le launcher ne se situe pas dans un dossier avec des espaces/accents et que le fichier se nomme bien 'Launcher-Modpack.jar' .");
            }
        }
        if(!FileUtils.getMD5FromURL(website).equals(FileUtils.getMD5ofFile(FileUtils.getFilePathOfClass(Updater.class))))
        {
            System.out.println("MD5 of the valid file is : " + FileUtils.getMD5FromURL(website) + " and the non-valid MD5 of your launcher is : " + FileUtils.getMD5ofFile(FileUtils.getFilePathOfClass(Updater.class)) + " .");
            Panel.setText("MD5 of the valid file is : " + FileUtils.getMD5FromURL(website) + " and the non-valid MD5 of your launcher is : " + FileUtils.getMD5ofFile(FileUtils.getFilePathOfClass(Updater.class)) + " .");
            System.out.println("Update found ! Downloading it !");
            Panel.setText("Update found ! Downloading it !");
            try
            {
                download();
                System.out.println("Ton launcher est sur le bureau !\nSupprime le launcher actuel et lance celui sur le bureau ! Enjoy.");
                Panel.setText("Ton launcher est sur le bureau !\nSupprime le launcher actuel et lance celui sur le bureau ! Enjoy.");
                JOptionPane.showMessageDialog(frame, "Ton launcher est sur le bureau !\nSupprime le launcher actuel et lance celui sur le bureau ! Enjoy.", "Finish", JOptionPane.INFORMATION_MESSAGE);
                Main.exit(0);
            }
            catch (IOException e)
            {
                Main.crashReporter.catchError(e, "Vérifiez que le launcher ne se situe pas dans un dossier avec des espaces/accents et que le fichier se nomme bien 'Launcher-Modpack.jar' .");
            }
        }
        else
        {
        	frame.setVisible(false);
        	System.out.println("No updates found." + " Launcher is running at " + Main.ACTUAL_VERSION + " version.");
        }
    }

    @SuppressWarnings("resource")
    private static void download() throws IOException
    {
        URL website = new URL("https://flowarg.github.io/minecraft/launcher/Launcher-Modpack.jar");
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(System.getProperty("user.home") + "\\Desktop\\Launcher-Modpack.jar");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }
}

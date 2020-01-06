package fr.flowarg.launcher.updater;

import fr.flowarg.launcher.Main;
import fr.flowarg.launcher.gui.updater.UFrame;
import fr.flowarg.launcher.gui.updater.UPanel;
import fr.flowarg.launcher.sha1.SHA1Manager;
import fr.flowarg.launcher.utils.Constants;
import fr.flowarg.launcher.utils.FileUtils;
import fr.flowarg.launcher.utils.Logger;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public final class Updater implements Constants
{
    public void start()
    {
    	UFrame frame = new UFrame();
    	SHA1Manager manager = new SHA1Manager();
    	frame.setVisible(true);
        Logger.info("Calling https://flowarg.github.io/ for updates... This can takes loooooong time !");
        UPanel.setText("Calling https://flowarg.github.io/ for updates... This can takes loooooong time !");
        String website = "https://flowarg.github.io/minecraft/launcher/Launcher-Modpack.jar";
        String actualSHA1 = FileUtils.getSHA1(FileUtils.getFilePathOfClass(Updater.class));
        String goodSHA1 = manager.getSha1OfGoodLauncher();
        try {
            if(!actualSHA1.equals(goodSHA1))
            {
                Logger.info("SHA1 of the valid file is : " + goodSHA1 + " and the non-valid SHA1 of your launcher is : " + actualSHA1 + " .");
                UPanel.setText("SHA1 of the valid file is : " + goodSHA1 + " and the non-valid SHA1 of your launcher is : " + actualSHA1 + " .");
                Thread.sleep(5000L);

                Logger.info("Update found ! Downloading it !");
                UPanel.setText("Update found ! Downloading it !");
                try
                {
                    org.apache.commons.io.FileUtils.copyURLToFile(new URL(website), new File(USER_HOME + "Desktop\\Launcher-Modpack.jar"));
                    Logger.info("Ton launcher est sur le bureau !\nSupprime le launcher actuel et lance celui sur le bureau ! Enjoy.");
                    UPanel.setText("Ton launcher est sur le bureau !\nSupprime le launcher actuel et lance celui sur le bureau ! Enjoy.");
                    JOptionPane.showMessageDialog(frame, "Ton launcher est sur le bureau !\nSupprime le launcher actuel et lance celui sur le bureau ! Enjoy.", "Finish", JOptionPane.INFORMATION_MESSAGE);
                    Main.exit(0);
                }
                catch (IOException e)
                {
                    Main.CRASH_REPORTER.catchError(e, "Verifiez que le launcher ne se situe pas dans un dossier avec des espaces/accents et que le fichier se nomme bien 'Launcher-Modpack.jar' .");
                }
            }
            else
            {
                frame.setVisible(false);
                Logger.info("No updates found." + " Launcher is running at " + Main.ACTUAL_VERSION + " version.");
            }
        } catch (InterruptedException e)
        {
            e.printStackTrace();
            Main.exit(-1);
        }
    }
}

package fr.flowarg.launcher.sha1;

import fr.flowarg.launcher.downloader.Downloader;
import fr.flowarg.launcher.utils.Constants;
import fr.flowarg.launcher.utils.FileUtils;
import fr.flowarg.launcher.utils.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public final class SHA1Printer implements Constants
{
    public void printLibsSha1s() throws IOException
    {
        File f = new File(COMMON + "sha1s.json");
        SHA1Manager manager = new SHA1Manager();
        StringBuilder sb = new StringBuilder();
        FileUtils.createFile(f);

        for (int i = 0; i < Downloader.NUMBER_OF_FILES; i++)
        {
            URL url = new URL(Downloader.LINK_OF_FILES.get(i));
            String sha1 = FileUtils.getSHA1(url);
            Logger.info(sha1);
            sb.append(sha1).append("\n x ");
        }

        ArrayList<String> list = new ArrayList<>(Arrays.asList(sb.toString().split("\n x ")));
        FileUtils.saveFile(f, manager.serialize(new SHA1S(list)));
        Downloader.FILE_NAME.clear();
        Downloader.LINK_OF_FILES.clear();
    }

    public void printObjectsSha1s() throws IOException
    {
        File f = new File(TEMP_DIR + "objects-sha1s.json");
        StringBuilder sb = new StringBuilder();
        SHA1Manager manager = new SHA1Manager();
        FileUtils.createFile(f);

        for (int i = 0; i < Downloader.OBJ_NUMBER_OF_FILES; i++)
        {
            URL url = new URL(Downloader.OBJ_LINK_OF_FILES.get(i));
            String sha1 = FileUtils.getSHA1(url);
            Logger.info(sha1);
            sb.append(sha1).append("\n x ");
        }

        ArrayList<String> list = new ArrayList<>(Arrays.asList(sb.toString().split("\n x ")));
        FileUtils.saveFile(f, manager.serialize(new SHA1S(list)));
        Downloader.FILE_NAME.clear();
        Downloader.LINK_OF_FILES.clear();
        Downloader.OBJ_FILE_NAME.clear();
        Downloader.OBJ_LINK_OF_FILES.clear();
    }
}

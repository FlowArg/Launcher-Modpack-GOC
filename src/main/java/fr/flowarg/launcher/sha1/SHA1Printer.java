package fr.flowarg.launcher.sha1;

import fr.flowarg.launcher.FileUtils;
import fr.flowarg.launcher.downloader.Downloader;
import fr.flowarg.launcher.downloader.Names;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class SHA1Printer
{
    public void printSHa1s() throws IOException
    {
        File f = new File(Names.COMMON + "sha1s.json");
        SHA1Manager manager = new SHA1Manager();
        StringBuilder sb = new StringBuilder();
        FileUtils.createFile(f);

        for (int i = 0; i < Downloader.NUMBER_OF_FILES; i++)
        {
            URL url = new URL(Downloader.LINK_OF_FILES.get(i));
            String sha1 = FileUtils.getSHA1(url);
            System.out.println(sha1);
            sb.append(sha1).append("\n x ");
        }

        ArrayList<String> list = new ArrayList<>(Arrays.asList(sb.toString().split("\n x ")));
        FileUtils.saveFile(f, manager.serialize(new SHA1S(list)));
        Downloader.FILE_NAME.clear();
        Downloader.LINK_OF_FILES.clear();
    }
}

package fr.flowarg.launcher.interfaces;

import java.io.IOException;

public interface IDownloader
{
    int getId();
    String getName();
    void download(int iterator) throws IOException;
    void start();
}

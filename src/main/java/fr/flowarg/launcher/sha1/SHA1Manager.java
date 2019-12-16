package fr.flowarg.launcher.sha1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.flowarg.launcher.FileUtils;
import fr.flowarg.launcher.downloader.Names;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SHA1Manager
{
    private static Gson gson;

    public SHA1Manager()
    {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .serializeNulls()
                .create();
    }

    private SHA1S deserialize(String json)
    {
        return gson.fromJson(json, SHA1S.class);
    }

    public String serialize(SHA1S sha1S)
    {
        return gson.toJson(sha1S);
    }

    public ArrayList<String> getSha1() throws IOException
    {
        String json = FileUtils.loadFile(new File(Names.COMMON + "sha1s.json"));
        SHA1S sha1S = deserialize(json);
        return sha1S.getSha1s();
    }
}

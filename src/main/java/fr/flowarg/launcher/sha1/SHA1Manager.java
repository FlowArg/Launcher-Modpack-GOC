package fr.flowarg.launcher.sha1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.flowarg.launcher.utils.Constants;
import fr.flowarg.launcher.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class SHA1Manager implements Constants
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

    public SHA1S deserialize(String json)
    {
        return gson.fromJson(json, SHA1S.class);
    }

    public String serialize(SHA1S sha1S)
    {
        return gson.toJson(sha1S);
    }

    public SHA1 deserializeOne(String json)
    {
        return gson.fromJson(json, SHA1.class);
    }

    public String serializeOne(SHA1 sha1)
    {
        return gson.toJson(sha1);
    }

    public String getSha1OfGoodLauncher()
    {
        try
        {
            String json = FileUtils.loadFile(new File(COMMON + "launcher.update.json"));
            SHA1 sha1 = deserializeOne(json);
            return sha1.getSha1();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public ArrayList<String> getSha1OfLibs() throws IOException
    {
        String json = FileUtils.loadFile(new File(COMMON + "sha1s.json"));
        SHA1S sha1S = deserialize(json);
        return sha1S.getSha1s();
    }
}

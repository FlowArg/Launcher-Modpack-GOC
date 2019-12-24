package fr.flowarg.launcher.utils;

import fr.flowarg.launcher.Main;

import java.io.*;

public final class Logger
{
    private static void message(boolean err, String toWrite)
    {
        final String prefix = "[Launcher - Guns of Chickens] ";
        final String info = prefix + toWrite;
        final String error = prefix + "[ERROR] " + toWrite;
        if (err)
        {
            writeToTheLogFile(error);
            System.err.println(error);
        }
        else
        {
            writeToTheLogFile(info);
            System.out.println(info);
        }
    }

    public static void info(String message)
    {
        message(false, message);
    }
    public static void err(String message)
    {
        message(true, message);
    }
    public static void writeToTheLogFile(String toLog)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(Main.LOG_FILE));
            StringBuilder text = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null)
            {
                text.append(line + "\n");
            }
            reader.close();

            String str = text.toString();
            String toWrite = str + "\n" + toLog;
            BufferedWriter writer = new BufferedWriter(new FileWriter(Main.LOG_FILE));
            writer.write(toWrite);
            writer.flush();
            writer.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

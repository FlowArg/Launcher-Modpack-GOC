package fr.flowarg.launcher.utils;

public final class Logger
{
    private static void message(boolean err, String toWrite)
    {
        String toLog = "[Launcher - Guns of Chickens] " + toWrite;

        if (err) System.err.println(toLog);
        else System.out.println(toLog);
    }

    public static void info(String message)
    {
        message(false, message);
    }

    public static void err(String message)
    {
        message(true, message);
    }
}

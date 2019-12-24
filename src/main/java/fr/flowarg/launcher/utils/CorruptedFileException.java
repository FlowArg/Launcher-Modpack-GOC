package fr.flowarg.launcher.utils;

public class CorruptedFileException extends Exception
{
    public CorruptedFileException()
    {
        super();
    }

    public CorruptedFileException(String message)
    {
        super(message);
    }

    public CorruptedFileException(String message, Throwable cause)
    {
        super(message, cause);
    }
}

package fr.flowarg.launcher.utils.exceptions.io;

import java.io.IOException;

public class CorruptedFileException extends IOException
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

package fr.flowarg.launcher.utils.exceptions.io;

import java.io.IOException;

public class NotADirectoryException extends IOException
{
    public NotADirectoryException()
    {
        super();
    }

    public NotADirectoryException(String message)
    {
        super(message);
    }

    public NotADirectoryException(String message, Throwable cause)
    {
        super(message, cause);
    }
}

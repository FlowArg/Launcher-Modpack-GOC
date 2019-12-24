package fr.flowarg.launcher.utils;

public class Sizes
{
    private long withOptifine;
    private long withoutOptifine;

    public Sizes(long withOptifine, long withoutOptifine)
    {
        this.withOptifine = withOptifine;
        this.withoutOptifine = withoutOptifine;
    }

    public long getWithOptifine()
    {
        return withOptifine;
    }

    public long getWithoutOptifine()
    {
        return withoutOptifine;
    }
}

package fr.flowarg.launcher;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileUtils
{
    public static void createFile(File file) throws IOException
    {
        if (!file.exists())
        {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }      
    }

    public static void saveFile(File file, String text) throws IOException
	{
        FileWriter fw;
        createFile(file);
        fw = new FileWriter(file);
        fw.write(text);
        fw.flush();
        fw.close();
    }

    public static String loadFile(File file) throws IOException
	{
        if (file.exists())
        {
        	BufferedReader reader = new BufferedReader(new FileReader(file));
        	StringBuilder text = new StringBuilder();

        	String line;

        	while ((line = reader.readLine()) != null)
        	{
        		text.append(line);
        	}
        	reader.close();
        	return text.toString();
        }
        return "";
    }
    
	public static void deleteDirectory(File file)
	{
		if (file.isDirectory() && file.exists())
		{
			File[] entries = file.listFiles();
		    if (entries != null)
		    {
		      for (File entry : entries)
		      {
		        deleteDirectory(entry);
		      }
		    }
		 }
	}
	
	public static void createDirectories(String location, String... dirsToCreate) throws IOException
	{
		for (int i = 0; i < dirsToCreate.length; i++)
		{
			File f = new File(location, dirsToCreate[i]);
			
			if(!f.exists()) Files.createDirectory(Paths.get(location + dirsToCreate[i]));
		}
	}
	
	public static String getFileSizeMegaBytes(File file)
	{
		return (double) file.length() / (1024 * 1024) + " MB";
	}
	public static String getFileSizeKiloBytes(File file)
	{
		return (double) file.length() / 1024 + "  KB";
	}
	public static String getFileSizeBytes(File file)
	{
		return file.length() + " bytes";
	}
	
	public static String getMD5FromURL(String input) throws IOException, NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("MD5");
		InputStream is = new URL(input).openStream();

		is = new DigestInputStream(is, md);
		while (is.read() != -1) {
			;
		}
		byte[] digest = md.digest();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < digest.length; i++) {
			sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
}

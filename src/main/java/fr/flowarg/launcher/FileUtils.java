package fr.flowarg.launcher;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@SuppressWarnings("rawtypes")
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
		for (String s : dirsToCreate) {
			File f = new File(location, s);

			if (!f.exists()) Files.createDirectory(Paths.get(location + s));
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

	public static String getStringPathOfClass(Class classToGetPath)
	{
		return classToGetPath.getProtectionDomain().getCodeSource().getLocation().getPath();
	}

	public static File getFilePathOfClass(Class classToGetPath)
	{
		return new File(classToGetPath.getProtectionDomain().getCodeSource().getLocation().getPath());
	}
	
	public static String getMD5FromURL(String input)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			InputStream is = new URL(input).openStream();

			try
			{
				is = new DigestInputStream(is, md);

				byte[] ignoredBuffer = new byte[8 * 1024];

				while (is.read(ignoredBuffer) > 0)
				{
					;
				}
			}
			finally
			{
				is.close();
			}
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();

			for(int i = 0; i < digest.length; i++)
			{
				sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();

		} catch (Exception ex)
		{
			throw new RuntimeException(ex);
		}
	}

	public static String getFileChecksum(MessageDigest digest, File file) throws IOException
	{
		FileInputStream fis = new FileInputStream(file);

		byte[] byteArray = new byte[1024];
		int bytesCount = 0;

		while ((bytesCount = fis.read(byteArray)) != -1)
		{
			digest.update(byteArray, 0, bytesCount);
		}

		fis.close();

		byte[] bytes = digest.digest();

		StringBuilder sb = new StringBuilder();
		for(int i=0; i< bytes.length ;i++)
		{
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}

	public static String getMD5ofFile(File file) throws NoSuchAlgorithmException, IOException
	{
		MessageDigest md5Digest = MessageDigest.getInstance("MD5");
		return getFileChecksum(md5Digest, file);
	}

	public static void unzipJar(String destinationDir, String jarPath) throws IOException
	{
		File file = new File(jarPath);
		JarFile jar = new JarFile(file);

		for (Enumeration<JarEntry> enums = jar.entries(); enums.hasMoreElements();)
		{
			JarEntry entry = (JarEntry) enums.nextElement();

			String fileName = destinationDir + File.separator + entry.getName();
			File f = new File(fileName);

			if (fileName.endsWith("/"))
			{
				f.mkdirs();
			}
		}

		for (Enumeration<JarEntry> enums = jar.entries(); enums.hasMoreElements();) {
			JarEntry entry = enums.nextElement();

			String fileName = destinationDir + File.separator + entry.getName();
			File f = new File(fileName);

			if (!fileName.endsWith("/")) {
				InputStream is = jar.getInputStream(entry);
				FileOutputStream fos = new FileOutputStream(f);

				while (is.available() > 0) {
					fos.write(is.read());
				}

				fos.close();
				is.close();
			}
		}
	}
}

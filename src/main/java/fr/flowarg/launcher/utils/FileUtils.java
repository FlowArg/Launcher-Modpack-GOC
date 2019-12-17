package fr.flowarg.launcher.utils;

import fr.arinonia.launcherlib.launchlib.exceptions.ErrorException;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static fr.flowarg.launcher.utils.Constants.TEMP_DIR;

@SuppressWarnings("rawtypes")
public final class FileUtils
{
	public static String getFileExtension(final File file)
	{
		final String fileName = file.getName();
		final int dotIndex = fileName.lastIndexOf(46);
		return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
	}

	public static String removeExtension(final String fileName)
	{
		if (fileName == null) {
			return "";
		}
		if (!getFileExtension(new File(fileName)).isEmpty()) {
			return fileName.substring(0, fileName.lastIndexOf(46));
		}
		return fileName;
	}

    public static void createFile(final File file) throws IOException
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

    public static String loadFile(final File file) throws IOException
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
    
	public static void deleteDirectory(final File folder)
	{
		if (folder.exists() && folder.isDirectory())
		{
			final ArrayList<File> files = listFilesForFolder(folder);
			if (files.isEmpty()) {
				folder.delete();
				return;
			}
			for (final File f : files) {
				f.delete();
			}
			folder.delete();
		}
	}

	public static ArrayList<File> listRecursive(final File directory)
	{
		ArrayList<File> files = new ArrayList<File>();
		File[] fs = directory.listFiles();
		if (fs == null)
			return files;

		for (File f : fs)
		{
			if (f.isDirectory())
				files.addAll(listRecursive(f));

			files.add(f);
		}

		return files;
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

	public static String getMD5ofFile(final File file) throws NoSuchAlgorithmException, IOException
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

	public static void unzipJars(JarPath... jars) throws IOException
	{
		for (JarPath jar : jars)
		{
			unzipJar(jar.getDestination(), jar.getJarPath());
		}
	}

	public static class JarPath
	{
		private String destination;
		private String jarPath;

		public JarPath(String destination, String jarPath)
		{
			this.destination = destination;
			this.jarPath = jarPath;
		}

		public String getDestination()
		{
			return destination;
		}

		public String getJarPath()
		{
			return jarPath;
		}
	}

	@Nullable
	public static String getSHA1(final File file) {
		try {
			final InputStream input = new FileInputStream(file);
			try {
				final MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
				final byte[] buffer = new byte[8192];
				for (int len = input.read(buffer); len != -1; len = input.read(buffer)) {
					sha1.update(buffer, 0, len);
				}
				return new HexBinaryAdapter().marshal(sha1.digest()).toLowerCase();
			} catch (Exception e) {
				e.printStackTrace();

			} finally {
				if (input != null) {
					input.close();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static String getSHA1(final URL url) throws IOException
	{
		String tempDir = TEMP_DIR;
		File tempFile = new File(tempDir + url.getFile());
		org.apache.commons.io.FileUtils.copyURLToFile(url, tempFile);
		String sha1 = FileUtils.getSHA1(tempFile);
		if(!tempFile.delete()) org.apache.commons.io.FileUtils.forceDelete(tempFile);
		return sha1;
	}

	public static ArrayList<File> listFilesForFolder(final File folder)
	{
		final ArrayList<File> files = new ArrayList<>();
		File[] listFiles;
		for (int length = (listFiles = folder.listFiles()).length, i = 0; i < length; ++i)
		{
			final File fileEntry = listFiles[i];
			if (fileEntry.isDirectory()) {
				files.addAll(listFilesForFolder(fileEntry));
			}
			files.add(fileEntry);
		}
		return files;
	}

	public static File[] list(final File dir)
	{
		File[] files = dir(dir).listFiles();

		return files == null ? new File[0] : files;
	}

	public static File dir(final File d)
	{
		if (!d.isDirectory())
			throw new ErrorException("Given directory is not one !");

		return d;
	}
}

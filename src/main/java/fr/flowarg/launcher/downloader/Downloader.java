package fr.flowarg.launcher.downloader;

import fr.arinonia.launcherlib.updater.utils.JsonManager;
import fr.arinonia.launcherlib.updater.versions.AssetIndex;
import fr.arinonia.launcherlib.updater.versions.AssetIndexInfo;
import fr.flowarg.launcher.Main;
import fr.flowarg.launcher.gui.GPanel;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Downloader
{
	public static List<String> LINK_OF_FILES = new ArrayList<>();
	public static List<String> FILE_NAME = new ArrayList<>();
	private static int NUMBER_OF_FILES = 0;
	
	public void init()
	{
		ll(Links.VERSIONS_INDEX);
		ll(Links.ASSETS_INDEX);
		ll(Links.MINECRAFT_CLIENT);
		ll(Links.PATCHY_1_1);
		ll(Links.OSHI_1_1);
		ll(Links.JNA_4_4_0);
		ll(Links.PLATFORM_3_4_0);
		ll(Links.ICU4J_CORE_MOJANG_51_2);
		ll(Links.JOPT_SIMPLE_5_0_3);
		ll(Links.CODECJORBIS_20101023);
		ll(Links.CODECWAV_20101023);
		ll(Links.LIBRARY_JAVA_SOUND_20101123);
		ll(Links.LIBRARY_LWJGL_OPENAL_20100824);
		ll(Links.SOUNDSYSTEM_20120107);
		ll(Links.NETTY_ALL_4_1_9_FINAL);
		ll(Links.GUAVA_21_0);
		ll(Links.COMMONS_LANG3_3_5);
		ll(Links.COMMONS_IO_2_5);
		ll(Links.COMMONS_CODEC_1_10);
		ll(Links.JINPUT_2_0_5);
		ll(Links.JUTILS_1_0_0);
		ll(Links.GSON_2_8_0);
		ll(Links.AUTHLIB_1_5_25);
		ll(Links.REALMS_1_10_22);
		ll(Links.COMMONS_COMPRESS_1_8_1);
		ll(Links.HTTPCLIENT_4_3_3);
		ll(Links.COMMONS_LOGGING_1_1_3);
		ll(Links.HTTPCORE_4_3_2);
		ll(Links.FASTUTIL_7_1_0);
		ll(Links.LOG4J_API_2_8_1);
		ll(Links.LOG4J_CORE_2_8_1);
		ll(Links.FORGE);
		ll(Links.LAUNCHWRAPPER);
		ll(Links.ASM);
		ll(Links.JLINE);
		ll(Links.AKKA_ACTOR);
		ll(Links.CONFIG);
		ll(Links.SCALA_ACTORS_MIGRATION);
		ll(Links.SCALA_COMPILER);
		ll(Links.SCALA_CONTINUATIONS_LIBRARY);
		ll(Links.SCALA_CONTINUATIONS_PLUGIN);
		ll(Links.SCALA_LIBRARY);
		ll(Links.SCALA_PARSER_COMBINATORS);
		ll(Links.SCALA_REFLECT);
		ll(Links.SCALA_SWING);
		ll(Links.SCALA_XML);
		ll(Links.MAVEN_ARTIFACT);
		ll(Links.LZMA);
		ll(Links.VECMATH);
		ll(Links.TROVEJ4);
		ll(Links.TEXTTOSPEECH);
		ll(Links.LWJGL);
		ll(Links.LWJGL_UTIL);

		if(System.getProperty("os.name").toLowerCase().contains("win"))
		{
			ll(Links.NATIVE_1_WIN);
			ll(Links.NATIVE_2_WIN);
			ll(Links.NATIVE_3_WIN);
		}
		else if(System.getProperty("os.name").toLowerCase().contains("mac"))
		{
			ll(Links.NATIVE_1_OSX);
			ll(Links.NATIVE_2_OSX);
		}
		else
		{
			ll(Links.NATIVE_1_LINUX);
			ll(Links.NATIVE_2_LINUX);
			ll(Links.NATIVE_3_LINUX);
		}

		ll(Links.MOD_JEI);
		ll(Links.MOD_LUNATRIUS_CORE);
		ll(Links.MOD_SCHEMATICA);
		ll(Links.MOD_FLOW_UTILS);
		ll(Links.MOD_GUNS_OF_CHICKENS);
		ll(Links.MOD_BACKPACKS);
		ll(Links.MOD_BIBLIOCRAFT);
		ll(Links.MOD_CHISEL);
		ll(Links.MOD_CTM);
		ll(Links.MOD_IRONCHEST);
		ll(Links.MOD_JOURNEY_MAP);
		ll(Links.MOD_ICE_AND_FIRE);
		ll(Links.MOD_LLIBRARY);
		ll(Links.MOD_NETHEREX);
		ll(Links.MOD_LIBRARYEX);
		
		NUMBER_OF_FILES = 0;
		System.out.println("Initializing Libraries list...");
		fl(Names.VERSIONS_INDEX);
		fl(Names.ASSETS_INDEX);
		fl(Names.MINECRAFT_CLIENT);
		fl(Names.PATCHY_1_1);
		fl(Names.OSHI_1_1);
		fl(Names.JNA_4_4_0);
		fl(Names.PLATFORM_3_4_0);
		fl(Names.ICU4J_CORE_MOJANG_51_2);
		fl(Names.JOPT_SIMPLE_5_0_3);
		fl(Names.CODECJORBIS_20101023);
		fl(Names.CODECWAV_20101023);
		fl(Names.LIBRARY_JAVA_SOUND_20101123);
		fl(Names.LIBRARY_LWJGL_OPENAL_20100824);
		fl(Names.SOUNDSYSTEM_20120107);
		fl(Names.NETTY_ALL_4_1_9_FINAL);
		fl(Names.GUAVA_21_0);
		fl(Names.COMMONS_LANG3_3_5);
		fl(Names.COMMONS_IO_2_5);
		fl(Names.COMMONS_CODEC_1_10);
		fl(Names.JINPUT_2_0_5);
		fl(Names.JUTILS_1_0_0);
		fl(Names.GSON_2_8_0);
		fl(Names.AUTHLIB_1_5_25);
		fl(Names.REALMS_1_10_22);
		fl(Names.COMMONS_COMPRESS_1_8_1);
		fl(Names.HTTPCLIENT_4_3_3);
		fl(Names.COMMONS_LOGGING_1_1_3);
		fl(Names.HTTPCORE_4_3_2);
		fl(Names.FASTUTIL_7_1_0);
		fl(Names.LOG4J_API_2_8_1);
		fl(Names.LOG4J_CORE_2_8_1);
		fl(Names.FORGE);
		fl(Names.LAUNCHWRAPPER);
		fl(Names.ASM);
		fl(Names.JLINE);
		fl(Names.AKKA_ACTOR);
		fl(Names.CONFIG);
		fl(Names.SCALA_ACTORS_MIGRATION);
		fl(Names.SCALA_COMPILER);
		fl(Names.SCALA_CONTINUATIONS_LIBRARY);
		fl(Names.SCALA_CONTINUATIONS_PLUGIN);
		fl(Names.SCALA_LIBRARY);
		fl(Names.SCALA_PARSER_COMBINATORS);
		fl(Names.SCALA_REFLECT);
		fl(Names.SCALA_SWING);
		fl(Names.SCALA_XML);
		fl(Names.MAVEN_ARTIFACT);
		fl(Names.LZMA);
		fl(Names.VECMATH);
		fl(Names.TROVEJ4);
		fl(Names.TEXTTOSPEECH);
		fl(Names.LWJGL);
		fl(Names.LWJGL_UTIL);

		System.out.println("Initializing Natives list...");
		if(System.getProperty("os.name").toLowerCase().contains("win"))
		{
			fl(Names.NATIVE_1_WIN);
			fl(Names.NATIVE_2_WIN);
			fl(Names.NATIVE_3_WIN);
		}
		else if(System.getProperty("os.name").toLowerCase().contains("mac"))
		{
			fl(Names.NATIVE_1_OSX);
			fl(Names.NATIVE_2_OSX);
		}
		else
		{
			fl(Names.NATIVE_1_LINUX);
			fl(Names.NATIVE_2_LINUX);
			fl(Names.NATIVE_3_LINUX);
		}

		System.out.println("Initializing Mods list...");
		fl(Names.MOD_JEI);
		fl(Names.MOD_LUNATRIUS_CORE);
		fl(Names.MOD_SCHEMATICA);
		fl(Names.MOD_FLOW_UTILS);
		fl(Names.MOD_GUNS_OF_CHICKENS);
		fl(Names.MOD_BACKPACKS);
		fl(Names.MOD_BIBLIOCRAFT);
		fl(Names.MOD_CHISEL);
		fl(Names.MOD_CTM);
		fl(Names.MOD_IRONCHEST);
		fl(Names.MOD_JOURNEY_MAP);
		fl(Names.MOD_ICE_AND_FIRE);
		fl(Names.MOD_LLIBRARY);
		fl(Names.MOD_NETHEREX);
		fl(Names.MOD_LIBRARYEX);
	}
	
	private static void ll(String e)
	{
		LINK_OF_FILES.add(NUMBER_OF_FILES, e);
		++NUMBER_OF_FILES;
	}
	
	private static void fl(String e)
	{
		FILE_NAME.add(NUMBER_OF_FILES, e);
		++NUMBER_OF_FILES;
	}

	public void start()
	{
		System.out.println("Downloading files...");
		GPanel.setText("Downloading files...");
		
		for (int i = 0; i < NUMBER_OF_FILES; i++)
		{
			try
			{
				File file = new File(FILE_NAME.get(i));
				File fileToDownload = new File(LINK_OF_FILES.get(i));
				File folder = file.getParentFile();
				folder.mkdirs();

				if(verifyFiles(file, fileToDownload))
				{
					System.out.println("\n" + "Downloading file : " + LINK_OF_FILES.get(i) + "...");
					GPanel.setText("Downloading file : " + LINK_OF_FILES.get(i) + "...");
					URL website = new URL(LINK_OF_FILES.get(i));
					FileUtils.copyURLToFile(website, new File(FILE_NAME.get(i)));
					System.out.println("File : " + LINK_OF_FILES.get(i) + " has been downloaded at : " + FILE_NAME.get(i) + ".");
					GPanel.setText("File : " + LINK_OF_FILES.get(i) + " has been downloaded at : " + FILE_NAME.get(i) + ".");
				}
			}
			catch (IOException e)
			{
				Main.crashReporter.catchError(e, "Le launcher n'a pas pu telecharge les fichiers requis pour le lancement, veuillez verifier votre connexion internet et votre pare-feu, si l'erreur persiste, contactez moi sur Discord.");
				GPanel.setFieldsEnabled(true);
			}
		}

		LINK_OF_FILES.clear();
		FILE_NAME.clear();
		
		System.out.println("All files are correctly downloaded.");
		GPanel.setText("All files are correctly downloaded.");

		GPanel.setText("Verifying objects...");
		System.out.println("Verifying objects...");
		this.downloadResources();

		try
		{
			System.out.println("Extracting natives...");
			GPanel.setText("Extracting natives...");
			if(System.getProperty("os.name").toLowerCase().contains("win"))
			{
				fr.flowarg.launcher.FileUtils.unzipJar(Names.NATIVES, Names.NATIVE_1_WIN);
				fr.flowarg.launcher.FileUtils.unzipJar(Names.NATIVES, Names.NATIVE_2_WIN);
				fr.flowarg.launcher.FileUtils.unzipJar(Names.NATIVES, Names.NATIVE_3_WIN);
			}
			else if(System.getProperty("os.name").toLowerCase().contains("mac"))
			{
				fr.flowarg.launcher.FileUtils.unzipJar(Names.NATIVES, Names.NATIVE_1_OSX);
				fr.flowarg.launcher.FileUtils.unzipJar(Names.NATIVES, Names.NATIVE_2_OSX);
			}
			else
			{
				fr.flowarg.launcher.FileUtils.unzipJar(Names.NATIVES, Names.NATIVE_1_LINUX);
				fr.flowarg.launcher.FileUtils.unzipJar(Names.NATIVES, Names.NATIVE_2_LINUX);
				fr.flowarg.launcher.FileUtils.unzipJar(Names.NATIVES, Names.NATIVE_3_LINUX);
			}
			FileUtils.deleteDirectory(new File(Names.NATIVES + "META-INF\\"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		System.out.println("All files are completely verified and downloaded !");
		GPanel.setText("All files are completely verified and downloaded !");
	}

	@SuppressWarnings({ "deprecation", "static-acces"})
	private void downloadResources()
	{
		InputStream stream = null;

		final File objectsFolder = new File(Names.COMMON + "assets\\objects\\");
		final File indexesFolder = new File(Names.COMMON, "assets\\indexes\\");

		if (!objectsFolder.exists())
		{
			objectsFolder.mkdirs();
		}

		final AssetIndexInfo indexInfo = new AssetIndexInfo("1.12");
		final File indexFile = new File(indexesFolder, "1.12" + ".json");

		try
		{
			final URL indexUrl = indexInfo.getURL();
			stream = indexUrl.openStream();
			final String json = IOUtils.toString(stream);
			FileUtils.writeStringToFile(indexFile, json);

			JsonManager manager = new JsonManager();
			final AssetIndex index = manager.getGson().fromJson(json, AssetIndex.class);

			for (final Map.Entry<AssetIndex.AssetObject, String> entry : index.getUniqueObjects().entrySet())
			{
				final AssetIndex.AssetObject object = entry.getKey();
				final String filename = object.getHash().substring(0, 2) + "/" + object.getHash();
				String url = "http://resources.download.minecraft.net/" + filename;
				final File file = new File(objectsFolder, filename);
				if(verifyFiles(file, new File(url)))
				{
					System.out.println("Downloading : " + url + " .");
					GPanel.setText("Downloading : " + url + " .");
					FileUtils.copyURLToFile(new URL(url), file);
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			IOUtils.closeQuietly(stream);
		}
		IOUtils.closeQuietly(stream);
	}
	
	public static boolean verifyFiles(File file, File fileToDownload)
    {
		if(!file.exists())
		{
			return true;
		}
		else if(file.exists() && fr.flowarg.launcher.FileUtils.getFileSizeBytes(file).equals(fr.flowarg.launcher.FileUtils.getFileSizeBytes(fileToDownload)))
		{
			System.err.println("File : " + file + " exist but it's invalid ! Deleting it ! (Invalid size).");
			file.delete();
			return true;
		}
		else return false;
	}
}

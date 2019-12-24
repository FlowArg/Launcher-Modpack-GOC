package fr.flowarg.launcher.downloader;

import com.google.gson.GsonBuilder;
import fr.arinonia.launcherlib.updater.utils.JsonManager;
import fr.arinonia.launcherlib.updater.versions.AssetIndex;
import fr.arinonia.launcherlib.updater.versions.AssetIndexInfo;
import fr.flowarg.launcher.Main;
import fr.flowarg.launcher.gui.GPanel;
import fr.flowarg.launcher.sha1.SHA1Manager;
import fr.flowarg.launcher.utils.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static fr.flowarg.launcher.utils.Constants.*;

public final class Downloader
{
	public static List<String> LINK_OF_FILES = new ArrayList<>();
	public static List<String> FILE_NAME = new ArrayList<>();
	public static List<String> OBJ_LINK_OF_FILES = new ArrayList<>();
	public static List<String> OBJ_FILE_NAME = new ArrayList<>();
	public static int OBJ_NUMBER_OF_FILES = 0;
	public static int NUMBER_OF_FILES = 0;
	
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

		if(OS.toLowerCase().contains("win"))
		{
			ll(Links.NATIVE_1_WIN);
			ll(Links.NATIVE_2_WIN);
			ll(Links.NATIVE_3_WIN);
		}
		else if(OS.toLowerCase().contains("mac"))
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
        ll(Links.MOD_TOROHEALTH);
		
		NUMBER_OF_FILES = 0;
		Logger.info("Initializing Libraries list...");
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

		Logger.info("Initializing Natives list...");
		if(OS.toLowerCase().contains("win"))
		{
			fl(Names.NATIVE_1_WIN);
			fl(Names.NATIVE_2_WIN);
			fl(Names.NATIVE_3_WIN);
		}
		else if(OS.toLowerCase().contains("mac"))
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

		Logger.info("Initializing Mods list...");
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
		fl(Names.MOD_TOROHEALTH);

		Logger.info("Initializing objects...");
		InputStream stream = null;

		final File objectsFolder = new File(OBJECTS);
		final File indexesFolder = new File(ASSETS, "indexes\\");

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
			final String json = IOUtils.toString(stream, StandardCharsets.UTF_8);
			FileUtils.writeStringToFile(indexFile, json, StandardCharsets.UTF_8);

			JsonManager.setGson(new GsonBuilder().serializeNulls().disableHtmlEscaping().setPrettyPrinting().create());
			final AssetIndex index = JsonManager.getGson().fromJson(json, AssetIndex.class);

			for (final Map.Entry<AssetIndex.AssetObject, String> entry : index.getUniqueObjects().entrySet())
			{
				final AssetIndex.AssetObject object = entry.getKey();
				final String filename = object.getHash().substring(0, 2) + "/" + object.getHash();
				String url = "http://resources.download.minecraft.net/" + filename;
				OBJ_LINK_OF_FILES.add(url);
				OBJ_FILE_NAME.add(OBJECTS + "objects\\" + filename);
				++OBJ_NUMBER_OF_FILES;
			}
		}
		catch (Exception ex)
		{
			Main.CRASH_REPORTER.catchError(ex, "Le launcher a rencontre une erreur lors de l'initialisation des fichiers. Veuillez relancer le launcher, si l'erreur persiste, contactez moi sur Discord.");
		}
		finally
		{
			IOUtils.closeQuietly(stream);
		}
		IOUtils.closeQuietly(stream);
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
		Logger.info("Verifying files...");
		GPanel.setText("Verifying files...");
		SHA1Manager manager = new SHA1Manager();
		
		for (int i = 0; i < NUMBER_OF_FILES; i++)
		{
			try
			{
				File file = new File(FILE_NAME.get(i));
				File fileToDownload = new File(LINK_OF_FILES.get(i));
				File folder = file.getParentFile();
				folder.mkdirs();
				String sha1 = manager.getSha1OfLibs().get(i);


				if(!isFileValid(file, fileToDownload, sha1))
				{
					Logger.info("Downloading file : " + LINK_OF_FILES.get(i) + "...");
					GPanel.setText("Downloading file : " + LINK_OF_FILES.get(i) + "...");
					URL website = new URL(LINK_OF_FILES.get(i));
					FileUtils.copyURLToFile(website, new File(FILE_NAME.get(i)));
					Logger.info("File : " + LINK_OF_FILES.get(i) + " has been downloaded at : " + FILE_NAME.get(i) + ".");
					GPanel.setText("File : " + LINK_OF_FILES.get(i) + " has been downloaded at : " + FILE_NAME.get(i) + ".");
				}
			}
			catch (IOException e)
			{
				Main.CRASH_REPORTER.catchError(e, "Le launcher n'a pas pu telecharge les fichiers requis pour le lancement, veuillez verifier votre connexion internet et votre pare-feu, si l'erreur persiste, contactez moi sur Discord.");
				GPanel.setFieldsEnabled(true);
			}
		}

		LINK_OF_FILES.clear();
		FILE_NAME.clear();
		
		Logger.info("All files are correctly downloaded.");
		GPanel.setText("All files are correctly downloaded.");

		GPanel.setText("Verifying objects...");
		Logger.info("Verifying objects...");
		this.downloadResources();

		String N1;
		String N2;
		String N3;

		String N = NATIVES;

		try
		{
			Logger.info("Extracting natives...");
			GPanel.setText("Extracting natives...");
			if(OS.toLowerCase().contains("win"))
			{
				N1 = Names.NATIVE_1_WIN;
				N2 = Names.NATIVE_2_WIN;
				N3 = Names.NATIVE_3_WIN;
				File n1 = new File(N1);
				File n2 = new File(N2);
				File n3 = new File(N3);
				fr.flowarg.launcher.utils.FileUtils.unzipJars(
						new fr.flowarg.launcher.utils.FileUtils.JarPath(N, N1),
						new fr.flowarg.launcher.utils.FileUtils.JarPath(N, N2),
						new fr.flowarg.launcher.utils.FileUtils.JarPath(N, N3));
				if(!n1.delete()) n1.deleteOnExit();
				if(!n2.delete()) n2.deleteOnExit();
				if(!n3.delete()) n3.deleteOnExit();
			}
			else if(OS.toLowerCase().contains("mac"))
			{
				N1 = Names.NATIVE_1_OSX;
				N2 = Names.NATIVE_2_OSX;
				File n1 = new File(N1);
				File n2 = new File(N2);
				fr.flowarg.launcher.utils.FileUtils.unzipJars(
						new fr.flowarg.launcher.utils.FileUtils.JarPath(N, N1),
						new fr.flowarg.launcher.utils.FileUtils.JarPath(N, N2));
				if(!n1.delete()) n1.deleteOnExit();
				if(!n2.delete()) n2.deleteOnExit();
			}
			else
			{
				N1 = Names.NATIVE_1_LINUX;
				N2 = Names.NATIVE_2_LINUX;
				N3 = Names.NATIVE_3_LINUX;
				File n1 = new File(N1);
				File n2 = new File(N2);
				File n3 = new File(N3);
				fr.flowarg.launcher.utils.FileUtils.unzipJars(
						new fr.flowarg.launcher.utils.FileUtils.JarPath(N, N1),
						new fr.flowarg.launcher.utils.FileUtils.JarPath(N, N2),
						new fr.flowarg.launcher.utils.FileUtils.JarPath(N, N3));
				if(!n1.delete()) n1.deleteOnExit();
				if(!n2.delete()) n2.deleteOnExit();
				if(!n3.delete()) n3.deleteOnExit();
			}
			FileUtils.deleteDirectory(new File(NATIVES + "META-INF\\"));
			Main.addOnExitAction(new Thread(() ->
			{
				try
				{
					FileUtils.cleanDirectory(new File(NATIVES));
				} catch (IOException ignored) {}
			}));
		} catch (IOException e)
		{
			Main.CRASH_REPORTER.catchError(e, "Le launcher a rencontre une erreur lors de l'extraction des natives, relancez le launcher voir l'ordinateur complet. Si le soucis persistes, contactez moi sur Discord.");
		}

		Logger.info("All files are completely verified and downloaded !");
		GPanel.setText("All files are completely verified and downloaded !");
	}

	@SuppressWarnings("deprecation")
	private void downloadResources()
	{
		InputStream stream = null;

		final File objectsFolder = new File(OBJECTS);
		final File indexesFolder = new File(ASSETS, "indexes\\");

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
			final String json = IOUtils.toString(stream, StandardCharsets.UTF_8);
			FileUtils.writeStringToFile(indexFile, json, StandardCharsets.UTF_8);

			final AssetIndex index = JsonManager.getGson().fromJson(json, AssetIndex.class);

			for (final Map.Entry<AssetIndex.AssetObject, String> entry : index.getUniqueObjects().entrySet())
			{
				final AssetIndex.AssetObject object = entry.getKey();
				final String filename = object.getHash().substring(0, 2) + "/" + object.getHash();
				final String url = "http://resources.download.minecraft.net/" + filename;
				final File file = new File(objectsFolder, filename);
				final String sha1 = entry.getKey().getHash();
				if(!isFileValid(file, new File(url), sha1))
				{
					Logger.info("Downloading : " + url + "...");
					GPanel.setText("Downloading : " + url + "...");
					FileUtils.copyURLToFile(new URL(url), file);
				}
			}
		}
		catch (Exception ex)
		{
			Main.CRASH_REPORTER.catchError(ex, "Le launcher à rencontré une erreur lors du telechargement des assets du jeu. Veuillez verifiez votre connexion internet, relancer le jeu voir me contactez sur discord si ca persiste.");
		}
		finally
		{
			IOUtils.closeQuietly(stream);
		}
		IOUtils.closeQuietly(stream);
		OBJ_FILE_NAME.clear();
		OBJ_LINK_OF_FILES.clear();
	}
	
	public static boolean isFileValid(File file, File fileToDownload, String sha1) throws IOException
	{
		if(!isFileValid(file, fileToDownload)) return false;
		else return sha1.equals(fr.flowarg.launcher.utils.FileUtils.getSHA1(file));
	}

	public static boolean isFileValid(File file, File fileToDownload) throws IOException
	{
		if(!file.exists())
		{
			return false;
		}
		else if(file.exists() && fr.flowarg.launcher.utils.FileUtils.getFileSizeBytes(file).equals(fr.flowarg.launcher.utils.FileUtils.getFileSizeBytes(fileToDownload)))
		{
			Logger.err("File : " + file + " exist but it's invalid ! Deleting it ! (Invalid size).");
			if(!file.delete()) FileUtils.forceDelete(file);
			return false;
		}
		else return true;
	}
}

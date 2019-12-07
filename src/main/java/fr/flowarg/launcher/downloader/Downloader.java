package fr.flowarg.launcher.downloader;

import fr.flowarg.launcher.Main;
import fr.flowarg.launcher.gui.Panel;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class Downloader
{
	public static List<String> LINK_OF_FILES = new ArrayList<>(61);
	public static List<String> FILE_NAME = new ArrayList<>(61);
	private static int NMBR_OF_FILES = 0;
	
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
		
		ll(Links.NATIVE_1);
		ll(Links.NATIVE_2);
		ll(Links.NATIVE_3);
		ll(Links.NATIVE_4);
		ll(Links.NATIVE_5);
		ll(Links.NATIVE_6);
		ll(Links.NATIVE_7);
		ll(Links.NATIVE_8);
		ll(Links.NATIVE_9);
		ll(Links.NATIVE_10);
		ll(Links.NATIVE_11);

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
		
		NMBR_OF_FILES = 0;
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
		fl(Names.NATIVE_1);
		fl(Names.NATIVE_2);
		fl(Names.NATIVE_3);
		fl(Names.NATIVE_4);
		fl(Names.NATIVE_5);
		fl(Names.NATIVE_6);
		fl(Names.NATIVE_7);
		fl(Names.NATIVE_8);
		fl(Names.NATIVE_9);
		fl(Names.NATIVE_10);
		fl(Names.NATIVE_11);

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
		LINK_OF_FILES.add(NMBR_OF_FILES, e);
		++NMBR_OF_FILES;
	}
	
	private static void fl(String e)
	{
		FILE_NAME.add(NMBR_OF_FILES, e);
		++NMBR_OF_FILES;
	}
	
	public static void start()
	{
		System.out.println("Downloading files...");
		Panel.setText("Downloading files...");
		
		for (int i = 0; i < NMBR_OF_FILES; i++)
		{
			try
			{
				File file = new File(FILE_NAME.get(i));
				File fileToDownload = new File(LINK_OF_FILES.get(i));
				File folder = file.getParentFile();
				folder.mkdirs();
				
				if(!verifFiles(file, fileToDownload))
				{
					download(i);
				}				
			}
			catch (IOException e)
			{
				Main.crashReporter.catchError(e, "Le launcher n'a pas pu telecharge les fichiers requis pour le lancement, veuillez verifiez votre connexion internet et votre pare-feu, si l'erreur persiste, contactez moi sur Discord.");
				Panel.setFieldsEnabled(true);
			}
		}

		LINK_OF_FILES.clear();
		FILE_NAME.clear();
		
		System.out.println("All files are correctly downloaded.");
		Panel.setText("All files are correctly downloaded.");
		
		try
		{
			Panel.setText("Verifying objects...");
			System.out.println("Verifying objects...");

			File objectsS = new File(Names.DIR + ".minecraft/assets/objects");
			File objectsT = new File(Main.GAME_DIR + "/common/assets/objects");
			
			File skinsS = new File(Names.DIR + ".minecraft/assets/skins");
			File skinsT = new File(Main.GAME_DIR + "/common/assets/skins");
			
			File virtualS = new File(Names.DIR + ".minecraft/assets/virtual");
			File virtualT = new File(Main.GAME_DIR + "/common/assets/virtual");

			if(!objectsT.exists())
			{
				Panel.setText("Copying objects...");
				System.out.println("Copying objects...");
				FileUtils.copyDirectory(objectsS, objectsT);
			}
			
			Panel.setText("Verifying skins...");
			System.out.println("Verifying skins...");
			if(!skinsT.exists())
			{
				if(skinsS.exists())
				{
					Panel.setText("Copying skins...");
					System.out.println("Copying skins...");
					FileUtils.copyDirectory(skinsS, skinsT);
				}
			}

			Panel.setText("Verifying virtuals...");
			System.out.println("Verifying virtuals...");
			if(!virtualT.exists())
			{
				Panel.setText("Copying virtuals...");
				System.out.println("Copying virtuals...");
				FileUtils.copyDirectory(virtualS, virtualT);
			}			
		}
		catch (IOException e)
		{
			Main.crashReporter.catchError(e, "Erreur dans la copie des fichiers, veuillez verifiez l'existance des dossiers dans votre .minecraft : assets/objects, assets/skins (optional), assets/virtual et assurez vous d'avoir lancer au moins une fois, une version 1.12.2 du jeu.");
			Panel.setFieldsEnabled(true);
		}	

		System.out.println("All files are completely verified and downloaded !");
		Panel.setText("All files are completely verified and downloaded !");
	}
	
	@SuppressWarnings("resource")
	private static void download(int iterator) throws IOException
	{
		System.out.println("\n" + "Downloading file : " + LINK_OF_FILES.get(iterator) + "...");
		Panel.setText("Downloading file : " + LINK_OF_FILES.get(iterator) + "...");
		URL website = new URL(LINK_OF_FILES.get(iterator));
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());		
		FileOutputStream fos = new FileOutputStream(FILE_NAME.get(iterator));
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		System.out.println("File : " + LINK_OF_FILES.get(iterator) + " has been downloaded at : " + FILE_NAME.get(iterator) + ".");
		Panel.setText("File : " + LINK_OF_FILES.get(iterator) + " has been downloaded at : " + FILE_NAME.get(iterator) + ".");
	}
	
	public static boolean verifFiles(File file, File fileToDownload)
	{		
		if(!file.exists())
		{
			return false;
		}
		else if(fr.flowarg.launcher.FileUtils.getFileSizeBytes(file).equals(fr.flowarg.launcher.FileUtils.getFileSizeBytes(fileToDownload)))
		{
			System.err.println("File : " + file + " exist but it's invalid ! Deleting it ! Error iFcBs1.");
			file.delete();
			return false;
		}
		else return true;
	}

	public static int getNmbrOfFiles()
	{
		return NMBR_OF_FILES;
	}
}

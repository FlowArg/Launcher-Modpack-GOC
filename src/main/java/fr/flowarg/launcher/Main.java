/*
 * 	Copyright 2019-2020 FlowArg
 *
 * 	Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.flowarg.launcher;

import fr.arinonia.launcherlib.updater.utils.JsonManager;
import fr.flowarg.launcher.downloader.Downloader;
import fr.flowarg.launcher.gui.GFrame;
import fr.flowarg.launcher.gui.GPanel;
import fr.flowarg.launcher.updater.Updater;
import fr.flowarg.launcher.utils.Constants;
import fr.flowarg.launcher.utils.Logger;
import fr.flowarg.launcher.utils.exceptions.io.CorruptedFileException;
import fr.flowarg.launcher.utils.exceptions.io.NotADirectoryException;
import fr.litarvan.openauth.AuthPoints;
import fr.litarvan.openauth.AuthenticationException;
import fr.litarvan.openauth.Authenticator;
import fr.litarvan.openauth.model.AuthAgent;
import fr.litarvan.openauth.model.response.AuthResponse;
import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.external.ExternalLaunchProfile;
import fr.theshark34.openlauncherlib.external.ExternalLauncher;
import fr.theshark34.openlauncherlib.minecraft.*;
import fr.theshark34.openlauncherlib.util.CrashReporter;
import fr.theshark34.swinger.Swinger;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static fr.flowarg.launcher.utils.FileUtils.*;

/**
 * @author FlowArg
 * @version 1.3.5
 * @since 0.0.1
 *
 * Main class of the launcher.
 */
public final class Main implements Constants
{
	public static final String ACTUAL_VERSION = "1.3.5";
	public static final GameVersion VERSION = new GameVersion("1.12.2", GameType.V1_8_HIGHER);
	public static final GameInfos INFOS = new GameInfos("gunsofchickens-modpack", VERSION, new GameTweak[] {GameTweak.FORGE});
	public static final File GAME_DIR = INFOS.getGameDir();
	public static final File LAUNCHER_CRASHS = new File(GAME_DIR + "\\logs");
	public static final GameFolder LAUNCH_DIR = new GameFolder("common\\assets", "common\\libraries", "common\\natives", "common\\versions\\1.12.2\\1.12.2.jar");
	public static final Downloader DOWNLOADER = new Downloader();
	public static final Updater UPDATER = new Updater();
	public static final CrashReporter CRASH_REPORTER = new CrashReporter("Launcher - Modpack Guns of Chickens", Main.LAUNCHER_CRASHS);
	public static final File LOG_FILE = new File(LAUNCHER_LOGS_DIR + "Launcher Log-" + new Date().toString().replace(':', '-').replace("CET ", "") + ".log");
	private static AuthInfos authInfos;

	static {
		try
		{
			File dir = new File(LAUNCHER_LOGS_DIR);
			if(dir.exists())
			{
				for (File file : dir.listFiles())
				{
					if(getFileExtension(file).contains("log"))
					{
						gzipFile(LAUNCHER_LOGS_DIR + file.getName(), removeExtension(LAUNCHER_LOGS_DIR + file.getName()) + ".log.gz");
						if (!file.delete()) file.deleteOnExit();
					}
				}
			}
			LOG_FILE.getParentFile().mkdirs();
			createFile(LOG_FILE);
			File f = new File(Main.GAME_DIR + "\\ram.txt");
			if (!f.exists()) createFile(f);
			Thread.sleep(1000);
			Logger.info("Launching launcher (" + new Date().toString() + ")...");
		} catch (IOException | InterruptedException e)
		{
			Main.CRASH_REPORTER.catchError(e, "Impossible de compresser les logs, ou de cr�er le fichier ram.txt.");
		}
	}

	public static void main(String[] args)
	{
		Swinger.setResourcePath("/assets/");
		Swinger.setSystemLookNFeel();
		Logger.info("Verifying available updates...");
		try
		{
		    addOnExitAction(new Thread(() ->
            {
                try
                {
                    FileUtils.deleteDirectory(new File(MODS + "memory_repo\\"));
                } catch (IOException ignored) {}
            }));

			FileUtils.copyURLToFile(new URL("https://flowarg.github.io/minecraft/launcher/sha1s.json"), new File(COMMON + "sha1s.json"));
			FileUtils.copyURLToFile(new URL("https://flowarg.github.io/minecraft/launcher/objects-sha1s.json"), new File(COMMON + "objects-sha1s.json"));
			FileUtils.copyURLToFile(new URL("https://flowarg.github.io/minecraft/launcher/launcher.update.json"), new File(COMMON + "launcher.update.json"));
			FileUtils.copyURLToFile(new URL("https://flowarg.github.io/minecraft/launcher/size.txt"), new File(COMMON + "size.txt"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		UPDATER.start();
		Logger.info("Initializing launcher...");
		reset();
		JsonManager.init();
		DOWNLOADER.init();
		Logger.info("Launching Window for " + OS + " os.");
		GFrame frame = new GFrame("Launcher By FlowArg");
		frame.setVisible(true);
	}

	public static void auth(String username, String password) throws AuthenticationException
	{
		Logger.info("Authentication...");
		Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL, AuthPoints.NORMAL_AUTH_POINTS);
		AuthResponse response = authenticator.authenticate(AuthAgent.MINECRAFT, username, password, "");
		authInfos = new AuthInfos(response.getSelectedProfile().getName(), response.getAccessToken(), response.getSelectedProfile().getId());
	}

	public static void launch() throws LaunchException, InterruptedException, IOException
    {
		ExternalLaunchProfile profile = MinecraftLauncher.createExternalProfile(INFOS, LAUNCH_DIR, authInfos);
		ExternalLauncher launcher = new ExternalLauncher(profile);
		
		profile.getVmArgs().addAll(Arrays.asList(GPanel.getRamSelector().getRamArguments()));

		final File modsDir = new File(MODS);
		List<String> list = FileUtils.readLines(new File(COMMON + "size.txt"), StandardCharsets.UTF_8);
		final String wi = list.get(0);
		final String wo = list.get(1);

		boolean hasOptiFine = false;

		for (File f : Objects.requireNonNull(modsDir.listFiles()))
		{
			if(f.getName().contains("OptiFine"))
			{
				hasOptiFine = true;
				Logger.info("Detected OptifineTweaker.");
			}
			if(f.isDirectory() && f.getName().equalsIgnoreCase("memory_repo")) if(!f.delete()) f.deleteOnExit();
		}

		if(hasOptiFine)
		{
			if(Integer.parseInt(wi) != getBytesOfADirectory(modsDir))
			{
				FileUtils.cleanDirectory(new File(MODS));
				Logger.info("Le launcher a trouve des fichiers malveillants ou corrompu dans le dossier mods, veuillez relancer le launcher. NOTE : Cette erreur peut avoir lieu lors d'une mise � jour d'un ou plusieurs mods, pas d'affolement.");
				GPanel.setText("Le launcher a trouve des fichiers malveillants ou corrompu dans le dossier mods, veuillez relancer le launcher. NOTE : Cette erreur peut avoir lieu lors d'une mise � jour d'un ou plusieurs mods, pas d'affolement.");
				throw new CorruptedFileException("Le launcher a trouve des fichiers malveillants ou corrompu dans le dossier mods, veuillez relancer le launcher. NOTE : Cette erreur peut avoir lieu lors d'une mise � jour d'un ou plusieurs mods, pas d'affolement.");
			}
		}
		else
		{
			if(Integer.parseInt(wo) != getBytesOfADirectory(modsDir))
			{
				FileUtils.cleanDirectory(new File(MODS));
				Logger.info("Le launcher a trouve des fichiers malveillants ou corrompu dans le dossier mods et a vid� le dossier mods, veuillez relancer le launcher. NOTE : Cette erreur peut avoir lieu lors d'une mise � jour d'un ou plusieurs mods, pas d'affolement.");
				GPanel.setText("Le launcher a trouve des fichiers malveillants ou corrompu dans le dossier mods et a vid� le dossier mods, veuillez relancer le launcher. NOTE : Cette erreur peut avoir lieu lors d'une mise � jour d'un ou plusieurs mods, pas d'affolement.");
				throw new CorruptedFileException("Le launcher a trouve des fichiers malveillants ou corrompu dans le dossier mods et a vid� le dossier mods, veuillez relancer le launcher. NOTE : Cette erreur peut avoir lieu lors d'une mise � jour d'un ou plusieurs mods, pas d'affolement.");
			}
		}
		reset();

		Process p = launcher.launch();
		Thread.sleep(5000L);
		GFrame.getInstance().setVisible(false);
		p.waitFor();
		exit(0);
	}
	
	public static void exit(int status)
	{
		Logger.info("Exit with exit code " + status + ".");
		try
		{
			FileUtils.cleanDirectory(new File(TEMP_DIR));
		} catch (IOException ignored) {}
		if(!Downloader.FILE_NAME.isEmpty()) Downloader.FILE_NAME.clear();
		if(!Downloader.LINK_OF_FILES.isEmpty()) Downloader.LINK_OF_FILES.clear();
		if(!Downloader.OBJ_LINK_OF_FILES.isEmpty()) Downloader.OBJ_LINK_OF_FILES.clear();
		if(!Downloader.OBJ_FILE_NAME.isEmpty()) Downloader.OBJ_FILE_NAME.clear();
		if(Downloader.NUMBER_OF_FILES != 0) Downloader.NUMBER_OF_FILES = 0;
		if(Downloader.OBJ_NUMBER_OF_FILES != 0) Downloader.OBJ_NUMBER_OF_FILES = 0;
		System.exit(status);
	}

	public static void addOnExitAction(Thread thread)
	{
		Runtime.getRuntime().addShutdownHook(thread);
	}
}